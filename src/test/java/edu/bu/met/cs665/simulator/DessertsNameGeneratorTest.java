package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class DessertsNameGeneratorTest {

    @Test
    public void getName() {
        DessertsNameGenerator dessertsNameGenerator = new DessertsNameGenerator();
        System.out.println(dessertsNameGenerator.getName());
    }
}