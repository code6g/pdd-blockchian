package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Chaininfo;
import com.ruoyi.blockchain.mapperbean.ChaininfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.sdk.BlockchainInfo;
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
 * 链信息(chaininfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/chaininfo")                      //区块链/链信息
public class ChaininfoController extends BaseController {

    private String prefix = "blockchain/chaininfo";          //url:区块链/链信息

    @Autowired
    private ChaininfoMapper chaininfoMapper;              //链信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 链信息(chaininfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:chaininfo:view")      //shiro权限：区块链:链信息:查看
    @GetMapping()                                           //url:blockchain/chaininfo
    public String toListChaininfo(ModelMap mmap){          //链信息(chaininfo).跳转到列表页面。从url:/blockchain/chaininfo跳转-->页面:blockchain/chaininfo/chaininfo.html
        Chaininfo chaininfo =getToListChaininfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("chaininfo", chaininfo);                 // 链信息(chaininfo)放入ModelMap
        return prefix + "/chaininfo";                      // 重定向到页面：blockchain/chaininfo/chaininfo.html
    }

    private Chaininfo getToListChaininfoCon() {           //链信息(chaininfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Chaininfo chaininfo=new Chaininfo();             //创建 链信息(chaininfo)
            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:chaininfo:list")      // shiro权限：区块链:链信息:列表
    @PostMapping("/list")                                   // url:/blockchain/chaininfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistChaininfo(Chaininfo chaininfo){    //链信息(chaininfo)：根据页面的form的数据组装chaininfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        chaininfo=getNewChaininfoCon(chaininfo);                     //修改链信息实体，构造新的查询条件
//        List<Chaininfo> list = chaininfoMapper.selectListByChaininfo(chaininfo);   //根据链信息(chaininfo)的属性，构造条件，查询出链信息list
//        list=getNewChaininfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现

        BlockchainInfo aBlockchainInfo= BlockChainTool.getBlockchainInfo();

        List list =new ArrayList();

        Chaininfo aChaininfo=new Chaininfo();

        aChaininfo.setLid(aBlockchainInfo.getBlockchainInfo().getCurrentBlockHash().toString()); //链编码
        aChaininfo.setLgd(String .valueOf(aBlockchainInfo.getHeight())); //链高度
        aChaininfo.setDqhx(aBlockchainInfo.getCurrentBlockHash().toString()); //当前块哈希
        aChaininfo.setQkhx(aBlockchainInfo.getPreviousBlockHash().toString()); //前块哈希
        aChaininfo.setCccs( ""); //存储初始化
        aChaininfo.setWzzd(aBlockchainInfo.getBlockchainInfo().getUnknownFields().toString()); //未知字段
        aChaininfo.setCcdx(String.valueOf(aBlockchainInfo.getBlockchainInfo().getSerializedSize())); //存储大小
        aChaininfo.setCchx(aBlockchainInfo.getBlockchainInfo().getCurrentBlockHash().toString()); //存储哈希

        list.add(aChaininfo);

        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Chaininfo getNewChaininfoCon(Chaininfo chaininfo) {                    //修改链信息实体，构造新的查询条件

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }
    private List<Chaininfo> getNewChaininfoList(List<Chaininfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Chaininfo chaininfo:list){
            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 链信息(chaininfo).列表查询

    //====begin 链信息(chaininfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/chaininfo/add的get方法， 跳转到url:blockchain/chaininfo/add.html
    public String toAddChaininfo(ModelMap mmap)
    {
        Chaininfo chaininfo = getInitChaininfoAdd();    // 创建chaininfo，并赋值，作为新增链信息的初始化值
        mmap.put("chaininfo", chaininfo);                // 链信息(chaininfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/chaininfo/add
    }

    private Chaininfo getInitChaininfoAdd() {            // 创建chaininfo，并赋值，作为新增链信息的初始化值
        Chaininfo chaininfo=new Chaininfo();            //初始化链信息实体
        String ts=InputUitl.getTimestamp();
        chaininfo.setLid(ts);                            //lid

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19

        return chaininfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:chaininfo:add")                 //shiro权限：区块链:链信息:新增
    @Log(title = "链信息", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交chaininfo的接口url:/blockchain/chaininfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveChaininfo(Chaininfo chaininfo)        //根据传入的chaininfo，持久化，返回结果restful-json
    {
        chaininfo=getNewAddChaininfo(chaininfo);                    //对新增后的的链信息对象进行赋值更新操作
        return toAjax(chaininfoMapper.insertChaininfo(chaininfo));  //持久化
    }

    private Chaininfo getNewAddChaininfo(Chaininfo chaininfo) {    //对新增后的的链信息对象进行赋值更新操作

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }
    //====end 链信息(chaininfo).新增


    //====begin 链信息(chaininfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{lid}")                                                        //从url:/blockchain/chaininfo/edit/{lid}的get方法， 跳转到页面：blockchain/chaininfo/edit.html
    public String toEditChaininfo(@PathVariable("lid") String lid, ModelMap mmap)   //根据传入的lid，查询数据，放入ModelMap，返回给页面
    {
        Chaininfo chaininfo = chaininfoMapper.selectById(lid);                     //根据传入的lid，查询链信息(chaininfo)
        chaininfo=getNewEditInitChaininfo(chaininfo);                               //对查询出来的链信息对象进行赋值操作
        mmap.put("chaininfo", chaininfo);                                            //链信息(chaininfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/chaininfo/edit.html
    }

    private Chaininfo getNewEditInitChaininfo(Chaininfo chaininfo) {               //对查询出来的链信息对象进行赋值操作

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:chaininfo:edit")                  //shiro权限：区块链:链信息:修改
    @Log(title = "链信息", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交chaininfo的接口url:/blockchain/chaininfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveChaininfo(Chaininfo chaininfo)         //根据传入的chaininfo，更新持久化，返回结果restful-json
    {
        chaininfo= getNewAfterEditChaininfo(chaininfo);                 //对编辑后的链信息对象进行赋值操作
        return toAjax(chaininfoMapper.updateByChaininfo(chaininfo));    //返回保存结果
    }
    private Chaininfo getNewAfterEditChaininfo(Chaininfo chaininfo) {   //对编辑后的链信息对象进行赋值操作

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19
       
	    
        return chaininfo;
    }
    //====end 链信息(chaininfo).修改


    //====begin 链信息(chaininfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:chaininfo:remove")                 //shiro权限：区块链:链信息:删除
    @Log(title = "链信息", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{lid}")                                       //提交chaininfo的接口url:/blockchain/chaininfo/remove/{lid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneChaininfo(@PathVariable("lid") String lid)    //根据传入的lid，删除单条数据，返回结果restful-json
    {
        return toAjax(chaininfoMapper.deleteByLid(lid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:chaininfo:remove")                         //shiro权限：区块链:链信息:删除
    @Log(title = "链信息", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交chaininfo的接口url:/blockchain/chaininfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeChaininfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(chaininfoMapper.deleteByLids(ids2));
    }
    //====end 链信息(chaininfo).删除


    //====begin 链信息(chaininfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:chaininfo:detail")                          //shiro权限：区块链:链信息:详情
    @GetMapping("/detail/{lid}")
    public String detail(@PathVariable("lid") String  lid, ModelMap mmap)       //查询链信息(chaininfo),导航到url:blockchain/chaininfo/detail.html
    {
        Chaininfo chaininfo=chaininfoMapper.selectById(lid);                  //查询链信息(chaininfo)
        chaininfo=getNewDetailChaininfo(chaininfo);                            //对链信息(chaininfo)进行赋值操作,以便获取新的详情
        mmap.put("chaininfo", chaininfo);                                       //链信息(chaininfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/chaininfo/detail.html
    }
    private Chaininfo getNewDetailChaininfo(Chaininfo chaininfo) {             //对链信息(chaininfo)进行赋值操作,以便获取新的详情
            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }
    //====end 链信息(chaininfo).详情


    //====begin 链信息(chaininfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:chaininfo:export")                  //shiro权限：区块链:链信息:导出
    @Log(title = "链信息", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据chaininfo条件进行导出接口url:/blockchain/chaininfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportChaininfo(Chaininfo chaininfo)           //根据传入的链信息(chaininfo)做条件，查询并导出excel，返回结果restful-json
    {
        chaininfo=getNewExportChaininfo(chaininfo);                  //对链信息(chaininfo)进行赋值操作,以便获取新的导出条件
        List<Chaininfo> list = chaininfoMapper.selectListByChaininfo(chaininfo);     //查询链信息
        list=getNewExportChaininfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Chaininfo> util = new ExcelUtil<Chaininfo>(Chaininfo.class);        //创建导出工具
        return util.exportExcel(list, "链信息(chaininfo)");                         //执行导出
    }
    private Chaininfo getNewExportChaininfo(Chaininfo chaininfo) {                   //对链信息(chaininfo)进行赋值操作,以便获取新的导出条件

            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        return chaininfo;
    }
    private List<Chaininfo> getNewExportChaininfoList(List<Chaininfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Chaininfo chaininfo:list){
            //chaininfo.setLid(""); //链编码
            //chaininfo.setLgd(""); //链高度
            //chaininfo.setDqhx(""); //当前块哈希
            //chaininfo.setQkhx(""); //前块哈希
            //chaininfo.setCccs(""); //存储初始化
            //chaininfo.setWzzd(""); //未知字段
            //chaininfo.setCcdx(""); //存储大小
            //chaininfo.setCchx(""); //存储哈希
            //chaininfo.setBz00(""); //备注项00
            //chaininfo.setBz01(""); //备注项01
            //chaininfo.setBz02(""); //备注项02
            //chaininfo.setBz03(""); //备注项03
            //chaininfo.setBz04(""); //备注项04
            //chaininfo.setBz05(""); //备注项05
            //chaininfo.setBz06(""); //备注项06
            //chaininfo.setBz07(""); //备注项07
            //chaininfo.setBz08(""); //备注项08
            //chaininfo.setBz09(""); //备注项09
            //chaininfo.setBz10(""); //备注项10
            //chaininfo.setBz11(""); //备注项11
            //chaininfo.setBz12(""); //备注项12
            //chaininfo.setBz13(""); //备注项13
            //chaininfo.setBz14(""); //备注项14
            //chaininfo.setBz15(""); //备注项15
            //chaininfo.setBz16(""); //备注项16
            //chaininfo.setBz17(""); //备注项17
            //chaininfo.setBz18(""); //备注项18
            //chaininfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 链信息(chaininfo).导出


    //====begin 链信息(chaininfo).测试
    @GetMapping("blockchain/chaininfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Chaininfo chaininfo = new Chaininfo();
        List list=null;
//        chaininfoMapper.updateByChaininfo(chaininfo);
//        list=sqlUtilService.list("select * from chaininfo where lid like '%5%'");
        return list;
    }
    //====end 链信息(chaininfo).测试

}
