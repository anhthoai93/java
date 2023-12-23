package com.andytran.concurent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<String> future = service.submit(() -> {
            return "Hello World";
        });
        System.out.println(future.get());
        service.shutdown();
    }
}
