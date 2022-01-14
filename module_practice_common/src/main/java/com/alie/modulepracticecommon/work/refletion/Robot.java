package com.alie.modulepracticecommon.work.refletion;

public class Robot {
    private String name;
    private int age;
    protected String type;
    public float price;

    public Robot() {
    }

    public Robot(String name, int age, String type, float price) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.price = price;
    }

    public void speak() {
        System.out.println("Robot speakxxxxx");
    }

    public void learn(String param) {
        System.out.println("Robot learn :"+param);
    }

    private void showInfo() {
        System.out.println("Robot showInfo");
    }
}
