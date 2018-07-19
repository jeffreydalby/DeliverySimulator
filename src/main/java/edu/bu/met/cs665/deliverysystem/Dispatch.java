package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.Main;
import edu.bu.met.cs665.geography.Distances;
import edu.bu.met.cs665.orders.Order;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Dispatch implements Subject, Runnable {

    private static Map<String, DeliveryVehicle> driverMap = new HashMap<>();
    private static Deque<Order> orders = new ArrayDeque<>();
    private static List<Delivery> deliveryList = new ArrayList<>();

    @Override
    public void registerObserver(String identity, DeliveryVehicle vehicle) {
        driverMap.put(identity, vehicle);

    }

    @Override
    public void removeObserver(Observer observer) {
        driverMap.remove(observer);
    }

    @Override
    public void notifyObserver() {

    }

    @Override
    public void run() {
        Order nextOrder;
        DeliveryDriver driver;
        boolean rushHour;

        //create a loop to run constantly
        while (true) {
            //check if it is "rush hour" which we a simulating by the fake system clock%2 = 0;
            if (Main.systemClock % 2 == 0) rushHour = true;
            else rushHour = false;


            //check if we have been interrupted
            if (Thread.currentThread().isInterrupted()) break;
            //check for order in the queue
            if (!orders.isEmpty()) {
                Display.output("Checking for next order");
                nextOrder = orders.removeFirst();
                driver = getNearestAvailableDriver(nextOrder.getCustomer().getLocation(),
                        nextOrder.getStore().getLocation(),
                        nextOrder.isNeedsWarm(),
                        nextOrder.isNeedsCold(),
                        rushHour);
                if (driver != null) {
                    //create the delivery and notify the driver
                    createDelivery(driver, nextOrder);
                }
                //no driver matches so the poor person gets their order sent to the bottom of the queue
                //TODO consider implementing a priority queue so we can bubble things up to the top
                else {
                    orders.addFirst(nextOrder);
                }

            }
            else
                Display.output("Order Queue is Empty!");


            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException ex){
                //reset thread so while loop catches it
                Thread.currentThread().isInterrupted();

            }
        }


    }

    private void createDelivery(DeliveryDriver driver, Order order) {
        Delivery newDelivery = new Delivery(driver, order);
        deliveryList.add(newDelivery);
        driver.update(newDelivery);

    }

    private DeliveryDriver getNearestAvailableDriver(Point customerLocation, Point storeLocation, boolean needsHot, boolean needsCold, boolean isRushHour) {
        DeliveryDriver returnDriver = null;
        //initialize to max so we know we'll get the closest
        Double closestValue = Double.MAX_VALUE;
        //make sure we have drivers
        if (!driverMap.isEmpty()) {
            for (DeliveryVehicle vehicle : driverMap.values()
                    ) {
                //Available vehicles, if it needs heat make sure we have a heater
                if (vehicle.isAvailable() && (vehicle.hasWarmer() == needsHot)) {

                    double storeToCustomerDistance = Distances.getDistanceBetweenPoints(customerLocation, storeLocation);
                    //if it needs cold we have to check distance and if it is rush hour
                    //if it is rush hour and the vehicle has no cooler move on
                    if (needsCold && isRushHour) {
                        if (!vehicle.hasCooler()) continue;
                    }
                    //if the distance is > 2000 units (20 blocks) and no cooler move on
                    if (storeToCustomerDistance >= 2000 && !vehicle.hasCooler()) continue;
                    //figure out if this is the closest vehicle
                    double thisVehicleDistance = Distances.getDistanceBetweenPoints(storeLocation, vehicle.getCurrentLocation());
                    if (thisVehicleDistance < closestValue) {
                        closestValue = thisVehicleDistance;
                        returnDriver = (DeliveryDriver) vehicle;
                    }
                }
            }
        }
        else Display.output("No Drivers Registered");
        return returnDriver;
    }

    public void placeOrder(Order order) {
        orders.addLast(order);
    }
}