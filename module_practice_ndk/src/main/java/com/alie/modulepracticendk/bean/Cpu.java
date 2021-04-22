package com.alie.modulepracticendk.bean;

public class Cpu {
    private static final String TAG = "Cpu";
    private String name;
    private float price;

    public Cpu() {
    }

    public Cpu(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static void showInfo() {
        System.out.println(TAG + "===" + "这是一个顶级的Cpu");
    }
}
