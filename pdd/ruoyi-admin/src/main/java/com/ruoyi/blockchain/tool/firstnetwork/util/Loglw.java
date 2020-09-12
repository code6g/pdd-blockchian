package com.ruoyi.blockchain.tool.firstnetwork.util;

public class Loglw {
    public static void print(String sLabel, String sValue) {

        System.out.println("");
        System.out.println("-------调试输出：--------------------\n");
        System.out.println("  调试  ==  [ " + sLabel + " ] ");
        System.out.println("  输出  ==  " + sValue + "");
        System.out.println("-----------------------------------");
        System.out.println("");



    }

    public static void print(String sLabel, long sValue) {

        print(sLabel,String.valueOf(sValue));

    }
    public static void print(String sLabel, int sValue) {

        print(sLabel,String.valueOf(sValue));

    }
    public static void print(String sLabel, double sValue) {

        print(sLabel,String.valueOf(sValue));

    }
}