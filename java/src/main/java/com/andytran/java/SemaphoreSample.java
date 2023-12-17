package com.andytran.java;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class SemaphoreSample {
    public static class LoginQueueUsingSemaphore {
        Semaphore semaphore;

        public LoginQueueUsingSemaphore(int slots) {
            this.semaphore = new Semaphore(slots);
        }

        boolean tryLogin() {
            return semaphore.tryAcquire();
        }

        void logout() {
            semaphore.release();
        }

        int availableSlot() {
            return semaphore.availablePermits();
        }
    }
}
