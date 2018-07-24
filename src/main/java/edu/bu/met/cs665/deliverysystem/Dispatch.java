package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.ClockTicker;
import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.geography.Distances;
import edu.bu.met.cs665.orders.Order;
import edu.bu.met.cs665.simulator.OrderSimulator;
import edu.bu.met.cs665.simulator.SetupSystem;

import java.awt.*;
import java.util.*;
import java.util.List;

//singleton, only one dispatcher per system
//Automated dispatcher that tracks orders and finds the closest driver
//with the right equipment to deliver it
public class Dispatch implements Subject, Runnable {

    private static Dispatch dispatchInstance;

    //dispatch thread so we can shut it down when done
    public Thread getDispatchThread() {
        return dispatchThread;
    }

    private Thread dispatchThread;

    //Map of all of our drivers
    public static Map<String, DeliveryVehicle> getDriverMap() {
        return driverMap;
    }

    private static Map<String, DeliveryVehicle> driverMap = new HashMap<>();
    private static Deque<Order> orders = new ArrayDeque<>(); //deque to hold the orders
    private static List<Delivery> deliveryList = new ArrayList<>(); //list to hold deliveries as they are created

    private Dispatch() {
        this.dispatchThread = new Thread(this);
        this.dispatchThread.start();
    }

    public static synchronized Dispatch getInstance() {
        if (dispatchInstance == null) dispatchInstance = new Dispatch();
        return dispatchInstance;
    }

    /**
     * Lets drivers register themselves to be able to deliver orders
     *
     * @param identity - name of the driver
     * @param vehicle  - driverObject
     */
    @Override
    public void registerObserver(String identity, DeliveryVehicle vehicle) {
        //if our driver map already has a driver with this name make it unique by adding
        //the current length of the map to it otherwise we'll loose track of a thread
        if(driverMap.containsKey(identity))
        {
            identity += " #" +driverMap.size();
            vehicle.setDriverName(identity);
        }

        driverMap.put(identity, vehicle);

    }

    /**
     * Remove driver from the driving map
     *
     * @param driverName - name of driver to remove
     */
    @Override
    public void removeObserver(String driverName) {
        driverMap.remove(driverName);
    }

    //The dispatch system is responsible for sending out notifications to the available driver
    // and doesn't have a need to multicast
    @Override
    public void notifyObserver(DeliveryDriver deliveryDriver, Delivery delivery) {
        deliveryDriver.updateDelivery(delivery);
    }

    /**
     * Requested all drivers post an update to where they are
     */
    @Override
    public void notifyAllObservers() {
        //go through each registered driver and ask for an update
        driverMap.values().forEach(theDriver->((Observer)theDriver).updateStatus());
    }

