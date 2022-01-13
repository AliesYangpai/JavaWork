package com.alie.modulepracticecommon.work.threadandlock;

import java.util.LinkedList;
import java.util.Queue;

/**
 * part 3
 * be familiar with obj.wait() & obj.notify()、obj.notifyAll()
 *
 */
public class ThreadModel3 {
    public static final int SIZE = 1000;

    public static final int SIZE2 = 30;
    private Queue<String> queue = new LinkedList<>();
    private byte[]  lock = {};


    public void addToQueue(String param) {
        synchronized (lock) {
            queue.add(param);
            System.out.println("===addToQueue param:"+param);
            lock.notifyAll(); // obj.notify() awake single stochastic thread waiting on the monitor,so  I prefer obj.notifyAll()
        }
    }

    public String popQueue() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.remove();
        }
    }
}
