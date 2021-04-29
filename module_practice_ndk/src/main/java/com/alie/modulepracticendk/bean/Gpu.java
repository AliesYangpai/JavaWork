package com.alie.modulepracticendk.bean;

public class Gpu {
    private static final String TAG = "Cpu";
    private String mName;
    private float mPrice;

    public Gpu() {
    }

    public Gpu(String mName, float mPrice) {
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float mPrice) {
        this.mPrice = mPrice;
    }
}
