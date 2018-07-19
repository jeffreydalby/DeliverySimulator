package edu.bu.met.cs665.simulator;

import org.junit.Test;

import static org.junit.Assert.*;

public class OfficeSupplyNameGeneratorTest {

    @Test
    public void getName() {
        OfficeSupplyNameGenerator officeSupplyNameGenerator = new OfficeSupplyNameGenerator();
        System.out.println(officeSupplyNameGenerator.getName());
    }
}