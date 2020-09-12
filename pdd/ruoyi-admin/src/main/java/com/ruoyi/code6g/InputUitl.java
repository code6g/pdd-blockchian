package com.ruoyi.code6g;

//import com.mysql.cj.xdevapi.JsonArray;
//import com.ruoyi.common.json.JSONObject.JsonArray;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import com.ruoyi.common.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import com.ruoyi.common.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputUitl {

    public  static String  query_value(String sql,String fieldname,SqlUtilService sqlUtilService){

        List list=sqlUtilService.list(sql);
        return InputUitl.getListMapValue(list,0,fieldname);
    }


    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    public static String getGuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    public static String get_date_bz() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }
    public static String get_date_02() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }

    public static String get_datetime_bz() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    public static String getTimestamp() {
        //到毫秒
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS-");//+ (new Random()).nextInt(10));

        // 纳秒
        Long nanoTime = System.nanoTime();
        //Long nanoTime2=(nanoTime - nanoTime / 1000000 * 1000000) / 1000;
        String sNanoTime= String.valueOf(nanoTime);
        //String sNa=sNanoTime.substring(sNanoTime.length()-3);
        // String.format("%03d", sNanoTime);
        //String.valueOf(nanoTime2);
        String sAll=formatter.format(new Date()) + sNanoTime;
        return sAll;
        /**
         *
         * 年月日-时分秒-毫秒-纳秒 共
         20191214-210129-908-11176103533500
         20191214-210129-908-11176103621900
         20191214-210129-908-11176103709700
         */
    }

    public static void main(String[] args){
        for(int i=0;i<100;i++){
            String s=InputUitl.getTimestamp();
            System.out.println(s+"  "+s.length()+"位");
        }


    }

    public static String getSelectConIn(String ids) {

        if(ids.equals("")){
            return "'error'";
        }
        String ids2=ids.replaceAll("\\|",",");

        String[] s1=ids2.split(",");
        String s2="";
        for(int i=0;i<s1.length;i++){
            s2+="'"+s1[i]+"',";
        }
        String s3=s2.substring(0,s2.length()-1);
        return s3;
    }

    public static String getListMapValue(List list, int i,String fieldname) {
        if(list==null){return "";}
        if( list.size()<(i+1) ){return "";}
        try {
            Map map = (Map) list.get(i);
            return map.get(fieldname).toString();
        }catch (Exception e2){
            return "";
        }

    }

    public static String  addNOfNumber(String number,int n){
        try{
            int i=Integer.parseInt(number)+n;
            String  s1=String.valueOf(i);
            String s2=StringUtils.leftPad(s1, number.length(), "0");
            return s2;

        }catch (Exception e2){

        }
        return "";

    }


    public static Float  getFloatFromString(String sFloat){
        try{
            Float f1=Float.parseFloat(sFloat);
            return f1;

        }catch (Exception e2){

        }
        return 0.0f;

    }

    public static String getFj_A_href(String htsm) {
        System.out.println(htsm);
        if(htsm.trim().equals("")) {return "";}
        String s0=htsm;
        String s1[]=s0.split(";");

        String s3="";

        for(int i=0;i<s1.length;i++){
            if(s1[i].trim()==""){continue;}
            String s1_i=s1[i];
            String[] s2=s1_i.split("\\|");
            s3+="<a  target='_blank'  style='margin: 2px;' href='"+s2[1]+"'>"+s2[0]+"</a>";
        }
        return s3;
        //$("#divFileShow").html(s3);

    }

    public static String getFj_A_href_sptp(String htsm) {
        System.out.println(htsm);
        if(htsm.trim().equals("")) {return "";}
        String s0=htsm;
        String s1[]=s0.split(";");

        String s3="";

        for(int i=0;i<s1.length;i++){
            if(s1[i].trim()==""){continue;}
            String s1_i=s1[i];
            //String[] s2=s1_i.split("\\|");
            s3+="<a  target='_blank'  style='margin: 2px;' href='/profile/spzp/"+s1_i+"'>"+s1_i+"</a>&nbsp;&nbsp;&nbsp;";
        }
        return s3;
        //$("#divFileShow").html(s3);

    }



    public static JSONObject.JSONArray getJsonArrayFromList(ArrayList<HashMap> list){

        JSONObject.JSONArray jsonArray=new JSONObject.JSONArray();
        JSONObject jsonObject=new JSONObject();
        String s="";
        for (int i = 0; i<list.size(); i++){
            Map map=(Map)list.get(i);
            JSONObject jo = new JSONObject();

            for(int k=0;k<map.keySet().toArray().length;k++){
                String key=map.keySet().toArray()[k].toString();
                String value=map.get(key).toString();
                jo.set(key,value);
            }
//            for(int j=0;j<arrayFieldname.length;j++){
//                String k=arrayFieldname[j];
//                String v=map.get(k).toString();
//                jo.set(k,v);
//            }
            jsonArray.add(jo);
        }
        return jsonArray;

    }





}
