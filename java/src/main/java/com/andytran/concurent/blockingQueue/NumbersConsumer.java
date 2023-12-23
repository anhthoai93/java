package com.andytran.concurent.blockingQueue;

import java.util.concurrent.BlockingQueue;

public class NumbersConsumer implements Runnable{
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public NumbersConsumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer number = queue.take();
                if (number.equals(poisonPill))
                    return;
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
