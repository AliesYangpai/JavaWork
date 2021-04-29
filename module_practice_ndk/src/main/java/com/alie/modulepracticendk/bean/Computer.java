package com.alie.modulepracticendk.bean;

public class Computer {
    private String mName;
    private Cpu mCpu;
    private Gpu mGpu;
    private Memory mMemory;

    public Computer() {
    }

    public Computer(String mName, Cpu mCpu, Gpu mGpu, Memory mMemory) {
        this.mName = mName;
        this.mCpu = mCpu;
        this.mGpu = mGpu;
        this.mMemory = mMemory;
    }



    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Cpu getCpu() {
        return mCpu;
    }

    public void setCpu(Cpu mCpu) {
        this.mCpu = mCpu;
    }

    public Gpu getGpu() {
        return mGpu;
    }

    public void setGpu(Gpu mGpu) {
        this.mGpu = mGpu;
    }

    public Memory getMemory() {
        return mMemory;
    }

    public void setMemory(Memory mMemory) {
        this.mMemory = mMemory;
    }
}
