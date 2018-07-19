package edu.bu.met.cs665.simulator;

import java.util.Random;

public class ChineseNameGenerator implements NameGenerator {
    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = chineseNames[rnd.nextInt(chineseNames.length)][0];
        String lastName = chineseNames[rnd.nextInt(chineseNames.length)][1];
        return firstName + " " + lastName;
    }
}
