package com.alie.modulepracticendk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Reference to learn ：
 * https://www.jianshu.com/p/4c890e23c021
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    void init() {
        HolderJni holderJni = new HolderJni();
        holderJni.doTest01(); //【ndk 打印】hello world
        holderJni.doTest02(); //【ndk 打印】打印int String Int[]
        holderJni.doTest03(); //【ndk 打印】打印String[]
    }
}