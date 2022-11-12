package com.alie.modulepracticepolymorphism;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alie.modulepracticepolymorphism.bean.java.test01.Animal;
import com.alie.modulepracticepolymorphism.bean.java.test01.BaseAnimal;
import com.alie.modulepracticepolymorphism.bean.java.test01.Caw;
import com.alie.modulepracticepolymorphism.bean.java.test01.Dog;
import com.alie.modulepracticepolymorphism.bean.java.test02.Container;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private void printInfo(String info) {
        System.out.println(info);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startMethod();
        initView();
    }

    void initView (){
        findViewById(R.id.btn1).setOnClickListener(v->{
            startActivity(new Intent(this,SecondActivity.class));
        });
    }

    void executeFun01(Animal animal) {
        animal.eat();
    }

    void executeFun02(BaseAnimal baseAnimal) {
        baseAnimal.showCommonInfo();
        baseAnimal.showAnimalInfo();
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

    /**
     * test polymorphism for LSP in abstract method
     */
    void doTest03() {
        printInfo("===doTest03");
        BaseAnimal baseAnimal = new Caw();
        executeFun02(baseAnimal);
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
         *   from parent and we can still operate child method but not c++ 【this is different】
         */
        //=============end==============
        doTest03();//【polymorphism】polymorphism for LSP in abstract method
        doTest04();
    }

    /**
     * PECS--PE
     */
    void doTest04() {
//        Container<Object> container = new Container<String>(); // 编译异常，因为没有给泛型指定协变
//        Container<? extends Object> container = new Container<String>(); // 编译正常，给泛型指定了协变
//        container.setItem("xx");// 编译异常，编译器仅仅知道 <? extends Object> 是 Object的一个子类，但是并不知道是哪一个
//       Object data = container.getItem();   // 编译正常，因此对于泛型上限 是生产者，仅仅能读取元素
    }

    /**
     * PECS--CS
     */
    void doTest05() {
//        Container<String> container = new Container<Object>();// 编译异常，因为没有给泛型进行逆变
//        Container<? super String> container = new Container<String>(); // 编译正常，此处进行了逆变
//        container.setItem("name"); // 编译正常，因为这里是将String的值 赋值给了 <? super String>
//        String data = container.getItem(); // 编译异常，左边<右边
    }
}