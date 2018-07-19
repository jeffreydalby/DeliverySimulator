package edu.bu.met.cs665.simulator;

import java.util.Random;

public class FlowerNameGenerator implements NameGenerator {
    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = flowerNames[rnd.nextInt(flowerNames.length)][0];
        String lastName = flowerNames[rnd.nextInt(flowerNames.length)][1];
        return firstName + " " + lastName;
    }
}
