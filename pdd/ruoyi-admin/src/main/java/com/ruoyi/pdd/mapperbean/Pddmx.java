package com.ruoyi.pdd.mapperbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单明细bean: pddmx
 * 
 * @author 牛哥 
 * @date 2019-12-16 
 */
public class Pddmx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

            @Excel(name = "明细编码")
            private String mxid;        //  明细编码
            @Excel(name = "订单编码")
            private String ddid;        //  订单编码
            @Excel(name = "订单编号")
            private String ddbh;        //  订单编号
            @Excel(name = "会员编码")
            private String hyid;        //  会员编码
            @Excel(name = "商品编码")
            private String spid;        //  商品编码
            @Excel(name = "商品名称")
            private String spmc;        //  商品名称
            @Excel(name = "商品数量")
            private String spsl;        //  商品数量
            @Excel(name = "商品价格")
            private String spjg;        //  商品价格
            @Excel(name = "价格小计")
            private String jgxj;        //  价格小计
            @Excel(name = "明细说明")
            private String mxsm;        //  明细说明
            @Excel(name = "备注项00")
            private String bz00;        //  备注项00
            @Excel(name = "备注项01")
            private String bz01;        //  备注项01
            @Excel(name = "备注项02")
            private String bz02;        //  备注项02
            @Excel(name = "备注项03")
            private String bz03;        //  备注项03
            @Excel(name = "备注项04")
            private String bz04;        //  备注项04
            @Excel(name = "备注项05")
            private String bz05;        //  备注项05
            @Excel(name = "备注项06")
            private String bz06;        //  备注项06
            @Excel(name = "备注项07")
            private String bz07;        //  备注项07
            @Excel(name = "备注项08")
            private String bz08;        //  备注项08
            @Excel(name = "备注项09")
            private String bz09;        //  备注项09
            @Excel(name = "备注项10")
            private String bz10;        //  备注项10
            @Excel(name = "备注项11")
            private String bz11;        //  备注项11
            @Excel(name = "备注项12")
            private String bz12;        //  备注项12
            @Excel(name = "备注项13")
            private String bz13;        //  备注项13
            @Excel(name = "备注项14")
            private String bz14;        //  备注项14
            @Excel(name = "备注项15")
            private String bz15;        //  备注项15
            @Excel(name = "备注项16")
            private String bz16;        //  备注项16
            @Excel(name = "备注项17")
            private String bz17;        //  备注项17
            @Excel(name = "备注项18")
            private String bz18;        //  备注项18
            @Excel(name = "备注项19")
            private String bz19;        //  备注项19
            //private String mxid;        //  明细编码
            //private String ddid;        //  订单编码
            //private String ddbh;        //  订单编号
            //private String hyid;        //  会员编码
            //private String spid;        //  商品编码
            //private String spmc;        //  商品名称
            //private String spsl;        //  商品数量
            //private String spjg;        //  商品价格
            //private String jgxj;        //  价格小计
            //private String mxsm;        //  明细说明
            //private String bz00;        //  备注项00
            //private String bz01;        //  备注项01
            //private String bz02;        //  备注项02
            //private String bz03;        //  备注项03
            //private String bz04;        //  备注项04
            //private String bz05;        //  备注项05
            //private String bz06;        //  备注项06
            //private String bz07;        //  备注项07
            //private String bz08;        //  备注项08
            //private String bz09;        //  备注项09
            //private String bz10;        //  备注项10
            //private String bz11;        //  备注项11
            //private String bz12;        //  备注项12
            //private String bz13;        //  备注项13
            //private String bz14;        //  备注项14
            //private String bz15;        //  备注项15
            //private String bz16;        //  备注项16
            //private String bz17;        //  备注项17
            //private String bz18;        //  备注项18
            //private String bz19;        //  备注项19
 

       public String getMxid() //明细编码
       {
           return mxid;
       }
       public void setMxid(String mxid) //明细编码
       {
           this.mxid = mxid;
       }	
       public String getDdid() //订单编码
       {
           return ddid;
       }
       public void setDdid(String ddid) //订单编码
       {
           this.ddid = ddid;
       }	
       public String getDdbh() //订单编号
       {
           return ddbh;
       }
       public void setDdbh(String ddbh) //订单编号
       {
           this.ddbh = ddbh;
       }	
       public String getHyid() //会员编码
       {
           return hyid;
       }
       public void setHyid(String hyid) //会员编码
       {
           this.hyid = hyid;
       }	
       public String getSpid() //商品编码
       {
           return spid;
       }
       public void setSpid(String spid) //商品编码
       {
           this.spid = spid;
       }	
       public String getSpmc() //商品名称
       {
           return spmc;
       }
       public void setSpmc(String spmc) //商品名称
       {
           this.spmc = spmc;
       }	
       public String getSpsl() //商品数量
       {
           return spsl;
       }
       public void setSpsl(String spsl) //商品数量
       {
           this.spsl = spsl;
       }	
       public String getSpjg() //商品价格
       {
           return spjg;
       }
       public void setSpjg(String spjg) //商品价格
       {
           this.spjg = spjg;
       }	
       public String getJgxj() //价格小计
       {
           return jgxj;
       }
       public void setJgxj(String jgxj) //价格小计
       {
           this.jgxj = jgxj;
       }	
       public String getMxsm() //明细说明
       {
           return mxsm;
       }
       public void setMxsm(String mxsm) //明细说明
       {
           this.mxsm = mxsm;
       }	
       public String getBz00() //备注项00
       {
           return bz00;
       }
       public void setBz00(String bz00) //备注项00
       {
           this.bz00 = bz00;
       }	
       public String getBz01() //备注项01
       {
           return bz01;
       }
       public void setBz01(String bz01) //备注项01
       {
           this.bz01 = bz01;
       }	
       public String getBz02() //备注项02
       {
           return bz02;
       }
       public void setBz02(String bz02) //备注项02
       {
           this.bz02 = bz02;
       }	
       public String getBz03() //备注项03
       {
           return bz03;
       }
       public void setBz03(String bz03) //备注项03
       {
           this.bz03 = bz03;
       }	
       public String getBz04() //备注项04
       {
           return bz04;
       }
       public void setBz04(String bz04) //备注项04
       {
           this.bz04 = bz04;
       }	
       public String getBz05() //备注项05
       {
           return bz05;
       }
       public void setBz05(String bz05) //备注项05
       {
           this.bz05 = bz05;
       }	
       public String getBz06() //备注项06
       {
           return bz06;
       }
       public void setBz06(String bz06) //备注项06
       {
           this.bz06 = bz06;
       }	
       public String getBz07() //备注项07
       {
           return bz07;
       }
       public void setBz07(String bz07) //备注项07
       {
           this.bz07 = bz07;
       }	
       public String getBz08() //备注项08
       {
           return bz08;
       }
       public void setBz08(String bz08) //备注项08
       {
           this.bz08 = bz08;
       }	
       public String getBz09() //备注项09
       {
           return bz09;
       }
       public void setBz09(String bz09) //备注项09
       {
           this.bz09 = bz09;
       }	
       public String getBz10() //备注项10
       {
           return bz10;
       }
       public void setBz10(String bz10) //备注项10
       {
           this.bz10 = bz10;
       }	
       public String getBz11() //备注项11
       {
           return bz11;
       }
       public void setBz11(String bz11) //备注项11
       {
           this.bz11 = bz11;
       }	
       public String getBz12() //备注项12
       {
           return bz12;
       }
       public void setBz12(String bz12) //备注项12
       {
           this.bz12 = bz12;
       }	
       public String getBz13() //备注项13
       {
           return bz13;
       }
       public void setBz13(String bz13) //备注项13
       {
           this.bz13 = bz13;
       }	
       public String getBz14() //备注项14
       {
           return bz14;
       }
       public void setBz14(String bz14) //备注项14
       {
           this.bz14 = bz14;
       }	
       public String getBz15() //备注项15
       {
           return bz15;
       }
       public void setBz15(String bz15) //备注项15
       {
           this.bz15 = bz15;
       }	
       public String getBz16() //备注项16
       {
           return bz16;
       }
       public void setBz16(String bz16) //备注项16
       {
           this.bz16 = bz16;
       }	
       public String getBz17() //备注项17
       {
           return bz17;
       }
       public void setBz17(String bz17) //备注项17
       {
           this.bz17 = bz17;
       }	
       public String getBz18() //备注项18
       {
           return bz18;
       }
       public void setBz18(String bz18) //备注项18
       {
           this.bz18 = bz18;
       }	
       public String getBz19() //备注项19
       {
           return bz19;
       }
       public void setBz19(String bz19) //备注项19
       {
           this.bz19 = bz19;
       }	
	
	 
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mxid", getMxid()) //明细编码
            .append("ddid", getDdid()) //订单编码
            .append("ddbh", getDdbh()) //订单编号
            .append("hyid", getHyid()) //会员编码
            .append("spid", getSpid()) //商品编码
            .append("spmc", getSpmc()) //商品名称
            .append("spsl", getSpsl()) //商品数量
            .append("spjg", getSpjg()) //商品价格
            .append("jgxj", getJgxj()) //价格小计
            .append("mxsm", getMxsm()) //明细说明
            .append("bz00", getBz00()) //备注项00
            .append("bz01", getBz01()) //备注项01
            .append("bz02", getBz02()) //备注项02
            .append("bz03", getBz03()) //备注项03
            .append("bz04", getBz04()) //备注项04
            .append("bz05", getBz05()) //备注项05
            .append("bz06", getBz06()) //备注项06
            .append("bz07", getBz07()) //备注项07
            .append("bz08", getBz08()) //备注项08
            .append("bz09", getBz09()) //备注项09
            .append("bz10", getBz10()) //备注项10
            .append("bz11", getBz11()) //备注项11
            .append("bz12", getBz12()) //备注项12
            .append("bz13", getBz13()) //备注项13
            .append("bz14", getBz14()) //备注项14
            .append("bz15", getBz15()) //备注项15
            .append("bz16", getBz16()) //备注项16
            .append("bz17", getBz17()) //备注项17
            .append("bz18", getBz18()) //备注项18
            .append("bz19", getBz19()) //备注项19
 
            .toString();
    }
}
