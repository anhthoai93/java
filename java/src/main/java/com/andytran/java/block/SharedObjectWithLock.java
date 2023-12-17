package com.andytran.java.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SharedObjectWithLock {
    Logger logger = LoggerFactory.getLogger(getClass());

    ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void perform() {
        lock.lock();
        counter++;
        logger.info("AAA  " + counter);

        lock.unlock();
        if (counter %2 ==0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        final SharedObjectWithLock object = new SharedObjectWithLock();
        IntStream.range(1, 100).forEach((i) -> service.submit(object::perform));
        service.shutdown();
    }

}
