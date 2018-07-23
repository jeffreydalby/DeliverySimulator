package edu.bu.met.cs665.deliverysystem;

public interface Subject {
    void registerObserver(String identity, DeliveryVehicle vehicle);
    void removeObserver(String driverName);
    void notifyObserver(DeliveryDriver deliveryDriver, Delivery delivery);
}
