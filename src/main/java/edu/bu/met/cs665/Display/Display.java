package edu.bu.met.cs665.Display;

///just a class to fake that we could be displaying to anywhere.  For this assignment being a console app it is just going to throw whatever string to stdout
public class Display {

    //synched to prevent overlapping output
    public synchronized static void output(String stringToDisplay){
        System.out.println(stringToDisplay);
    }
}
