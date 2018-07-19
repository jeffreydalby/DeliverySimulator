package edu.bu.met.cs665.simulator;

import java.util.Random;

public class FrozenDinnerNameGenerator implements NameGenerator{
    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = frozenDinnerNames[rnd.nextInt(frozenDinnerNames.length)][0];
        String lastName = frozenDinnerNames[rnd.nextInt(frozenDinnerNames.length)][1];
        return firstName + " " + lastName;
    }
}
