package edu.bu.met.cs665;

public class ClockTicker implements Runnable {

    private static ClockTicker clockTickerInstance;

    public Thread getClockTickerThread() {
        return clockTickerThread;
    }

    private static Thread clockTickerThread;
    private ClockTicker(){}


    public static synchronized ClockTicker getClockTickerInstance(){
        if(clockTickerInstance == null) clockTickerInstance = new ClockTicker();
        return clockTickerInstance;
    }

    public void startClock(){
        this.clockTickerThread = new Thread(ClockTicker.getClockTickerInstance());
        this.clockTickerThread.start();

    }

  public static final int CLOCK_SPEED = 1000;
  //Instead of going off of real time for the simulation (which would take forever)
  //just using an int ticker system clock;
  public static int systemClock;



  @Override
  public void run() {
    while (true) {
      if(Thread.currentThread().isInterrupted()) break;
      systemClock++;

      try {
        Thread.sleep(CLOCK_SPEED);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
        //exit our loop cause something went wrong
        break;
      }
    }
  }
}
