package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 排序节点(ordererinfo)的Mapper注解接口-OrdererinfoMapper
 */

@Mapper
public interface OrdererinfoMapper {

    /**
     * 排序节点的各类查询
     * @return
     */
    //根据查询所有的排序节点(ordererinfo)。
    @Select("select * from ordererinfo")
    List<Ordererinfo> selectList();

    //根据ordererinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from ordererinfo where 1=1 "        //排序节点
            + " and jdid like '%${jdid}%'  " //节点编码
            + " and jdmc like '%${jdmc}%'  " //节点名称
            + " and jdlj like '%${jdlj}%'  " //节点链接
            + " and jddz like '%${jddz}%'  " //节点地址
            + " and tdmc like '%${tdmc}%'  " //通道名称
            + " and fsgb like '%${fsgb}%'  " //是否关闭
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
    public List<Ordererinfo> selectListByOrdererinfo(Ordererinfo ordererinfo);

    //根据jdid查询单个排序节点。
    @Select(" select * from ordererinfo where jdid='${jdid}' limit 1 " )
    public  Ordererinfo selectById(@Param("jdid") String jdid);

    //根据ordererinfo查询单个排序节点。
    @Select(" select * from ordererinfo where jdid='${jdid}' limit 1" )
    public  Ordererinfo selectByOrdererinfo(Ordererinfo ordererinfo);

    /**
     * 排序节点的各类新增
     * @return
     */
    //新增单个排序节点。
    @Insert(
            " insert into ordererinfo(" +
            "jdid," +   //节点编码
            "jdmc," +   //节点名称
            "jdlj," +   //节点链接
            "jddz," +   //节点地址
            "tdmc," +   //通道名称
            "fsgb," +   //是否关闭
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
            "'${jdid}'," +  //节点编码
            "'${jdmc}'," +  //节点名称
            "'${jdlj}'," +  //节点链接
            "'${jddz}'," +  //节点地址
            "'${tdmc}'," +  //通道名称
            "'${fsgb}'," +  //是否关闭
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
    public  int insertOrdererinfo(Ordererinfo ordererinfo);

    /**
     * 排序节点的各类删除
     * @return
     */
    //根据主键删除单个排序节点。
    @Delete(" delete from ordererinfo where jdid='${jdid}' " )
    public  boolean deleteByJdid(@Param("jdid") String jdid);

    //根据实体信息删除单个排序节点。
    @Delete(" delete from ordererinfo where jdid='${jdid}' " )
    public  boolean deleteByOrdererinfo(Ordererinfo ordererinfo);

    //根据主键删除多个排序节点。
    @Delete(" delete from ordererinfo where jdid in (${jdids}) " )
    public  boolean deleteByJdids(@Param("jdids")String jdids);

    /**
     * 排序节点的各类修改
     * @return
     */
    //根据实体修改单个排序节点。
    @Update(
        "update ordererinfo set " +
       "jdid = '${jdid}'," +  //节点编码
       "jdmc = '${jdmc}'," +  //节点名称
       "jdlj = '${jdlj}'," +  //节点链接
       "jddz = '${jddz}'," +  //节点地址
       "tdmc = '${tdmc}'," +  //通道名称
       "fsgb = '${fsgb}'," +  //是否关闭
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

        
        "where jdid='${jdid}' " )
    public  boolean updateByOrdererinfo(Ordererinfo ordererinfo);

}
