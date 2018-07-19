package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class PizzaNameGeneratorTest {

    @Test
    public void getName() {
        PizzaNameGenerator pizzaNameGenerator = new PizzaNameGenerator();
        System.out.println(pizzaNameGenerator.getName());
    }
}