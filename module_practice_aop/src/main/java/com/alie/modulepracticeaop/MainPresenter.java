package com.alie.modulepracticeaop;

public class MainPresenter {

    private static final String TAG = "MainPresenter";
    public MainPresenter() {
    }

    public void onBtn1Click() {
        TestConst.showLog("onBtn1Click","");
    }
    public void onBtn2Click(String name) {
        TestConst.showLog("onBtn2Click",name);
    }
    public void onBtn3Click(String name,short age) {
        TestConst.showLog("onBtn3Click",name+age);
    }
    public void onBtn4Click(Person person) {
        TestConst.showLog("onBtn2Click",""+person.hashCode());
    }
}
