package com.ruoyi.pdd.mapperbean;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * 订单信息(pddxx)的Mapper注解接口-PddxxMapper
 */

@Mapper
public interface PddxxMapper {

    /**
     * 订单信息的各类查询
     * @return
     */
    //根据查询所有的订单信息(pddxx)。
    @Select("select * from pddxx")
    List<Pddxx> selectList();

    //根据pddxx的属性进行组合条件查询，排序和分页通过pagehelper，在controller里调用时，自动实现了。
    @Select(
             " select * from pddxx where 1=1 "        //订单信息
            + " and ddid like '%${ddid}%'  " //订单编码
            + " and ddbh like '%${ddbh}%'  " //订单编号
            + " and hyid like '%${hyid}%'  " //会员编码
            + " and hymc like '%${hymc}%'  " //会员名称
            + " and spsl like '%${spsl}%'  " //商品数量
            + " and jgzj like '%${jgzj}%'  " //价格总计
            + " and ddzt like '%${ddzt}%'  " //订单状态
            + " and xdsj like '%${xdsj}%'  " //下单时间
            + " and xdsm like '%${xdsm}%'  " //下单说明
            + " and zfqd like '%${zfqd}%'  " //支付渠道
            + " and zfzh like '%${zfzh}%'  " //支付账号
            + " and zfry like '%${zfry}%'  " //支付人
            + " and zfsj like '%${zfsj}%'  " //支付时间
            + " and zfsm like '%${zfsm}%'  " //支付说明
            + " and shry like '%${shry}%'  " //收货人
            + " and shdz like '%${shdz}%'  " //收货地址
            + " and shdh like '%${shdh}%'  " //收货人电话
            + " and psry like '%${psry}%'  " //配送人
            + " and pssj like '%${pssj}%'  " //配送时间
            + " and pssm like '%${pssm}%'  " //配送说明
            + " and qsry like '%${qsry}%'  " //签收人
            + " and qssj like '%${qssj}%'  " //签收时间
            + " and qssm like '%${qssm}%'  " //签收说明
            + " and pjnr like '%${pjnr}%'  " //评价内容
            + " and pjsj like '%${pjsj}%'  " //评价时间
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
    public List<Pddxx> selectListByPddxx(Pddxx pddxx);

    //根据ddid查询单个订单信息。
    @Select(" select * from pddxx where ddid='${ddid}' limit 1 " )
    public  Pddxx selectById(@Param("ddid") String ddid);

    //根据pddxx查询单个订单信息。
    @Select(" select * from pddxx where ddid='${ddid}' limit 1" )
    public  Pddxx selectByPddxx(Pddxx pddxx);

    /**
     * 订单信息的各类新增
     * @return
     */
    //新增单个订单信息。
    @Insert(
            " insert into pddxx(" +
            "ddid," +   //订单编码
            "ddbh," +   //订单编号
            "hyid," +   //会员编码
            "hymc," +   //会员名称
            "spsl," +   //商品数量
            "jgzj," +   //价格总计
            "ddzt," +   //订单状态
            "xdsj," +   //下单时间
            "xdsm," +   //下单说明
            "zfqd," +   //支付渠道
            "zfzh," +   //支付账号
            "zfry," +   //支付人
            "zfsj," +   //支付时间
            "zfsm," +   //支付说明
            "shry," +   //收货人
            "shdz," +   //收货地址
            "shdh," +   //收货人电话
            "psry," +   //配送人
            "pssj," +   //配送时间
            "pssm," +   //配送说明
            "qsry," +   //签收人
            "qssj," +   //签收时间
            "qssm," +   //签收说明
            "pjnr," +   //评价内容
            "pjsj," +   //评价时间
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
            "'${ddid}'," +  //订单编码
            "'${ddbh}'," +  //订单编号
            "'${hyid}'," +  //会员编码
            "'${hymc}'," +  //会员名称
            "'${spsl}'," +  //商品数量
            "'${jgzj}'," +  //价格总计
            "'${ddzt}'," +  //订单状态
            "'${xdsj}'," +  //下单时间
            "'${xdsm}'," +  //下单说明
            "'${zfqd}'," +  //支付渠道
            "'${zfzh}'," +  //支付账号
            "'${zfry}'," +  //支付人
            "'${zfsj}'," +  //支付时间
            "'${zfsm}'," +  //支付说明
            "'${shry}'," +  //收货人
            "'${shdz}'," +  //收货地址
            "'${shdh}'," +  //收货人电话
            "'${psry}'," +  //配送人
            "'${pssj}'," +  //配送时间
            "'${pssm}'," +  //配送说明
            "'${qsry}'," +  //签收人
            "'${qssj}'," +  //签收时间
            "'${qssm}'," +  //签收说明
            "'${pjnr}'," +  //评价内容
            "'${pjsj}'," +  //评价时间
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
    public  int insertPddxx(Pddxx pddxx);

    /**
     * 订单信息的各类删除
     * @return
     */
    //根据主键删除单个订单信息。
    @Delete(" delete from pddxx where ddid='${ddid}' " )
    public  boolean deleteByDdid(@Param("ddid") String ddid);

    //根据实体信息删除单个订单信息。
    @Delete(" delete from pddxx where ddid='${ddid}' " )
    public  boolean deleteByPddxx(Pddxx pddxx);

    //根据主键删除多个订单信息。
    @Delete(" delete from pddxx where ddid in (${ddids}) " )
    public  boolean deleteByDdids(@Param("ddids")String ddids);

    /**
     * 订单信息的各类修改
     * @return
     */
    //根据实体修改单个订单信息。
    @Update(
        "update pddxx set " +
       "ddid = '${ddid}'," +  //订单编码
       "ddbh = '${ddbh}'," +  //订单编号
       "hyid = '${hyid}'," +  //会员编码
       "hymc = '${hymc}'," +  //会员名称
       "spsl = '${spsl}'," +  //商品数量
       "jgzj = '${jgzj}'," +  //价格总计
       "ddzt = '${ddzt}'," +  //订单状态
       "xdsj = '${xdsj}'," +  //下单时间
       "xdsm = '${xdsm}'," +  //下单说明
       "zfqd = '${zfqd}'," +  //支付渠道
       "zfzh = '${zfzh}'," +  //支付账号
       "zfry = '${zfry}'," +  //支付人
       "zfsj = '${zfsj}'," +  //支付时间
       "zfsm = '${zfsm}'," +  //支付说明
       "shry = '${shry}'," +  //收货人
       "shdz = '${shdz}'," +  //收货地址
       "shdh = '${shdh}'," +  //收货人电话
       "psry = '${psry}'," +  //配送人
       "pssj = '${pssj}'," +  //配送时间
       "pssm = '${pssm}'," +  //配送说明
       "qsry = '${qsry}'," +  //签收人
       "qssj = '${qssj}'," +  //签收时间
       "qssm = '${qssm}'," +  //签收说明
       "pjnr = '${pjnr}'," +  //评价内容
       "pjsj = '${pjsj}'," +  //评价时间
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

        
        "where ddid='${ddid}' " )
    public  boolean updateByPddxx(Pddxx pddxx);

}
