package com.alie.modulepracticecommon.work.threadandlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * part8
 * ThreadModel8
 * Atomic is about CAS
 *
 */
public class ThreadModel8 {
    private final AtomicInteger atomicInteger = new AtomicInteger();
    public static final int PRODUCE_COUNT = 500;
    public void donate(int money) {
        atomicInteger.getAndAdd(money);
    }


    public int getAllMoney() {
        return atomicInteger.get();
    }
}
