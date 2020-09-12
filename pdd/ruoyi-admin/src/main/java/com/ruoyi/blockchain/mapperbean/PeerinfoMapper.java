package com.ruoyi.blockchain.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 对等节点(peerinfo)的Mapper注解接口-PeerinfoMapper
 */

@Mapper
public interface PeerinfoMapper {

    /**
     * 对等节点的各类查询
     * @return
     */
    //根据查询所有的对等节点(peerinfo)。
    @Select("select * from peerinfo")
    List<Peerinfo> selectList();

    //根据peerinfo的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from peerinfo where 1=1 "        //对等节点
            + " and jdid like '%${jdid}%'  " //节点编码
            + " and jdmc like '%${jdmc}%'  " //节点名称
            + " and jdlj like '%${jdlj}%'  " //节点链接
            + " and jddz like '%${jddz}%'  " //节点地址
            + " and sfgb like '%${sfgb}%'  " //是否关闭
            + " and tdmc like '%${tdmc}%'  " //通道名称
            + " and qkhm like '%${qkhm}%'  " //区块号码
            + " and sflj like '%${sflj}%'  " //是否连接
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
    public List<Peerinfo> selectListByPeerinfo(Peerinfo peerinfo);

    //根据jdid查询单个对等节点。
    @Select(" select * from peerinfo where jdid='${jdid}' limit 1 " )
    public  Peerinfo selectById(@Param("jdid") String jdid);

    //根据peerinfo查询单个对等节点。
    @Select(" select * from peerinfo where jdid='${jdid}' limit 1" )
    public  Peerinfo selectByPeerinfo(Peerinfo peerinfo);

    /**
     * 对等节点的各类新增
     * @return
     */
    //新增单个对等节点。
    @Insert(
            " insert into peerinfo(" +
            "jdid," +   //节点编码
            "jdmc," +   //节点名称
            "jdlj," +   //节点链接
            "jddz," +   //节点地址
            "sfgb," +   //是否关闭
            "tdmc," +   //通道名称
            "qkhm," +   //区块号码
            "sflj," +   //是否连接
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
            "'${sfgb}'," +  //是否关闭
            "'${tdmc}'," +  //通道名称
            "'${qkhm}'," +  //区块号码
            "'${sflj}'," +  //是否连接
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
    public  int insertPeerinfo(Peerinfo peerinfo);

    /**
     * 对等节点的各类删除
     * @return
     */
    //根据主键删除单个对等节点。
    @Delete(" delete from peerinfo where jdid='${jdid}' " )
    public  boolean deleteByJdid(@Param("jdid") String jdid);

    //根据实体信息删除单个对等节点。
    @Delete(" delete from peerinfo where jdid='${jdid}' " )
    public  boolean deleteByPeerinfo(Peerinfo peerinfo);

    //根据主键删除多个对等节点。
    @Delete(" delete from peerinfo where jdid in (${jdids}) " )
    public  boolean deleteByJdids(@Param("jdids")String jdids);

    /**
     * 对等节点的各类修改
     * @return
     */
    //根据实体修改单个对等节点。
    @Update(
        "update peerinfo set " +
       "jdid = '${jdid}'," +  //节点编码
       "jdmc = '${jdmc}'," +  //节点名称
       "jdlj = '${jdlj}'," +  //节点链接
       "jddz = '${jddz}'," +  //节点地址
       "sfgb = '${sfgb}'," +  //是否关闭
       "tdmc = '${tdmc}'," +  //通道名称
       "qkhm = '${qkhm}'," +  //区块号码
       "sflj = '${sflj}'," +  //是否连接
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
    public  boolean updateByPeerinfo(Peerinfo peerinfo);

}
