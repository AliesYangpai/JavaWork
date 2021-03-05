package com.alie.libpracticeapilock;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class will show a sample
 * that How do we change same data in multi threads and make it sync
 */
public class MyClass {
    private static List<Integer> generateNum() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9999;i++) {
            list.add(i+1);
        }
        return list;
    }
    private static List<Integer> list= generateNum();
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition conditionPart1 = reentrantLock.newCondition();
    private static Condition conditionPart2 = reentrantLock.newCondition();
    private static Condition conditionPart3 = reentrantLock.newCondition();

    private static final int DEFAULT = 0;
    private volatile static int TAG = DEFAULT; // default value
    private static final int PART1_FINISH = 1;
    private static final int PART2_FINISH = 2;
    private static final int PART3_FINISH = 3;


    public static void main(String[] args) {
        doChangeSecondPart();
        doChangeFirstPart();
        doChangeThirdPart();

        try {
            Thread.sleep(3 * 1000);
            for (int i : list) {
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doChangeFirstPart() {

        new Thread(() -> {
            reentrantLock.lock();
            if (TAG == DEFAULT) {
                for (int i = 0; i < list.size() / 3; i++) {
                    list.set(i,list.get(i)*10);
                }
                TAG = PART1_FINISH;
            }
            conditionPart2.signal();
            reentrantLock.unlock();
        }).start();
    }

    private static void doChangeSecondPart() {
        new Thread(() -> {
            reentrantLock.lock();
            try {
                if (TAG != PART1_FINISH) {
                    conditionPart2.await();
                    System.out.println(Thread.currentThread().getName()+ "====conditionPart2 await");

                }
                for (int i = list.size() / 3; i < list.size() / 3 * 2; i++) {
                    list.set(i,list.get(i)*100);
                }
                TAG = PART2_FINISH;
                conditionPart3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }).start();
    }

    private static void doChangeThirdPart() {
        new Thread(() -> {
            reentrantLock.lock();
            try {
                if (TAG != PART2_FINISH) {
                    conditionPart3.await();
                    System.out.println(Thread.currentThread().getName()+ "====conditionPart3 await");
                }
                for (int i = list.size() / 3 * 2; i < list.size(); i++) {
                    list.set(i,list.get(i)*1000);
                }
                TAG = PART3_FINISH;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }

        }).start();
    }
}