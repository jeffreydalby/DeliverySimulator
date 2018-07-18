package edu.bu.met.cs665.deliverysystem;

public interface Subject {
    void registerObserver();
    void removeObserver();
    void notifyObserver();
}
