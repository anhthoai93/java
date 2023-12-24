package com.andytran.concurent.thread;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreadLocalTest {
    @Test
    public void givenThreadThatStoresContextInThreadLocal_whenStartThread_thenShouldStoreContextInThreadLocal() throws InterruptedException {
        //when
        ThreadLocalWithUserContext firstUser = new ThreadLocalWithUserContext();
        ThreadLocalWithUserContext secondUser = new ThreadLocalWithUserContext();
        new Thread(firstUser).start();
        new Thread(secondUser).start();

        Thread.sleep(3000);

    }
}