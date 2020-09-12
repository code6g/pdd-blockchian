package com.ruoyi.pdd.mapperbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息bean: pddxx
 * 
 * @author 牛哥 
 * @date 2019-12-16 
 */
public class Pddxx extends BaseEntity
{
    private static final long serialVersionUID = 1L;

            @Excel(name = "订单编码")
            private String ddid;        //  订单编码
            @Excel(name = "订单编号")
            private String ddbh;        //  订单编号
            @Excel(name = "会员编码")
            private String hyid;        //  会员编码
            @Excel(name = "会员名称")
            private String hymc;        //  会员名称
            @Excel(name = "商品数量")
            private String spsl;        //  商品数量
            @Excel(name = "价格总计")
            private String jgzj;        //  价格总计
            @Excel(name = "订单状态")
            private String ddzt;        //  订单状态
            @Excel(name = "下单时间")
            private String xdsj;        //  下单时间
            @Excel(name = "下单说明")
            private String xdsm;        //  下单说明
            @Excel(name = "支付渠道")
            private String zfqd;        //  支付渠道
            @Excel(name = "支付账号")
            private String zfzh;        //  支付账号
            @Excel(name = "支付人")
            private String zfry;        //  支付人
            @Excel(name = "支付时间")
            private String zfsj;        //  支付时间
            @Excel(name = "支付说明")
            private String zfsm;        //  支付说明
            @Excel(name = "收货人")
            private String shry;        //  收货人
            @Excel(name = "收货地址")
            private String shdz;        //  收货地址
            @Excel(name = "收货人电话")
            private String shdh;        //  收货人电话
            @Excel(name = "配送人")
            private String psry;        //  配送人
            @Excel(name = "配送时间")
            private String pssj;        //  配送时间
            @Excel(name = "配送说明")
            private String pssm;        //  配送说明
            @Excel(name = "签收人")
            private String qsry;        //  签收人
            @Excel(name = "签收时间")
            private String qssj;        //  签收时间
            @Excel(name = "签收说明")
            private String qssm;        //  签收说明
            @Excel(name = "评价内容")
            private String pjnr;        //  评价内容
            @Excel(name = "评价时间")
            private String pjsj;        //  评价时间
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

            @Excel(name = "ddmx")
            private  String ddmx;

