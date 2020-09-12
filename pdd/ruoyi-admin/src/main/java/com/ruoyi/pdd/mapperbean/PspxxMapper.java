package com.ruoyi.pdd.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 商品信息(pspxx)的Mapper注解接口-PspxxMapper
 */

@Mapper
public interface PspxxMapper {

    /**
     * 商品信息的各类查询
     * @return
     */
    //根据查询所有的商品信息(pspxx)。
    @Select("select * from pspxx")
    List<Pspxx> selectList();

    //根据pspxx的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from pspxx where 1=1 "        //商品信息
            + " and spid like '%${spid}%'  " //商品编码
            + " and flid like '%${flid}%'  " //分类编码
            + " and flmc like '%${flmc}%'  " //分类名称
            + " and spmc like '%${spmc}%'  " //商品名称
            + " and spjg like '%${spjg}%'  " //商品价格
            + " and spms like '%${spms}%'  " //商品描述
            + " and sptp like '%${sptp}%'  " //商品图片
            + " and spzt like '%${spzt}%'  " //商品状态
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
    public List<Pspxx> selectListByPspxx(Pspxx pspxx);

    //根据spid查询单个商品信息。
    @Select(" select * from pspxx where spid='${spid}' limit 1 " )
    public  Pspxx selectById(@Param("spid") String spid);

    //根据pspxx查询单个商品信息。
    @Select(" select * from pspxx where spid='${spid}' limit 1" )
    public  Pspxx selectByPspxx(Pspxx pspxx);

    /**
     * 商品信息的各类新增
     * @return
     */
    //新增单个商品信息。
    @Insert(
            " insert into pspxx(" +
            "spid," +   //商品编码
            "flid," +   //分类编码
            "flmc," +   //分类名称
            "spmc," +   //商品名称
            "spjg," +   //商品价格
            "spms," +   //商品描述
            "sptp," +   //商品图片
            "spzt," +   //商品状态
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
            "'${spid}'," +  //商品编码
            "'${flid}'," +  //分类编码
            "'${flmc}'," +  //分类名称
            "'${spmc}'," +  //商品名称
            "'${spjg}'," +  //商品价格
            "'${spms}'," +  //商品描述
            "'${sptp}'," +  //商品图片
            "'${spzt}'," +  //商品状态
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
    public  int insertPspxx(Pspxx pspxx);

    /**
     * 商品信息的各类删除
     * @return
     */
    //根据主键删除单个商品信息。
    @Delete(" delete from pspxx where spid='${spid}' " )
    public  boolean deleteBySpid(@Param("spid") String spid);

    //根据实体信息删除单个商品信息。
    @Delete(" delete from pspxx where spid='${spid}' " )
    public  boolean deleteByPspxx(Pspxx pspxx);

    //根据主键删除多个商品信息。
    @Delete(" delete from pspxx where spid in (${spids}) " )
    public  boolean deleteBySpids(@Param("spids")String spids);

    /**
     * 商品信息的各类修改
     * @return
     */
    //根据实体修改单个商品信息。
    @Update(
        "update pspxx set " +
       "spid = '${spid}'," +  //商品编码
       "flid = '${flid}'," +  //分类编码
       "flmc = '${flmc}'," +  //分类名称
       "spmc = '${spmc}'," +  //商品名称
       "spjg = '${spjg}'," +  //商品价格
       "spms = '${spms}'," +  //商品描述
       "sptp = '${sptp}'," +  //商品图片
       "spzt = '${spzt}'," +  //商品状态
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

        
        "where spid='${spid}' " )
    public  boolean updateByPspxx(Pspxx pspxx);

}
