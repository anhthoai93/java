package com.andytran.java.phaser;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

import static org.junit.jupiter.api.Assertions.*;

class LongRunningActionTest {
    private static final Logger log = LoggerFactory.getLogger(LongRunningActionTest.class);

    @Test
    public void simplePhaser() throws InterruptedException {
        Phaser ph = new Phaser(1);
        assertEquals(0, ph.getPhase());

        new Thread(new LongRunningAction("thread-1", ph)).start();
        new Thread(new LongRunningAction("thread-2", ph)).start();
        new Thread(new LongRunningAction("thread-3", ph)).start();

        log.info("Thread {} waiting for others", Thread.currentThread().getName());
        ph.arriveAndAwaitAdvance();
        log.info("Thread {} proceeding in phase {}", Thread.currentThread().getName(), ph.getPhase());
        assertEquals(1, ph.getPhase());

        new Thread(new LongRunningAction("thread-4", ph)).start();
        new Thread(new LongRunningAction("thread-5", ph)).start();

        log.info("Thread {} waiting for new phase", Thread.currentThread().getName());
        ph.arriveAndAwaitAdvance();

        log.info("Thread {} proceeding in phase {}", Thread.currentThread().getName(), ph.getPhase());
        assertEquals(2, ph.getPhase());

        ph.arriveAndDeregister();
        Thread.sleep(1000);
        assertEquals(true, ph.isTerminated());
    }

}