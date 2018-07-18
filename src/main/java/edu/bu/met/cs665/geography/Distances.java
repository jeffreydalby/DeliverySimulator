package edu.bu.met.cs665.geography;

import java.awt.*;

public class Distances {
    public static double getDistanceBetweenStoreAndLocation(Point store, Point location) {
        return Math.sqrt(Math.pow((location.x - store.x), 2) + Math.pow(location.y - store.y, 2));
    }

}
