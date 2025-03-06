package com.lh.heating.algorithm;

import java.util.*;
import java.util.stream.Collectors;


public class Q5BoilerSelection {

    /*
    锅炉房选型计算
    大于热负荷
    sum(x,y...)>=totalHeating
    最大一台锅炉检修时，剩余不低于0.7
    sum(x,y....)-max(x,y,...)>=0.7*totalHeating
    热负荷不低于50%
    0.5*x <=totalHeating -sum(y,z...)
    0.5y <= totalHeading -sum(x,z....)
    ....
    热量均分,方差最小
    argmin(var(x,y,z))
     */

    private static final double[] power = new double[]{0.35, 0.7, 1.4, 2.8, 4.2, 5.6, 7.0};


    private static final HashMap<Double, Integer> AirSourcePumpParam = new HashMap<Double, Integer>();
    private static final double[] AirSourcePumpList = new double[]{70.0, 106.0, 135.0};

    static {


        AirSourcePumpParam.put(70.0, 36);
        AirSourcePumpParam.put(106.0, 60);
        AirSourcePumpParam.put(135.0, 70);
    }

    public static void main(String[] args) {
        Q5BoilerSelection q5 = new Q5BoilerSelection();
        List<Integer> ret = q5.Q53calExchangerCnt(1200, 18);
        System.out.println(ret);


    }

    public static List<List<Double>> combination(int n, List<List<Double>> t) {
        int i = 1;
        List<List<Double>> ret = new ArrayList<List<Double>>();
        if (n == 1) {
            return t;
        }
        while (i < n) {
            ret = new ArrayList<List<Double>>();
            for (int j = 0; j < t.size(); j++) {
                List<Double> tt = t.get(j);

                for (double d : power) {
                    List<Double> ttt = new ArrayList<>(tt);
                    ttt.add(new Double(d));
                    ret.add(ttt);
                }

            }
            t = new ArrayList<List<Double>>(ret);

            i++;

        }

        //台数为3台及以上时，输出的锅炉容量类型不得多于两种
        if (n >= 3) {
            ret = ret.stream().filter(x -> new HashSet<>(x).size() <= 2).collect(Collectors.toList());
        }
        return ret;
    }

    public static List<List<Double>> filters(List<List<Double>> t, double totalHeating) {
        List<List<Double>> ret = new ArrayList<List<Double>>();
        List<List<Double>> softRet = new ArrayList<List<Double>>();

        for (List<Double> plan : t) {
            //求和
            double list_sum = plan.stream().mapToDouble(Double::doubleValue).sum();
            //sum
            double list_max = plan.stream().mapToDouble(Double::doubleValue).max().getAsDouble();

            double tt = list_sum - list_max - 0.7 * totalHeating;
            //每台锅炉实际运行负荷率不宜低于50％,
            boolean flag = list_sum * 0.5 >= totalHeating - 1E-8;
//            for(int i=0;i<plan.size();i++){
//                final int filter_indx = i;
//                double sumWithoutCurrent = IntStream.range(0, plan.size())
//                        .filter(j -> j==filter_indx)
//                        .mapToObj(plan::get).mapToDouble(Double::doubleValue).sum();
//                if(totalHeating-0.5*sumWithoutCurrent-0.5*plan.get(i)>=0.0){
//                    flag=true;
//                }else{
//                    flag =false;
//                    break;
//                }
//
//
//            }

            //去掉 tt>=-1E-8
            if (list_sum >= totalHeating - 1E-8) {
                if (flag) {
                    ret.add(plan);
                } else {
                    softRet.add(plan);
                }
            }


        }
        if (ret.size() > 0) {
            return ret;
        } else {
            return softRet;
        }


    }

    public static List<List<Double>> filters2(List<List<Double>> t, double totalHeating) {
        List<List<Double>> ret = new ArrayList<List<Double>>();
        List<List<Double>> softRet = new ArrayList<List<Double>>();

        for (List<Double> plan : t) {
            //求和
            double list_sum = plan.stream().mapToDouble(Double::doubleValue).sum();

            //去掉 tt>=-1E-8
            if (list_sum >= totalHeating - 1E-8) {
                ret.add(plan);
            }


        }
        return ret;


    }

    public static List<Double> argminVar(List<List<Double>> t) {
        List<Double> varList = new ArrayList<Double>();
        for (List<Double> i : t) {
            varList.add(calVar(i));
        }
        int minIndex = varList.indexOf(Collections.min(varList));
        return t.get(minIndex);

    }

    public static double calVar(List<Double> t) {
        double mean = t.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
        double var = t.stream().map(x -> Math.pow(x - mean, 2)).mapToDouble(Double::doubleValue).sum();
        return var;

    }

