package com.andytran.concurent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceSample {

    public static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Task is running %s", Thread.currentThread().getName());
            System.out.println();
        }
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Task());
        service.submit(new Task());
        service.submit(new Task());
        service.shutdown(); // wait until all the summited task finish executing.
    }
}