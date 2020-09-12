package com.ruoyi.pdd.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 会员信息(phyxx)的Mapper注解接口-PhyxxMapper
 */

@Mapper
public interface PhyxxMapper {

    /**
     * 会员信息的各类查询
     * @return
     */
    //根据查询所有的会员信息(phyxx)。
    @Select("select * from phyxx")
    List<Phyxx> selectList();

    //根据phyxx的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from phyxx where 1=1 "        //会员信息
            + " and hyid like '%${hyid}%'  " //会员编码
            + " and hyzh like '%${hyzh}%'  " //会员账号
            + " and hymm like '%${hymm}%'  " //会员密码
            + " and hymc like '%${hymc}%'  " //会员名称
            + " and hydz like '%${hydz}%'  " //会员地址
            + " and hydh like '%${hydh}%'  " //会员电话
            + " and hyzt like '%${hyzt}%'  " //会员状态
            + " and zcrq like '%${zcrq}%'  " //注册时间
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
    public List<Phyxx> selectListByPhyxx(Phyxx phyxx);



    //根据hyid查询单个会员信息。
    @Select(" select * from phyxx where hyid='${hyid}' limit 1 " )
    public  Phyxx selectById(@Param("hyid") String hyid);

    //根据phyxx查询单个会员信息。
    @Select(" select * from phyxx where hyid='${hyid}' limit 1" )
    public  Phyxx selectByPhyxx(Phyxx phyxx);

    /**
     * 会员信息的各类新增
     * @return
     */
    //新增单个会员信息。
    @Insert(
            " insert into phyxx(" +
            "hyid," +   //会员编码
            "hyzh," +   //会员账号
            "hymm," +   //会员密码
            "hymc," +   //会员名称
            "hydz," +   //会员地址
            "hydh," +   //会员电话
            "hyzt," +   //会员状态
            "zcrq," +   //注册时间
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
            "'${hyid}'," +  //会员编码
            "'${hyzh}'," +  //会员账号
            "'${hymm}'," +  //会员密码
            "'${hymc}'," +  //会员名称
            "'${hydz}'," +  //会员地址
            "'${hydh}'," +  //会员电话
            "'${hyzt}'," +  //会员状态
            "'${zcrq}'," +  //注册时间
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
    public  int insertPhyxx(Phyxx phyxx);

    /**
     * 会员信息的各类删除
     * @return
     */
    //根据主键删除单个会员信息。
    @Delete(" delete from phyxx where hyid='${hyid}' " )
    public  boolean deleteByHyid(@Param("hyid") String hyid);

    //根据实体信息删除单个会员信息。
    @Delete(" delete from phyxx where hyid='${hyid}' " )
    public  boolean deleteByPhyxx(Phyxx phyxx);

    //根据主键删除多个会员信息。
    @Delete(" delete from phyxx where hyid in (${hyids}) " )
    public  boolean deleteByHyids(@Param("hyids")String hyids);

    /**
     * 会员信息的各类修改
     * @return
     */
    //根据实体修改单个会员信息。
    @Update(
        "update phyxx set " +
       "hyid = '${hyid}'," +  //会员编码
       "hyzh = '${hyzh}'," +  //会员账号
       "hymm = '${hymm}'," +  //会员密码
       "hymc = '${hymc}'," +  //会员名称
       "hydz = '${hydz}'," +  //会员地址
       "hydh = '${hydh}'," +  //会员电话
       "hyzt = '${hyzt}'," +  //会员状态
       "zcrq = '${zcrq}'," +  //注册时间
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

        
        "where hyid='${hyid}' " )
    public  boolean updateByPhyxx(Phyxx phyxx);

}
