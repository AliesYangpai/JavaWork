package com.alie.modulepracticepolymorphism;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alie.modulepracticepolymorphism.bean.Animal;
import com.alie.modulepracticepolymorphism.bean.Dog;

public class MainActivity extends AppCompatActivity {


    private void printInfo(String info) {
        System.out.println(info);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMethod();
    }

    void executeFun01(Animal animal) {
        animal.eat();
    }

    /**
     * test polymorphism for parent only
     */
    void doTest01() {
        printInfo("===doTest01");
        Animal animal = new Animal();
        executeFun01(animal);
    }

    /**
     * test polymorphism for LSP(里氏原则)
     */
    void doTest02() {
        printInfo("===doTest02");
        Animal animal = new Dog();
        executeFun01(animal);
    }

    void startMethod() {
        //=============start==============
        doTest01();//【polymorphism】param is parent only
        doTest02();//【polymorphism】param is LSP
        /**
         * summarize:
         * 1.java is same to c++ in method pointer address early binding when passing param is LSP
         *   and also late bind
         * 2.java is more flexible in method pointer address early binding,it will invoke same method
         *   from parent and we can still operate child method but not c++
         */
        //=============end==============
    }
}