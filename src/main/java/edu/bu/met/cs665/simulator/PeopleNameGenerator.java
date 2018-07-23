package edu.bu.met.cs665.simulator;

import java.util.Random;

public class PeopleNameGenerator implements NameGenerator {

    private String[][] peopleNames= {{"Bob","Smith"},{"Jack","Parker"},{"Sophia","Johnson"},
            {"Jacob","Williams"},{"Emma", "Brown"}, {"Mason","Jones"},{"Emily","Miller"},{"Madison","Wilson"},
            {"Daniel","Anderson"},{"Ella","Garcia"},{"Joshua","Taylor"},{"Grace","Hernandez"},
            {"Andrew","Moore"},{"Zoey","Lee"},{"Ryan","Perez"}};

    @Override
    public String getName() {
        Random rnd = new Random();
        String firstName = peopleNames[rnd.nextInt(peopleNames.length)][0];
        String lastName = peopleNames[rnd.nextInt(peopleNames.length)][1];
        return firstName + " " + lastName;

    }
}
