package com.alie.modulepracticecommon.work.refletion;

public class Robot {
    private String name;
    private int age;
    protected String type;
    public float price;

    public Robot() {
    }


    public Robot(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Robot(String name, int age, String type, float price) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
