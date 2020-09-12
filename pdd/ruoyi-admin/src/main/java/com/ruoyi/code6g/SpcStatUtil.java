package com.ruoyi.code6g;

import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.commons.lang.math.Range;
import org.apache.commons.lang3.StringUtils;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.remoting.rmi.RmiBasedExporter;

import java.util.ArrayList;
import java.util.HashMap;




/*
 * @description:简单的数据统计分析commons-math3
 * */


// SPC的统计工具类
public class SpcStatUtil {

    public static void main(String[] args){

        double[] values = new double[] { 0.33, 1.33,0.27333, 0.3, 0.501,
                0.444, 0.44, 0.34496, 0.33,0.3, 0.292, 0.667 };

        double[] values2 = new double[] { 0.89, 1.51,0.37999, 0.4, 0.701,
                0.484, 0.54, 0.56496, 0.43,0.3, 0.392, 0.567 };

        //计数
        System.out.println("计算样本个数为：" +values.length);
        //mean--算数平均数
        System.out.println("平均数：" + StatUtils.mean(values));
        //sum--和
        System.out.println("所有数据相加结果为：" + StatUtils.sum(values));
        //max--最小值
        System.out.println("最小值：" + StatUtils.min(values));
        //max--最大值
        System.out.println("最大值：" + StatUtils.max(values));
        //范围
        System.out.println("范围是：" + (StatUtils.max(values)-StatUtils.min(values)));

        //标准差
        StandardDeviation standardDeviation =new StandardDeviation();
        System.out.println("一组数据的标准差为：" + standardDeviation.evaluate(values));

        //variance--方差
        System.out.println("一组数据的方差为：" + StatUtils.variance(values));
        //median--中位数
        Median median= new Median();
        System.out.println("中位数：" + median.evaluate(values));
        //mode--众数
        double[] res = StatUtils.mode(values);
        System.out.println("众数：" + res[0]+","+res[1]);
        for(int i = 0;i<res.length;i++){
            System.out.println("第"+(i+1)+"个众数为："+res[i]);
        }
        //geometricMean--几何平均数
        System.out.println("几何平均数为：" +StatUtils.geometricMean(values));
        //meanDifference-- 平均差，平均概率偏差
        System.out.println("平均差为："+StatUtils.meanDifference(values, values2));
        //normalize--标准化
        double[] norm = StatUtils.normalize(values2);

        for(int i = 0;i<res.length;i++){
            System.out.println("第"+(i+1)+"个数据标准化结果为：" + norm[i]);
        }
        //percentile--百分位数
        System.out.println("从小到大排序后位于80%位置的数：" + StatUtils.percentile(values, 70.0));
        //populationVariance--总体方差
        System.out.println("总体方差为：" + StatUtils.populationVariance(values));
        //product--乘积
        System.out.println("所有数据相乘结果为：" + StatUtils.product(values));
        //sumDifference--和差
        System.out.println("两样本数据的和差为：" + StatUtils.sumDifference(values,values2));
        //sumLog--对数求和
        System.out.println("一组数据的对数求和为：" + StatUtils.sumLog(values));
        //sumSq--计算一组数值的平方和
        System.out.println("一组数据的平方和：" + StatUtils.sumSq(values));
        //varianceDifference --方差差异性。
        System.out.println("一组数据的方差差异性为：" + StatUtils.varianceDifference(values,values2,StatUtils.meanDifference(values, values2)));
    }


    //根据多样本二维数据组，得到平均值数据的一维数据组
    /*
       [
       {1,2,3},
       {1,2,3},
       {1,2,3},
       {1,2,3}
       ]
       得到：
       {2,2,2,2}
     */


    //把map转为数组
    public static double[] get_array_from_map(HashMap map){
        double[] s1=new double[5];
        s1[0]=Double.parseDouble(map.get("ybh1").toString());
        s1[1]=Double.parseDouble(map.get("ybh2").toString());
        s1[2]=Double.parseDouble(map.get("ybh3").toString());
        s1[3]=Double.parseDouble(map.get("ybh4").toString());
        s1[4]=Double.parseDouble(map.get("ybh5").toString());
        return s1;
    }

    //把list的某一列转为数组
    public static String[] get_array_from_list(ArrayList<HashMap> list,String fieldname,String weishu){
        String[] s1=new String[list.size()];
        for(int i=0;i<list.size();i++){
            String val=InputUitl.getListMapValue(list,i,fieldname);
            String val2=get_str_weishu(val,weishu);
            s1[i]=val2;
        }

        return s1;
    }
    //得到多个样本的矩阵的平均值的一维数组。
    public static double[] get_array_avg_from_listmap(ArrayList<HashMap> list){
        int count=list.size();
        double[] d=new double[count];

        for(int i=0;i<count;i++){
            HashMap map=(HashMap)list.get(i);
            double[] s1=get_array_from_map(map);
            double m_avg=StatUtils.mean(s1); //计算平均值
            d[i]=m_avg;
        }
        return d;

    }


