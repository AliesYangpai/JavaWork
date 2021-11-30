package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.ThreadSample;
import com.alie.modulepracticecommon.work.ThreadSample2;

import org.junit.Test;

public class ExampleUnitJavaTest {

    private void doPrint(String tag) {
        System.out.println("===" + tag);
    }

    @Test
    public void test01() {
        doPrint("test01");
        Thread thread1 = new Thread(() -> System.out.println("===Thread1 run"));
        thread1.start();
    }

    /**
     * 线程同步
     */
    @Test
    public void test02() {
        doPrint("test02");
        ThreadSample threadSample = new ThreadSample();
        threadSample.add();
        threadSample.decrease();
        threadSample.showData();
    }


    /**
     * obj.wait()
     * obj.notify() notifyAll()
     */
    @Test
    public void test03() {
        doPrint("test03");
        ThreadSample2 threadSample2 = new ThreadSample2();
        for (int i = 0; i < ThreadSample2.SIZE; i++) {
            int finalI = i;
            new Thread(() -> threadSample2.addToQueue(String.valueOf(finalI))).start();
        }
        for (int i = 0; i < ThreadSample2.SIZE2; i++) {
            new Thread(() -> {
                String data = threadSample2.popQueue();
                System.out.println("===data:"+data);
            }).start();
        }


    }
}