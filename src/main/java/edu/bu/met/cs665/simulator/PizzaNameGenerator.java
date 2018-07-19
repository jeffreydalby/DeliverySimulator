package edu.bu.met.cs665.simulator;

import java.util.Random;

public class PizzaNameGenerator implements NameGenerator
{
    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = pizzaNames[rnd.nextInt(pizzaNames.length)][0];
        String lastName = pizzaNames[rnd.nextInt(pizzaNames.length)][1];
        return firstName + " " + lastName;
    }
}
