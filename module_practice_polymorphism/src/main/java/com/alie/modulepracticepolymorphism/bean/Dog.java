package com.alie.modulepracticepolymorphism.bean;

public class Dog extends Animal {
    public Dog() {
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {
        super.eat();
    }
}
