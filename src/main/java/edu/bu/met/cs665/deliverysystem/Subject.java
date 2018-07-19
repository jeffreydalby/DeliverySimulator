package edu.bu.met.cs665.deliverysystem;

public interface Subject {
    void registerObserver(String identity, DeliveryVehicle vehicle);
    void removeObserver(Observer observer);
    void notifyObserver();
}
