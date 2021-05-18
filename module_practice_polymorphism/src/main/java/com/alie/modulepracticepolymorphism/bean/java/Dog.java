package com.alie.modulepracticepolymorphism.bean.java;

public class Dog extends Animal {
    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("===Dog eat");
    }
}
