package com.alie.modulepracticecommon.work.threadandlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * part 4
 * replace synchronized with ReentrantLock
 */
public class ThreadModel4 {
    private static final int SIZE = 100000;
    private int count = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void increaseCount() {
        new Thread(() -> {
            for (int i = 0; i < SIZE; i++) {
                reentrantLock.lock();
                try {
                    count++;
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();
    }

    public void decreaseCount() {
        new Thread(() -> {
            for (int i = 0; i < SIZE; i++) {
                reentrantLock.lock();
                try {
                    count--;
                } finally {
                    reentrantLock.unlock();
                }
            }
        }).start();
    }

    public void showData() {
        try {
            Thread.sleep(2000);
            System.out.println("===count:" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
