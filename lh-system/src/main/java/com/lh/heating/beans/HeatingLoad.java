package com.lh.heating.beans;

public class HeatingLoad {

    private double heatingLoad;
    public double getHeatingLoad() {
        return heatingLoad;
    }

    public void setHeatingLoad(double heatingLoad) {
        this.heatingLoad = heatingLoad;
    }



    public HeatingLoad(double heatingLoad){
        this.heatingLoad = heatingLoad;
    }

    @Override
    public String toString() {
        return "HeatingLoad{" +
                "heatingLoad=" + heatingLoad +
                '}';
    }



}
