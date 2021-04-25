package com.alie.modulepracticendk.bean;

public class Cpu {
    private static final String TAG = "Cpu";
    private String mName;
    private float mPrice;

    public Cpu() {
    }

    public Cpu(String name, float price) {
        this.mName = name;
        this.mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        this.mPrice = price;
    }

    public static void showInfo() {
        System.out.println(TAG + "===" + "这是一个顶级的Cpu");
    }
}
