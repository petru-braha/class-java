package com.lab7;

import java.util.concurrent.atomic.AtomicBoolean;

public class TimeKeeper extends Thread {

  private final AtomicBoolean isRunning;
  private final long maxMilliseconds;

  public TimeKeeper(AtomicBoolean isRunning, int seconds) {
    this.isRunning = isRunning;
    this.maxMilliseconds = seconds * 1000L;
    setDaemon(true);
  }

  @Override
  public void run() {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() - start < maxMilliseconds) {
    }

    System.out.println("⏰ Time limit reached!");
    isRunning.set(false);
  }
}
