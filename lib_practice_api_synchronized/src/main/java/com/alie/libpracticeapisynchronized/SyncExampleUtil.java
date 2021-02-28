package com.alie.libpracticeapisynchronized;

public class SyncExampleUtil {
    /**
     * 测试对象锁
     */
    public static void doTest1() {

        final Synchronize1 synchronize1 = new Synchronize1();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize1.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                synchronize1.addCountAgain();
            }
        };

        thread1.start();
        thread2.start();
    }

    /**
     * 测试类锁1
     */
    public static void doTest2()    {
        final Synchronize2 synchronize2A = new Synchronize2();
        final Synchronize2 synchronize2B = new Synchronize2();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize2A.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                synchronize2B.addCountAgain();
            }
        };

        thread1.start();
        thread2.start();
    }

    /**
     * 测试类锁2
     */
    public static void doTest3() {
        final Synchronize3 synchronize3A = new Synchronize3();
        final Synchronize3 synchronize3B = new Synchronize3();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize3A.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                Synchronize3.addCountAgain();
            }
        };



        Thread thread3 = new Thread("Thread3") {
            @Override
            public void run() {
                synchronize3B.addCountAgain();
            }
        };

        // 测试1
//        thread1.start();
//        thread2.start();

        // 测试2
        thread1.start();
        thread3.start();
    }

    /**
     * 测试 同步代码块
     * 非静态 属性的对象锁
     */
    public static void doTest4() {
        final Synchronize4 synchronize4A = new Synchronize4();
        final Synchronize4 synchronize4B = new Synchronize4();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize4A.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                synchronize4B.addCount();
            }
        };



        Thread thread3 = new Thread("Thread3") {
            @Override
            public void run() {
                synchronize4A.addCountAgain();
            }
        };

        // 测试1：（结果不同步）
//        thread1.start();
//        thread2.start();

        // 测试2：
        thread1.start();
        thread3.start();
    }

    /**
     * 测试 同步代码块
     * 静态 属性的对象锁
     */
    public static void doTest5() {
        final Synchronize5 synchronize5A = new Synchronize5();
        final Synchronize5 synchronize5B = new Synchronize5();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize5A.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                synchronize5B.addCount();
            }
        };

        Thread thread3 = new Thread("Thread3") {
            @Override
            public void run() {
                synchronize5A.addCountAgain();
            }
        };

        // 测试1：（结果:同步,虽然是不同的对象，但是其lock对象锁是静态的，因此这个锁一直在内存区域中，所以
        // 对于持有这个锁的类的所有对象的含锁方法，都是同步的）
//        thread1.start();
//        thread2.start();

        // 测试2：（结果：同步）
        thread1.start();
        thread3.start();
    }

    /**
     * 测试 同步代码块和类的静态方法上同意吧所的作用效果
     * 静态 属性的对象锁
     */
    public static void doTest6() {
        final Synchronize6 synchronize6A = new Synchronize6();
        final Synchronize6 synchronize6B = new Synchronize6();
        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                super.run();
                synchronize6A.addCount();
            }
        };
        Thread thread2 = new Thread("Thread2") {
            @Override
            public void run() {
                synchronize6B.addCountAgain();
            }
        };

        Thread thread3 = new Thread("Thread3") {
            @Override
            public void run() {
                Synchronize6.addCountAgain();
            }
        };

        Thread thread4 = new Thread("Thread4") {
            @Override
            public void run() {
                Synchronize6.addCountAgainAgain();
            }
        };

        // 测试1：（结果:同步,虽然是不同的对象，但是其lock对象锁是静态的，因此这个锁一直在内存区域中，所以
        // 对于持有这个锁的类的所有对象的含锁方法，都是同步的）
//        thread1.start();
//        thread2.start();

        // 测试2：（结果：同步）
//        thread1.start();
//        thread3.start();

        // 测试3：（结果：不同步）
        thread1.start();
        thread4.start();
    }

}