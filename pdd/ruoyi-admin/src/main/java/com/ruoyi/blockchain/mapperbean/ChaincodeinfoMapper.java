package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 链码（智能合约）(chaincodeinfo)的Mapper注解接口-ChaincodeinfoMapper
 */

@Mapper
public interface ChaincodeinfoMapper {

    /**
     * 链码（智能合约）的各类查询
     * @return
     */
    //根据查询所有的链码（智能合约）(chaincodeinfo)。
    @Select("select * from chaincodeinfo")
    List<Chaincodeinfo> selectList();

    //根据chaincodeinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from chaincodeinfo where 1=1 "        //链码（智能合约）
            + " and ccmc like '%${ccmc}%'  " //链码名称
            + " and ccbh like '%${ccbh}%'  " //链码编号
            + " and ccbb like '%${ccbb}%'  " //链码版本
            + " and cclj like '%${cclj}%'  " //链码路径
            + " and ccsr like '%${ccsr}%'  " //链码输入
            + " and ccsm like '%${ccsm}%'  " //链码说明
            + " and tdmc like '%${tdmc}%'  " //通道名称
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
    public List<Chaincodeinfo> selectListByChaincodeinfo(Chaincodeinfo chaincodeinfo);

    //根据ccmc查询单个链码（智能合约）。
    @Select(" select * from chaincodeinfo where ccmc='${ccmc}' limit 1 " )
    public  Chaincodeinfo selectById(@Param("ccmc") String ccmc);

    //根据chaincodeinfo查询单个链码（智能合约）。
    @Select(" select * from chaincodeinfo where ccmc='${ccmc}' limit 1" )
    public  Chaincodeinfo selectByChaincodeinfo(Chaincodeinfo chaincodeinfo);

    /**
     * 链码（智能合约）的各类新增
     * @return
     */
    //新增单个链码（智能合约）。
    @Insert(
            " insert into chaincodeinfo(" +
            "ccmc," +   //链码名称
            "ccbh," +   //链码编号
            "ccbb," +   //链码版本
            "cclj," +   //链码路径
            "ccsr," +   //链码输入
            "ccsm," +   //链码说明
            "tdmc," +   //通道名称
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
            "'${ccmc}'," +  //链码名称
            "'${ccbh}'," +  //链码编号
            "'${ccbb}'," +  //链码版本
            "'${cclj}'," +  //链码路径
            "'${ccsr}'," +  //链码输入
            "'${ccsm}'," +  //链码说明
            "'${tdmc}'," +  //通道名称
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
    public  int insertChaincodeinfo(Chaincodeinfo chaincodeinfo);

    /**
     * 链码（智能合约）的各类删除
     * @return
     */
    //根据主键删除单个链码（智能合约）。
    @Delete(" delete from chaincodeinfo where ccmc='${ccmc}' " )
    public  boolean deleteByCcmc(@Param("ccmc") String ccmc);

    //根据实体信息删除单个链码（智能合约）。
    @Delete(" delete from chaincodeinfo where ccmc='${ccmc}' " )
    public  boolean deleteByChaincodeinfo(Chaincodeinfo chaincodeinfo);

    //根据主键删除多个链码（智能合约）。
    @Delete(" delete from chaincodeinfo where ccmc in (${ccmcs}) " )
    public  boolean deleteByCcmcs(@Param("ccmcs")String ccmcs);

    /**
     * 链码（智能合约）的各类修改
     * @return
     */
    //根据实体修改单个链码（智能合约）。
    @Update(
        "update chaincodeinfo set " +
       "ccmc = '${ccmc}'," +  //链码名称
       "ccbh = '${ccbh}'," +  //链码编号
       "ccbb = '${ccbb}'," +  //链码版本
       "cclj = '${cclj}'," +  //链码路径
       "ccsr = '${ccsr}'," +  //链码输入
       "ccsm = '${ccsm}'," +  //链码说明
       "tdmc = '${tdmc}'," +  //通道名称
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

        
        "where ccmc='${ccmc}' " )
    public  boolean updateByChaincodeinfo(Chaincodeinfo chaincodeinfo);

}
