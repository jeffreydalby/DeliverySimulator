package edu.bu.met.cs665.geography;

import java.awt.*;
import java.util.Random;

public class Address {


    public static Point getRandomGridPoint(){
        //north/south address vs east/west
        boolean isNorthSouth = false;
        Random rnd = new Random();
        //get two numbers one is a multiple of 100 and is the street, one is completely random
        int street = rnd.nextInt(101) * 100;
        int address = rnd.nextInt(10001);

        //50/50 chance of a north south
        if (rnd.nextInt(10) < 5) isNorthSouth = true;
        // if we are north south x is a multple of 100, otherwise y is
        if (isNorthSouth) return new Point(street,address);
        else return new Point(address,street);
    }


    public static String getAddress(Point point){
        if(point.x % 100 == 0) return point.y + " " + point.x + "th Avenue";
        else if (point.y % 100 == 0) return point.x + " " + point.y + "th Street";
        else return "Unknown Location";
    }
}