    //平均值
    public static double  get_avg_from_listmap(ArrayList<HashMap> list) {
         double[] d=get_array_avg_from_listmap(list);

         return StatUtils.mean(d);
    }

    //获得double
    public static double str2double(String s){
        try {
            return Double.parseDouble(s);
        }catch (Exception e){
            return 0.0;
        }
    }


    //取得list某个字段的平均值
    public static String  get_avg_from_listmap(ArrayList<HashMap> list,String fieldname,String weishu) {
        String[] s1 =get_array_from_list(list,  fieldname,  weishu);
        double[] d  =array_str_to_array_double_weishu(s1,weishu);
        double d2= StatUtils.mean(d);
        return  get_str_weishu(String.valueOf(d2),weishu);
    }



    public static String[]  array_double_to_array_str_weishu(double[] d,String weishu) {
        String[] s1=new String[d.length];
        for(int i=0;i<d.length;i++){
            s1[i]=get_str_weishu(String.valueOf(d[i]),weishu);
        }
        return s1;
    }

    public static String[]  array_double_to_array_str(double[] d) {
        String[] s1=new String[d.length];
        for(int i=0;i<d.length;i++){
            s1[i]=String.valueOf(d[i]);
        }
        return s1;
    }


    public static double[]  array_str_to_array_double_weishu(String[] s,String weishu) {
        double[] d1=new double[s.length];
        for(int i=0;i<s.length;i++){
            d1[i]= str2double(s[i]);
        }
        return d1;
    }

    public static double[]  array_str_to_double_str(String[] s) {
        double[] d1=new double[s.length];
        for(int i=0;i<s.length;i++){
            d1[i]= str2double(s[i]);
        }
        return d1;
    }

        //标准差
    public static double get_xigema_avg_from_listmap(ArrayList<HashMap> list) {
        double[] d=get_array_avg_from_listmap(list);
        //标准差
        StandardDeviation standardDeviation =new StandardDeviation();
        System.out.println("一组数据的标准差为：" + standardDeviation.evaluate(d));
        double xigema=standardDeviation.evaluate(d);
        return xigema;
    }

    //获取一维数组的标准差
    public static double get_xigema_from_array(double[] d) {
         //标准差
        StandardDeviation standardDeviation =new StandardDeviation();
        //System.out.println("一组数据的标准差为：" + standardDeviation.evaluate(d));
        double xigema=standardDeviation.evaluate(d);
        return xigema;
    }


    //获取CL_UCL_LCL 组数
    public static double[] get_LCL_CL_UCL(ArrayList<HashMap> list){
        double CL=get_avg_from_listmap(list);
        double xigema=get_xigema_avg_from_listmap(list);

        double UCL=CL+3*xigema;
        double LCL=CL-3*xigema;

        double[] d3={LCL,CL,UCL};
        return d3;
    }







    //为了 统计 XbarR图，XbarS 图，根据样本批次编码，每组样本数，处理数据库的样本数据，更新下列数据，
    //ypjz	平均值    //yjcz	极差值    //ybzc	标准差    //yzdz	最大值    //yzxz	最小值
    public static   void update_ybsj_for_stat_XbarR(String aybpc, String aybsl, String weishu, SqlUtilService sqlUtilService){
        //20200626-151015-632-21255828928500
        String sql1="update spcybsj set "
                +" yzxz=ROUND(least(ybh1,ybh2,ybh3,ybh4,ybh5),"+weishu+"), "
                +" yzdz=ROUND(greatest(ybh1,ybh2,ybh3,ybh4,ybh5),"+weishu+"), "
                +" ypjz=ROUND((ybh1+ybh2+ybh3+ybh4+ybh5)/5,"+weishu+"), "
                +" yjcz=ROUND(greatest(ybh1,ybh2,ybh3,ybh4,ybh5)-least(ybh1,ybh2,ybh3,ybh4,ybh5),"+weishu+"), "
                +" ybzc='' "
                +" where ybpc='"+aybpc+"'  ";
        sqlUtilService.update(sql1);
        String sql2="select * from spcybsj where ybpc='"+aybpc+"' order by sjxh";

        //更新每组样本的标准差（西格玛）值
        ArrayList<HashMap> list= sqlUtilService.listArrayList(sql2);
        int count=list.size();
        for(int i=0;i<count;i++){
            String  sjid=InputUitl.getListMapValue(list,i,"sjid");
            HashMap map=(HashMap)list.get(i);
            double[] s1= SpcStatUtil.get_array_from_map(map);
            double xigema= SpcStatUtil.get_xigema_from_array(s1);
            String sql3="update spcybsj set "
                    +" ybzc=ROUND('"+xigema+"',"+weishu+") "
                    +" where sjid='"+sjid+"'  ";
            sqlUtilService.update(sql3);
        }
    }




