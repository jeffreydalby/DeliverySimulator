package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.Main;
import edu.bu.met.cs665.orders.Order;
import edu.bu.met.cs665.stores.Store;

import java.sql.Driver;

public class Delivery {



    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
        pickupTime = Main.systemClock;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
        deliveryTime = Main.systemClock;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Store getStore() {
        return store;
    }

    public Order getOrder() {
        return order;
    }

    private Order order;
    private boolean pickedUp;
    private boolean delivered;
    private Driver driver;
    private Store store;
    private int pickupTime;
    private int orderSubmittedTime; //just using an int to represent time
    private int deliveryTime;

    public Delivery(Store store, Order order, int orderSubmittedTime){
        this.store = store;
        this.order = order;
        this.orderSubmittedTime = orderSubmittedTime;
    }

    public int getWaitTime(){
        return this.pickupTime - this.orderSubmittedTime;
    }

    public int getDeliveredTime(){
        return this.deliveryTime - this.orderSubmittedTime;
    }



}
