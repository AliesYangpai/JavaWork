package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.ThreadModel1;
import com.alie.modulepracticecommon.work.ThreadModel2;

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
        ThreadModel1 threadModel1 = new ThreadModel1();
        threadModel1.add();
        threadModel1.decrease();
        threadModel1.showData();
    }


    /**
     * obj.wait()
     * obj.notify() notifyAll()
     */
    @Test
    public void test03() {
        doPrint("test03");
        ThreadModel2 threadModel2 = new ThreadModel2();
        for (int i = 0; i < ThreadModel2.SIZE; i++) {
            int finalI = i;
            new Thread(() -> threadModel2.addToQueue(String.valueOf(finalI))).start();
        }
        for (int i = 0; i < ThreadModel2.SIZE2; i++) {
            new Thread(() -> {
                String data = threadModel2.popQueue();
                System.out.println("===data:"+data);
            }).start();
        }


    }
}