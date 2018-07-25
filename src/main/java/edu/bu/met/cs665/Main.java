package edu.bu.met.cs665;

import edu.bu.met.cs665.simulator.PrimarySimulator;

public class Main {

    private static final int NUMBER_OF_STORES = 15;
    private static final int NUMBER_OF_DRIVERS = 10;
    private static final int NUMBER_OF_CUSTOMERS = 30;
    private static final int NUMBER_OF_ORDERS = 20;
    private static final int MILLISECONDS_BETWEEN_ORDERS = 2000;


    /**
     * A main method to run examples.
     *
     * @param args not used
     */
    public static void main(String[] args) {


        //the simulation is started by PrimarySimulator and allows for any of the parameters to be changed
        //create and start up the simulator
        System.out.println("Starting Delivery Simulation");
        //TODO Consider switching to the Builder Pattern for future project
        PrimarySimulator primarySimulator = new PrimarySimulator();
        primarySimulator.createSimulation(NUMBER_OF_STORES, NUMBER_OF_DRIVERS, NUMBER_OF_CUSTOMERS, NUMBER_OF_ORDERS, MILLISECONDS_BETWEEN_ORDERS);

    }

}

