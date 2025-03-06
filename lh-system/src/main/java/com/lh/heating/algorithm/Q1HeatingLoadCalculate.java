package com.lh.heating.algorithm;

import java.util.HashMap;

public class Q1HeatingLoadCalculate {


    /*
     param buildingType:建筑物类型
     param buildingArea:建筑物面积
     param save :是否有节能措施
     return: 热负荷
     */
    public static final HashMap<Integer, double[]> HeadingLoadIndicatorHyperParam = new HashMap<Integer,double[]>();
    static {
        double[] typ0Param = new double[]{60,40};
        double[] typ1Param = new double[]{70,60};
        double[] typ2Param = new double[]{73,60};
        double[] typ3Param = new double[]{65,55};
        double[] typ4Param = new double[]{73,60};
        double[] typ5Param = new double[]{120,110};


        HeadingLoadIndicatorHyperParam.put(0,typ0Param);
        HeadingLoadIndicatorHyperParam.put(1,typ1Param);
        HeadingLoadIndicatorHyperParam.put(2,typ2Param);
        HeadingLoadIndicatorHyperParam.put(3,typ3Param);
        HeadingLoadIndicatorHyperParam.put(4,typ4Param);
        HeadingLoadIndicatorHyperParam.put(5,typ5Param);
    }

    public double heatingLoadCalculate(int buildingType,double buildingArea,int save){
        if(save==0){
            switch (buildingType){
                case 0:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(0)[0],buildingArea);
                    return heatingload;
                }
                case 1:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(1)[0],buildingArea);
                    return heatingload;

                }
                case 2:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(2)[0],buildingArea);
                    return heatingload;

                }
                case 3:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(3)[0],buildingArea);
                    return heatingload;
                }
                case 4:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(4)[0],buildingArea);
                     return heatingload;
                }
                case 5:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(5)[0],buildingArea);
                     return heatingload;
                }

                default:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(0)[0],buildingArea);
                     return heatingload;

                }

            }//switch



        }else {
            switch (buildingType){
                case 0:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(0)[1],buildingArea);
                     return heatingload;
                }
                case 1:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(1)[1],buildingArea);
                     return heatingload;
                }
                case 2:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(2)[1],buildingArea);
                     return heatingload;
                }
                case 3:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(3)[1],buildingArea);
                     return heatingload;
                }
                case 4:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(4)[1],buildingArea);
                     return heatingload;
                }
                case 5:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(5)[1],buildingArea);
                     return heatingload;
                }
                default:{
                    double heatingload = calulate(HeadingLoadIndicatorHyperParam.get(0)[1],buildingArea);
                     return heatingload;

                }

            }

        }


    }

    private double calulate(double hyperParam,double buildingArea){
        return buildingArea*hyperParam/1000.0;
    }




    public static void main(String[]args){
        Q1HeatingLoadCalculate hlc= new Q1HeatingLoadCalculate();
        double hl = hlc.heatingLoadCalculate(1,20000,1);
        System.out.print(hl);


    }
}
