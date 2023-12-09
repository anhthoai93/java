package com.andytran.java.thread;

import java.util.concurrent.atomic.AtomicReference;

public class SimpleRunnable implements Runnable {
    static AtomicReference<Thread> mainThread = new AtomicReference<>();

    public SimpleRunnable() {
        mainThread.set(Thread.currentThread());
    }
    public void run() {
      Thread t1 = new ChildThread();
      t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

class ChildRunnable implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("BEFFOR");
            Thread.sleep(3000); // This thread take 3s to process
            System.out.println("END");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.printf("Start Thread %s %s %n", SimpleThread.mainThread.get().getName(), SimpleThread.mainThread.get().getState());

    }
}