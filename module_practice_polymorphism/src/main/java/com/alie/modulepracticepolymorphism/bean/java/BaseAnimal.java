package com.alie.modulepracticepolymorphism.bean.java;

public abstract class BaseAnimal {
    public BaseAnimal() {
    }

    public void showCommonInfo() {
        System.out.println("===BaseAnimal showCommonInfo");
    }

    /**
     * this method is similar to pure virtual method in c++
     */
    public abstract void showAnimalInfo();
}
