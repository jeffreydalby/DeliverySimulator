package edu.bu.met.cs665.Display;

///just a class to fake that we could be displaying to anywhere.  For this assignment being a console app it is just going to throw whatever string to stdout
public class Display {
    //allows a standard seperator text for each output
    public static final String SEPARATOR_TEXT = "\n***************************************\n";

    //synched to prevent overlapping output
    //could be changed to allow output anywhere
    public synchronized static void output(String stringToDisplay) {
        System.out.println(SEPARATOR_TEXT + stringToDisplay + SEPARATOR_TEXT);
    }
}
