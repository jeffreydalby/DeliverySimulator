package edu.bu.met.cs665.simulator;

import java.util.Random;

public class PeopleNameGenerator implements NameGenerator {
    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = peopleNames[rnd.nextInt(peopleNames.length)][0];
        String lastName = peopleNames[rnd.nextInt(peopleNames.length)][1];
        return firstName + " " + lastName;

    }
}
