package com.lh.heating.beans;

public class MinMaxTuple {
    private double minValue;
    private double maxValue;

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public MinMaxTuple(double minValue,double maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "MinMaxTuple{" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
