package com.ruoyi.blockchain.qy3.util;

import java.io.FileWriter;

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
    public  static void write_to_logfile(String val){

        try {
            // FileReader fbbRijiben = new FileReader("c:\\java01\\fbb.txt"); //创建读取文件的对象 fbbRijiben

            FileWriter fbbRijiben = new FileWriter("/root/projava/blockchain-log-lw.txt",true);
            //String s="\n\n *********以上内容就是范冰冰的简历";

            fbbRijiben.write(val+"\n");
            System.out.println("=="+val);
            fbbRijiben.close();
        }catch (Exception e){
            System.out.println(e.toString()); //异常的话，打印出异常的信息；
        }

    }
}