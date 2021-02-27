package com.alie.libpracticeapisynchronized;


/**
 * Created by Alie on 2019/7/7.
 * 类描述 用于测试 类锁,
 * 以下2中写法获取的是相同的锁
 * 类锁：对于该类所创建的所有对象的对应锁方法均同步
 * 版本
 */
public class Synchronize2 implements Runnable {

    private int count;

    @Override
    public void run() {

    }

    /**
     * 类锁：
     * 写法1： synchronized (Synchronize2.class)
     */
    public void addCount() {
        synchronized (Synchronize2.class) {
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


    public void addCountAgain() {
        synchronized (Synchronize2.class) {
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
}
