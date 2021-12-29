package com.alie.modulepracticecommon.work;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * part 5
 * replace synchronized with ReentrantLock in 'QUEUE'
 */
public class ThreadModel5 {

    private Queue<String> queue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static final int PRODUCE_COUNT = 50;
    public static final int CONSUME_COUNT = 500;

    public void addToQueue(String param) {
        lock.lock();
        try {
            queue.add(param);
            System.out.println("===addToQueue param:"+param);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popQueue() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                boolean ret = condition.await(1000, TimeUnit.MILLISECONDS);
                if (!ret) {
                    return null;
                }
            }
            return queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