    //main automated dispatch system
    @Override
    public void run() {
        Order nextOrder;
        DeliveryDriver driver;
        boolean rushHour;
        //get the order system so we can exit cleanly
        OrderSimulator orderSimulatorInstance = OrderSimulator.getInstance();
        ClockTicker clockTickerInstance = ClockTicker.getClockTickerInstance();


        //create a loop to run constantly
        while (true) {
            //check if we have been interrupted
            if (Thread.currentThread().isInterrupted()) break;
            //check if it is "rush hour" which occurs between ticker time 20 and 80 (won't hit this with less than 20 orders)
            rushHour = clockTickerInstance.getSystemClock() > 20 && clockTickerInstance.getSystemClock() < 80;
            Display.output("Dispatch Update:"
                    + "\nNumber of orders: " + orders.size()
                    + "\nDrivers Waiting on Assignment: " + driverMap.values().stream().filter(theDrivers -> theDrivers.isAvailable()).count()
                    + "\nCurrently Rush hour: " + rushHour);


            //check for order in the queue and at least one available driver
            if (!orders.isEmpty() && driverMap.values().stream().anyMatch(thisDriver -> thisDriver.isAvailable())) {
                nextOrder = orders.removeFirst();


                driver = getNearestAvailableDriver(nextOrder.getCustomer().getLocation(),
                        nextOrder.getStore().getLocation(),
                        nextOrder.isNeedsWarm(),
                        nextOrder.isNeedsCold(),
                        rushHour);
                if (driver != null) {
                    //let people know we have a traffic event
                    if (rushHour && nextOrder.isNeedsCold())
                        Display.output("Traffic event, routing refrigerated truck.");
                    //create the delivery and notify the driver
                    createDelivery(driver, nextOrder, rushHour && nextOrder.isNeedsCold());
                }
                //no driver matches so the poor person gets their order sent to the bottom of the queue
                //consider implementing a priority queue so we can bubble things up to the top
                else {
                    orders.addLast(nextOrder);
                }

            } else if (orders.isEmpty()) {
                Display.output("Order Queue is Empty!");
                //if the order system is done creating orders and all drivers are available
                // and the order queus is empty we can assume we are done
                if (!orderSimulatorInstance.isCreatingOrders() && driverMap.values().stream().allMatch(theDrivers -> theDrivers.isAvailable())) {
                    SetupSystem.stopSimulation();
                    if (Thread.currentThread().isInterrupted()) break;
                }

            }
            //get an update from all of our drivers
            this.notifyAllObservers();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Display.output("Killed Dispatch Thread");
                //reset thread so while loop catches it
                Thread.currentThread().isInterrupted();
                break;

            }

        }
    }

    /**
     * Create the Delivery Object
     *
     * @param driver    - driver object who is handling the delivery
     * @param order-    the order to be delivered
     * @param rushHour- was the order created during a traffic event (rush hour)
     */
    private void createDelivery(DeliveryDriver driver, Order order, boolean rushHour) {
        Delivery newDelivery = new Delivery(driver, order);
        newDelivery.setRefergerated(rushHour);
        deliveryList.add(newDelivery);
        //notify the driver
        notifyObserver(driver, newDelivery);

    }

    /**
     * Find the closest driver that can handle the order
     *
     * @param customerLocation - point on the grid of the customer
     * @param storeLocation-   point on the grid to the store
     * @param needsHot         - does the order need to be kept warm
     * @param needsCold        - does the order need to be kept colde
     * @param isRushHour       - is it rush hour?
     * @return - the driver that will be handling the delivery
     */
    private DeliveryDriver getNearestAvailableDriver(Point customerLocation, Point storeLocation, boolean needsHot, boolean needsCold, boolean isRushHour) {
        DeliveryDriver returnDriver = null;
        //initialize to max so we know we'll get the closest
        double closestValue = Double.MAX_VALUE;
        //make sure we have drivers
        if (!driverMap.isEmpty()) {
            for (DeliveryVehicle vehicle : driverMap.values()
                    ) {
                //Available vehicles, if it needs heat make sure we have a heater
                if (vehicle.isAvailable()) {
                    //if it needs to be heated and the car has no warmer move on
                    if (needsHot && !vehicle.hasWarmer()) continue;

                    double storeToCustomerDistance = Distances.getDistanceBetweenPoints(customerLocation, storeLocation);
                    //if it needs cold we have to check distance and if it is rush hour
                    //if it is rush hour and the vehicle has no cooler move on
                    if (needsCold && isRushHour) {
                        if (!vehicle.hasCooler()) continue;
                    }
                    //if the distance is > 2000 units (20 blocks) and no cooler and needs cold move on
                    if (storeToCustomerDistance >= 2000 && !vehicle.hasCooler() && needsCold) continue;
                    //figure out if this is the closest vehicle
                    double thisVehicleDistance = Distances.getDistanceBetweenPoints(storeLocation, vehicle.getCurrentLocation());
                    if (thisVehicleDistance < closestValue) {
                        closestValue = thisVehicleDistance;
                        returnDriver = (DeliveryDriver) vehicle;
                    }
                }
            }
        } else Display.output("No Drivers Registered");
        if (returnDriver != null)
            Display.output("Found nearest available driver: " + returnDriver.getDriverName() + "\nDistance to store: " + (int) closestValue + " blocks");
        return returnDriver;
    }

    /**
     * Add the order to the queue
     *
     * @param order - the order to be added
     */
    public void placeOrder(Order order) {
        Display.output("New Order From Customer: \n"
                + "Order #" + order.getOrderNumber()
                + " \n" + order.toString());
        orders.addLast(order);
    }


    @Override
    public String toString() {
        //return our list of drivers
        StringBuilder returnString = new StringBuilder();
        driverMap.forEach((k, v) -> returnString.append(v.toString() + "\n"));
        return returnString.toString();
    }
}
