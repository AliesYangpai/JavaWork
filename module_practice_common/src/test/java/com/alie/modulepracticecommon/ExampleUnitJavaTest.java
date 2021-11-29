package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.ThreadSample;

import org.junit.Test;

public class ExampleUnitJavaTest {

    private void doPrint(String tag) {
        System.out.println("==="+tag);
    }

    @Test
    public void test01(){
        doPrint("test01");
        Thread thread1 = new Thread(()-> System.out.println("===Thread1 run"));
        thread1.start();
    }

    /**
     * 线程同步
     */
    @Test
    public void test02(){
        doPrint("test02");
        ThreadSample threadSample = new ThreadSample();
        threadSample.add();
        threadSample.decrease();
        threadSample.showData();
    }

}