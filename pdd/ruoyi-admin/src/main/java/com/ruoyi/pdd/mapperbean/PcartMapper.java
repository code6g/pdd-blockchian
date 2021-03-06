package com.ruoyi.pdd.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 购物车(pcart)的Mapper注解接口-PcartMapper
 */

@Mapper
public interface PcartMapper {

    /**
     * 购物车的各类查询
     * @return
     */
    //根据查询所有的购物车(pcart)。
    @Select("select * from pcart")
    List<Pcart> selectList();

    //根据pcart的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from pcart where 1=1 "        //购物车
            + " and gwid like '%${gwid}%'  " //购物车编码
            + " and hyid like '%${hyid}%'  " //会员编码
            + " and spid like '%${spid}%'  " //商品编码
            + " and spmc like '%${spmc}%'  " //商品名称
            + " and spsl like '%${spsl}%'  " //商品数量
            + " and spjg like '%${spjg}%'  " //商品价格
            + " and jgxj like '%${jgxj}%'  " //价格小计
            + " and mxsm like '%${mxsm}%'  " //明细说明
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
    public List<Pcart> selectListByPcart(Pcart pcart);

    //根据gwid查询单个购物车。
    @Select(" select * from pcart where gwid='${gwid}' limit 1 " )
    public  Pcart selectById(@Param("gwid") String gwid);

    //根据pcart查询单个购物车。
    @Select(" select * from pcart where gwid='${gwid}' limit 1" )
    public  Pcart selectByPcart(Pcart pcart);

    /**
     * 购物车的各类新增
     * @return
     */
    //新增单个购物车。
    @Insert(
            " insert into pcart(" +
            "gwid," +   //购物车编码
            "hyid," +   //会员编码
            "spid," +   //商品编码
            "spmc," +   //商品名称
            "spsl," +   //商品数量
            "spjg," +   //商品价格
            "jgxj," +   //价格小计
            "mxsm," +   //明细说明
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
            "'${gwid}'," +  //购物车编码
            "'${hyid}'," +  //会员编码
            "'${spid}'," +  //商品编码
            "'${spmc}'," +  //商品名称
            "'${spsl}'," +  //商品数量
            "'${spjg}'," +  //商品价格
            "'${jgxj}'," +  //价格小计
            "'${mxsm}'," +  //明细说明
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
    public  int insertPcart(Pcart pcart);

    /**
     * 购物车的各类删除
     * @return
     */
    //根据主键删除单个购物车。
    @Delete(" delete from pcart where gwid='${gwid}' " )
    public  boolean deleteByGwid(@Param("gwid") String gwid);

    //根据实体信息删除单个购物车。
    @Delete(" delete from pcart where gwid='${gwid}' " )
    public  boolean deleteByPcart(Pcart pcart);

    //根据主键删除多个购物车。
    @Delete(" delete from pcart where gwid in (${gwids}) " )
    public  boolean deleteByGwids(@Param("gwids")String gwids);

    /**
     * 购物车的各类修改
     * @return
     */
    //根据实体修改单个购物车。
    @Update(
        "update pcart set " +
       "gwid = '${gwid}'," +  //购物车编码
       "hyid = '${hyid}'," +  //会员编码
       "spid = '${spid}'," +  //商品编码
       "spmc = '${spmc}'," +  //商品名称
       "spsl = '${spsl}'," +  //商品数量
       "spjg = '${spjg}'," +  //商品价格
       "jgxj = '${jgxj}'," +  //价格小计
       "mxsm = '${mxsm}'," +  //明细说明
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

        
        "where gwid='${gwid}' " )
    public  boolean updateByPcart(Pcart pcart);

}