    //为了 统计 XRS 图，根据样本批次编码，每组样本数，处理数据库的样本数据，更新下列数据，
    //ypjz	平均值    //yjcz	极差值    //ybzc	标准差    //yzdz	最大值    //yzxz	最小值
    public static  void update_ybsj_for_stat_XRS(String aybpc, String aybsl, String weishu, SqlUtilService sqlUtilService){
        //20200626-151015-632-21255828928500
        String sql1="update spcybsj set "
                +" yzxz=ROUND(ybh1,"+weishu+"), "
                +" yzdz=ROUND(ybh1,"+weishu+"), "
                +" ypjz=ROUND(ybh1,"+weishu+"), "
                +" yjcz='', "    //yjcz	极差值
                +" ybzc='' "     //ybzc	标准差
                +" where ybpc='"+aybpc+"'  ";
        System.out.println("==== sql = "+sql1);
        sqlUtilService.update(sql1);
        String sql2="select * from spcybsj where ybpc='"+aybpc+"' order by (sjxh+0)";

        //更新每组样本的 R值 ,即极差值，用前样本与前一个样本差值的绝对值
        ArrayList<HashMap> list= sqlUtilService.listArrayList(sql2);
        int count=list.size();
        String X_per="0.0";
        String X_cur="0.0";

        for(int i=0;i<count;i++){
            String  sjid=InputUitl.getListMapValue(list,i,"sjid");
            HashMap map=(HashMap)list.get(i);

            X_cur=InputUitl.getListMapValue(list,i,"ybh1");

            String Rm=jian_weishu(X_cur,X_per,weishu);
            Rm=abs(Rm);

            String sql3="update spcybsj set "
                    +" yjcz='"+Rm+"' "
                    +" where sjid='"+sjid+"'  ";
            sqlUtilService.update(sql3);
            X_per=X_cur;

        }
    }




    //保留字符串的x小数
    public static String get_str_weishu(String s,String weishu){
        //不四舍五入
//        double d = getDouble(s);// 114.145;
//        int i_weishu=Integer.parseInt(weishu);
//        String pattern="#.";
//        for(int i=0;i<i_weishu;i++){
//            pattern+="0";
//        }
//        DecimalFormat df = new DecimalFormat("#.00");
//        String str = df.format(d);
//        return str;

        //四舍五入
        double d =  getDouble(s);
        return String.format("%."+weishu+"f", d);
    }

    //字符串转化为double相加
    public static String jia(String a,String b) {
        double d=getDouble(a)+getDouble(b);
        return String.valueOf(d);
    }
    //字符串转化为double相减
    public static String jian(String a,String b) {
        double d=getDouble(a)-getDouble(b);
        return String.valueOf(d);
    }
    //字符串转化为double相乘
    public static String cheng(String a,String b) {
        double d=getDouble(a)*getDouble(b);
        return String.valueOf(d);
    }
    //字符串转化为double相除
    public static String chu(String a,String b) {
        double d=getDouble(a)/getDouble(b);
        return String.valueOf(d);
    }



    //字符串转化为double相加，保留x位小数
    public static String jia_weishu(String a,String b,String weishu) {
        String s=jia(a,b);
        return get_str_weishu(s,weishu);
    }
    //字符串转化为double相减，保留x位小数
    public static String jian_weishu(String a,String b,String weishu) {
        String s=jian(a,b);
        return get_str_weishu(s,weishu);
    }
    //字符串转化为double相乘，保留x位小数
    public static String cheng_weishu(String a,String b,String weishu) {
        String s=cheng(a,b);
        return get_str_weishu(s,weishu);
    }
    //字符串转化为double相除，保留x位小数
    public static String chu_weishu(String a,String b,String weishu) {
        String s=chu(a,b);
        return get_str_weishu(s,weishu);
    }

    //字符串小数取绝对值,并截取位数
    public static String abs_weishu(String a,String weishu) {
        String s=a.replaceAll("-","");
        return get_str_weishu(s,weishu);
    }

    //字符串小数取绝对值
    public static String abs(String a) {
        String s=a.replaceAll("-","");
        return s;
    }

    public static double  getDouble(String s){
        try{
            double f1=Double.parseDouble(s);
            return f1;
        }catch (Exception e2){
        }
        return 0.0;
    }





















}