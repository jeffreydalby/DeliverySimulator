package edu.bu.met.cs665.simulator;

import java.util.Random;

public class OfficeSupplyNameGenerator implements NameGenerator {
    @Override
    public String getName() {
        String[][] officeSupplyNames = {{"Office", "Supply"}, {"Office", "Depot"}};

        Random rnd = new Random();
        String firstName = officeSupplyNames[rnd.nextInt(officeSupplyNames.length)][0];
        String lastName = officeSupplyNames[rnd.nextInt(officeSupplyNames.length)][1];
        return firstName + " " + lastName;
    }
}
