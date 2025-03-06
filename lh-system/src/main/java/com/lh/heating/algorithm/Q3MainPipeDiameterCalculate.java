package com.lh.heating.algorithm;

import com.lh.heating.beans.MinMaxTuple;

import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Q3MainPipeDiameterCalculate {




    /*
     需要用户输入的参数有1.输入温度，2.输出温度，3.管材类型，管材类型会对应一个管壁粗糙度。这里没有管材类型，直接输入管壁粗糙度即可。4.是否是主干管管径是的话1，不是的话0
     计算逻辑：首先计算供热系统设计流量，Q2TotalHeatingFlow.java中可计算，这里直接作为一个输入。
             然后，计算管摩比，判断管摩比是否符合要求，符合要求则输出管道内径，不符合则返回-1.0

     param totalHeatingFlow:供热系统设计流量

     */


    public static final double hyperParam = 594.5;
    public static final double hyperParam2 = 1500.0;
    public static final double hyperParamFriction = 1E-6;

    private static final HashMap<Integer,double[]>frictionResistanceDemandMap = new HashMap<Integer, double[]>();
    double[] typ0Param = new double[]{60,40};
    static {
        frictionResistanceDemandMap.put(0, new double[]{ 30.0,50.0});
        frictionResistanceDemandMap.put(1, new double[]{ 60.0,100.0});
    }

    private static final double[] PipeDiameter =  new double[]{25.0,32.0,40.0,50.0,65.0,80.0,100.0,125.0,150.0,175.0,200.0,225.0,250.0,300.0,350.0};
    private static final int  DNLen = PipeDiameter.length;

    public double maincPipeDiameterCalculate(double totalHeatingFlow) {
        return hyperParam * Math.sqrt(totalHeatingFlow / hyperParam2);
    }


    public double frictionResistanceCalculate( double totalHeatingFlow, double pipeInnerDiameter) {
        return hyperParamFriction * totalHeatingFlow * totalHeatingFlow / Math.pow(pipeInnerDiameter,5.25);
    }


    /*
     param totalHeatingFlow:热流量
     returen MinMaxTuple 第一个是DN，第二个是比摩阻
     */
    public List<MinMaxTuple>  calculate(double totalHeatingFlow)throws Exception{
        List<MinMaxTuple> candidates = new ArrayList<MinMaxTuple>();


        double pipeD = maincPipeDiameterCalculate(totalHeatingFlow);


        if(pipeD>PipeDiameter[DNLen-1]){
            System.out.println("热量过大");
            throw new Exception("热量过大");
        }


        //计算最小直径
        int i;
        for( i = 0;i<DNLen;i++){
            if(pipeD<PipeDiameter[i]){
                break;
            }
        }

        while(i<DNLen){
            //公称直接DN 单位是mm 转化为m，计算比摩阻单位是m
            double pipeDia = PipeDiameter[i]/1000.0;
            //比摩阻
            double friction = frictionResistanceCalculate(totalHeatingFlow,pipeDia);

            candidates.add(new MinMaxTuple(pipeDia,friction));

//            if(pipeDia>=0.25 &&pipeDia<=0.35){
//                if(friction>=frictionResistanceDemandMap.get(0)[0] && friction<=frictionResistanceDemandMap.get(0)[1]){
//                    //符合条件，加入到结果集
//                    ret.add(new MinMaxTuple(pipeDia,friction));
//                }
//
//            }
//            if(pipeDia>=0.025 &&pipeDia<0.25){
//                if(friction>=frictionResistanceDemandMap.get(1)[0] && friction<=frictionResistanceDemandMap.get(1)[1]){
//                    //符合条件，加入到结果集
//                    ret.add(new MinMaxTuple(pipeDia,friction));
//                }
//
//
//            }
            i = i+1;


        }
        if(candidates.size()<1){
            System.out.println(" 不满足");
            System.exit(0);
        }
        List<MinMaxTuple> ret = new ArrayList<MinMaxTuple>();
        //计算是否有满足硬性条件的
        for(MinMaxTuple t :candidates){
            double pipeDia = t.getMinValue();
            double friction = t.getMaxValue();

            if(pipeDia>=0.25 &&pipeDia<=0.35){
                if(friction>=frictionResistanceDemandMap.get(0)[0] && friction<=frictionResistanceDemandMap.get(0)[1]){
                    //符合条件，加入到结果集
                    ret.add(t);
                }

            }
            if(pipeDia>=0.025 &&pipeDia<0.25){
                if(friction>=frictionResistanceDemandMap.get(1)[0] && friction<=frictionResistanceDemandMap.get(1)[1]){
                    //符合条件，加入到结果集
                    ret.add(t);
                }


            }

        }
        if(ret.size()>0){
            return ret;
        }else{
            //没有满足硬性条件的，取R值更接近范围值对应的管径
            List<Double> errors = new ArrayList<Double>();
            for(MinMaxTuple t:candidates){
                double pipeDia = t.getMinValue();
                double friction = t.getMaxValue();

                if(pipeDia>=0.25 &&pipeDia<=0.35){
                    if(friction<frictionResistanceDemandMap.get(0)[0]){
                        errors.add(frictionResistanceDemandMap.get(0)[0]-friction);

                    }else{
                        errors.add(friction - frictionResistanceDemandMap.get(0)[1]);
                    }

                }else{
                    if(friction<frictionResistanceDemandMap.get(1)[0]){
                        errors.add(frictionResistanceDemandMap.get(1)[0]-friction);
                    }else{
                        errors.add(friction - frictionResistanceDemandMap.get(1)[1]);
                    }

                }

            }


            int minIndex = errors.indexOf(Collections.min(errors));
            ret.add(candidates.get(minIndex));
            return ret;

        }



    }


    public static void main(String[]args)throws Exception{
        Q3MainPipeDiameterCalculate q3 =new Q3MainPipeDiameterCalculate();
        List<MinMaxTuple> r = q3.calculate(51.6);
        for(MinMaxTuple t:r){
            System.out.println(t.toString());
        }


    }


}














