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
    private NativeRawMgr mNativeRawMgr = NativeRawMgr.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }


    void initData() {
        mNativeRawMgr.doHelloWorld();
        mNativeRawMgr.doTest01();//【ndk print】int
        mNativeRawMgr.doTest02();//【ndk print】String
        mNativeRawMgr.doTest03();//【ndk print】int[]
        mNativeRawMgr.doTest04();//【ndk print】String[]
        mNativeRawMgr.doTest05();//【ndk print】Object - Cpu
        mNativeRawMgr.doTest06();//【ndk print】static method
        mNativeRawMgr.doTest07();//【ndk print】Object - Field
        mNativeRawMgr.doTest08();//【ndk create】Object - generate Cpu in ndk
    }
}