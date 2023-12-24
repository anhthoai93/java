package com.andytran.concurent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ThreadLocalWithUserContext implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ThreadLocalWithUserContext.class);

    public record Context (String userName) {};
    public static final ThreadLocal<Context> userContext = new ThreadLocal<>();

    @Override
    public void run() {
        userContext.set(new Context(UUID.randomUUID().toString()));
        LOG.info("thread context for given userId: " + Thread.currentThread().getName() + " is: " + userContext.get());
    }
}
