package com.lh.heating.algorithm;
import com.lh.heating.exceptions.*;

import java.text.DecimalFormat;

public class Q2TotalHeatingFlow {


    /*
     param inputWaterT:供水温度
     param outputWaterT:出水温度
     param heatingSource:热源 0 锅炉 ；1热电联产集中供热 ；2热泵
     param endingType:住宅末端，0：散热器 1：地暖 -1 其他
     param buildingType:建筑类型，同HeatingLoadCalculate的定义
     */
    public static final double hyperParam = 0.86;
    public double totalHeatingFlowCalculate(double heatingLoad,double inputWaterT, double outputWaterT) {
        try{
            checkT(inputWaterT,outputWaterT);

        }catch (TotalHeatingFlowExceptions e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
        DecimalFormat format = new DecimalFormat("#.00");
        String str = format.format(hyperParam*heatingLoad/(inputWaterT-outputWaterT));
        return Double.parseDouble(str);

    }

    private void checkT(double inputWaterT,double outputWaterT)throws TotalHeatingFlowExceptions{
        if(inputWaterT<outputWaterT){
            throw new TotalHeatingFlowExceptions("供水温度应大于出水温度");
        }
    }


    public static void main(String[]args){
        Q2TotalHeatingFlow q2 = new Q2TotalHeatingFlow();
        double flow = q2.totalHeatingFlowCalculate(1260,70,50);
        System.out.println(flow);


    }
}
