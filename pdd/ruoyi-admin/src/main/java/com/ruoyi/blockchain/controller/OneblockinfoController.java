package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Oneblockinfo;
import com.ruoyi.blockchain.mapperbean.OneblockinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.blockchain.tool.BlockDataOper;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import com.ruoyi.common.json.JSONObject;
import javafx.util.converter.ByteStringConverter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.servlet.ModelAndView;

/**
 * 单区块信息(oneblockinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/oneblockinfo")                      //区块链/单区块信息
public class OneblockinfoController extends BaseController {

    private String prefix = "blockchain/oneblockinfo";          //url:区块链/单区块信息

    @Autowired
    private OneblockinfoMapper oneblockinfoMapper;              //单区块信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 单区块信息(oneblockinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:oneblockinfo:view")      //shiro权限：区块链:单区块信息:查看
    @GetMapping()                                           //url:blockchain/oneblockinfo
    public String toListOneblockinfo(ModelMap mmap){          //单区块信息(oneblockinfo).跳转到列表页面。从url:/blockchain/oneblockinfo跳转-->页面:blockchain/oneblockinfo/oneblockinfo.html
        Oneblockinfo oneblockinfo =getToListOneblockinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("oneblockinfo", oneblockinfo);                 // 单区块信息(oneblockinfo)放入ModelMap
        return prefix + "/oneblockinfo";                      // 重定向到页面：blockchain/oneblockinfo/oneblockinfo.html
    }

    private Oneblockinfo getToListOneblockinfoCon() {           //单区块信息(oneblockinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Oneblockinfo oneblockinfo=new Oneblockinfo();             //创建 单区块信息(oneblockinfo)
            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:oneblockinfo:list")      // shiro权限：区块链:单区块信息:列表
    @PostMapping("/list")                                   // url:/blockchain/oneblockinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistOneblockinfo(Oneblockinfo oneblockinfo){    //单区块信息(oneblockinfo)：根据页面的form的数据组装oneblockinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        oneblockinfo=getNewOneblockinfoCon(oneblockinfo);                     //修改单区块信息实体，构造新的查询条件
//        List<Oneblockinfo> list = oneblockinfoMapper.selectListByOneblockinfo(oneblockinfo);   //根据单区块信息(oneblockinfo)的属性，构造条件，查询出单区块信息list
//        list=getNewOneblockinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现


        List list=new ArrayList();
        long blockHeight=BlockChainTool.getBlockchainInfo().getHeight();
        for(int i=0;i<blockHeight;i++){
            try {

                BlockInfo bi = BlockChainTool.getChannel().queryBlockByNumber(i);
                Oneblockinfo aOneblockinfo=new Oneblockinfo();
                aOneblockinfo.setBlockid(                    String .valueOf(bi.getBlockNumber())); //块编码
                aOneblockinfo.setBlocknumber(                String .valueOf(bi.getBlockNumber())); //块编号

                aOneblockinfo.setDatahash(                   BlockDataOper.bytesToHexString(bi.getDataHash())); //数据哈希
                aOneblockinfo.setPrevioushash(               BlockDataOper.bytesToHexString(bi.getPreviousHash())); //前块哈希

                aOneblockinfo.setChannelid(                  bi.getChannelId()); //通道
                aOneblockinfo.setEnvelopecount(              String .valueOf(bi.getEnvelopeCount())); //信封数量
                aOneblockinfo.setEnvelopeinfos(              bi.getEnvelopeInfos().toString()); //信封内容


                aOneblockinfo.setTransactioncount(           String .valueOf(bi.getTransactionCount())); //交易数量
                aOneblockinfo.setTransactionsmetadata(       BlockDataOper.bytes2String(bi.getTransActionsMetaData())); //交易元数据
                aOneblockinfo.setBlockallfields(             bi.getBlock().getAllFields().toString()); //区块所有字段
                aOneblockinfo.setBlockheaderprevioushash(    BlockDataOper.ByteString2String16Hex( bi.getBlock().getHeader().getPreviousHash())); //区块头前哈希
                aOneblockinfo.setBlockheaderdatahash(        BlockDataOper.ByteString2String16Hex( bi.getBlock().getHeader().getDataHash())); //区块头数据哈希
                aOneblockinfo.setBlockheaderserializedsize(  String .valueOf(bi.getBlock().getHeader().getSerializedSize())); //区块头序列化尺寸
                aOneblockinfo.setBlockheadernumber(          String .valueOf(bi.getBlock().getHeader().getNumber())); //区块头编号
                aOneblockinfo.setBlockheaderallfields(       bi.getBlock().getHeader().getAllFields().toString()); //区块头所有字段
                aOneblockinfo.setBlockdatadatacount(         String .valueOf(bi.getBlock().getData().getDataCount())); //区块体数据量

                aOneblockinfo.setBlockdatadatalist(         BlockDataOper.getDataListString(bi.getBlock().getData().getDataList())); //区块体数据列表

                aOneblockinfo.setBlockdataallfields(         bi.getBlock().getData().getAllFields().toString()); //区块体所有字段

//                bi.getBlockNumber()
//                bi.getDataHash()
//                bi.getPreviousHash()
//                bi.getChannelId()
//                bi.getEnvelopeCount()
//                bi.getEnvelopeInfos()
//                bi.getTransactionCount()
//                bi.getTransActionsMetaData()
//                bi.getBlock().getAllFields()
//                bi.getBlock().getHeader().getPreviousHash()
//                bi.getBlock().getHeader().getDataHash()
//                bi.getBlock().getHeader().getSerializedSize()
//                bi.getBlock().getHeader().getNumber()
//                bi.getBlock().getHeader().getAllFields()
//                bi.getBlock().getData().getDataCount()
//                bi.getBlock().getData().getDataList()
//                bi.getBlock().getData().getAllFields()

//                bi.getBlockNumber();
//                bi.getDataHash();
//                bi.getPreviousHash();
//                bi.getChannelId();
//                bi.getEnvelopeCount();
//                bi.getEnvelopeInfos();
//                bi.getTransactionCount();
//                bi.getTransActionsMetaData();
//                bi.getBlock().getAllFields();
//                bi.getBlock().getHeader().getPreviousHash();
//                bi.getBlock().getHeader().getDataHash();
//                bi.getBlock().getHeader().getSerializedSize();
//                bi.getBlock().getHeader().getNumber();
//                bi.getBlock().getHeader().getAllFields();
//                bi.getBlock().getData().getDataCount();
//                bi.getBlock().getData().getDataList();
//                bi.getBlock().getData().getAllFields();



                list.add(aOneblockinfo);
            }catch (Exception e2){

            }

        }



        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }


    //----2.1. 查询数据，返回给列表页面--common list
    //@RequiresPermissions("blockchain:oneblockinfo:list")      // shiro权限：区块链:单区块信息:列表
    @PostMapping("/listcom")                                   // url:/blockchain/oneblockinfo/list
    @ResponseBody                                           //返回restful-json
    public JSONObject.JSONArray querylistOneblockinfocom(){    //单区块信息(oneblockinfo)：根据页面的form的数据组装oneblockinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        oneblockinfo=getNewOneblockinfoCon(oneblockinfo);                     //修改单区块信息实体，构造新的查询条件
//        List<Oneblockinfo> list = oneblockinfoMapper.selectListByOneblockinfo(oneblockinfo);   //根据单区块信息(oneblockinfo)的属性，构造条件，查询出单区块信息list
//        list=getNewOneblockinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现


        JSONObject.JSONArray list=new JSONObject.JSONArray();
        long blockHeight=BlockChainTool.getBlockchainInfo().getHeight();
        for(int i=0;i<blockHeight;i++){
            try {

                BlockInfo bi = BlockChainTool.getChannel().queryBlockByNumber(i);
                Oneblockinfo aOneblockinfo=new Oneblockinfo();
                aOneblockinfo.setBlockid(                    String .valueOf(bi.getBlockNumber())); //块编码
                aOneblockinfo.setBlocknumber(                String .valueOf(bi.getBlockNumber())); //块编号

                aOneblockinfo.setDatahash(                   BlockDataOper.bytesToHexString(bi.getDataHash())); //数据哈希
                aOneblockinfo.setPrevioushash(               BlockDataOper.bytesToHexString(bi.getPreviousHash())); //前块哈希

                aOneblockinfo.setChannelid(                  bi.getChannelId()); //通道
                aOneblockinfo.setEnvelopecount(              String .valueOf(bi.getEnvelopeCount())); //信封数量
                aOneblockinfo.setEnvelopeinfos(              bi.getEnvelopeInfos().toString()); //信封内容


                aOneblockinfo.setTransactioncount(           String .valueOf(bi.getTransactionCount())); //交易数量
                aOneblockinfo.setTransactionsmetadata(       BlockDataOper.bytes2String(bi.getTransActionsMetaData())); //交易元数据
                aOneblockinfo.setBlockallfields(             bi.getBlock().getAllFields().toString()); //区块所有字段
                aOneblockinfo.setBlockheaderprevioushash(    BlockDataOper.ByteString2String16Hex( bi.getBlock().getHeader().getPreviousHash())); //区块头前哈希
                aOneblockinfo.setBlockheaderdatahash(        BlockDataOper.ByteString2String16Hex( bi.getBlock().getHeader().getDataHash())); //区块头数据哈希
                aOneblockinfo.setBlockheaderserializedsize(  String .valueOf(bi.getBlock().getHeader().getSerializedSize())); //区块头序列化尺寸
                aOneblockinfo.setBlockheadernumber(          String .valueOf(bi.getBlock().getHeader().getNumber())); //区块头编号
                aOneblockinfo.setBlockheaderallfields(       bi.getBlock().getHeader().getAllFields().toString()); //区块头所有字段
                aOneblockinfo.setBlockdatadatacount(         String .valueOf(bi.getBlock().getData().getDataCount())); //区块体数据量

                aOneblockinfo.setBlockdatadatalist(         BlockDataOper.getDataListString(bi.getBlock().getData().getDataList())); //区块体数据列表

                aOneblockinfo.setBlockdataallfields(         bi.getBlock().getData().getAllFields().toString()); //区块体所有字段

//                bi.getBlockNumber()
//                bi.getDataHash()
//                bi.getPreviousHash()
//                bi.getChannelId()
//                bi.getEnvelopeCount()
//                bi.getEnvelopeInfos()
//                bi.getTransactionCount()
//                bi.getTransActionsMetaData()
//                bi.getBlock().getAllFields()
//                bi.getBlock().getHeader().getPreviousHash()
//                bi.getBlock().getHeader().getDataHash()
//                bi.getBlock().getHeader().getSerializedSize()
//                bi.getBlock().getHeader().getNumber()
//                bi.getBlock().getHeader().getAllFields()
//                bi.getBlock().getData().getDataCount()
//                bi.getBlock().getData().getDataList()
//                bi.getBlock().getData().getAllFields()

//                bi.getBlockNumber();
//                bi.getDataHash();
//                bi.getPreviousHash();
//                bi.getChannelId();
//                bi.getEnvelopeCount();
//                bi.getEnvelopeInfos();
//                bi.getTransactionCount();
//                bi.getTransActionsMetaData();
//                bi.getBlock().getAllFields();
//                bi.getBlock().getHeader().getPreviousHash();
//                bi.getBlock().getHeader().getDataHash();
//                bi.getBlock().getHeader().getSerializedSize();
//                bi.getBlock().getHeader().getNumber();
//                bi.getBlock().getHeader().getAllFields();
//                bi.getBlock().getData().getDataCount();
//                bi.getBlock().getData().getDataList();
//                bi.getBlock().getData().getAllFields();



                list.add(aOneblockinfo);
            }catch (Exception e2){

            }

        }

        return list;


        //ModelAndView mv=new ModelAndView();
        //mv.addObject("listOneblockinfoCom",list);

        //return // mv;                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Oneblockinfo getNewOneblockinfoCon(Oneblockinfo oneblockinfo) {                    //修改单区块信息实体，构造新的查询条件

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }
    private List<Oneblockinfo> getNewOneblockinfoList(List<Oneblockinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Oneblockinfo oneblockinfo:list){
            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 单区块信息(oneblockinfo).列表查询

    //====begin 单区块信息(oneblockinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/oneblockinfo/add的get方法， 跳转到url:blockchain/oneblockinfo/add.html
    public String toAddOneblockinfo(ModelMap mmap)
    {
        Oneblockinfo oneblockinfo = getInitOneblockinfoAdd();    // 创建oneblockinfo，并赋值，作为新增单区块信息的初始化值
        mmap.put("oneblockinfo", oneblockinfo);                // 单区块信息(oneblockinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/oneblockinfo/add
    }

    private Oneblockinfo getInitOneblockinfoAdd() {            // 创建oneblockinfo，并赋值，作为新增单区块信息的初始化值
        Oneblockinfo oneblockinfo=new Oneblockinfo();            //初始化单区块信息实体
        String ts=InputUitl.getTimestamp();
        oneblockinfo.setBlockid(ts);                            //blockid

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19

        return oneblockinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:oneblockinfo:add")                 //shiro权限：区块链:单区块信息:新增
    @Log(title = "单区块信息", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交oneblockinfo的接口url:/blockchain/oneblockinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveOneblockinfo(Oneblockinfo oneblockinfo)        //根据传入的oneblockinfo，持久化，返回结果restful-json
    {
        oneblockinfo=getNewAddOneblockinfo(oneblockinfo);                    //对新增后的的单区块信息对象进行赋值更新操作
        return toAjax(oneblockinfoMapper.insertOneblockinfo(oneblockinfo));  //持久化
    }

    private Oneblockinfo getNewAddOneblockinfo(Oneblockinfo oneblockinfo) {    //对新增后的的单区块信息对象进行赋值更新操作

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }
    //====end 单区块信息(oneblockinfo).新增


    //====begin 单区块信息(oneblockinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{blockid}")                                                        //从url:/blockchain/oneblockinfo/edit/{blockid}的get方法， 跳转到页面：blockchain/oneblockinfo/edit.html
    public String toEditOneblockinfo(@PathVariable("blockid") String blockid, ModelMap mmap)   //根据传入的blockid，查询数据，放入ModelMap，返回给页面
    {
        Oneblockinfo oneblockinfo = oneblockinfoMapper.selectById(blockid);                     //根据传入的blockid，查询单区块信息(oneblockinfo)
        oneblockinfo=getNewEditInitOneblockinfo(oneblockinfo);                               //对查询出来的单区块信息对象进行赋值操作
        mmap.put("oneblockinfo", oneblockinfo);                                            //单区块信息(oneblockinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/oneblockinfo/edit.html
    }

    private Oneblockinfo getNewEditInitOneblockinfo(Oneblockinfo oneblockinfo) {               //对查询出来的单区块信息对象进行赋值操作

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:oneblockinfo:edit")                  //shiro权限：区块链:单区块信息:修改
    @Log(title = "单区块信息", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交oneblockinfo的接口url:/blockchain/oneblockinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveOneblockinfo(Oneblockinfo oneblockinfo)         //根据传入的oneblockinfo，更新持久化，返回结果restful-json
    {
        oneblockinfo= getNewAfterEditOneblockinfo(oneblockinfo);                 //对编辑后的单区块信息对象进行赋值操作
        return toAjax(oneblockinfoMapper.updateByOneblockinfo(oneblockinfo));    //返回保存结果
    }
    private Oneblockinfo getNewAfterEditOneblockinfo(Oneblockinfo oneblockinfo) {   //对编辑后的单区块信息对象进行赋值操作

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19
       
	    
        return oneblockinfo;
    }
    //====end 单区块信息(oneblockinfo).修改


    //====begin 单区块信息(oneblockinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:oneblockinfo:remove")                 //shiro权限：区块链:单区块信息:删除
    @Log(title = "单区块信息", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{blockid}")                                       //提交oneblockinfo的接口url:/blockchain/oneblockinfo/remove/{blockid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneOneblockinfo(@PathVariable("blockid") String blockid)    //根据传入的blockid，删除单条数据，返回结果restful-json
    {
        return toAjax(oneblockinfoMapper.deleteByBlockid(blockid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:oneblockinfo:remove")                         //shiro权限：区块链:单区块信息:删除
    @Log(title = "单区块信息", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交oneblockinfo的接口url:/blockchain/oneblockinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeOneblockinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(oneblockinfoMapper.deleteByBlockids(ids2));
    }
    //====end 单区块信息(oneblockinfo).删除


    //====begin 单区块信息(oneblockinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:oneblockinfo:detail")                          //shiro权限：区块链:单区块信息:详情
    @GetMapping("/detail/{blockid}")
    public String detail(@PathVariable("blockid") String  blockid, ModelMap mmap)       //查询单区块信息(oneblockinfo),导航到url:blockchain/oneblockinfo/detail.html
    {
        Oneblockinfo oneblockinfo=oneblockinfoMapper.selectById(blockid);                  //查询单区块信息(oneblockinfo)
        oneblockinfo=getNewDetailOneblockinfo(oneblockinfo);                            //对单区块信息(oneblockinfo)进行赋值操作,以便获取新的详情
        mmap.put("oneblockinfo", oneblockinfo);                                       //单区块信息(oneblockinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/oneblockinfo/detail.html
    }
    private Oneblockinfo getNewDetailOneblockinfo(Oneblockinfo oneblockinfo) {             //对单区块信息(oneblockinfo)进行赋值操作,以便获取新的详情
            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }
    //====end 单区块信息(oneblockinfo).详情


    //====begin 单区块信息(oneblockinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:oneblockinfo:export")                  //shiro权限：区块链:单区块信息:导出
    @Log(title = "单区块信息", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据oneblockinfo条件进行导出接口url:/blockchain/oneblockinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportOneblockinfo(Oneblockinfo oneblockinfo)           //根据传入的单区块信息(oneblockinfo)做条件，查询并导出excel，返回结果restful-json
    {
        oneblockinfo=getNewExportOneblockinfo(oneblockinfo);                  //对单区块信息(oneblockinfo)进行赋值操作,以便获取新的导出条件
        List<Oneblockinfo> list = oneblockinfoMapper.selectListByOneblockinfo(oneblockinfo);     //查询单区块信息
        list=getNewExportOneblockinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Oneblockinfo> util = new ExcelUtil<Oneblockinfo>(Oneblockinfo.class);        //创建导出工具
        return util.exportExcel(list, "单区块信息(oneblockinfo)");                         //执行导出
    }
    private Oneblockinfo getNewExportOneblockinfo(Oneblockinfo oneblockinfo) {                   //对单区块信息(oneblockinfo)进行赋值操作,以便获取新的导出条件

            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        return oneblockinfo;
    }
    private List<Oneblockinfo> getNewExportOneblockinfoList(List<Oneblockinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Oneblockinfo oneblockinfo:list){
            //oneblockinfo.setBlockid(""); //块编码
            //oneblockinfo.setBlocknumber(""); //块编号
            //oneblockinfo.setDatahash(""); //数据哈希
            //oneblockinfo.setPrevioushash(""); //前块哈希
            //oneblockinfo.setChannelid(""); //通道
            //oneblockinfo.setEnvelopecount(""); //信封数量
            //oneblockinfo.setEnvelopeinfos(""); //信封内容
            //oneblockinfo.setTransactioncount(""); //交易数量
            //oneblockinfo.setTransactionsmetadata(""); //交易元数据
            //oneblockinfo.setBlockallfields(""); //区块所有字段
            //oneblockinfo.setBlockheaderprevioushash(""); //区块头前哈希
            //oneblockinfo.setBlockheaderdatahash(""); //区块头数据哈希
            //oneblockinfo.setBlockheaderserializedsize(""); //区块头序列化尺寸
            //oneblockinfo.setBlockheadernumber(""); //区块头编号
            //oneblockinfo.setBlockheaderallfields(""); //区块头所有字段
            //oneblockinfo.setBlockdatadatacount(""); //区块体数据量
            //oneblockinfo.setBlockdatadatalist(""); //区块体数据列表
            //oneblockinfo.setBlockdataallfields(""); //区块体所有字段
            //oneblockinfo.setBz00(""); //备注项00
            //oneblockinfo.setBz01(""); //备注项01
            //oneblockinfo.setBz02(""); //备注项02
            //oneblockinfo.setBz03(""); //备注项03
            //oneblockinfo.setBz04(""); //备注项04
            //oneblockinfo.setBz05(""); //备注项05
            //oneblockinfo.setBz06(""); //备注项06
            //oneblockinfo.setBz07(""); //备注项07
            //oneblockinfo.setBz08(""); //备注项08
            //oneblockinfo.setBz09(""); //备注项09
            //oneblockinfo.setBz10(""); //备注项10
            //oneblockinfo.setBz11(""); //备注项11
            //oneblockinfo.setBz12(""); //备注项12
            //oneblockinfo.setBz13(""); //备注项13
            //oneblockinfo.setBz14(""); //备注项14
            //oneblockinfo.setBz15(""); //备注项15
            //oneblockinfo.setBz16(""); //备注项16
            //oneblockinfo.setBz17(""); //备注项17
            //oneblockinfo.setBz18(""); //备注项18
            //oneblockinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 单区块信息(oneblockinfo).导出


    //====begin 单区块信息(oneblockinfo).测试
    @GetMapping("blockchain/oneblockinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Oneblockinfo oneblockinfo = new Oneblockinfo();
        List list=null;
//        oneblockinfoMapper.updateByOneblockinfo(oneblockinfo);
//        list=sqlUtilService.list("select * from oneblockinfo where blockid like '%5%'");
        return list;
    }
    //====end 单区块信息(oneblockinfo).测试

}
