package com.alie.modulepracticecommon;

import com.alie.modulepracticecommon.work.refletion.ClassUtil;
import com.alie.modulepracticecommon.work.refletion.Robot;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel2;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel3;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel4;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel5;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel6;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel7;
import com.alie.modulepracticecommon.work.threadandlock.ThreadModel8;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
            System.out.println("money = " + threadModel8.getAllMoney());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reflection
     * part1 Class
     */
    @Test
    public void test09() {
        doPrint("test09");
        String packageName = "com.alie.modulepracticecommon.work.threadandlock.ThreadModel2";
        ThreadModel2 threadModel2 = new ThreadModel2();
        ClassUtil.showClassInfo1(packageName);
        ClassUtil.showClassInfo2(threadModel2);
    }

    /**
     * Reflection
     * part2 field
     */
    @Test
    public void test10() {
        doPrint("test10");
        Robot robot = new Robot("Terminator001", 10, "COMPREHENSIVE", 15.63F);
        try {
            Field fieldPrice = robot.getClass().getDeclaredField("price");
            Class<?> type = fieldPrice.getType();
            Float value = (Float) fieldPrice.get(robot);
            doPrint("the type:" + type.getSimpleName() + "  value:" + value);
            Field filedName = robot.getClass().getDeclaredField("name");
            int modifierName = filedName.getModifiers();
            if (Modifier.isPrivate(modifierName)) {
                filedName.setAccessible(true);
            }
            String name = (String) filedName.get(robot);
            doPrint("the name is :" + name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reflection
     * part3 Method
     */
    @Test
    public void test11() {
        doPrint("test11");
        Robot robot = new Robot("Terminator001", 10, "COMPREHENSIVE", 15.63F);
        Class<? extends Robot> aClass = robot.getClass();
        try {
            Method speakMethod = aClass.getDeclaredMethod("speak");
            if (Modifier.isPrivate(speakMethod.getModifiers())) {
                speakMethod.setAccessible(true);
            }
            speakMethod.invoke(robot);

            Method learnMethod = aClass.getDeclaredMethod("learn", String.class);
            if (Modifier.isPrivate(learnMethod.getModifiers())) {
                learnMethod.setAccessible(true);
            }
            learnMethod.invoke(robot, "do laundry");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reflection
     * part3 method review
     */
    @Test
    public void test12() {
        doPrint("test12");
        Robot robot = new Robot("Terminator001", 10, "COMPREHENSIVE", 15.63F);
        Class<? extends Robot> aClass = robot.getClass();
        try {
            Field fieldName = aClass.getDeclaredField("name");
            Method methodSpeak = aClass.getDeclaredMethod("speak");
            Method methodLearn = aClass.getDeclaredMethod("learn", String.class);
            if (Modifier.isPrivate(fieldName.getModifiers())) {
                fieldName.setAccessible(true);
            }
            if (Modifier.isPrivate(methodSpeak.getModifiers())) {
                methodSpeak.setAccessible(true);
            }
            if (Modifier.isPrivate(methodLearn.getModifiers())) {
                methodLearn.setAccessible(true);
            }
            String name = (String) fieldName.get(robot);
            doPrint(name);
            methodSpeak.invoke(robot);
            methodLearn.invoke(robot, "hi practice");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reflection
     * part4 public construction with no param
     * class.newInstance() only follow
     * 1.public construction
     * 2.no param
     */
    @Test
    public void test13() {
        doPrint("test13");
        try {
            Robot robot = Robot.class.newInstance();
            robot.setAge(15);
            robot.setName("Terminator001");
            doPrint(robot.getName());
            doPrint(robot.getAge() + "");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reflection
     * part5 construction -- all situation
     * class.getConstructor(class)
     */
    @Test
    public void test14() {
        doPrint("test14");
        try {
            Class aClass = Class.forName("com.alie.modulepracticecommon.work.refletion.Robot");
            Constructor constructor = aClass.getConstructor(String.class, int.class);
            if (!Modifier.isPublic(constructor.getModifiers())) {
                constructor.setAccessible(true);
            }
            Robot robot = (Robot) constructor.newInstance("Terminator001", 99);
            doPrint(robot.getName());
            doPrint(robot.getAge() + "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}