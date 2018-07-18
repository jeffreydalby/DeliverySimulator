package edu.bu.met.cs665.deliverydrivers;

import edu.bu.met.cs665.deliverysystem.Delivery;

public interface Observer {
    void update(Delivery delivery);
}
