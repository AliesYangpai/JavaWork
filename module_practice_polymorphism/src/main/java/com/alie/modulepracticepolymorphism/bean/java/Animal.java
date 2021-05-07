package com.alie.modulepracticepolymorphism.bean.java;

public class Animal {
    private String mName;

    public Animal() {
    }

    public Animal(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void eat() {
        System.out.println("===Animal eat");
    }

}
