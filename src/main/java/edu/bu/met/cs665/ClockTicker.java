package edu.bu.met.cs665;

public class ClockTicker implements Runnable {
  public static final int CLOCK_SPEED = 1000;
  @Override
  public void run() {
    while (true) {
      if(Thread.currentThread().isInterrupted()) break;
      Main.systemClock++;
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
