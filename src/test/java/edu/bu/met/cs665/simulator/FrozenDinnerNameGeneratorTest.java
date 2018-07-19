package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrozenDinnerNameGeneratorTest {

    @Test
    public void getName() {
        FrozenDinnerNameGenerator frozenDinnerNameGenerator = new FrozenDinnerNameGenerator();
        System.out.println(frozenDinnerNameGenerator.getName());
    }
}