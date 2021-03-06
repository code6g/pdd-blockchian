package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 通道信息(channelinfo)的Mapper注解接口-ChannelinfoMapper
 */

@Mapper
public interface ChannelinfoMapper {

    /**
     * 通道信息的各类查询
     * @return
     */
    //根据查询所有的通道信息(channelinfo)。
    @Select("select * from channelinfo")
    List<Channelinfo> selectList();

    //根据channelinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from channelinfo where 1=1 "        //通道信息
            + " and tdid like '%${tdid}%'  " //通道编码
            + " and tdmc like '%${tdmc}%'  " //通道名称
            + " and pxjd like '%${pxjd}%'  " //排序节点
            + " and ddjd like '%${ddjd}%'  " //对等节点
            + " and sfgb like '%${sfgb}%'  " //是否关闭
            + " and sfcs like '%${sfcs}%'  " //是否初始化
            + " and csjk like '%${csjk}%'  " //创世纪块
            + " and qkxx like '%${qkxx}%'  " //区块信息
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
    public List<Channelinfo> selectListByChannelinfo(Channelinfo channelinfo);

    //根据tdid查询单个通道信息。
    @Select(" select * from channelinfo where tdid='${tdid}' limit 1 " )
    public  Channelinfo selectById(@Param("tdid") String tdid);

    //根据channelinfo查询单个通道信息。
    @Select(" select * from channelinfo where tdid='${tdid}' limit 1" )
    public  Channelinfo selectByChannelinfo(Channelinfo channelinfo);

    /**
     * 通道信息的各类新增
     * @return
     */
    //新增单个通道信息。
    @Insert(
            " insert into channelinfo(" +
            "tdid," +   //通道编码
            "tdmc," +   //通道名称
            "pxjd," +   //排序节点
            "ddjd," +   //对等节点
            "sfgb," +   //是否关闭
            "sfcs," +   //是否初始化
            "csjk," +   //创世纪块
            "qkxx," +   //区块信息
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
            "'${tdid}'," +  //通道编码
            "'${tdmc}'," +  //通道名称
            "'${pxjd}'," +  //排序节点
            "'${ddjd}'," +  //对等节点
            "'${sfgb}'," +  //是否关闭
            "'${sfcs}'," +  //是否初始化
            "'${csjk}'," +  //创世纪块
            "'${qkxx}'," +  //区块信息
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
    public  int insertChannelinfo(Channelinfo channelinfo);

    /**
     * 通道信息的各类删除
     * @return
     */
    //根据主键删除单个通道信息。
    @Delete(" delete from channelinfo where tdid='${tdid}' " )
    public  boolean deleteByTdid(@Param("tdid") String tdid);

    //根据实体信息删除单个通道信息。
    @Delete(" delete from channelinfo where tdid='${tdid}' " )
    public  boolean deleteByChannelinfo(Channelinfo channelinfo);

    //根据主键删除多个通道信息。
    @Delete(" delete from channelinfo where tdid in (${tdids}) " )
    public  boolean deleteByTdids(@Param("tdids")String tdids);

    /**
     * 通道信息的各类修改
     * @return
     */
    //根据实体修改单个通道信息。
    @Update(
        "update channelinfo set " +
       "tdid = '${tdid}'," +  //通道编码
       "tdmc = '${tdmc}'," +  //通道名称
       "pxjd = '${pxjd}'," +  //排序节点
       "ddjd = '${ddjd}'," +  //对等节点
       "sfgb = '${sfgb}'," +  //是否关闭
       "sfcs = '${sfcs}'," +  //是否初始化
       "csjk = '${csjk}'," +  //创世纪块
       "qkxx = '${qkxx}'," +  //区块信息
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

        
        "where tdid='${tdid}' " )
    public  boolean updateByChannelinfo(Channelinfo channelinfo);

}
