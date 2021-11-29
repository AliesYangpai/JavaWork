package com.alie.modulepracticecommon.work;

public class ThreadSample {
    private Byte[] lock = {};

    private static final int SIZE = 10000;
    private int count = 0;


    public void add() {
        new Thread(()->{
            for (int i = 0; i < SIZE; i++) {
                synchronized (lock){
                    count++;
                }
            }
        }).start();
    }

    public void decrease(){
        new Thread(()->{
            for (int i = 0; i < SIZE; i++) {
                synchronized (lock) {
                    count--;
                }
            }
        }).start();
    }

    public void showData() {
        try {
            Thread.sleep(2000);
            System.out.println("===count:"+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
