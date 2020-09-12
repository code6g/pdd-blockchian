package com.ruoyi.blockchain.mapperbean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单区块信息bean: oneblockinfo
 * 
 * @author 牛哥 
 * @date 2019-12-16 
 */
public class Oneblockinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

            @Excel(name = "块编码")
            private String blockid;        //  块编码
            @Excel(name = "块编号")
            private String blocknumber;        //  块编号
            @Excel(name = "数据哈希")
            private String datahash;        //  数据哈希
            @Excel(name = "前块哈希")
            private String previoushash;        //  前块哈希
            @Excel(name = "通道")
            private String channelid;        //  通道
            @Excel(name = "信封数量")
            private String envelopecount;        //  信封数量
            @Excel(name = "信封内容")
            private String envelopeinfos;        //  信封内容
            @Excel(name = "交易数量")
            private String transactioncount;        //  交易数量
            @Excel(name = "交易元数据")
            private String transactionsmetadata;        //  交易元数据
            @Excel(name = "区块所有字段")
            private String blockallfields;        //  区块所有字段
            @Excel(name = "区块头前哈希")
            private String blockheaderprevioushash;        //  区块头前哈希
            @Excel(name = "区块头数据哈希")
            private String blockheaderdatahash;        //  区块头数据哈希
            @Excel(name = "区块头序列化尺寸")
            private String blockheaderserializedsize;        //  区块头序列化尺寸
            @Excel(name = "区块头编号")
            private String blockheadernumber;        //  区块头编号
            @Excel(name = "区块头所有字段")
            private String blockheaderallfields;        //  区块头所有字段
            @Excel(name = "区块体数据量")
            private String blockdatadatacount;        //  区块体数据量
            @Excel(name = "区块体数据列表")
            private String blockdatadatalist;        //  区块体数据列表
            @Excel(name = "区块体所有字段")
            private String blockdataallfields;        //  区块体所有字段
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
            //private String blockid;        //  块编码
            //private String blocknumber;        //  块编号
            //private String datahash;        //  数据哈希
            //private String previoushash;        //  前块哈希
            //private String channelid;        //  通道
            //private String envelopecount;        //  信封数量
            //private String envelopeinfos;        //  信封内容
            //private String transactioncount;        //  交易数量
            //private String transactionsmetadata;        //  交易元数据
            //private String blockallfields;        //  区块所有字段
            //private String blockheaderprevioushash;        //  区块头前哈希
            //private String blockheaderdatahash;        //  区块头数据哈希
            //private String blockheaderserializedsize;        //  区块头序列化尺寸
            //private String blockheadernumber;        //  区块头编号
            //private String blockheaderallfields;        //  区块头所有字段
            //private String blockdatadatacount;        //  区块体数据量
            //private String blockdatadatalist;        //  区块体数据列表
            //private String blockdataallfields;        //  区块体所有字段
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
 

       public String getBlockid() //块编码
       {
           return blockid;
       }
       public void setBlockid(String blockid) //块编码
       {
           this.blockid = blockid;
       }	
       public String getBlocknumber() //块编号
       {
           return blocknumber;
       }
       public void setBlocknumber(String blocknumber) //块编号
       {
           this.blocknumber = blocknumber;
       }	
       public String getDatahash() //数据哈希
       {
           return datahash;
       }
       public void setDatahash(String datahash) //数据哈希
       {
           this.datahash = datahash;
       }	
       public String getPrevioushash() //前块哈希
       {
           return previoushash;
       }
       public void setPrevioushash(String previoushash) //前块哈希
       {
           this.previoushash = previoushash;
       }	
       public String getChannelid() //通道
       {
           return channelid;
       }
       public void setChannelid(String channelid) //通道
       {
           this.channelid = channelid;
       }	
       public String getEnvelopecount() //信封数量
       {
           return envelopecount;
       }
       public void setEnvelopecount(String envelopecount) //信封数量
       {
           this.envelopecount = envelopecount;
       }	
       public String getEnvelopeinfos() //信封内容
       {
           return envelopeinfos;
       }
       public void setEnvelopeinfos(String envelopeinfos) //信封内容
       {
           this.envelopeinfos = envelopeinfos;
       }	
       public String getTransactioncount() //交易数量
       {
           return transactioncount;
       }
       public void setTransactioncount(String transactioncount) //交易数量
       {
           this.transactioncount = transactioncount;
       }	
       public String getTransactionsmetadata() //交易元数据
       {
           return transactionsmetadata;
       }
       public void setTransactionsmetadata(String transactionsmetadata) //交易元数据
       {
           this.transactionsmetadata = transactionsmetadata;
       }	
       public String getBlockallfields() //区块所有字段
       {
           return blockallfields;
       }
       public void setBlockallfields(String blockallfields) //区块所有字段
       {
           this.blockallfields = blockallfields;
       }	
       public String getBlockheaderprevioushash() //区块头前哈希
       {
           return blockheaderprevioushash;
       }
       public void setBlockheaderprevioushash(String blockheaderprevioushash) //区块头前哈希
       {
           this.blockheaderprevioushash = blockheaderprevioushash;
       }	
       public String getBlockheaderdatahash() //区块头数据哈希
       {
           return blockheaderdatahash;
       }
       public void setBlockheaderdatahash(String blockheaderdatahash) //区块头数据哈希
       {
           this.blockheaderdatahash = blockheaderdatahash;
       }	
       public String getBlockheaderserializedsize() //区块头序列化尺寸
       {
           return blockheaderserializedsize;
       }
       public void setBlockheaderserializedsize(String blockheaderserializedsize) //区块头序列化尺寸
       {
           this.blockheaderserializedsize = blockheaderserializedsize;
       }	
       public String getBlockheadernumber() //区块头编号
       {
           return blockheadernumber;
       }
       public void setBlockheadernumber(String blockheadernumber) //区块头编号
       {
           this.blockheadernumber = blockheadernumber;
       }	
       public String getBlockheaderallfields() //区块头所有字段
       {
           return blockheaderallfields;
       }
       public void setBlockheaderallfields(String blockheaderallfields) //区块头所有字段
       {
           this.blockheaderallfields = blockheaderallfields;
       }	
       public String getBlockdatadatacount() //区块体数据量
       {
           return blockdatadatacount;
       }
       public void setBlockdatadatacount(String blockdatadatacount) //区块体数据量
       {
           this.blockdatadatacount = blockdatadatacount;
       }	
       public String getBlockdatadatalist() //区块体数据列表
       {
           return blockdatadatalist;
       }
       public void setBlockdatadatalist(String blockdatadatalist) //区块体数据列表
       {
           this.blockdatadatalist = blockdatadatalist;
       }	
       public String getBlockdataallfields() //区块体所有字段
       {
           return blockdataallfields;
       }
       public void setBlockdataallfields(String blockdataallfields) //区块体所有字段
       {
           this.blockdataallfields = blockdataallfields;
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
            .append("blockid", getBlockid()) //块编码
            .append("blocknumber", getBlocknumber()) //块编号
            .append("datahash", getDatahash()) //数据哈希
            .append("previoushash", getPrevioushash()) //前块哈希
            .append("channelid", getChannelid()) //通道
            .append("envelopecount", getEnvelopecount()) //信封数量
            .append("envelopeinfos", getEnvelopeinfos()) //信封内容
            .append("transactioncount", getTransactioncount()) //交易数量
            .append("transactionsmetadata", getTransactionsmetadata()) //交易元数据
            .append("blockallfields", getBlockallfields()) //区块所有字段
            .append("blockheaderprevioushash", getBlockheaderprevioushash()) //区块头前哈希
            .append("blockheaderdatahash", getBlockheaderdatahash()) //区块头数据哈希
            .append("blockheaderserializedsize", getBlockheaderserializedsize()) //区块头序列化尺寸
            .append("blockheadernumber", getBlockheadernumber()) //区块头编号
            .append("blockheaderallfields", getBlockheaderallfields()) //区块头所有字段
            .append("blockdatadatacount", getBlockdatadatacount()) //区块体数据量
            .append("blockdatadatalist", getBlockdatadatalist()) //区块体数据列表
            .append("blockdataallfields", getBlockdataallfields()) //区块体所有字段
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
