package com.andytran.java;

import java.util.concurrent.Executor;

public class ExecutorSample {

    public static class Invoker implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();;
        }
    }
    public static void main(String[] args) {
        Executor executor = new Invoker();
        executor.execute(() -> {
            System.out.println("Perform Task");
        });
    }
}
