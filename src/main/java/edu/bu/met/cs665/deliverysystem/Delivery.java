package edu.bu.met.cs665.deliverysystem;

import edu.bu.met.cs665.ClockTicker;
import edu.bu.met.cs665.orders.Order;

public class Delivery {


    private ClockTicker clockTickerInstance = ClockTicker.getClockTickerInstance();
    public boolean isPickedUp() {
        return pickedUp;
    }

    void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
        this.pickupTime = clockTickerInstance.getSystemClock();
    }

    public boolean isDelivered() {
        return delivered;
    }

    void setDelivered(boolean delivered) {
        this.delivered = delivered;
        this.deliveryTime = clockTickerInstance.getSystemClock();
    }

    public DeliveryDriver getDeliveryDriver() {
        return driver;
    }

    public void setDeliveryDriver(DeliveryDriver driver) {
        this.driver = driver;
    }

    boolean getRefergerated() {
        return refergerated;
    }

    void setRefergerated(boolean refergerated) {
        this.refergerated = refergerated;
    }

    Order getOrder() {
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

    Delivery(DeliveryDriver driver, Order order){
        this.driver = driver;
        this.order = order;
        this.orderSubmittedTime = clockTickerInstance.getSystemClock();
    }

    int getWaitTime(){
        return this.pickupTime - this.orderSubmittedTime;
    }

    int getDeliveredTime(){
        return this.deliveryTime - this.orderSubmittedTime;
    }



}
