package com.alie.libpracticeapiobject;

public class MyClass {

    public static void main(String[] args) {
        makeObjectOfWaitAndNotify();
    }

    /**
     * give an example of wait and notify to show how do they work;
     * tip: do some work and start wait,after 3s,find out what happens.
     * result: continue to work for previous wait work (check logs)
     * ex:must be in different thread to show example!!! if wait in main thread,waiting..........
     */
    public static void makeObjectOfWaitAndNotify() {
        Byte[] obj = new Byte[1];
        doWait(obj);
        try {
            Thread.sleep(3 * 1000);
            doNotify(obj);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doWait(final Object obj) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (obj) {
                    try {
                        System.out.println("===doWait do some work "+Thread.currentThread().getId());
                        System.out.println("===doWait star wait");
                        obj.wait();
                        System.out.println("===doWait continue to go");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    private static void doNotify(final Object obj) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                synchronized (obj) {

                    obj.notify();
                }
            }
        }.start();
    }
}