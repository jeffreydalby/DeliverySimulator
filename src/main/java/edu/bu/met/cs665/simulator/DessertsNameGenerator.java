package edu.bu.met.cs665.simulator;

import java.util.Random;

public class DessertsNameGenerator implements NameGenerator {
    private String[][] dessertsNames= {{"The Ice Cream", "Shop"},{"Ma's Candy", "Store"},{"Frank's Sugar", "Parlor"}};

    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = dessertsNames[rnd.nextInt(dessertsNames.length)][0];
        String lastName = dessertsNames[rnd.nextInt(dessertsNames.length)][1];
        return firstName + " " + lastName;
    }
}
