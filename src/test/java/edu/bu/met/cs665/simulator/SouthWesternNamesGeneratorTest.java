package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class SouthWesternNamesGeneratorTest {

    @Test
    public void getName() {
        SouthWesternNamesGenerator southWesternNamesGenerator = new SouthWesternNamesGenerator();
        System.out.println(southWesternNamesGenerator.getName());
    }
}