            //private String ddid;        //  订单编码
            //private String ddbh;        //  订单编号
            //private String hyid;        //  会员编码
            //private String hymc;        //  会员名称
            //private String spsl;        //  商品数量
            //private String jgzj;        //  价格总计
            //private String ddzt;        //  订单状态
            //private String xdsj;        //  下单时间
            //private String xdsm;        //  下单说明
            //private String zfqd;        //  支付渠道
            //private String zfzh;        //  支付账号
            //private String zfry;        //  支付人
            //private String zfsj;        //  支付时间
            //private String zfsm;        //  支付说明
            //private String shry;        //  收货人
            //private String shdz;        //  收货地址
            //private String shdh;        //  收货人电话
            //private String psry;        //  配送人
            //private String pssj;        //  配送时间
            //private String pssm;        //  配送说明
            //private String qsry;        //  签收人
            //private String qssj;        //  签收时间
            //private String qssm;        //  签收说明
            //private String pjnr;        //  评价内容
            //private String pjsj;        //  评价时间
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
       public String getHymc() //会员名称
       {
           return hymc;
       }
       public void setHymc(String hymc) //会员名称
       {
           this.hymc = hymc;
       }	
       public String getSpsl() //商品数量
       {
           return spsl;
       }
       public void setSpsl(String spsl) //商品数量
       {
           this.spsl = spsl;
       }	
       public String getJgzj() //价格总计
       {
           return jgzj;
       }
       public void setJgzj(String jgzj) //价格总计
       {
           this.jgzj = jgzj;
       }	
       public String getDdzt() //订单状态
       {
           return ddzt;
       }
       public void setDdzt(String ddzt) //订单状态
       {
           this.ddzt = ddzt;
       }	
       public String getXdsj() //下单时间
       {
           return xdsj;
       }
       public void setXdsj(String xdsj) //下单时间
       {
           this.xdsj = xdsj;
       }	
       public String getXdsm() //下单说明
       {
           return xdsm;
       }
       public void setXdsm(String xdsm) //下单说明
       {
           this.xdsm = xdsm;
       }	
       public String getZfqd() //支付渠道
       {
           return zfqd;
       }
       public void setZfqd(String zfqd) //支付渠道
       {
           this.zfqd = zfqd;
       }	
       public String getZfzh() //支付账号
       {
           return zfzh;
       }
       public void setZfzh(String zfzh) //支付账号
       {
           this.zfzh = zfzh;
       }	
       public String getZfry() //支付人
       {
           return zfry;
       }
       public void setZfry(String zfry) //支付人
       {
           this.zfry = zfry;
       }	
       public String getZfsj() //支付时间
       {
           return zfsj;
       }
       public void setZfsj(String zfsj) //支付时间
       {
           this.zfsj = zfsj;
       }	
       public String getZfsm() //支付说明
       {
           return zfsm;
       }
       public void setZfsm(String zfsm) //支付说明
       {
           this.zfsm = zfsm;
       }	
       public String getShry() //收货人
       {
           return shry;
       }
       public void setShry(String shry) //收货人
       {
           this.shry = shry;
       }	
       public String getShdz() //收货地址
       {
           return shdz;
       }
       public void setShdz(String shdz) //收货地址
       {
           this.shdz = shdz;
       }	
       public String getShdh() //收货人电话
       {
           return shdh;
       }
       public void setShdh(String shdh) //收货人电话
       {
           this.shdh = shdh;
       }	
       public String getPsry() //配送人
       {
           return psry;
       }
       public void setPsry(String psry) //配送人
       {
           this.psry = psry;
       }	
       public String getPssj() //配送时间
       {
           return pssj;
       }
       public void setPssj(String pssj) //配送时间
       {
           this.pssj = pssj;
       }	
       public String getPssm() //配送说明
       {
           return pssm;
       }
       public void setPssm(String pssm) //配送说明
       {
           this.pssm = pssm;
       }	
       public String getQsry() //签收人
       {
           return qsry;
       }
       public void setQsry(String qsry) //签收人
       {
           this.qsry = qsry;
       }	
       public String getQssj() //签收时间
       {
           return qssj;
       }
       public void setQssj(String qssj) //签收时间
       {
           this.qssj = qssj;
       }	
       public String getQssm() //签收说明
       {
           return qssm;
       }
       public void setQssm(String qssm) //签收说明
       {
           this.qssm = qssm;
       }	
       public String getPjnr() //评价内容
       {
           return pjnr;
       }
       public void setPjnr(String pjnr) //评价内容
       {
           this.pjnr = pjnr;
       }	
       public String getPjsj() //评价时间
       {
           return pjsj;
       }
       public void setPjsj(String pjsj) //评价时间
       {
           this.pjsj = pjsj;
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
            .append("ddid", getDdid()) //订单编码
            .append("ddbh", getDdbh()) //订单编号
            .append("hyid", getHyid()) //会员编码
            .append("hymc", getHymc()) //会员名称
            .append("spsl", getSpsl()) //商品数量
            .append("jgzj", getJgzj()) //价格总计
            .append("ddzt", getDdzt()) //订单状态
            .append("xdsj", getXdsj()) //下单时间
            .append("xdsm", getXdsm()) //下单说明
            .append("zfqd", getZfqd()) //支付渠道
            .append("zfzh", getZfzh()) //支付账号
            .append("zfry", getZfry()) //支付人
            .append("zfsj", getZfsj()) //支付时间
            .append("zfsm", getZfsm()) //支付说明
            .append("shry", getShry()) //收货人
            .append("shdz", getShdz()) //收货地址
            .append("shdh", getShdh()) //收货人电话
            .append("psry", getPsry()) //配送人
            .append("pssj", getPssj()) //配送时间
            .append("pssm", getPssm()) //配送说明
            .append("qsry", getQsry()) //签收人
            .append("qssj", getQssj()) //签收时间
            .append("qssm", getQssm()) //签收说明
            .append("pjnr", getPjnr()) //评价内容
            .append("pjsj", getPjsj()) //评价时间
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

    public String getDdmx() {
        return ddmx;
    }

    public void setDdmx(String ddmx) {
        this.ddmx = ddmx;
    }
}
