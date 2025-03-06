package com.lh.heating.algorithm;

public class Q4PumpSelection {



    //一次侧循环水泵 0
    //二次侧循环水泵 1
    public  double calLift(double type){
        if(type==0.0){
            return 25.0;
        }else {
            return 32.0;
        }

    }


    //用户的输入或者之前计算所得
    public double calFlow(double flow){
        return flow;
    }

}
