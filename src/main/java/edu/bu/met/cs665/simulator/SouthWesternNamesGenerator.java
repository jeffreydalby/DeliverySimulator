package edu.bu.met.cs665.simulator;

import java.util.Random;

public class SouthWesternNamesGenerator implements NameGenerator {
    @Override
    public String getName() {

        String[][] southWesternNames ={{"Paco's", "Tacos"}, {"Southwestern", "Grill"}, {"Burritos","Cocina"}};

        Random rnd = new Random();
        String firstName = southWesternNames[rnd.nextInt(southWesternNames.length)][0];
        String lastName = southWesternNames[rnd.nextInt(southWesternNames.length)][1];
        return firstName + " " + lastName;
    }
}
