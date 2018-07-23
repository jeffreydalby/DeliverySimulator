package edu.bu.met.cs665.simulator;

import java.util.Random;

public class ChineseNameGenerator implements NameGenerator {

    private String[][] chineseNames={{"China","Wok"},{"PeKing","Garden"},{"Szechuan","Grill"},
            {"Asian","House"},{"Mandarin","Buffet"}};

    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = chineseNames[rnd.nextInt(chineseNames.length)][0];
        String lastName = chineseNames[rnd.nextInt(chineseNames.length)][1];
        return firstName + " " + lastName;
    }
}
