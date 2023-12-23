package com.andytran.concurent.delayQueue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class DelayQueueTest {
    @Test
    public void givenDelayQueue_whenProduceElement_thenShouldConsumeAfterGivenDelay() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        int numberOfElementsToProduce = 2;
        int delayOfEachProducedMessageMilliseconds = 500;
        DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);
        DelayQueueProducer producer = new DelayQueueProducer(queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);
        executor.submit(consumer);
        executor.submit(producer);
        executor.awaitTermination(2, TimeUnit.SECONDS);
        executor.shutdown();
        assertEquals(consumer.getNumberOfConsumedElements().get(), 2);
    }

}