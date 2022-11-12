package com.alie.modulepracticepolymorphism.bean.java.test01;

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
