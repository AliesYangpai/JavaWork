package com.alie.modulepracticendk.bean.car;

/**
 * 车轮类
 */
public class CarWheel {
    private String mWheelName;

    public CarWheel() {
    }

    public CarWheel(String wheelName) {
        this.mWheelName = wheelName;
    }

    public String getWheelName() {
        return mWheelName;
    }

    public void setWheelName(String wheelName) {
        this.mWheelName = wheelName;
    }
}
