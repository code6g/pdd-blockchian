package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 区块信息(blockinfo)的Mapper注解接口-BlockinfoMapper
 */

@Mapper
public interface BlockinfoMapper {

    /**
     * 区块信息的各类查询
     * @return
     */
    //根据查询所有的区块信息(blockinfo)。
    @Select("select * from blockinfo")
    List<Blockinfo> selectList();

    //根据blockinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from blockinfo where 1=1 "        //区块信息
            + " and tqkid like '%${tqkid}%'  " //头区块编号
            + " and tqkhx like '%${tqkhx}%'  " //头前块哈希
            + " and tsjhx like '%${tsjhx}%'  " //头数据哈希
            + " and twzzd like '%${twzzd}%'  " //头未知字段
            + " and tcccs like '%${tcccs}%'  " //头存储初始化
            + " and tccdx like '%${tccdx}%'  " //头存储大小
            + " and tcchx like '%${tcchx}%'  " //头存储哈希
            + " and sjnr like '%${sjnr}%'  " //数据内容
            + " and sjcccs like '%${sjcccs}%'  " //数据存储初始化
            + " and sjwzzd like '%${sjwzzd}%'  " //数据未知字段
            + " and sjccdx like '%${sjccdx}%'  " //数据存储大小
            + " and sjcchx like '%${sjcchx}%'  " //数据存储哈希
            + " and ysjnr like '%${ysjnr}%'  " //元数据内容
            + " and ysjcccs like '%${ysjcccs}%'  " //元数据存储初始化
            + " and ysjwzzd like '%${ysjwzzd}%'  " //元数据未知字段
            + " and ysjccdx like '%${ysjccdx}%'  " //元数据存储大小
            + " and ysscchx like '%${ysscchx}%'  " //元数据存储哈希
            + " and glk like '%${glk}%'  " //过滤块
            + " and jjcs like '%${jjcs}%'  " //交易次数
            + " and qksm like '%${qksm}%'  " //区块说明
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
    public List<Blockinfo> selectListByBlockinfo(Blockinfo blockinfo);

    //根据tqkid查询单个区块信息。
    @Select(" select * from blockinfo where tqkid='${tqkid}' limit 1 " )
    public  Blockinfo selectById(@Param("tqkid") String tqkid);

    //根据blockinfo查询单个区块信息。
    @Select(" select * from blockinfo where tqkid='${tqkid}' limit 1" )
    public  Blockinfo selectByBlockinfo(Blockinfo blockinfo);

    /**
     * 区块信息的各类新增
     * @return
     */
    //新增单个区块信息。
    @Insert(
            " insert into blockinfo(" +
            "tqkid," +   //头区块编号
            "tqkhx," +   //头前块哈希
            "tsjhx," +   //头数据哈希
            "twzzd," +   //头未知字段
            "tcccs," +   //头存储初始化
            "tccdx," +   //头存储大小
            "tcchx," +   //头存储哈希
            "sjnr," +   //数据内容
            "sjcccs," +   //数据存储初始化
            "sjwzzd," +   //数据未知字段
            "sjccdx," +   //数据存储大小
            "sjcchx," +   //数据存储哈希
            "ysjnr," +   //元数据内容
            "ysjcccs," +   //元数据存储初始化
            "ysjwzzd," +   //元数据未知字段
            "ysjccdx," +   //元数据存储大小
            "ysscchx," +   //元数据存储哈希
            "glk," +   //过滤块
            "jjcs," +   //交易次数
            "qksm," +   //区块说明
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
            "'${tqkid}'," +  //头区块编号
            "'${tqkhx}'," +  //头前块哈希
            "'${tsjhx}'," +  //头数据哈希
            "'${twzzd}'," +  //头未知字段
            "'${tcccs}'," +  //头存储初始化
            "'${tccdx}'," +  //头存储大小
            "'${tcchx}'," +  //头存储哈希
            "'${sjnr}'," +  //数据内容
            "'${sjcccs}'," +  //数据存储初始化
            "'${sjwzzd}'," +  //数据未知字段
            "'${sjccdx}'," +  //数据存储大小
            "'${sjcchx}'," +  //数据存储哈希
            "'${ysjnr}'," +  //元数据内容
            "'${ysjcccs}'," +  //元数据存储初始化
            "'${ysjwzzd}'," +  //元数据未知字段
            "'${ysjccdx}'," +  //元数据存储大小
            "'${ysscchx}'," +  //元数据存储哈希
            "'${glk}'," +  //过滤块
            "'${jjcs}'," +  //交易次数
            "'${qksm}'," +  //区块说明
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
    public  int insertBlockinfo(Blockinfo blockinfo);

    /**
     * 区块信息的各类删除
     * @return
     */
    //根据主键删除单个区块信息。
    @Delete(" delete from blockinfo where tqkid='${tqkid}' " )
    public  boolean deleteByTqkid(@Param("tqkid") String tqkid);

    //根据实体信息删除单个区块信息。
    @Delete(" delete from blockinfo where tqkid='${tqkid}' " )
    public  boolean deleteByBlockinfo(Blockinfo blockinfo);

    //根据主键删除多个区块信息。
    @Delete(" delete from blockinfo where tqkid in (${tqkids}) " )
    public  boolean deleteByTqkids(@Param("tqkids")String tqkids);

    /**
     * 区块信息的各类修改
     * @return
     */
    //根据实体修改单个区块信息。
    @Update(
        "update blockinfo set " +
       "tqkid = '${tqkid}'," +  //头区块编号
       "tqkhx = '${tqkhx}'," +  //头前块哈希
       "tsjhx = '${tsjhx}'," +  //头数据哈希
       "twzzd = '${twzzd}'," +  //头未知字段
       "tcccs = '${tcccs}'," +  //头存储初始化
       "tccdx = '${tccdx}'," +  //头存储大小
       "tcchx = '${tcchx}'," +  //头存储哈希
       "sjnr = '${sjnr}'," +  //数据内容
       "sjcccs = '${sjcccs}'," +  //数据存储初始化
       "sjwzzd = '${sjwzzd}'," +  //数据未知字段
       "sjccdx = '${sjccdx}'," +  //数据存储大小
       "sjcchx = '${sjcchx}'," +  //数据存储哈希
       "ysjnr = '${ysjnr}'," +  //元数据内容
       "ysjcccs = '${ysjcccs}'," +  //元数据存储初始化
       "ysjwzzd = '${ysjwzzd}'," +  //元数据未知字段
       "ysjccdx = '${ysjccdx}'," +  //元数据存储大小
       "ysscchx = '${ysscchx}'," +  //元数据存储哈希
       "glk = '${glk}'," +  //过滤块
       "jjcs = '${jjcs}'," +  //交易次数
       "qksm = '${qksm}'," +  //区块说明
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

        
        "where tqkid='${tqkid}' " )
    public  boolean updateByBlockinfo(Blockinfo blockinfo);

}