    //暴力解法 后续可换为线性规划求解
    //结果说明：返回一个list<Double>
    //若返回List<Double>{-1} 表示台数过多异常
    //若返回List<Double>{-2} 表示没有结果异常
    public List<Double> Q51CalBoilerCnt(double totalHeating, int boilerCnt) {
        //totalHeating 输入的是KW
        //转成MW
        totalHeating = totalHeating / 1000.0;
        int k = (int) (totalHeating / 0.35);

        //返回台数过大
        if (0.35 * (k + 2) > totalHeating && 0.35 * k <= totalHeating && boilerCnt > k + 1) {
            List<Double> ret = new ArrayList<>();
            ret.add(-1.0);
            return ret;
        }


        List<List<Double>> plans_temp = new ArrayList<List<Double>>();

        for (int i = 0; i < power.length; i++) {
            List<Double> temp = new ArrayList<Double>();
            temp.add(power[i]);
            plans_temp.add(temp);
        }


        //如果总负荷小于0.35，用户选择的台数大于1，返回台数过多提示
        //总负荷大于0.35*1小于0.35*2  用户选择的台数大于2，返回台数过多提示
        //总负荷大于0.35*2小于 0.35*3 用户选择的台数大于3，返回台数过多提示
        //总负荷大于0.35*3小于 0.35*4 用户选择的台数大于4，返回台数过多提示
        //总负荷大于0.35*4小于 0.35*5 用户选择的台数大于5，返回台数过多提示
        //总负荷大于0.35*5小于 0.35*6 用户选择的台数大于6，返回台数过多提示


        //所有候选
        List<List<Double>> plans = combination(boilerCnt, plans_temp);
//        System.out.println(plans.size());
//
//        for(List<Double>plan:plans){
//            System.out.println(plan);
//        }


        //满足条件的所有候选
//        List<List<Double>> candidatPlans = filters(plans,totalHeating);
        //满足条件的所有候选
        List<List<Double>> candidatPlans = filters2(plans, totalHeating);
        //没有结果
        if (candidatPlans.size() < 1) {
            List<Double> ret = new ArrayList<>();
            ret.add(-2.0);
            return ret;

        }


        List<Double> errorList = new ArrayList<Double>();

        for (List<Double> i : candidatPlans) {
            errorList.add(i.stream().mapToDouble(Double::doubleValue).sum() - totalHeating);
        }
        int minIndex = errorList.indexOf(Collections.min(errorList));
        return candidatPlans.get(minIndex);


//        //方差最小候选
//        System.out.println(candidatPlans.size());
//        List<Double> t = argminVar(candidatPlans);
//        return t;


    }

    /*
    换热器选型计算
     */
    public double Q52calArea(double totalHeating, double FirstInputT, double FirstOutputT, double SecondInputT, double SecondOutputT) {
        double deltaBig = FirstInputT - SecondOutputT;
        double deltaSmall = SecondInputT - FirstOutputT;
        double deltaT = 0.0;
        if (deltaBig / deltaSmall <= 1.7) {
            deltaT = (deltaBig + deltaSmall) / 2;
        } else {
            deltaT = (deltaBig - deltaSmall) / Math.log(deltaBig / deltaSmall);
        }
        System.out.println(deltaT);
        return totalHeating * 1000 / 3500 / deltaT;
    }

    /*
    热泵机组选型计算
    param totalHeating :用户输入的热负荷
    param cnt :用户输入的热泵台数
    返回结果说明：正常返回：List<Integer> length=3 会有三个数字,分别代表36P，60P,70P的台数。至少有一个数是0。展示的时候只展示非0数
    异常放回：List<Integer>{-1}length=-1 只返回-1.表示台数过多，让用户重新输入。
    异常放回：List<Integer>{-2}length=-2 只返回-2.表示台数过少，让用户重新输入。
     */
    //1122修改
    public List<Integer> Q53calExchangerCnt(double totalHeating, int cnt) {
        List<Integer> ret = new ArrayList<Integer>();
        int k = (int) (totalHeating / 70);

        //返回台数过大
        if (70 * (k + 2) > totalHeating && 70 * k <= totalHeating && cnt > k + 1) {
            ret.add(-1);
            return ret;
        }
        List<List<Integer>> candidates = Q53Candidates(cnt);
        List<List<Integer>> candidates2 = new ArrayList<List<Integer>>();
        List<Double> candidateHeating = new ArrayList<Double>();
        //过滤不满足
        for (List<Integer> candidate : candidates) {
            double temp = candidate.get(0) * AirSourcePumpList[0] + candidate.get(1) * AirSourcePumpList[1] + candidate.get(2) * AirSourcePumpList[2];
            if (temp >= totalHeating) {
                candidates2.add(candidate);
                candidateHeating.add(temp);
            }
        }

        if (candidates2.size() < 1) {
            ret.add(-2);
            return ret;
        }

        int minIndex = candidateHeating.indexOf(Collections.min(candidateHeating));
        return candidates2.get(minIndex);


    }

    public List<List<Integer>> Q53Candidates(int cnt) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i <= cnt; i++) {
            for (int j = 0; j <= cnt; j++) {
                for (int k = 0; k <= cnt; k++) {
                    if (i + j + k == cnt && (i == 0 || j == 0 || k == 0)) {
                        List<Integer> list1 = new ArrayList<>();
                        list1.add(i);
                        list1.add(j);
                        list1.add(k);
                        ret.add(list1);
                    }//if

                }//k
            }//j
        }//i

        return ret;
    }


}
