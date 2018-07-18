package edu.bu.met.cs665.geography;

import java.awt.*;

public class Distances {
    public static double getDistanceBetweenPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.x - point1.x), 2) + Math.pow(point2.y - point1.y, 2));
    }

}
