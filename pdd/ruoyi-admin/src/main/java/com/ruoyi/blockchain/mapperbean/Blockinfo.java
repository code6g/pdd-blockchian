package com.ruoyi.blockchain.mapperbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 区块信息bean: blockinfo
 * 
 * @author 牛哥 
 * @date 2019-12-16 
 */
public class Blockinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

            @Excel(name = "头区块编号")
            private String tqkid;        //  头区块编号
            @Excel(name = "头前块哈希")
            private String tqkhx;        //  头前块哈希
            @Excel(name = "头数据哈希")
            private String tsjhx;        //  头数据哈希
            @Excel(name = "头未知字段")
            private String twzzd;        //  头未知字段
            @Excel(name = "头存储初始化")
            private String tcccs;        //  头存储初始化
            @Excel(name = "头存储大小")
            private String tccdx;        //  头存储大小
            @Excel(name = "头存储哈希")
            private String tcchx;        //  头存储哈希
            @Excel(name = "数据内容")
            private String sjnr;        //  数据内容
            @Excel(name = "数据存储初始化")
            private String sjcccs;        //  数据存储初始化
            @Excel(name = "数据未知字段")
            private String sjwzzd;        //  数据未知字段
            @Excel(name = "数据存储大小")
            private String sjccdx;        //  数据存储大小
            @Excel(name = "数据存储哈希")
            private String sjcchx;        //  数据存储哈希
            @Excel(name = "元数据内容")
            private String ysjnr;        //  元数据内容
            @Excel(name = "元数据存储初始化")
            private String ysjcccs;        //  元数据存储初始化
            @Excel(name = "元数据未知字段")
            private String ysjwzzd;        //  元数据未知字段
            @Excel(name = "元数据存储大小")
            private String ysjccdx;        //  元数据存储大小
            @Excel(name = "元数据存储哈希")
            private String ysscchx;        //  元数据存储哈希
            @Excel(name = "过滤块")
            private String glk;        //  过滤块
            @Excel(name = "交易次数")
            private String jjcs;        //  交易次数
            @Excel(name = "区块说明")
            private String qksm;        //  区块说明
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
            //private String tqkid;        //  头区块编号
            //private String tqkhx;        //  头前块哈希
            //private String tsjhx;        //  头数据哈希
            //private String twzzd;        //  头未知字段
            //private String tcccs;        //  头存储初始化
            //private String tccdx;        //  头存储大小
            //private String tcchx;        //  头存储哈希
            //private String sjnr;        //  数据内容
            //private String sjcccs;        //  数据存储初始化
            //private String sjwzzd;        //  数据未知字段
            //private String sjccdx;        //  数据存储大小
            //private String sjcchx;        //  数据存储哈希
            //private String ysjnr;        //  元数据内容
            //private String ysjcccs;        //  元数据存储初始化
            //private String ysjwzzd;        //  元数据未知字段
            //private String ysjccdx;        //  元数据存储大小
            //private String ysscchx;        //  元数据存储哈希
            //private String glk;        //  过滤块
            //private String jjcs;        //  交易次数
            //private String qksm;        //  区块说明
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
 

       public String getTqkid() //头区块编号
       {
           return tqkid;
       }
       public void setTqkid(String tqkid) //头区块编号
       {
           this.tqkid = tqkid;
       }	
       public String getTqkhx() //头前块哈希
       {
           return tqkhx;
       }
       public void setTqkhx(String tqkhx) //头前块哈希
       {
           this.tqkhx = tqkhx;
       }	
       public String getTsjhx() //头数据哈希
       {
           return tsjhx;
       }
       public void setTsjhx(String tsjhx) //头数据哈希
       {
           this.tsjhx = tsjhx;
       }	
       public String getTwzzd() //头未知字段
       {
           return twzzd;
       }
       public void setTwzzd(String twzzd) //头未知字段
       {
           this.twzzd = twzzd;
       }	
       public String getTcccs() //头存储初始化
       {
           return tcccs;
       }
       public void setTcccs(String tcccs) //头存储初始化
       {
           this.tcccs = tcccs;
       }	
       public String getTccdx() //头存储大小
       {
           return tccdx;
       }
       public void setTccdx(String tccdx) //头存储大小
       {
           this.tccdx = tccdx;
       }	
       public String getTcchx() //头存储哈希
       {
           return tcchx;
       }
       public void setTcchx(String tcchx) //头存储哈希
       {
           this.tcchx = tcchx;
       }	
       public String getSjnr() //数据内容
       {
           return sjnr;
       }
       public void setSjnr(String sjnr) //数据内容
       {
           this.sjnr = sjnr;
       }	
       public String getSjcccs() //数据存储初始化
       {
           return sjcccs;
       }
       public void setSjcccs(String sjcccs) //数据存储初始化
       {
           this.sjcccs = sjcccs;
       }	
       public String getSjwzzd() //数据未知字段
       {
           return sjwzzd;
       }
       public void setSjwzzd(String sjwzzd) //数据未知字段
       {
           this.sjwzzd = sjwzzd;
       }	
       public String getSjccdx() //数据存储大小
       {
           return sjccdx;
       }
       public void setSjccdx(String sjccdx) //数据存储大小
       {
           this.sjccdx = sjccdx;
       }	
       public String getSjcchx() //数据存储哈希
       {
           return sjcchx;
       }
       public void setSjcchx(String sjcchx) //数据存储哈希
       {
           this.sjcchx = sjcchx;
       }	
       public String getYsjnr() //元数据内容
       {
           return ysjnr;
       }
       public void setYsjnr(String ysjnr) //元数据内容
       {
           this.ysjnr = ysjnr;
       }	
       public String getYsjcccs() //元数据存储初始化
       {
           return ysjcccs;
       }
       public void setYsjcccs(String ysjcccs) //元数据存储初始化
       {
           this.ysjcccs = ysjcccs;
       }	
       public String getYsjwzzd() //元数据未知字段
       {
           return ysjwzzd;
       }
       public void setYsjwzzd(String ysjwzzd) //元数据未知字段
       {
           this.ysjwzzd = ysjwzzd;
       }	
       public String getYsjccdx() //元数据存储大小
       {
           return ysjccdx;
       }
       public void setYsjccdx(String ysjccdx) //元数据存储大小
       {
           this.ysjccdx = ysjccdx;
       }	
       public String getYsscchx() //元数据存储哈希
       {
           return ysscchx;
       }
       public void setYsscchx(String ysscchx) //元数据存储哈希
       {
           this.ysscchx = ysscchx;
       }	
       public String getGlk() //过滤块
       {
           return glk;
       }
       public void setGlk(String glk) //过滤块
       {
           this.glk = glk;
       }	
       public String getJjcs() //交易次数
       {
           return jjcs;
       }
       public void setJjcs(String jjcs) //交易次数
       {
           this.jjcs = jjcs;
       }	
       public String getQksm() //区块说明
       {
           return qksm;
       }
       public void setQksm(String qksm) //区块说明
       {
           this.qksm = qksm;
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
            .append("tqkid", getTqkid()) //头区块编号
            .append("tqkhx", getTqkhx()) //头前块哈希
            .append("tsjhx", getTsjhx()) //头数据哈希
            .append("twzzd", getTwzzd()) //头未知字段
            .append("tcccs", getTcccs()) //头存储初始化
            .append("tccdx", getTccdx()) //头存储大小
            .append("tcchx", getTcchx()) //头存储哈希
            .append("sjnr", getSjnr()) //数据内容
            .append("sjcccs", getSjcccs()) //数据存储初始化
            .append("sjwzzd", getSjwzzd()) //数据未知字段
            .append("sjccdx", getSjccdx()) //数据存储大小
            .append("sjcchx", getSjcchx()) //数据存储哈希
            .append("ysjnr", getYsjnr()) //元数据内容
            .append("ysjcccs", getYsjcccs()) //元数据存储初始化
            .append("ysjwzzd", getYsjwzzd()) //元数据未知字段
            .append("ysjccdx", getYsjccdx()) //元数据存储大小
            .append("ysscchx", getYsscchx()) //元数据存储哈希
            .append("glk", getGlk()) //过滤块
            .append("jjcs", getJjcs()) //交易次数
            .append("qksm", getQksm()) //区块说明
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
