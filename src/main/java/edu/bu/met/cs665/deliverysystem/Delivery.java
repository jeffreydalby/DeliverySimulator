package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.ClockTicker;
import edu.bu.met.cs665.orders.Order;

public class Delivery {



    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
        this.pickupTime = ClockTicker.systemClock;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
        this.deliveryTime = ClockTicker.systemClock;
    }

    public DeliveryDriver getDeliveryDriver() {
        return driver;
    }

    public void setDeliveryDriver(DeliveryDriver driver) {
        this.driver = driver;
    }

    public boolean getRefergerated() {
        return refergerated;
    }

    public void setRefergerated(boolean refergerated) {
        this.refergerated = refergerated;
    }

    public Order getOrder() {
        return order;
    }

    private Order order;
    private boolean pickedUp;
    private boolean delivered;
    private DeliveryDriver driver;
    private int pickupTime;
    private int orderSubmittedTime; //just using an int to represent time
    private int deliveryTime;
    private boolean refergerated;

    public Delivery(DeliveryDriver driver,Order order){
        this.driver = driver;
        this.order = order;
        this.orderSubmittedTime = ClockTicker.systemClock;
    }

    public int getWaitTime(){
        return this.pickupTime - this.orderSubmittedTime;
    }

    public int getDeliveredTime(){
        return this.deliveryTime - this.orderSubmittedTime;
    }



}
