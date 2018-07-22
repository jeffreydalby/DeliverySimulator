package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.geography.Address;
import edu.bu.met.cs665.geography.Distances;

import java.awt.*;

public class DeliveryDriver implements Observer, Runnable, DeliveryVehicle {
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

    private Thread driverThread;
    private Point currentLocation;
    private boolean warmer;
    private boolean cooler;
    private boolean available;
    private String driverName;
    private Delivery currentDelivery;
    private int distanceToStore;
    private int distanceStoreToCustomer;
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

        System.out.println("Got delivery for: " + delivery.getOrder().getCustomer());
        this.available = false;
        this.currentDelivery = delivery;
        this.currentDelivery.setPickedUp(true);
        this.distanceToStore = (int) Distances.getDistanceBetweenPoints(this.currentLocation, delivery.getOrder().getStore().getLocation());
        this.distanceStoreToCustomer = (int) Distances.getDistanceBetweenPoints(delivery.getOrder().getStore().getLocation(), delivery.getOrder().getCustomer().getLocation());
        this.pickingUp = true;
    }

    @Override
    public void run() {
        //loop to continuously run
        while (true) {
            //allow us to cleanly exit the thread.
            if (Thread.currentThread().isInterrupted()) break;
            int distanceTravelled = 0;
            //if the driver isn't available we can assume he has a delivery to make.
            if (!available && this.currentDelivery != null) {

                //if we are picking up decrement the distance to the store
                if (pickingUp) {
                    distanceToStore -= 10; //reduce by 100 cause we can travel one block a second in this world
                    distanceTravelled += 10; //Keep track of how far we have gone
                    //if the distance to store <= 0 we are at the store;
                    if (distanceToStore <= 0) {
                        Display.output("Picking up Order #"
                                + currentDelivery.getOrder().getOrderNumber()
                                + " from " + this.currentDelivery.getOrder().getStore().getName()
                                + "\nDelivering to " + this.currentDelivery.getOrder().getCustomer().getAddress()
                                + "\nOrder waited for " + this.currentDelivery.getWaitTime() + " time units");

                        pickingUp = false;
                    }

                }
                //if we aren't available and we aren't picking up, we must be dropping off
                else {
                    distanceStoreToCustomer -= 10; //reduce by 100 cause we can travel one block a second in this world
                    distanceTravelled += 10; //Keep track of how far we have gone
                    //if the distance to store <= 0 we are at the customer;
                    if (distanceStoreToCustomer <= 0) {
                        Display.output("Delivered Order #"
                                + currentDelivery.getOrder().getOrderNumber()
                                + " to " + this.currentDelivery.getOrder().getCustomer().getCustomerName()
                                + "\nat " + this.currentDelivery.getOrder().getCustomer().getAddress());
                        this.deliverOrder();  //deliver the order and reset for the next one
                    }

                }

            }

            //We are going to sleep for a 10 millisecond each time
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Display.output("Driver: " + this.driverName + " went to sleep");
            }

        }


    }

    private void deliverOrder() {
        //set our location to the customers house cause we are creepy and just hang there until another order comes in
        this.currentLocation = this.currentDelivery.getOrder().getCustomer().getLocation();
        this.currentDelivery.setDelivered(true);
        this.available = true;
        Display.output("Delivered at time code: " + this.currentDelivery.getDeliveredTime()
                + " Total delivery time was: " + this.currentDelivery.getDeliveredTime());
        this.currentDelivery = null;
        //set to -1 to indicate not in use
        this.distanceStoreToCustomer = -1;
        this.distanceToStore = -1;

    }

    @Override
    public String toString() {
        return ("Name: "
                + this.driverName + "\n"
                + "Current Location: "
                + Address.getAddress(this.currentLocation) + "\n"
                + "Has cooler: " + hasCooler()
                + "\n" + "Has warmer: " + hasWarmer() + "\n" +
                "Is available: " + available + "\n");
    }
}
