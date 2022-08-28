package com.alie.modulepracticereflect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {


    private void showInfo(String tag, String tip) {
        System.out.println("xxxReflect " + tag + " " + tip);
    }

    private Person generatePerson() {
        Person person = new Person("xigua", 10);
        person.gender = "male";
        return person;
    }

    private static final String CLASS_NAME_PERSON = "com.alie.modulepracticereflect.Person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doReflect();
    }


    private void doReflect() {
        //==========方法
        doReflect01();
        doReflect02();
        doReflect03();
        //==========成员变量
        doReflect04();
        doReflect05();
    }

    private void doReflect01() {
        showInfo("doReflect01", "==========================");
        try {
            Class<?> aClass = Class.forName(CLASS_NAME_PERSON);
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                Method declaredMethod = declaredMethods[i];
                String name = declaredMethod.getName();
                showInfo("doReflect01", name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射对象方法
     * 【注】测试对象方法时，一定要将对象传入
     */
    private void doReflect02() {
        try {
            showInfo("doReflect02", "==========================");
            Person person = generatePerson();

            Class<?> aClass = Class.forName(CLASS_NAME_PERSON);
            Method method = aClass.getDeclaredMethod("getName");
            String invoke = (String) method.invoke(person);
            showInfo("doReflect02", invoke);
        } catch (ClassNotFoundException e) {
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
     * 测试类方法
     * 【注】invoke(Object obj, Object... args) 中
     * 对象方法，传入对象，
     * 类方法，传入类的Class对象
     */
    private void doReflect03() {
        try {
            showInfo("doReflect03", "==========================");
            Class<?> aClass = Class.forName(CLASS_NAME_PERSON);
            Method method = aClass.getDeclaredMethod("getTestTheName");
            String tip = (String) method.invoke(aClass);
            showInfo("doReflect03", tip);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private void doReflect04() {
        try {
            showInfo("doReflect04", "==========================");
            Person person = generatePerson();
            Class<?> aClass = Class.forName(CLASS_NAME_PERSON);
            Field field = aClass.getDeclaredField("name");
            field.setAccessible(true);
            String gender = (String) field.get(person);
            showInfo("doReflect04", gender);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            showInfo("doReflect04", e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doReflect05() {
        showInfo("doReflect05", "==========================");
        try {
            Person person = generatePerson();
            Class<?> aClass = Class.forName(CLASS_NAME_PERSON);
            Field field = aClass.getDeclaredField("gender");
            String gender = (String) field.get(person);
            showInfo("doReflect05",gender);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}