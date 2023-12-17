package com.andytran.java;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SemaphoreSampleTest {
    @Test
    public void sampleSemaphore() throws InterruptedException {
        int slots = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        SemaphoreSample.LoginQueueUsingSemaphore semaphore = new SemaphoreSample.LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots).forEach(user -> service.execute(semaphore::tryLogin));
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println(semaphore.availableSlot());
        System.out.println(semaphore.tryLogin());
        semaphore.logout();
        System.out.println("After logout");
        System.out.println(semaphore.availableSlot());
        System.out.println(semaphore.tryLogin());
    }
}