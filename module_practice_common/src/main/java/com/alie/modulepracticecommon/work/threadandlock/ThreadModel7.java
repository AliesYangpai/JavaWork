package com.alie.modulepracticecommon.work.threadandlock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.StampedLock;

/**
 * part7
 * StampedLock
 * from ReentrantReadWriteLock to StampedLock
 * StampedLock also has write and read lock
 * 【attention】 fail-fast error in collection when using StampedLock
 */
public class ThreadModel7 {

    private Queue<String> queue = new LinkedList<>();
    private StampedLock stampedLock = new StampedLock();
    public static final int PRODUCE_COUNT = 50;
    public static final int CONSUME_COUNT = 500;


    public void addToQueue(String param) {
        long stamp = stampedLock.writeLock();
        try {
            queue.add(param);
            System.out.println("addToQueue param:"+param);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public void readInfo() {
        long stamp = stampedLock.tryOptimisticRead();
        if (stampedLock.validate(stamp)) { // there will have been executed write lock on stampedLock if False
            showData();
        } else {
            stamp = stampedLock.readLock();
            try {
                showData();
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
    }

    private void showData() {
        queue.forEach(s -> System.out.println( "=== currentThread:"+Thread.currentThread().getName()+" value:"+s));
    }
}
