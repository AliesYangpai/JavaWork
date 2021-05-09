package com.alie.modulepracticendk;

import com.alie.modulepracticendk.bean.Computer;
import com.alie.modulepracticendk.bean.Cpu;
import com.alie.modulepracticendk.bean.Gpu;
import com.alie.modulepracticendk.bean.Memory;
import com.alie.modulepracticendk.bean.car.CarEngine;
import com.alie.modulepracticendk.bean.car.CarFrame;
import com.alie.modulepracticendk.bean.car.CarWheel;
import com.alie.modulepracticendk.bean.car.Vehicle;

public class NativeRawMgr {

    private static final String TAG = "NativeRawMgr";
    private NativeRaw mNativeRaw;

    public NativeRawMgr() {
        mNativeRaw = new NativeRaw();
    }

    private static class NativeRawMgrHolder {
        private static NativeRawMgr NATIVE_RAW_MGR = new NativeRawMgr();
    }

    public static NativeRawMgr getInstance() {
        return NativeRawMgrHolder.NATIVE_RAW_MGR;
    }

    void showLog(String text) {
        System.out.println(TAG + "===" + text);
    }

    void doHelloWorld() {
        showLog("doHelloWorld");
        showLog(mNativeRaw.doHelloWorld());
    }

    void doTest01() {
        showLog("doTest01");
        mNativeRaw.printData(12);
    }

    void doTest02() {
        showLog("doTest02");
        mNativeRaw.printData("HI MY FRIENDS");
    }

    void doTest03() {
        showLog("doTest03");
        int[] array = {5, 66, 777, 888, 999};
        mNativeRaw.printData(array);
    }

    void doTest04() {
        showLog("doTest04");
        String[] array = {"Benz,Bmw,Porsche,Byd"};
        mNativeRaw.printData(array);
    }

    void doTest05() {
        showLog("doTest05");
        mNativeRaw.printData(new Cpu("Alienware", 999F));
    }

    void doTest06() {
        showLog("doTest06");
        mNativeRaw.printDataObjStaticMethod(new Cpu("Alienware", 999F));
    }

    void doTest07() {
        showLog("doTest07");
        Cpu cpu = new Cpu("Alienware", 888F);
        mNativeRaw.printDataObjField(cpu);
        showLog("doTest07" + " price:" + cpu.getPrice() + " name:" + cpu.getName());
    }

    void doTest08() {
        showLog("doTest08");
        Cpu cpu = mNativeRaw.generateCpu("明基", 456F);
        if (cpu != null) {
            showLog(" generateCpu: name:" + cpu.getName() + " price:" + cpu.getPrice());
        }
    }

    void doTest09() {
        showLog("doTest09");
        Cpu cpu = mNativeRaw.generateCpu("Dell", 111F);
        Gpu gpu = mNativeRaw.generateGpu("lenovo", 222F);
        Memory memory = mNativeRaw.generateMemory("Alienware", 999F);
        Computer computer = mNativeRaw.generateComputer("Alie_Computer", cpu, gpu, memory);
        showLog(computer.getName() + " "
                + computer.getCpu().getName() + " "
                + computer.getGpu().getName() + " "
                + computer.getMemory().getName());
    }

    void doTest10Thread() {
        showLog("doTest10Thread");
        Cpu cpu = mNativeRaw.generateCpu("Dell", 111F);
        Gpu gpu = mNativeRaw.generateGpu("lenovo", 222F);
        Memory memory = mNativeRaw.generateMemory("Alienware", 999F);
        Computer computer = mNativeRaw.generateComputer("Alie_Computer", cpu, gpu, memory);
        mNativeRaw.printDataThreadWork(computer);
    }

    void doTest11Thread() {
        showLog("doTest11Thread");
        Cpu cpu = mNativeRaw.generateCpu("Dell", 111F);
        Gpu gpu = mNativeRaw.generateGpu("lenovo", 222F);
        Memory memory = mNativeRaw.generateMemory("Alienware", 999F);
        Computer computer = mNativeRaw.generateComputer("Alie_Computer", cpu, gpu, memory);
        mNativeRaw.printDataThreadWorkVm(computer);
    }

    void doTest12() {
        showLog("doTest12");
        CarEngine carEngine = mNativeRaw.generateCarEngine("V8");
        CarFrame carFrame = mNativeRaw.generateCarFrame("Ferrari Rafa -001");
        CarWheel carWheel = mNativeRaw.generateCarWheel("Bridgestone");
        Vehicle vehicle = mNativeRaw.generateVehicle("Ferrari Rafa", carEngine, carFrame, carWheel);
        showLog(vehicle.getVehicleName() + " "
                + vehicle.getCarEngine().getEngineName() + " "
                + vehicle.getCarFrame().getFrameName() + " "
                + vehicle.getCaWheel().getWheelName());
    }
}

