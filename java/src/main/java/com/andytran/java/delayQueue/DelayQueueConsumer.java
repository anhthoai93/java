package com.andytran.java.delayQueue;

import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayQueueConsumer implements Runnable {
    private BlockingQueue<DelayObject> queue;

    private Integer numberOfElementsToTake;
    @Getter
    private AtomicInteger numberOfConsumedElements = new AtomicInteger();

    public DelayQueueConsumer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToTake) {
        this.queue = queue;
        this.numberOfElementsToTake = numberOfElementsToTake;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToTake; i++) {
            try {
                DelayObject object = queue.take();
                numberOfConsumedElements.incrementAndGet();
                System.out.println("Consumer take: " + object);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
