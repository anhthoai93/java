package com.andytran.concurent.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithCondition {
    private static Logger LOG = LoggerFactory.getLogger(ReentrantLockWithCondition.class);

    private Stack<String> stack = new Stack<>();
    private static final int CAPACITY = 5;
    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    private void pushToStack(String item) throws InterruptedException {
        try {
            lock.lock();
            if (stack.size() == CAPACITY) {
                LOG.info(Thread.currentThread().getName() + " wait on stack full");
                stackFullCondition.await();
            }
            LOG.info("Pushing the item " + item);
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private String popFromStack() throws InterruptedException {
        try {

            lock.lock();
            if (stack.empty()) {
                LOG.info(Thread.currentThread().getName() + " wait on stack empty");
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockWithCondition object = new ReentrantLockWithCondition();
        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    object.pushToStack("Item " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    object.popFromStack();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        service.shutdown();
    }
}
