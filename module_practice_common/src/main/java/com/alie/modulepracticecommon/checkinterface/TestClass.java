package com.alie.modulepracticecommon.checkinterface;

/**
 * 测试1
 * 相同方法签名
 * 结果：编译正常，运行正常
 */
//public class TestClass implements TestInterface1,TestInterface2 {
//    @Override
//    public void callback() {
//        System.out.println("TestClass play game");
//    }
//}


/**
 * 测试2
 * 名称相同，返回类型相同，参数不同
 * 结果：编译正常，运行正常
 */
//public class TestClass implements TestInterface1,TestInterface2,TestInterface3 {
//
//    @Override
//    public void callback() {
//        System.out.println("TestClass play game");
//    }
//
//    @Override
//    public void callback(int args) {
//        System.out.println("TestClass play game with param");
//    }
//}

/**
 * 测试3
 * 名称相同，参数相同 返回类型不同
 * 结果：编译异常
 */
//public class TestClass implements TestInterface1,TestInterface4 {
//
//    @Override
//    public void callback() {
//
//    }
//}

/**
 * 测试5
 * 名称相同，返回类型不同，参数不同
 * 结果：编译正常
 */
//public class TestClass implements TestInterface4,TestInterface5 {
//
//
//    @Override
//    public int callback() {
//        return 0;
//    }
//
//    @Override
//    public String callback(String args) {
//        return null;
//    }
//}

/**
 * 综上
 * 一个类实现多个接口，并且这些接口中含有同名方法时：
 *  方法签名 只有返回值不同的情况下 才会编译异常，其他情况下均可正常运行
 *
 */