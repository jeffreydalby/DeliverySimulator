package edu.bu.met.cs665;

public class ClockTicker implements Runnable {

    private static ClockTicker clockTickerInstance;

    public Thread getClockTickerThread() {
        return clockTickerThread;
    }

    private Thread clockTickerThread;
    private ClockTicker(){}


    public static synchronized ClockTicker getClockTickerInstance(){
        if(clockTickerInstance == null) clockTickerInstance = new ClockTicker();
        return clockTickerInstance;
    }

    void startClock(){
        this.clockTickerThread = new Thread(ClockTicker.getClockTickerInstance());
        this.clockTickerThread.start();

    }

  private static final int CLOCK_SPEED = 1000;

    public int getSystemClock() {
        return this.systemClock;
    }

    //Instead of going off of real time for the simulation (which would take forever)
  //just using an int ticker system clock;
  private int systemClock;




  @Override
  public void run() {
    while (true) {
      if(Thread.currentThread().isInterrupted()) break;
      this.systemClock++;

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
