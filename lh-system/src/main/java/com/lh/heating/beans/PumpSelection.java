package com.lh.heating.beans;

public class PumpSelection {
    private double lift;
    private double flow;

    public double getLift() {
        return lift;
    }

    public void setLift(double lift) {
        this.lift = lift;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public double getCnt() {
        return cnt;
    }

    public void setCnt(double cnt) {
        this.cnt = cnt;
    }

    private double cnt;

    public PumpSelection(double lift){
        this.lift=lift;
    }
    public PumpSelection(double lift,double flow){
        this.lift=lift;
        this.flow=flow;
    }
}
