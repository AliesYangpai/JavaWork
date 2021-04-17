package com.alie.modulepracticejni;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        /**
         * still get problem in vs compile need to fix
         */
        String path = "C:\\Users\\Administrator\\CMakeBuilds\\cc3a7d45-531b-a63f-a832-69baa309635b\\build\\x64-Debug\\CMakeProject4_Jni_first\\alie_first.dll";
        System.load(path);
        String name = "Alie first jni";
        int num = 8899;
        doTestJni(name,num);
    }
    native void doTestJni(String name,int num);
}