package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.ClockTicker;
import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.geography.Address;
import edu.bu.met.cs665.geography.Distances;

import java.awt.*;

public class DeliveryDriver implements Observer, Runnable, DeliveryVehicle {

    public static final int BLOCKS_PER_TICK = 300;
    public Point getCurrentLocation() {
        return currentLocation;
    }

    public boolean hasWarmer() {
        return warmer;
    }

    public boolean hasCooler() {
        return cooler;
    }

    public boolean isAvailable() {
        return available;
    }


    public Thread getDriverThread() {
        return driverThread;
    }

    public String getDriverName() {
        return driverName;
    }

    private Thread driverThread;
    private Point currentLocation;
    private boolean warmer;
    private boolean cooler;
    private boolean available;
    private String driverName;
    private Delivery currentDelivery;
    private int distanceToStore;
    private int distanceStoreToCustomer;
    private int distanceTravelled;
    boolean pickingUp;

    //create the driver and give him a random starting location
    //set up with a cooler, warmer or both
    public DeliveryDriver(String driverName, Point currentLocation, boolean hasWarmer, boolean hasCooler) {
        this.driverName = driverName;
        this.currentLocation = currentLocation;
        this.warmer = hasWarmer;
        this.cooler = hasCooler;
        this.available = true;
        this.currentDelivery = null;

        //register the driver
        Dispatch dispatch = Dispatch.getInstance();
        dispatch.registerObserver(this.driverName, this);
        //start them driving
        driverThread = new Thread(this);
        driverThread.start();
    }

    /**
     * Update from the observable
     *
     * @param delivery - the delivery to be made;
     */
    @Override
    public void update(Delivery delivery) {


        this.available = false;
        this.currentDelivery = delivery;
        this.distanceToStore = (int) Distances.getDistanceBetweenPoints(this.currentLocation, delivery.getOrder().getStore().getLocation());
        this.distanceStoreToCustomer = (int) Distances.getDistanceBetweenPoints(delivery.getOrder().getStore().getLocation(), delivery.getOrder().getCustomer().getLocation());
        this.pickingUp = true;

        Display.output("Order Accepted"
                +"\nDriver: " + this.driverName
                +"\nPicking up Order #" +delivery.getOrder().getOrderNumber()
                +"\nRefrigerated due to traffic: " + this.currentDelivery.getRefergerated()
                +"\nFrom: " + delivery.getOrder().getStore().getName()
                +"\nAt: " + delivery.getOrder().getStore().getAddress()
                +"\nFor: " + delivery.getOrder().getCustomer().getCustomerName()
                +"\nAt: " + delivery.getOrder().getCustomer().getAddress()
                +"\nEstimated Total distance: "+ (int)(this.distanceToStore + this.distanceStoreToCustomer));
    }

    @Override
    public void run() {
        //loop to continuously run
        while (true) {

            //We are going to sleep for a 1000 millisecond each time
            //sleep at the beginning so the system can ramp up.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Display.output("Driver: " + this.driverName + " went to sleep");
            }

            //allow us to cleanly exit the thread.
            if (Thread.currentThread().isInterrupted()) break;
            //if the driver isn't available we can assume he has a delivery to make.
            if (!available && this.currentDelivery != null) {

                //if we are picking up decrement the distance to the store
                if (pickingUp) {
                    distanceToStore -= BLOCKS_PER_TICK; //reduce by BLOCKS_PER_TICK
                    distanceTravelled += BLOCKS_PER_TICK; //Keep track of how far we have gone
                    //if the distance to store <= 0 we are at the store;
                    if (distanceToStore <= 0) {
                        //we made it to the store to pickup
                        this.currentDelivery.setPickedUp(true);
                        Display.output("Picking up Order #"
                                + currentDelivery.getOrder().getOrderNumber() + " at time index: " + ClockTicker.systemClock
                                + "\nFrom: " + this.currentDelivery.getOrder().getStore().getName()
                                + "\nDelivering to " + this.currentDelivery.getOrder().getCustomer().getAddress()
                                + "\nOrder waited for " + this.currentDelivery.getWaitTime() + "time units");

                        pickingUp = false;
                    }

                }
                //if we aren't available and we aren't picking up, we must be dropping off
                else {
                    distanceStoreToCustomer -= BLOCKS_PER_TICK; //reduce by BLOCKS_PER_TICK
                    distanceTravelled += BLOCKS_PER_TICK; //Keep track of how far we have gone
                    //if the distance to store <= 0 we are at the customer;
                    if (distanceStoreToCustomer <= 0) {
                        this.deliverOrder();  //deliver the order and reset for the next one
                    }

                }

            }
            Display.output("Driver Status: \n" + this.toString());


        }


    }

    private void deliverOrder() {
        //set our location to the customers house cause we are creepy and just hang there until another order comes in
        this.currentLocation = this.currentDelivery.getOrder().getCustomer().getLocation();
        this.currentDelivery.setDelivered(true);
        this.available = true;
        Display.output("Delivered Order #" + this.currentDelivery.getOrder().getOrderNumber() + "at time index: " + ClockTicker.systemClock
                +"\nDriver Name: " + this.driverName
                +"\nRefrigerated: " + this.currentDelivery.getRefergerated()
                +"\nTotal Distance: " + (int)(this.distanceTravelled)
                +"\nCustomer Name: " + this.currentDelivery.getOrder().getCustomer().getCustomerName()
                +"\nCustomer Address: " + this.currentDelivery.getOrder().getCustomer().getAddress()
                +"\nTotal time: " + this.currentDelivery.getDeliveredTime()
                +"\nFrom: " + this.currentDelivery.getOrder().getStore().getName()
                +"\nOrder Contents: " + this.currentDelivery.getOrder().getOrderItems());
        this.currentDelivery = null;
        //set to -1 to indicate not in use
        this.distanceStoreToCustomer = -1;
        this.distanceToStore = -1;
        this.distanceTravelled = 0;

    }

    @Override
    public String toString() {

        String returnString = ("Name: "
                + this.driverName + "\n"
                + "Last Address: "
                + Address.getAddress(this.currentLocation) + "\n"
                + "Has cooler: " + this.hasCooler()
                + "\n" + "Has warmer: " + this.hasWarmer() + "\n" +
                "Is available: " + this.available);
        if (this.distanceStoreToCustomer > 0)
            returnString += "\nCurrent distance to Customer: " + (this.distanceStoreToCustomer + this.distanceToStore);
        if(this.distanceToStore > 0)
            returnString += "\nDistance to store for pickup: " + this.distanceToStore;
        return returnString;
    }
}
