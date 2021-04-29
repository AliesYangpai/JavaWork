package com.alie.modulepracticendk.bean;

public class Memory {
    private static final String TAG = "Memory";
    private String mName;
    private float mPrice;

    public Memory() {
    }

    public Memory(String mName, float mPrice) {
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
