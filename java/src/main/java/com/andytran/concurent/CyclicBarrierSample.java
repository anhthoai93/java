package com.andytran.concurent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierSample {
    private static List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
    static int NUM_PARTIAL_RESULTS;
    static int NUM_WORKERS;
    static Random random = new Random();
    private static CyclicBarrier cyclicBarrier;

    static class NumberCruncherThread implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            List<Integer> partialResult = new ArrayList<>();
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
                Integer num = random.nextInt(10);
                System.out.println(threadName
                        + ": Crunching some numbers! Final result - " + num);
                partialResult.add(num);
            }
            partialResults.add(partialResult);
            try {
                System.out.println(threadName
                        + " waiting for others to reach barrier.");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        }
    }

    static class AggregatorThread implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(
                    threadName + ": Computing sum of " + NUM_WORKERS
                            + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;
            for (List<Integer> threadResult : partialResults) {
                System.out.print("Adding ");
                for (Integer partialResult : threadResult) {
                    System.out.print(partialResult+" ");
                    sum += partialResult;
                }
                System.out.println();
            }
            System.out.println(threadName + ": Final result = " + sum);
        }
    }

    public static void runSimulation(int numWorkers, int numberOfPartialResults) {
        NUM_WORKERS = numWorkers;
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());
        System.out.println("Spawning " + NUM_WORKERS
                + " worker threads to compute "
                + NUM_PARTIAL_RESULTS + " partial results each");

        for (int i = 0; i < NUM_WORKERS; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            worker.setName("Thread " + i);
            worker.start();
        }
    }


    public static void main(String[] args) {
        runSimulation(5,3);
    }
}
