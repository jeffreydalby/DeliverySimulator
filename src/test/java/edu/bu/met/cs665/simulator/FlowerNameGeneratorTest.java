package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlowerNameGeneratorTest {

    @Test
    public void getName() {
        FlowerNameGenerator flowerNameGenerator = new FlowerNameGenerator();
        System.out.println(flowerNameGenerator.getName());
    }
}