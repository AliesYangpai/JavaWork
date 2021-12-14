package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.ThreadModel1;
import com.alie.modulepracticecommon.work.ThreadModel2;
import com.alie.modulepracticecommon.work.ThreadModel3;
import com.alie.modulepracticecommon.work.ThreadModel4;

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

    /**
     * ReentrantLock
     */
    @Test
    public void test04() {
        doPrint("test04");
        ThreadModel3 threadModel3 = new ThreadModel3();
        threadModel3.increaseCount();
        threadModel3.decreaseCount();
        threadModel3.showData();
    }

    /**
     * ReentrantLock
     * lock.newCondition();
     */
    @Test
    public void test() {
        doPrint("test04");
        ThreadModel4 threadModel4 = new ThreadModel4();
        for (int i = 0; i < ThreadModel4.PRODUCE_COUNT; i++) {
            int finalI = i;
            new Thread(()-> threadModel4.addToQueue(String.valueOf(finalI))).start();
        }

        for (int i = 0; i < ThreadModel4.CONSUME_COUNT; i++) {
            new Thread(()->{
                String data = threadModel4.popQueue();
                System.out.println("===popQueue data:"+data);
            }).start();
        }
    }
}