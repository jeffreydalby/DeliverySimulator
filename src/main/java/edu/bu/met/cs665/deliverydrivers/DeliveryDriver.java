package edu.bu.met.cs665.deliverydrivers;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.deliverysystem.Delivery;
import edu.bu.met.cs665.geography.Address;
import edu.bu.met.cs665.geography.Distances;

import java.awt.*;

public class DeliveryDriver implements Observer, Runnable {
    public Point getCurrentLocation() {
        return currentLocation;
    }

    public boolean isWarmer() {
        return warmer;
    }

    public boolean isCooler() {
        return cooler;
    }

    public boolean isAvailable() {
        return available;
    }

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
    public DeliveryDriver(String driverName, boolean hasWarmer, boolean hasCooler) {
        this.driverName = driverName;
        this.currentLocation = Address.getRandomGridPoint();
        this.warmer = hasWarmer;
        this.cooler = hasCooler;
        this.available = true;
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
        this.currentDelivery.setPickedUp(true);
        this.distanceToStore = (int) Distances.getDistanceBetweenPoints(this.currentLocation, delivery.getStore().getLocation());
        this.distanceStoreToCustomer = (int) Distances.getDistanceBetweenPoints(delivery.getStore().getLocation(), delivery.getOrder().getCustomer().getLocation());
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
                                + "from " + this.currentDelivery.getStore().getName()
                                + "\nDelivering to " + this.currentDelivery.getOrder().getCustomer().getAddress()
                                + "\nOrder waited for " + this.currentDelivery.getWaitTime() + "time units");

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
                                + "to " + this.currentDelivery.getOrder().getCustomer().getCustomerName()
                                + "\n at " + this.currentDelivery.getOrder().getCustomer().getAddress());
                        this.deliverOrder();  //deliver the order and reset for the next one
                    }

                }

            }

            //We are going to sleep for a millisecond each time
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException ex){
                Thread.currentThread().interrupt();
                Display.output("Driver:" + this.driverName + "couldn't sleep");
            }

        }


    }

    private void deliverOrder() {
        //set our location to the customers house cause we are creepy and just hang there until another order comes in
        this.currentLocation = this.currentDelivery.getOrder().getCustomer().getLocation();
        this.currentDelivery.setDelivered(true);
        this.available = true;
        Display.output("Delivered at time code: " + this.currentDelivery.getDeliveredTime()
                +" Total delivery time was: " + this.currentDelivery.getDeliveredTime());
        this.currentDelivery = null;
        //set to -1 to indicate not in use
        this.distanceStoreToCustomer = -1;
        this.distanceToStore = -1;

    }
}
