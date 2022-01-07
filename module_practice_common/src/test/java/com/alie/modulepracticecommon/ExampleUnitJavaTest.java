package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.ThreadModel2;
import com.alie.modulepracticecommon.work.ThreadModel3;
import com.alie.modulepracticecommon.work.ThreadModel4;
import com.alie.modulepracticecommon.work.ThreadModel5;
import com.alie.modulepracticecommon.work.ThreadModel6;
import com.alie.modulepracticecommon.work.ThreadModel7;
import com.alie.modulepracticecommon.work.ThreadModel8;

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
        ThreadModel2 threadModel2 = new ThreadModel2();
        threadModel2.add();
        threadModel2.decrease();
        threadModel2.showData();
    }


    /**
     * obj.wait()
     * obj.notify() notifyAll()
     */
    @Test
    public void test03() {
        doPrint("test03");
        ThreadModel3 threadModel3 = new ThreadModel3();
        for (int i = 0; i < ThreadModel3.SIZE; i++) {
            int finalI = i;
            new Thread(() -> threadModel3.addToQueue(String.valueOf(finalI))).start();
        }
        for (int i = 0; i < ThreadModel3.SIZE2; i++) {
            new Thread(() -> {
                String data = threadModel3.popQueue();
                System.out.println("===data:" + data);
            }).start();
        }
    }

    /**
     * ReentrantLock
     */
    @Test
    public void test04() {
        doPrint("test04");
        ThreadModel4 threadModel4 = new ThreadModel4();
        threadModel4.increaseCount();
        threadModel4.decreaseCount();
        threadModel4.showData();
    }

    /**
     * ReentrantLock
     * lock.newCondition();
     * await；
     * signalAll;
     */
    @Test
    public void test05() {
        doPrint("test05");
        ThreadModel5 threadModel5 = new ThreadModel5();
        for (int i = 0; i < ThreadModel5.PRODUCE_COUNT; i++) {
            int finalI = i;
            new Thread(() -> threadModel5.addToQueue(String.valueOf(finalI))).start();
        }

        for (int i = 0; i < ThreadModel5.CONSUME_COUNT; i++) {
            new Thread(() -> {
                String data = threadModel5.popQueue();
                System.out.println("===popQueue data:" + data);
            }).start();
        }
    }


    /**
     * ReentrantReadWriteLock
     */
    @Test
    public void test06() {
        doPrint("test06");
        ThreadModel6 threadModel6 = new ThreadModel6();
        for (int i = 0; i < ThreadModel6.PRODUCE_COUNT; i++) {
            int finalI = i;
            new Thread(() -> threadModel6.addToQueue(String.valueOf(finalI))).start();
        }

        for (int i = 0; i < ThreadModel6.CONSUME_COUNT; i++) {
            new Thread(() -> {
                threadModel6.readInfo();
            }).start();
        }
    }

    /**
     * StampedLock
     */
    @Test
    public void test07() {
        doPrint("test07");
        ThreadModel7 threadModel7 = new ThreadModel7();
        for (int i = 0; i < ThreadModel7.PRODUCE_COUNT; i++) {
            int finalI = i;
            new Thread(() -> threadModel7.addToQueue(String.valueOf(finalI))).start();
        }

        for (int i = 0; i < ThreadModel7.CONSUME_COUNT; i++) {
            new Thread(threadModel7::readInfo).start();
        }
    }

    /**
     * AtomicInteger
     */
    @Test
    public void test08() {
        doPrint("test08");
        ThreadModel8 threadModel8 = new ThreadModel8();
        for (int i = 0; i < ThreadModel8.PRODUCE_COUNT; i++) {
            new Thread(() -> threadModel8.donate(2)).start();
        }
        try {
            Thread.sleep(2000);
            System.out.println("money = "+threadModel8.getAllMoney());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}