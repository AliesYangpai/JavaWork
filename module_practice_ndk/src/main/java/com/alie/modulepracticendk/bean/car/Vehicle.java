package com.alie.modulepracticendk.bean.car;

public class Vehicle {
    private String mVehicleName;
    private CarEngine mCarEngine;
    private CarFrame mCarFrame;
    private CarWheel mCarWheel;

    public Vehicle() {
    }

    public Vehicle(String vehicleName) {
        this.mVehicleName = vehicleName;
    }

    public Vehicle(String vehicleName, CarEngine carEngine, CarFrame carFrame, CarWheel carWheel) {
        this.mVehicleName = vehicleName;
        this.mCarEngine = carEngine;
        this.mCarFrame = carFrame;
        this.mCarWheel = carWheel;
    }

    public String getVehicleName() {
        return mVehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.mVehicleName = vehicleName;
    }

    public CarEngine getCarEngine() {
        return mCarEngine;
    }

    public void setCarEngine(CarEngine carEngine) {
        this.mCarEngine = carEngine;
    }

    public CarFrame getCarFrame() {
        return mCarFrame;
    }

    public void setCarFrame(CarFrame carFrame) {
        this.mCarFrame = carFrame;
    }

    public CarWheel getCaWheel() {
        return mCarWheel;
    }

    public void setCaWheel(CarWheel carWheel) {
        this.mCarWheel = carWheel;
    }
}
