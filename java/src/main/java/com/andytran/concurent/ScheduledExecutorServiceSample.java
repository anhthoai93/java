package com.andytran.concurent;

import java.util.concurrent.*;

public class ScheduledExecutorServiceSample {
    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Task is running....");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Future<String> future = service.schedule(() -> "Hello world", 5, TimeUnit.SECONDS);
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());
        service.shutdown();
    }
}
