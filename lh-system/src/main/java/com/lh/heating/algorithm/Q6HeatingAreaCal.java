package com.lh.heating.algorithm;

public class Q6HeatingAreaCal {

    /*
      用户输入 建筑面积，供热类型
       heatType供热类型
       0 锅炉房  输入建筑物面积
       1 换热站供热
     */


    public static void main(String[] args) {
        Q6HeatingAreaCal q6 = new Q6HeatingAreaCal();
        double ret = q6.calExchange(60000);
        System.out.println(ret);

        double ret1 = q6.calBiloer(80000);
        System.out.println(ret1);
    }

    //锅炉房调用该方法
    //1122修改
    public double calBiloer(double area) {

        double heatingArea = area * 0.01;
        double temp = heatingArea > 100.0 ? heatingArea : 100.0;
        return temp < 500.0 ? temp : 500.0;
    }

    //换热站调用该方法
    //1122修改
    public double calExchange(double area) {

        if (area <= 50000.0) {
            return 200.0;
        } else {
            if (area > 50000.0 && area <= 100000.0) {
                return 300.0;
            } else {
                return 350.0;
            }
        }//else
    }



}
