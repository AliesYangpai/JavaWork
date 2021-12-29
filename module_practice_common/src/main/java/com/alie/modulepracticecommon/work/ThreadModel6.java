package com.alie.modulepracticecommon.work;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * part 6
 * ReentrantReadWriteLock
 * write synchronized but read can be concurrency
 */
public class ThreadModel6 {
    private Queue<String> queue = new LinkedList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    public static final int PRODUCE_COUNT = 50;
    public static final int CONSUME_COUNT = 500;

    public void addToQueue(String param) {
        writeLock.lock();
        try{
            queue.add(param);
        }finally {
            writeLock.unlock();
        }
    }


    public String popQueue() {
        readLock.lock();
        String data = null;
        try{
            if (!queue.isEmpty()) {
                data = queue.remove();
            }
        } finally {
            readLock.unlock();
        }
        return data;
    }
}
