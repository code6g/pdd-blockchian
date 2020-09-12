package com.ruoyi.blockchain.mapperbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通道信息bean: channelinfo
 * 
 * @author 牛哥 
 * @date 2019-12-16 
 */
public class Channelinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

            @Excel(name = "通道编码")
            private String tdid;        //  通道编码
            @Excel(name = "通道名称")
            private String tdmc;        //  通道名称
            @Excel(name = "排序节点")
            private String pxjd;        //  排序节点
            @Excel(name = "对等节点")
            private String ddjd;        //  对等节点
            @Excel(name = "是否关闭")
            private String sfgb;        //  是否关闭
            @Excel(name = "是否初始化")
            private String sfcs;        //  是否初始化
            @Excel(name = "创世纪块")
            private String csjk;        //  创世纪块
            @Excel(name = "区块信息")
            private String qkxx;        //  区块信息
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
            //private String tdid;        //  通道编码
            //private String tdmc;        //  通道名称
            //private String pxjd;        //  排序节点
            //private String ddjd;        //  对等节点
            //private String sfgb;        //  是否关闭
            //private String sfcs;        //  是否初始化
            //private String csjk;        //  创世纪块
            //private String qkxx;        //  区块信息
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
 

       public String getTdid() //通道编码
       {
           return tdid;
       }
       public void setTdid(String tdid) //通道编码
       {
           this.tdid = tdid;
       }	
       public String getTdmc() //通道名称
       {
           return tdmc;
       }
       public void setTdmc(String tdmc) //通道名称
       {
           this.tdmc = tdmc;
       }	
       public String getPxjd() //排序节点
       {
           return pxjd;
       }
       public void setPxjd(String pxjd) //排序节点
       {
           this.pxjd = pxjd;
       }	
       public String getDdjd() //对等节点
       {
           return ddjd;
       }
       public void setDdjd(String ddjd) //对等节点
       {
           this.ddjd = ddjd;
       }	
       public String getSfgb() //是否关闭
       {
           return sfgb;
       }
       public void setSfgb(String sfgb) //是否关闭
       {
           this.sfgb = sfgb;
       }	
       public String getSfcs() //是否初始化
       {
           return sfcs;
       }
       public void setSfcs(String sfcs) //是否初始化
       {
           this.sfcs = sfcs;
       }	
       public String getCsjk() //创世纪块
       {
           return csjk;
       }
       public void setCsjk(String csjk) //创世纪块
       {
           this.csjk = csjk;
       }	
       public String getQkxx() //区块信息
       {
           return qkxx;
       }
       public void setQkxx(String qkxx) //区块信息
       {
           this.qkxx = qkxx;
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
            .append("tdid", getTdid()) //通道编码
            .append("tdmc", getTdmc()) //通道名称
            .append("pxjd", getPxjd()) //排序节点
            .append("ddjd", getDdjd()) //对等节点
            .append("sfgb", getSfgb()) //是否关闭
            .append("sfcs", getSfcs()) //是否初始化
            .append("csjk", getCsjk()) //创世纪块
            .append("qkxx", getQkxx()) //区块信息
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
