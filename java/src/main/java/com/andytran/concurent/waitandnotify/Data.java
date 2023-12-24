package com.andytran.concurent.waitandnotify;

public class Data {
    private String packet;

    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;

    public synchronized String receive() {
        while(transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = true;
        String returnPacket = packet;
        notifyAll();
        return returnPacket;

    }

    public  void send(String data) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread()
                        .interrupt();
                System.err.println("Thread Interrupted");
            }
        }
        transfer = false;
        this.packet = data;
        notifyAll();

    }
}
