package com.alie.modulepracticepolymorphism.bean.java;

public class Caw extends BaseAnimal {
    public Caw() {
    }

    @Override
    public void showCommonInfo() {
        super.showCommonInfo();
    }

    /**
     * this method is similar to pure virtual method in c++
     */
    @Override
    public void showAnimalInfo() {
        System.out.println("===Caw showAnimalInfo");
    }
}
