package com.andytran.java.synchronize;

public class SynchronizedMethods {
    private int sum = 0;
    private int syncSum = 0;
    static int staticSum = 0;

    int count  = 0;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void performSynchronisedTask() {
        synchronized (this) {
            setCount(getCount() +1);
        }
    }

    public synchronized static void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }

    public int getSyncSum() {
        return syncSum;
    }

    public void setSyncSum(int syncSum) {
        this.syncSum = syncSum;
    }


    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void calculate() {
        setSum(getSum() + 1);
    }

    public synchronized void synchronisedCalculate() { // Instance Methods way
        setSyncSum(getSyncSum() + 1);
    }
}
