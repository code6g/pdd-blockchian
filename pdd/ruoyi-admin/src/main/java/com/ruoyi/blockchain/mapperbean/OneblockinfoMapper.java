package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 单区块信息(oneblockinfo)的Mapper注解接口-OneblockinfoMapper
 */

@Mapper
public interface OneblockinfoMapper {

    /**
     * 单区块信息的各类查询
     * @return
     */
    //根据查询所有的单区块信息(oneblockinfo)。
    @Select("select * from oneblockinfo")
    List<Oneblockinfo> selectList();

    //根据oneblockinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from oneblockinfo where 1=1 "        //单区块信息
            + " and blockid like '%${blockid}%'  " //块编码
            + " and blocknumber like '%${blocknumber}%'  " //块编号
            + " and datahash like '%${datahash}%'  " //数据哈希
            + " and previoushash like '%${previoushash}%'  " //前块哈希
            + " and channelid like '%${channelid}%'  " //通道
            + " and envelopecount like '%${envelopecount}%'  " //信封数量
            + " and envelopeinfos like '%${envelopeinfos}%'  " //信封内容
            + " and transactioncount like '%${transactioncount}%'  " //交易数量
            + " and transactionsmetadata like '%${transactionsmetadata}%'  " //交易元数据
            + " and blockallfields like '%${blockallfields}%'  " //区块所有字段
            + " and blockheaderprevioushash like '%${blockheaderprevioushash}%'  " //区块头前哈希
            + " and blockheaderdatahash like '%${blockheaderdatahash}%'  " //区块头数据哈希
            + " and blockheaderserializedsize like '%${blockheaderserializedsize}%'  " //区块头序列化尺寸
            + " and blockheadernumber like '%${blockheadernumber}%'  " //区块头编号
            + " and blockheaderallfields like '%${blockheaderallfields}%'  " //区块头所有字段
            + " and blockdatadatacount like '%${blockdatadatacount}%'  " //区块体数据量
            + " and blockdatadatalist like '%${blockdatadatalist}%'  " //区块体数据列表
            + " and blockdataallfields like '%${blockdataallfields}%'  " //区块体所有字段
            + " and bz00 like '%${bz00}%'  " //备注项00
            + " and bz01 like '%${bz01}%'  " //备注项01
            + " and bz02 like '%${bz02}%'  " //备注项02
            + " and bz03 like '%${bz03}%'  " //备注项03
            + " and bz04 like '%${bz04}%'  " //备注项04
            + " and bz05 like '%${bz05}%'  " //备注项05
            + " and bz06 like '%${bz06}%'  " //备注项06
            + " and bz07 like '%${bz07}%'  " //备注项07
            + " and bz08 like '%${bz08}%'  " //备注项08
            + " and bz09 like '%${bz09}%'  " //备注项09
            + " and bz10 like '%${bz10}%'  " //备注项10
            + " and bz11 like '%${bz11}%'  " //备注项11
            + " and bz12 like '%${bz12}%'  " //备注项12
            + " and bz13 like '%${bz13}%'  " //备注项13
            + " and bz14 like '%${bz14}%'  " //备注项14
            + " and bz15 like '%${bz15}%'  " //备注项15
            + " and bz16 like '%${bz16}%'  " //备注项16
            + " and bz17 like '%${bz17}%'  " //备注项17
            + " and bz18 like '%${bz18}%'  " //备注项18
            + " and bz19 like '%${bz19}%'  " //备注项19
             
    )
    public List<Oneblockinfo> selectListByOneblockinfo(Oneblockinfo oneblockinfo);

    //根据blockid查询单个单区块信息。
    @Select(" select * from oneblockinfo where blockid='${blockid}' limit 1 " )
    public  Oneblockinfo selectById(@Param("blockid") String blockid);

    //根据oneblockinfo查询单个单区块信息。
    @Select(" select * from oneblockinfo where blockid='${blockid}' limit 1" )
    public  Oneblockinfo selectByOneblockinfo(Oneblockinfo oneblockinfo);

    /**
     * 单区块信息的各类新增
     * @return
     */
    //新增单个单区块信息。
    @Insert(
            " insert into oneblockinfo(" +
            "blockid," +   //块编码
            "blocknumber," +   //块编号
            "datahash," +   //数据哈希
            "previoushash," +   //前块哈希
            "channelid," +   //通道
            "envelopecount," +   //信封数量
            "envelopeinfos," +   //信封内容
            "transactioncount," +   //交易数量
            "transactionsmetadata," +   //交易元数据
            "blockallfields," +   //区块所有字段
            "blockheaderprevioushash," +   //区块头前哈希
            "blockheaderdatahash," +   //区块头数据哈希
            "blockheaderserializedsize," +   //区块头序列化尺寸
            "blockheadernumber," +   //区块头编号
            "blockheaderallfields," +   //区块头所有字段
            "blockdatadatacount," +   //区块体数据量
            "blockdatadatalist," +   //区块体数据列表
            "blockdataallfields," +   //区块体所有字段
            "bz00," +   //备注项00
            "bz01," +   //备注项01
            "bz02," +   //备注项02
            "bz03," +   //备注项03
            "bz04," +   //备注项04
            "bz05," +   //备注项05
            "bz06," +   //备注项06
            "bz07," +   //备注项07
            "bz08," +   //备注项08
            "bz09," +   //备注项09
            "bz10," +   //备注项10
            "bz11," +   //备注项11
            "bz12," +   //备注项12
            "bz13," +   //备注项13
            "bz14," +   //备注项14
            "bz15," +   //备注项15
            "bz16," +   //备注项16
            "bz17," +   //备注项17
            "bz18," +   //备注项18
            "bz19 " +   //备注项19
                     
                    ") values("  +
            "'${blockid}'," +  //块编码
            "'${blocknumber}'," +  //块编号
            "'${datahash}'," +  //数据哈希
            "'${previoushash}'," +  //前块哈希
            "'${channelid}'," +  //通道
            "'${envelopecount}'," +  //信封数量
            "'${envelopeinfos}'," +  //信封内容
            "'${transactioncount}'," +  //交易数量
            "'${transactionsmetadata}'," +  //交易元数据
            "'${blockallfields}'," +  //区块所有字段
            "'${blockheaderprevioushash}'," +  //区块头前哈希
            "'${blockheaderdatahash}'," +  //区块头数据哈希
            "'${blockheaderserializedsize}'," +  //区块头序列化尺寸
            "'${blockheadernumber}'," +  //区块头编号
            "'${blockheaderallfields}'," +  //区块头所有字段
            "'${blockdatadatacount}'," +  //区块体数据量
            "'${blockdatadatalist}'," +  //区块体数据列表
            "'${blockdataallfields}'," +  //区块体所有字段
            "'${bz00}'," +  //备注项00
            "'${bz01}'," +  //备注项01
            "'${bz02}'," +  //备注项02
            "'${bz03}'," +  //备注项03
            "'${bz04}'," +  //备注项04
            "'${bz05}'," +  //备注项05
            "'${bz06}'," +  //备注项06
            "'${bz07}'," +  //备注项07
            "'${bz08}'," +  //备注项08
            "'${bz09}'," +  //备注项09
            "'${bz10}'," +  //备注项10
            "'${bz11}'," +  //备注项11
            "'${bz12}'," +  //备注项12
            "'${bz13}'," +  //备注项13
            "'${bz14}'," +  //备注项14
            "'${bz15}'," +  //备注项15
            "'${bz16}'," +  //备注项16
            "'${bz17}'," +  //备注项17
            "'${bz18}'," +  //备注项18
            "'${bz19}' " +  //备注项19

                    ") "
    )
    public  int insertOneblockinfo(Oneblockinfo oneblockinfo);

    /**
     * 单区块信息的各类删除
     * @return
     */
    //根据主键删除单个单区块信息。
    @Delete(" delete from oneblockinfo where blockid='${blockid}' " )
    public  boolean deleteByBlockid(@Param("blockid") String blockid);

    //根据实体信息删除单个单区块信息。
    @Delete(" delete from oneblockinfo where blockid='${blockid}' " )
    public  boolean deleteByOneblockinfo(Oneblockinfo oneblockinfo);

    //根据主键删除多个单区块信息。
    @Delete(" delete from oneblockinfo where blockid in (${blockids}) " )
    public  boolean deleteByBlockids(@Param("blockids")String blockids);

    /**
     * 单区块信息的各类修改
     * @return
     */
    //根据实体修改单个单区块信息。
    @Update(
        "update oneblockinfo set " +
       "blockid = '${blockid}'," +  //块编码
       "blocknumber = '${blocknumber}'," +  //块编号
       "datahash = '${datahash}'," +  //数据哈希
       "previoushash = '${previoushash}'," +  //前块哈希
       "channelid = '${channelid}'," +  //通道
       "envelopecount = '${envelopecount}'," +  //信封数量
       "envelopeinfos = '${envelopeinfos}'," +  //信封内容
       "transactioncount = '${transactioncount}'," +  //交易数量
       "transactionsmetadata = '${transactionsmetadata}'," +  //交易元数据
       "blockallfields = '${blockallfields}'," +  //区块所有字段
       "blockheaderprevioushash = '${blockheaderprevioushash}'," +  //区块头前哈希
       "blockheaderdatahash = '${blockheaderdatahash}'," +  //区块头数据哈希
       "blockheaderserializedsize = '${blockheaderserializedsize}'," +  //区块头序列化尺寸
       "blockheadernumber = '${blockheadernumber}'," +  //区块头编号
       "blockheaderallfields = '${blockheaderallfields}'," +  //区块头所有字段
       "blockdatadatacount = '${blockdatadatacount}'," +  //区块体数据量
       "blockdatadatalist = '${blockdatadatalist}'," +  //区块体数据列表
       "blockdataallfields = '${blockdataallfields}'," +  //区块体所有字段
       "bz00 = '${bz00}'," +  //备注项00
       "bz01 = '${bz01}'," +  //备注项01
       "bz02 = '${bz02}'," +  //备注项02
       "bz03 = '${bz03}'," +  //备注项03
       "bz04 = '${bz04}'," +  //备注项04
       "bz05 = '${bz05}'," +  //备注项05
       "bz06 = '${bz06}'," +  //备注项06
       "bz07 = '${bz07}'," +  //备注项07
       "bz08 = '${bz08}'," +  //备注项08
       "bz09 = '${bz09}'," +  //备注项09
       "bz10 = '${bz10}'," +  //备注项10
       "bz11 = '${bz11}'," +  //备注项11
       "bz12 = '${bz12}'," +  //备注项12
       "bz13 = '${bz13}'," +  //备注项13
       "bz14 = '${bz14}'," +  //备注项14
       "bz15 = '${bz15}'," +  //备注项15
       "bz16 = '${bz16}'," +  //备注项16
       "bz17 = '${bz17}'," +  //备注项17
       "bz18 = '${bz18}'," +  //备注项18
       "bz19 = '${bz19}' " +  //备注项19

        
        "where blockid='${blockid}' " )
    public  boolean updateByOneblockinfo(Oneblockinfo oneblockinfo);

}
