package com.andytran.concurent;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchSample {
    public static class Worker implements Runnable {

        List<String> output;
        CountDownLatch latch;

        public Worker(List<String> output, CountDownLatch latch) {
            this.output = output;
            this.latch = latch;
        }

        @Override
        public void run() {
            output.add("Count Down. Thread %s".formatted(Thread.currentThread().getName()));
            latch.countDown();
        }
    }

    public static class AdvanceWorker implements Runnable {

        List<String> output;
        CountDownLatch readyThreadCounter;
        CountDownLatch callingThreadBlocker;
        CountDownLatch completedThreadCounter;

        public AdvanceWorker(List<String> output,
                             CountDownLatch readyThreadCounter,
                             CountDownLatch callingThreadBlocker,
                             CountDownLatch completedThreadCounter) {
            this.output = output;
            this.readyThreadCounter = readyThreadCounter;
            this.callingThreadBlocker = callingThreadBlocker;
            this.completedThreadCounter = completedThreadCounter;
        }

        @Override
        public void run() {
            readyThreadCounter.countDown();
            System.out.println("Thread %s Started".formatted(Thread.currentThread().getName()));
            try {
                System.out.println("Thread %s Wait".formatted(Thread.currentThread().getName()));
                callingThreadBlocker.await();
                System.out.println("Thread %s Continue".formatted(Thread.currentThread().getName()));
                output.add("Count Down. Thread %s".formatted(Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                completedThreadCounter.countDown();
            }

        }
    }

}
