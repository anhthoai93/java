package com.andytran.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
class CountDownLatchSampleTest {
    @Test
    public void simpleCountDownLatch() throws InterruptedException {
        // Simple CountDownLatch
        List<String> output = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(5);
        List<Thread> threads = Stream.generate(() -> new Thread(new CountDownLatchSample.Worker(output, latch))).limit(5).toList();
        threads.forEach(Thread::start);
        latch.await();
        output.add("Done");
        System.out.println(String.join("\n", output));
    }

    @Test
    public void advanceCountDownLatch() throws InterruptedException {
        List<String> output = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> threads = Stream.generate(() ->
                new Thread(new CountDownLatchSample.AdvanceWorker(output, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
                .limit(5)
                .toList();
        threads.forEach(Thread::start);
        readyThreadCounter.await();
        output.add("Workers ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        output.add("Done");
        System.out.println(String.join("\n", output));
    }
}