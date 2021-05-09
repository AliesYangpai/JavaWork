package com.alie.modulepracticendk.bean.car;

/**
 * 车架类
 */
public class CarFrame {
    private String mFrameName;

    public CarFrame() {
    }

    public CarFrame(String frameName) {
        this.mFrameName = frameName;
    }

    public String getFrameName() {
        return mFrameName;
    }

    public void setFrameName(String frameName) {
        this.mFrameName = frameName;
    }
}
