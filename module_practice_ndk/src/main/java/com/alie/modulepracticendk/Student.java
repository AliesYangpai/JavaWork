package com.alie.modulepracticendk;

public class Student {
    private String mName;
    private short mAge;

    public Student() {
    }

    public Student(String mName, short mAge) {
        this.mName = mName;
        this.mAge = mAge;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        System.out.println("=================c++调用我了 mName:"+mName);
        this.mName = mName;
    }

    public short getAge() {
        return mAge;
    }

    public void setAge(short mAge) {
        System.out.println("=================c++调用我了 mAge:"+mAge);
        this.mAge = mAge;
    }

    public static void ShowCommonData() {
        System.out.println("ShowCommonData HI I AM STUDENT");
    }
}
