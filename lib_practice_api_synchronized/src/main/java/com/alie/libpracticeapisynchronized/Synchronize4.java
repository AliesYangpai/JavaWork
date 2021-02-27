package com.alie.libpracticeapisynchronized;



/**
 * Created by Alie on 2019/7/7.
 * 类描述 用于测试 同步代码块锁,lock的业务作用域在{}中，但是锁只是在当前类对应的当前对象有用
 * 版本
 */
public class Synchronize4 implements Runnable {

    private int count;

    private byte[] lock = new byte[0]; // 当没有明确的对象作为锁，只是想让一段代码同步时，可以创建一个特殊的对象来充当锁：
    @Override
    public void run() {

    }

    /**
     * 类锁：
     * 写法1： synchronized (Synchronize2.class)
     */
    public void addCount() {
        synchronized (lock) {
            for (int i = 0 ; i < 50;i++) {
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


    public void addCountAgain(){
        synchronized (lock) {
            for (int i = 0 ; i < 50;i++) {
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
