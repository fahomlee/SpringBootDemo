package com.example.demo.util;


import java.math.BigDecimal;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @ClassName: IntervalUtil
 */
public class IntervalUtil {

    /**
     * 判断dataValue是否在interval区间范围内
     * 
     * @param dataValue 数值类型的
     * @param interval 正常的数学区间，包括无穷大等，如：(1,3)、>5%、(-∞,6]、(125%,135%)U(70%,80%)
     * @return true：表示dataValue在区间interval范围内，false：表示dataValue不在区间interval范围内
     */
    public boolean isInTheInterval(String dataValue, String interval) {
        // 将区间和dataValue转化为可计算的表达式
        String formula = getFormulaByAllInterval(dataValue, interval, "||");
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            // 计算表达式
            return (Boolean) jse.eval(formula);
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * 将所有阀值区间转化为公式：如 [75,80) =》 dataValue < 80 && dataValue >= 75 (125%,135%)U(70%,80%) =》 (dataValue < 1.35 &&
     * dataValue > 1.25) || (dataValue < 0.8 && dataValue > 0.7)
     * 
     * @param dataValue
     * @param interval 形式如：(125%,135%)U(70%,80%)
     * @param connector 连接符 如：") || ("
     */
    private String getFormulaByAllInterval(String dataValue, String interval, String connector) {
        StringBuffer buff = new StringBuffer();
        for (String limit : interval.split("U")) {// 如：（125%,135%）U (70%,80%)
            buff.append("(").append(getFormulaByInterval(dataValue, limit, " && ")).append(")").append(connector);
        }
        String allLimitInvel = buff.toString();
        int index = allLimitInvel.lastIndexOf(connector);
        allLimitInvel = allLimitInvel.substring(0, index);
        return allLimitInvel;
    }

    /**
     * 将整个阀值区间转化为公式：如 145) =》 dataValue < 145 [75,80) =》 dataValue < 80 && dataValue >= 75
     * 
     * @param dataValue
     * @param interval 形式如：145)、[75,80)
     * @param connector 连接符 如：&&
     */
    private String getFormulaByInterval(String dataValue, String interval, String connector) {
        StringBuffer buff = new StringBuffer();
        for (String halfInterval : interval.split(",")) {// 如：[75,80)、≥80
            buff.append(getFormulaByHalfInterval(halfInterval, dataValue)).append(connector);
        }
        String limitInvel = buff.toString();
        int index = limitInvel.lastIndexOf(connector);
        limitInvel = limitInvel.substring(0, index);
        return limitInvel;
    }

    /**
     * 将半个阀值区间转化为公式：如 145) =》 dataValue < 145 ≥80% =》 dataValue >= 0.8 [130 =》 dataValue >= 130 <80% =》 dataValue <
     * 0.8
     * 
     * @param halfInterval 形式如：145)、≥80%、[130、<80%
     * @param dataValue
     * @return dataValue < 145
     */
    private String getFormulaByHalfInterval(String halfInterval, String dataValue) {
        halfInterval = halfInterval.trim();
        if (halfInterval.contains("∞")) {// 包含无穷大则不需要公式
            return "1 == 1";
        }
        StringBuffer formula = new StringBuffer();
        String data = "";
        String opera = "";
        if (halfInterval.matches("^([<>≤≥\\[\\(]{1}(-?\\d+.?\\d*\\%?))$")) {// 表示判断方向（如>）在前面 如：≥80%
            opera = halfInterval.substring(0, 1);
            data = halfInterval.substring(1);
        } else {// [130、145)
            opera = halfInterval.substring(halfInterval.length() - 1);
            data = halfInterval.substring(0, halfInterval.length() - 1);
        }
        double value = dealPercent(data);
        formula.append(dataValue).append(" ").append(opera).append(" ").append(value);
        String a = formula.toString();
        // 转化特定字符
        return a.replace("[", ">=").replace("(", ">").replace("]", "<=").replace(")", "<").replace("≤", "<=")
                        .replace("≥", ">=");
    }

    /**
     * 去除百分号，转为小数
     * 
     * @param str 可能含百分号的数字
     * @return
     */
    private double dealPercent(String str) {
        double d = 0.0;
        if (str.contains("%")) {
            str = str.substring(0, str.length() - 1);
            d = Double.parseDouble(str) / 100;
        } else {
            d = Double.parseDouble(str);
        }
        return d;
    }

    private static String turnScope(int a, int b, String c, String d) {
        String left = "";
        String right = "";
        if (a == 0) {
            left = "(";
        } else {
            left = "[";
        }
        if (b == 0) {
            right = ")";
        } else {
            right = "]";
        }
        return left + c + "," + d + right;
    }

    private static String getMiddle(String a, String b) {
        BigDecimal c = new BigDecimal(a);
        BigDecimal d = new BigDecimal(b);
        return c.add(d).divide(new BigDecimal(2)).toString();
    }

    // true 3<x<5 4 4<x<5 4.5 false 0<x<=3 0<x<1 false true 0<x<2 1 0<x<1 1 0.5
    //0<= x<25  25=<x <50.1   50<=x<75  75<= x<100
    //0<=x<1   1<=x<2
    public static void main(String[] args) {
        IntervalUtil a = new IntervalUtil();
        System.out.println(turnScope(0, 0, "1", "2"));
        System.out.println(getMiddle("1.111","0.1111"));
        System.out.println(getMiddle("1","2"));
        
        System.out.println(a.isInTheInterval(getMiddle("25","50.1"), turnScope(1, 0, "50", "75")));
        System.out.println(a.isInTheInterval("25", turnScope(1, 0, "50", "75")));        
        System.out.println(a.isInTheInterval("50.1", turnScope(1, 0, "50", "75")));
        System.out.println(a.isInTheInterval(getMiddle("50","75"), turnScope(1, 0, "25", "50.1")));
        System.out.println(a.isInTheInterval("50", turnScope(1, 0, "25", "50.1")));
        System.out.println(a.isInTheInterval("75", turnScope(1, 0, "25", "50.1")));

        
        //判断起始值是否在里面
        //判断终止值是否在里面
        //判断(起始+终止)/2是否在里面
        
        
    }
}
