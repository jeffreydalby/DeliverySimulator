package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class PeopleNameGeneratorTest {

    @Test
    public void getName() {
        PeopleNameGenerator peopleNameGenerator = new PeopleNameGenerator();
        System.out.println(peopleNameGenerator.getName());
    }
}