package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 链信息(chaininfo)的Mapper注解接口-ChaininfoMapper
 */

@Mapper
public interface ChaininfoMapper {

    /**
     * 链信息的各类查询
     * @return
     */
    //根据查询所有的链信息(chaininfo)。
    @Select("select * from chaininfo")
    List<Chaininfo> selectList();

    //根据chaininfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from chaininfo where 1=1 "        //链信息
            + " and lid like '%${lid}%'  " //链编码
            + " and lgd like '%${lgd}%'  " //链高度
            + " and dqhx like '%${dqhx}%'  " //当前块哈希
            + " and qkhx like '%${qkhx}%'  " //前块哈希
            + " and cccs like '%${cccs}%'  " //存储初始化
            + " and wzzd like '%${wzzd}%'  " //未知字段
            + " and ccdx like '%${ccdx}%'  " //存储大小
            + " and cchx like '%${cchx}%'  " //存储哈希
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
    public List<Chaininfo> selectListByChaininfo(Chaininfo chaininfo);

    //根据lid查询单个链信息。
    @Select(" select * from chaininfo where lid='${lid}' limit 1 " )
    public  Chaininfo selectById(@Param("lid") String lid);

    //根据chaininfo查询单个链信息。
    @Select(" select * from chaininfo where lid='${lid}' limit 1" )
    public  Chaininfo selectByChaininfo(Chaininfo chaininfo);

    /**
     * 链信息的各类新增
     * @return
     */
    //新增单个链信息。
    @Insert(
            " insert into chaininfo(" +
            "lid," +   //链编码
            "lgd," +   //链高度
            "dqhx," +   //当前块哈希
            "qkhx," +   //前块哈希
            "cccs," +   //存储初始化
            "wzzd," +   //未知字段
            "ccdx," +   //存储大小
            "cchx," +   //存储哈希
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
            "'${lid}'," +  //链编码
            "'${lgd}'," +  //链高度
            "'${dqhx}'," +  //当前块哈希
            "'${qkhx}'," +  //前块哈希
            "'${cccs}'," +  //存储初始化
            "'${wzzd}'," +  //未知字段
            "'${ccdx}'," +  //存储大小
            "'${cchx}'," +  //存储哈希
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
    public  int insertChaininfo(Chaininfo chaininfo);

    /**
     * 链信息的各类删除
     * @return
     */
    //根据主键删除单个链信息。
    @Delete(" delete from chaininfo where lid='${lid}' " )
    public  boolean deleteByLid(@Param("lid") String lid);

    //根据实体信息删除单个链信息。
    @Delete(" delete from chaininfo where lid='${lid}' " )
    public  boolean deleteByChaininfo(Chaininfo chaininfo);

    //根据主键删除多个链信息。
    @Delete(" delete from chaininfo where lid in (${lids}) " )
    public  boolean deleteByLids(@Param("lids")String lids);

    /**
     * 链信息的各类修改
     * @return
     */
    //根据实体修改单个链信息。
    @Update(
        "update chaininfo set " +
       "lid = '${lid}'," +  //链编码
       "lgd = '${lgd}'," +  //链高度
       "dqhx = '${dqhx}'," +  //当前块哈希
       "qkhx = '${qkhx}'," +  //前块哈希
       "cccs = '${cccs}'," +  //存储初始化
       "wzzd = '${wzzd}'," +  //未知字段
       "ccdx = '${ccdx}'," +  //存储大小
       "cchx = '${cchx}'," +  //存储哈希
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

        
        "where lid='${lid}' " )
    public  boolean updateByChaininfo(Chaininfo chaininfo);

}
