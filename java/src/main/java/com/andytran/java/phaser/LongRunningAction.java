package com.andytran.java.phaser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Phaser;

public class LongRunningAction implements Runnable {
    private String threadName;
    private Phaser phaser;
    private static final Logger log = LoggerFactory.getLogger(LongRunningAction.class);

    public LongRunningAction(String threadName, Phaser ph) {
        this.threadName = threadName;
        this.phaser = ph;
        this.randomWait();
        phaser.register();
        log.info("Thread {} registered during phase {}", threadName, phaser.getPhase());
    }

    private void randomWait() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        log.info("Thread {} BEFORE long running action in phase {}", threadName, phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        randomWait();
        log.info("Thread {} AFTER long running action in phase {}", threadName, phaser.getPhase());
        phaser.arriveAndDeregister();
    }

}