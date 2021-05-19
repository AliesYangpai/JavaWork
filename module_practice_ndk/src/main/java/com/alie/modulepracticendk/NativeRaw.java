package com.alie.modulepracticendk;

import com.alie.modulepracticendk.bean.Computer;
import com.alie.modulepracticendk.bean.Cpu;
import com.alie.modulepracticendk.bean.Gpu;
import com.alie.modulepracticendk.bean.Memory;
import com.alie.modulepracticendk.bean.car.CarEngine;
import com.alie.modulepracticendk.bean.car.CarFrame;
import com.alie.modulepracticendk.bean.car.CarWheel;
import com.alie.modulepracticendk.bean.car.Vehicle;

public class NativeRaw {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    protected native String doHelloWorld();
    protected native void printData(int age);
    protected native void printData(String name);
    protected native void printData(int[] intArray);
    protected native void printData(String[] stringArray);
    protected native void printData(Cpu cpu);
    protected native void printDataObjStaticMethod(Cpu cpu);
    protected native void printDataObjField(Cpu cpu);
    protected native Cpu generateCpu(String name,float price);
    protected native Gpu generateGpu(String name,float price);
    protected native Memory generateMemory(String name,float price);
    protected native Computer generateComputer(String name,Cpu cpu,Gpu gpu,Memory memory);
    protected native void printDataThreadWork(Computer computer);
    protected native void printDataThreadWorkVm(Computer computer);

    protected native CarEngine generateCarEngine(String name);
    protected native CarFrame generateCarFrame(String name);
    protected native CarWheel generateCarWheel(String name);
    protected native Vehicle generateVehicle(String name,CarEngine carEngine,CarFrame carFrame,CarWheel carWheel);
    protected native Vehicle generateVehicle(String name);
}
