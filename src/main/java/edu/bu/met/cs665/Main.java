package edu.bu.met.cs665;

import edu.bu.met.cs665.deliverysystem.Dispatch;

public class Main{

  //Instead of going off of real time for the simulation (which would take forever)
  //just using an int ticker system clock;
  public static int systemClock;

  /**
   * A main method to run examples. 
   * @param args not used 
   */
  public static void main(String[] args) {
    System.out.println("Starting Delivery System");
    ClockTicker ticker = new ClockTicker();
    Thread tickerThread = new Thread(ticker);
    tickerThread.start();
    Dispatch dispather = Dispatch.getInstance();
    Thread dispatchThread = new Thread(dispather);
    dispatchThread.start();

  }

}

