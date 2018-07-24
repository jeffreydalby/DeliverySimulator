package edu.bu.met.cs665;

import edu.bu.met.cs665.Display.Display;
import edu.bu.met.cs665.simulator.SetupSystem;

public class Main{

  public static final int NUMBER_OF_STORES = 15;
  public static final int NUMBER_OF_DRIVERS = 12;
  public static final int NUMBER_OF_CUSTOMERS = 30;
  public static final int NUMBER_OF_ORDERS = 1;
  public static final int MILLISECONDS_BETWEEN_ORDERS = 1000;


  /**
   * A main method to run examples. 
   * @param args not used 
   */
  public static void main(String[] args) {
    Display.output("Starting Clock");
    ClockTicker mainClock = ClockTicker.getClockTickerInstance();
    mainClock.startClock();

    System.out.println("Starting Delivery Simulation");
    SetupSystem primarySimulator = new SetupSystem();
    primarySimulator.createSimulation(NUMBER_OF_STORES,NUMBER_OF_DRIVERS ,NUMBER_OF_CUSTOMERS,NUMBER_OF_ORDERS,MILLISECONDS_BETWEEN_ORDERS);

  }

}

