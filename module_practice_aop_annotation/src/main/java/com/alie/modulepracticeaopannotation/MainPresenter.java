package com.alie.modulepracticeaopannotation;

import com.alie.modulepracticeaopannotation.aop.InterpolateWork;

public class MainPresenter {

    private static final String TAG = "MainPresenter";
    public MainPresenter() {
    }

    @InterpolateWork("onBtn1Click")
    public void onBtn1Click() {
        TestConst.showLog("onBtn1Click","");
    }

    @InterpolateWork("onBtn2Click")
    public void onBtn2Click(String name) {
        TestConst.showLog("onBtn2Click",name);
    }

    @InterpolateWork("onBtn3Click")
    public void onBtn3Click(String name,short age) {
        TestConst.showLog("onBtn3Click",name+age);
    }

    @InterpolateWork("onBtn4Click")
    public void onBtn4Click(Person person) {
        TestConst.showLog("onBtn4Click",""+person.hashCode());
    }
}
