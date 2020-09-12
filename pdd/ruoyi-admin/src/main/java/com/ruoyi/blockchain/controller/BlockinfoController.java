package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Blockinfo;
import com.ruoyi.blockchain.mapperbean.BlockinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
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

/**
 * 区块信息(blockinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/blockinfo")                      //区块链/区块信息
public class BlockinfoController extends BaseController {

    private String prefix = "blockchain/blockinfo";          //url:区块链/区块信息

    @Autowired
    private BlockinfoMapper blockinfoMapper;              //区块信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 区块信息(blockinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:blockinfo:view")      //shiro权限：区块链:区块信息:查看
    @GetMapping()                                           //url:blockchain/blockinfo
    public String toListBlockinfo(ModelMap mmap){          //区块信息(blockinfo).跳转到列表页面。从url:/blockchain/blockinfo跳转-->页面:blockchain/blockinfo/blockinfo.html
        Blockinfo blockinfo =getToListBlockinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("blockinfo", blockinfo);                 // 区块信息(blockinfo)放入ModelMap
        return prefix + "/blockinfo";                      // 重定向到页面：blockchain/blockinfo/blockinfo.html
    }

    private Blockinfo getToListBlockinfoCon() {           //区块信息(blockinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Blockinfo blockinfo=new Blockinfo();             //创建 区块信息(blockinfo)
            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:blockinfo:list")      // shiro权限：区块链:区块信息:列表
    @PostMapping("/list")                                   // url:/blockchain/blockinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistBlockinfo(Blockinfo blockinfo){    //区块信息(blockinfo)：根据页面的form的数据组装blockinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        blockinfo=getNewBlockinfoCon(blockinfo);                     //修改区块信息实体，构造新的查询条件
//        List<Blockinfo> list = blockinfoMapper.selectListByBlockinfo(blockinfo);   //根据区块信息(blockinfo)的属性，构造条件，查询出区块信息list
//        list=getNewBlockinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现

        List list=new ArrayList();
        long blockHeight=BlockChainTool.getBlockchainInfo().getHeight();
        for(int i=0;i<blockHeight;i++){
            try {
                BlockInfo bi = BlockChainTool.getChannel().queryBlockByNumber(i);
                 bi.getBlockNumber();
                 bi.getDataHash();
                 bi.getPreviousHash();
                 bi.getChannelId();
                 bi.getEnvelopeCount();
                 bi.getEnvelopeInfos();
                 bi.getTransactionCount();
                 bi.getTransActionsMetaData();

                 bi.getBlock().getAllFields();

                 bi.getBlock().getHeader().getPreviousHash();
                 bi.getBlock().getHeader().getDataHash();
                 bi.getBlock().getHeader().getSerializedSize();
                 bi.getBlock().getHeader().getNumber();
                 bi.getBlock().getHeader().getAllFields();

                 bi.getBlock().getData().getDataCount();
                 bi.getBlock().getData().getDataList();
                 bi.getBlock().getData().getAllFields();





                Blockinfo aBlockinfo=new Blockinfo();
                aBlockinfo.setTqkid(String.valueOf(bi.getBlockNumber())); //头区块编号
                aBlockinfo.setTqkhx(bi.getBlock().getHeader().getPreviousHash().toString()); //头前块哈希
                aBlockinfo.setTsjhx(bi.getBlock().getHeader().getDataHash().toString()); //头数据哈希
                aBlockinfo.setTwzzd(bi.getBlock().getHeader().getUnknownFields().toString()); //头未知字段
                aBlockinfo.setTcccs(String.valueOf(bi.getBlock().getHeader().getSerializedSize())); //头存储初始化
                aBlockinfo.setTccdx(String.valueOf(bi.getBlock().getHeader().getSerializedSize())); //头存储大小
                aBlockinfo.setTcchx(bi.getBlock().getHeader().getDataHash().toString()); //头存储哈希
                aBlockinfo.setSjnr(bi.getBlock().getData().getDataList().toString()); //数据内容
                aBlockinfo.setSjcccs(""); //数据存储初始化
                aBlockinfo.setSjwzzd(""); //数据未知字段
                aBlockinfo.setSjccdx(String .valueOf(bi.getBlock().getData().getDataCount())); //数据存储大小
                aBlockinfo.setSjcchx(bi.getDataHash().toString()); //数据存储哈希
                aBlockinfo.setYsjnr(""); //元数据内容
                aBlockinfo.setYsjcccs(""); //元数据存储初始化
                aBlockinfo.setYsjwzzd(""); //元数据未知字段
                aBlockinfo.setYsjccdx(""); //元数据存储大小
                aBlockinfo.setYsscchx(""); //元数据存储哈希
                aBlockinfo.setGlk(""); //过滤块
                aBlockinfo.setJjcs(""); //交易次数
                aBlockinfo.setQksm(""); //区块说明



                list.add(aBlockinfo);
            }catch (Exception e2){

            }

        }





        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Blockinfo getNewBlockinfoCon(Blockinfo blockinfo) {                    //修改区块信息实体，构造新的查询条件

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }
    private List<Blockinfo> getNewBlockinfoList(List<Blockinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Blockinfo blockinfo:list){
            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 区块信息(blockinfo).列表查询

    //====begin 区块信息(blockinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/blockinfo/add的get方法， 跳转到url:blockchain/blockinfo/add.html
    public String toAddBlockinfo(ModelMap mmap)
    {
        Blockinfo blockinfo = getInitBlockinfoAdd();    // 创建blockinfo，并赋值，作为新增区块信息的初始化值
        mmap.put("blockinfo", blockinfo);                // 区块信息(blockinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/blockinfo/add
    }

    private Blockinfo getInitBlockinfoAdd() {            // 创建blockinfo，并赋值，作为新增区块信息的初始化值
        Blockinfo blockinfo=new Blockinfo();            //初始化区块信息实体
        String ts=InputUitl.getTimestamp();
        blockinfo.setTqkid(ts);                            //tqkid

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19

        return blockinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:blockinfo:add")                 //shiro权限：区块链:区块信息:新增
    @Log(title = "区块信息", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交blockinfo的接口url:/blockchain/blockinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveBlockinfo(Blockinfo blockinfo)        //根据传入的blockinfo，持久化，返回结果restful-json
    {
        blockinfo=getNewAddBlockinfo(blockinfo);                    //对新增后的的区块信息对象进行赋值更新操作
        return toAjax(blockinfoMapper.insertBlockinfo(blockinfo));  //持久化
    }

    private Blockinfo getNewAddBlockinfo(Blockinfo blockinfo) {    //对新增后的的区块信息对象进行赋值更新操作

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }
    //====end 区块信息(blockinfo).新增


    //====begin 区块信息(blockinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{tqkid}")                                                        //从url:/blockchain/blockinfo/edit/{tqkid}的get方法， 跳转到页面：blockchain/blockinfo/edit.html
    public String toEditBlockinfo(@PathVariable("tqkid") String tqkid, ModelMap mmap)   //根据传入的tqkid，查询数据，放入ModelMap，返回给页面
    {
        Blockinfo blockinfo = blockinfoMapper.selectById(tqkid);                     //根据传入的tqkid，查询区块信息(blockinfo)
        blockinfo=getNewEditInitBlockinfo(blockinfo);                               //对查询出来的区块信息对象进行赋值操作
        mmap.put("blockinfo", blockinfo);                                            //区块信息(blockinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/blockinfo/edit.html
    }

    private Blockinfo getNewEditInitBlockinfo(Blockinfo blockinfo) {               //对查询出来的区块信息对象进行赋值操作

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:blockinfo:edit")                  //shiro权限：区块链:区块信息:修改
    @Log(title = "区块信息", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交blockinfo的接口url:/blockchain/blockinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveBlockinfo(Blockinfo blockinfo)         //根据传入的blockinfo，更新持久化，返回结果restful-json
    {
        blockinfo= getNewAfterEditBlockinfo(blockinfo);                 //对编辑后的区块信息对象进行赋值操作
        return toAjax(blockinfoMapper.updateByBlockinfo(blockinfo));    //返回保存结果
    }
    private Blockinfo getNewAfterEditBlockinfo(Blockinfo blockinfo) {   //对编辑后的区块信息对象进行赋值操作

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19
       
	    
        return blockinfo;
    }
    //====end 区块信息(blockinfo).修改


    //====begin 区块信息(blockinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:blockinfo:remove")                 //shiro权限：区块链:区块信息:删除
    @Log(title = "区块信息", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{tqkid}")                                       //提交blockinfo的接口url:/blockchain/blockinfo/remove/{tqkid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneBlockinfo(@PathVariable("tqkid") String tqkid)    //根据传入的tqkid，删除单条数据，返回结果restful-json
    {
        return toAjax(blockinfoMapper.deleteByTqkid(tqkid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:blockinfo:remove")                         //shiro权限：区块链:区块信息:删除
    @Log(title = "区块信息", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交blockinfo的接口url:/blockchain/blockinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeBlockinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(blockinfoMapper.deleteByTqkids(ids2));
    }
    //====end 区块信息(blockinfo).删除


    //====begin 区块信息(blockinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:blockinfo:detail")                          //shiro权限：区块链:区块信息:详情
    @GetMapping("/detail/{tqkid}")
    public String detail(@PathVariable("tqkid") String  tqkid, ModelMap mmap)       //查询区块信息(blockinfo),导航到url:blockchain/blockinfo/detail.html
    {
        Blockinfo blockinfo=blockinfoMapper.selectById(tqkid);                  //查询区块信息(blockinfo)
        blockinfo=getNewDetailBlockinfo(blockinfo);                            //对区块信息(blockinfo)进行赋值操作,以便获取新的详情
        mmap.put("blockinfo", blockinfo);                                       //区块信息(blockinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/blockinfo/detail.html
    }
    private Blockinfo getNewDetailBlockinfo(Blockinfo blockinfo) {             //对区块信息(blockinfo)进行赋值操作,以便获取新的详情
            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }
    //====end 区块信息(blockinfo).详情


    //====begin 区块信息(blockinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:blockinfo:export")                  //shiro权限：区块链:区块信息:导出
    @Log(title = "区块信息", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据blockinfo条件进行导出接口url:/blockchain/blockinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportBlockinfo(Blockinfo blockinfo)           //根据传入的区块信息(blockinfo)做条件，查询并导出excel，返回结果restful-json
    {
        blockinfo=getNewExportBlockinfo(blockinfo);                  //对区块信息(blockinfo)进行赋值操作,以便获取新的导出条件
        List<Blockinfo> list = blockinfoMapper.selectListByBlockinfo(blockinfo);     //查询区块信息
        list=getNewExportBlockinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Blockinfo> util = new ExcelUtil<Blockinfo>(Blockinfo.class);        //创建导出工具
        return util.exportExcel(list, "区块信息(blockinfo)");                         //执行导出
    }
    private Blockinfo getNewExportBlockinfo(Blockinfo blockinfo) {                   //对区块信息(blockinfo)进行赋值操作,以便获取新的导出条件

            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        return blockinfo;
    }
    private List<Blockinfo> getNewExportBlockinfoList(List<Blockinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Blockinfo blockinfo:list){
            //blockinfo.setTqkid(""); //头区块编号
            //blockinfo.setTqkhx(""); //头前块哈希
            //blockinfo.setTsjhx(""); //头数据哈希
            //blockinfo.setTwzzd(""); //头未知字段
            //blockinfo.setTcccs(""); //头存储初始化
            //blockinfo.setTccdx(""); //头存储大小
            //blockinfo.setTcchx(""); //头存储哈希
            //blockinfo.setSjnr(""); //数据内容
            //blockinfo.setSjcccs(""); //数据存储初始化
            //blockinfo.setSjwzzd(""); //数据未知字段
            //blockinfo.setSjccdx(""); //数据存储大小
            //blockinfo.setSjcchx(""); //数据存储哈希
            //blockinfo.setYsjnr(""); //元数据内容
            //blockinfo.setYsjcccs(""); //元数据存储初始化
            //blockinfo.setYsjwzzd(""); //元数据未知字段
            //blockinfo.setYsjccdx(""); //元数据存储大小
            //blockinfo.setYsscchx(""); //元数据存储哈希
            //blockinfo.setGlk(""); //过滤块
            //blockinfo.setJjcs(""); //交易次数
            //blockinfo.setQksm(""); //区块说明
            //blockinfo.setBz00(""); //备注项00
            //blockinfo.setBz01(""); //备注项01
            //blockinfo.setBz02(""); //备注项02
            //blockinfo.setBz03(""); //备注项03
            //blockinfo.setBz04(""); //备注项04
            //blockinfo.setBz05(""); //备注项05
            //blockinfo.setBz06(""); //备注项06
            //blockinfo.setBz07(""); //备注项07
            //blockinfo.setBz08(""); //备注项08
            //blockinfo.setBz09(""); //备注项09
            //blockinfo.setBz10(""); //备注项10
            //blockinfo.setBz11(""); //备注项11
            //blockinfo.setBz12(""); //备注项12
            //blockinfo.setBz13(""); //备注项13
            //blockinfo.setBz14(""); //备注项14
            //blockinfo.setBz15(""); //备注项15
            //blockinfo.setBz16(""); //备注项16
            //blockinfo.setBz17(""); //备注项17
            //blockinfo.setBz18(""); //备注项18
            //blockinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 区块信息(blockinfo).导出


    //====begin 区块信息(blockinfo).测试
    @GetMapping("blockchain/blockinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Blockinfo blockinfo = new Blockinfo();
        List list=null;
//        blockinfoMapper.updateByBlockinfo(blockinfo);
//        list=sqlUtilService.list("select * from blockinfo where tqkid like '%5%'");
        return list;
    }
    //====end 区块信息(blockinfo).测试

}
