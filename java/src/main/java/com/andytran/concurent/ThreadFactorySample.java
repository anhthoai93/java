package com.andytran.concurent;

import java.util.concurrent.ThreadFactory;

public class ThreadFactorySample implements ThreadFactory {
    private int threadId;
    private String name;

    public ThreadFactorySample(String name) {
        this.threadId = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + threadId);
        System.out.println("created new thread with id : " + threadId +
                " and name : " + t.getName());
        threadId++;
        return t;
    }

    public static void main(String[] args) {
       ThreadFactorySample factorySample = new ThreadFactorySample("ThreadFactorySample");
        for (int i = 0; i < 10; i++) {
            Thread thread = factorySample.newThread(() -> {});
            thread.start();
        }
    }
}
