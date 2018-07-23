package edu.bu.met.cs665.simulator;

import java.util.Random;

public class FrozenDinnerNameGenerator implements NameGenerator{

    private String[][] frozenDinnerNames={{"Easy","Dinners"}, {"Ready", "Meals"},{"Home","Chef"}};

    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = frozenDinnerNames[rnd.nextInt(frozenDinnerNames.length)][0];
        String lastName = frozenDinnerNames[rnd.nextInt(frozenDinnerNames.length)][1];
        return firstName + " " + lastName;
    }
}
