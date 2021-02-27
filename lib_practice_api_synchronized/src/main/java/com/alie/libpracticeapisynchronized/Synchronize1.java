package com.alie.libpracticeapisynchronized;


/**
 * Created by Alie on 2019/7/7.
 * 类描述 用于测试对象锁,以下2中写法获取的是相同的锁
 * 对象锁 针对类的单个对象有效 （如果是多个对象 那么就是2把锁了）
 * 版本
 */
public class Synchronize1 implements Runnable {

    private int count;

    @Override
    public void run() {

    }

    /**
     * 对象锁：
     * 写法1： synchronized (this)
     */
    public void addCount() {
        synchronized (this) {
            for (int i = 0; i < 50; i++) {
                try {
                    Thread.sleep(300);
                    System.out.println(" current thread：" + Thread.currentThread().getName() +
                            " current index i:" + i +
                            " current count：" + count++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对象锁：
     * 写法2： synchronized (this)
     */
    public synchronized void addCountAgain() {
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(300);
                System.out.println(" current thread：" + Thread.currentThread().getName() +
                        " current index i:" + i +
                        " current count：" + count++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
