package com.alie.modulepracticendk.bean.car;

/**
 * 引擎类
 */
public class CarEngine {
    private String mEngineName;

    public CarEngine() {
    }

    public CarEngine(String engineName) {
        this.mEngineName = engineName;
    }

    public String getEngineName() {
        return mEngineName;
    }

    public void setEngineName(String engineName) {
        this.mEngineName = engineName;
    }
}
