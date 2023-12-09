package com.andytran.java.thread;

import org.junit.jupiter.api.Test;

class SimpleRunnableTest {
    @Test
    public void givenAThread_when_RunIt_ThenResultState1() throws InterruptedException {
        Thread thread = new Thread(new SimpleRunnable());
        System.out.printf("New Thread %s %n", thread.getState());
        thread.start();
        System.out.printf("Start Thread %s %n", thread.getState());
        thread.join();
        System.out.printf("Terminated Thread %s %n", thread.getState());
    }

    @Test
    public void givenAThread_when_RunIt_ThenResultState2() throws InterruptedException {
        Thread thread = new Thread(new SimpleRunnable());
        System.out.printf("New Thread %s %s %n", thread.getName(), thread.getState());
        thread.start();
        System.out.printf("Start Thread %s %s %n", thread.getName(), thread.getState());
        thread.join();
        System.out.printf("Terminated Thread %s  %s %n", thread.getName(), thread.getState());
    }


    @Test
    public void givenAThread_when_RunIt_ThenResultState3() throws InterruptedException {
        Thread thread = new Thread(new SimpleRunnable());
        System.out.printf("New Thread %s %s %n", thread.getName(), thread.getState());
        thread.start();
        System.out.printf("Start Thread %s %s %n", thread.getName(), thread.getState());
        thread.join();
        System.out.printf("Terminated Thread %s  %s %n", thread.getName(), thread.getState());
    }

}