package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Chaincodeinfo;
import com.ruoyi.blockchain.mapperbean.ChaincodeinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.protos.peer.Query;
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
 * 链码（智能合约）(chaincodeinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/chaincodeinfo")                      //区块链/链码（智能合约）
public class ChaincodeinfoController extends BaseController {

    private String prefix = "blockchain/chaincodeinfo";          //url:区块链/链码（智能合约）

    @Autowired
    private ChaincodeinfoMapper chaincodeinfoMapper;              //链码（智能合约）Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 链码（智能合约）(chaincodeinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:chaincodeinfo:view")      //shiro权限：区块链:链码（智能合约）:查看
    @GetMapping()                                           //url:blockchain/chaincodeinfo
    public String toListChaincodeinfo(ModelMap mmap){          //链码（智能合约）(chaincodeinfo).跳转到列表页面。从url:/blockchain/chaincodeinfo跳转-->页面:blockchain/chaincodeinfo/chaincodeinfo.html
        Chaincodeinfo chaincodeinfo =getToListChaincodeinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("chaincodeinfo", chaincodeinfo);                 // 链码（智能合约）(chaincodeinfo)放入ModelMap
        return prefix + "/chaincodeinfo";                      // 重定向到页面：blockchain/chaincodeinfo/chaincodeinfo.html
    }

    private Chaincodeinfo getToListChaincodeinfoCon() {           //链码（智能合约）(chaincodeinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Chaincodeinfo chaincodeinfo=new Chaincodeinfo();             //创建 链码（智能合约）(chaincodeinfo)
            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:chaincodeinfo:list")      // shiro权限：区块链:链码（智能合约）:列表
    @PostMapping("/list")                                   // url:/blockchain/chaincodeinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistChaincodeinfo(Chaincodeinfo chaincodeinfo){    //链码（智能合约）(chaincodeinfo)：根据页面的form的数据组装chaincodeinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        chaincodeinfo=getNewChaincodeinfoCon(chaincodeinfo);                     //修改链码（智能合约）实体，构造新的查询条件
//        List<Chaincodeinfo> list = chaincodeinfoMapper.selectListByChaincodeinfo(chaincodeinfo);   //根据链码（智能合约）(chaincodeinfo)的属性，构造条件，查询出链码（智能合约）list
//        list=getNewChaincodeinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现


        List<Query.ChaincodeInfo> list= BlockChainTool.getListInstantiatedChaincodeInfo();

        List list2=new ArrayList();

        for(int i=0;i<list.size();i++){
            Query.ChaincodeInfo QueryChaincodeInfo=  list.get(i);

            Chaincodeinfo aChaincodeinfo=new Chaincodeinfo();

            aChaincodeinfo.setCcmc(QueryChaincodeInfo.getName()); //链码名称
            aChaincodeinfo.setCcbh(QueryChaincodeInfo.getId().toString()); //链码编号
            aChaincodeinfo.setCcbb(QueryChaincodeInfo.getVersion()); //链码版本
            aChaincodeinfo.setCclj(QueryChaincodeInfo.getPath()); //链码路径
            aChaincodeinfo.setCcsr(QueryChaincodeInfo.getInput()); //链码输入
            aChaincodeinfo.setCcsm(""); //链码说明
            aChaincodeinfo.setTdmc(""); //通道名称

            list2.add(aChaincodeinfo);
        }



        return getDataTable(list2);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Chaincodeinfo getNewChaincodeinfoCon(Chaincodeinfo chaincodeinfo) {                    //修改链码（智能合约）实体，构造新的查询条件

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }
    private List<Chaincodeinfo> getNewChaincodeinfoList(List<Chaincodeinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Chaincodeinfo chaincodeinfo:list){
            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 链码（智能合约）(chaincodeinfo).列表查询

    //====begin 链码（智能合约）(chaincodeinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/chaincodeinfo/add的get方法， 跳转到url:blockchain/chaincodeinfo/add.html
    public String toAddChaincodeinfo(ModelMap mmap)
    {
        Chaincodeinfo chaincodeinfo = getInitChaincodeinfoAdd();    // 创建chaincodeinfo，并赋值，作为新增链码（智能合约）的初始化值
        mmap.put("chaincodeinfo", chaincodeinfo);                // 链码（智能合约）(chaincodeinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/chaincodeinfo/add
    }

    private Chaincodeinfo getInitChaincodeinfoAdd() {            // 创建chaincodeinfo，并赋值，作为新增链码（智能合约）的初始化值
        Chaincodeinfo chaincodeinfo=new Chaincodeinfo();            //初始化链码（智能合约）实体
        String ts=InputUitl.getTimestamp();
        chaincodeinfo.setCcmc(ts);                            //ccmc

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19

        return chaincodeinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:chaincodeinfo:add")                 //shiro权限：区块链:链码（智能合约）:新增
    @Log(title = "链码（智能合约）", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交chaincodeinfo的接口url:/blockchain/chaincodeinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveChaincodeinfo(Chaincodeinfo chaincodeinfo)        //根据传入的chaincodeinfo，持久化，返回结果restful-json
    {
        chaincodeinfo=getNewAddChaincodeinfo(chaincodeinfo);                    //对新增后的的链码（智能合约）对象进行赋值更新操作
        return toAjax(chaincodeinfoMapper.insertChaincodeinfo(chaincodeinfo));  //持久化
    }

    private Chaincodeinfo getNewAddChaincodeinfo(Chaincodeinfo chaincodeinfo) {    //对新增后的的链码（智能合约）对象进行赋值更新操作

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }
    //====end 链码（智能合约）(chaincodeinfo).新增


    //====begin 链码（智能合约）(chaincodeinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{ccmc}")                                                        //从url:/blockchain/chaincodeinfo/edit/{ccmc}的get方法， 跳转到页面：blockchain/chaincodeinfo/edit.html
    public String toEditChaincodeinfo(@PathVariable("ccmc") String ccmc, ModelMap mmap)   //根据传入的ccmc，查询数据，放入ModelMap，返回给页面
    {
        Chaincodeinfo chaincodeinfo = chaincodeinfoMapper.selectById(ccmc);                     //根据传入的ccmc，查询链码（智能合约）(chaincodeinfo)
        chaincodeinfo=getNewEditInitChaincodeinfo(chaincodeinfo);                               //对查询出来的链码（智能合约）对象进行赋值操作
        mmap.put("chaincodeinfo", chaincodeinfo);                                            //链码（智能合约）(chaincodeinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/chaincodeinfo/edit.html
    }

    private Chaincodeinfo getNewEditInitChaincodeinfo(Chaincodeinfo chaincodeinfo) {               //对查询出来的链码（智能合约）对象进行赋值操作

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:chaincodeinfo:edit")                  //shiro权限：区块链:链码（智能合约）:修改
    @Log(title = "链码（智能合约）", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交chaincodeinfo的接口url:/blockchain/chaincodeinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveChaincodeinfo(Chaincodeinfo chaincodeinfo)         //根据传入的chaincodeinfo，更新持久化，返回结果restful-json
    {
        chaincodeinfo= getNewAfterEditChaincodeinfo(chaincodeinfo);                 //对编辑后的链码（智能合约）对象进行赋值操作
        return toAjax(chaincodeinfoMapper.updateByChaincodeinfo(chaincodeinfo));    //返回保存结果
    }
    private Chaincodeinfo getNewAfterEditChaincodeinfo(Chaincodeinfo chaincodeinfo) {   //对编辑后的链码（智能合约）对象进行赋值操作

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19
       
	    
        return chaincodeinfo;
    }
    //====end 链码（智能合约）(chaincodeinfo).修改


    //====begin 链码（智能合约）(chaincodeinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:chaincodeinfo:remove")                 //shiro权限：区块链:链码（智能合约）:删除
    @Log(title = "链码（智能合约）", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{ccmc}")                                       //提交chaincodeinfo的接口url:/blockchain/chaincodeinfo/remove/{ccmc}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneChaincodeinfo(@PathVariable("ccmc") String ccmc)    //根据传入的ccmc，删除单条数据，返回结果restful-json
    {
        return toAjax(chaincodeinfoMapper.deleteByCcmc(ccmc));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:chaincodeinfo:remove")                         //shiro权限：区块链:链码（智能合约）:删除
    @Log(title = "链码（智能合约）", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交chaincodeinfo的接口url:/blockchain/chaincodeinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeChaincodeinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(chaincodeinfoMapper.deleteByCcmcs(ids2));
    }
    //====end 链码（智能合约）(chaincodeinfo).删除


    //====begin 链码（智能合约）(chaincodeinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:chaincodeinfo:detail")                          //shiro权限：区块链:链码（智能合约）:详情
    @GetMapping("/detail/{ccmc}")
    public String detail(@PathVariable("ccmc") String  ccmc, ModelMap mmap)       //查询链码（智能合约）(chaincodeinfo),导航到url:blockchain/chaincodeinfo/detail.html
    {
        Chaincodeinfo chaincodeinfo=chaincodeinfoMapper.selectById(ccmc);                  //查询链码（智能合约）(chaincodeinfo)
        chaincodeinfo=getNewDetailChaincodeinfo(chaincodeinfo);                            //对链码（智能合约）(chaincodeinfo)进行赋值操作,以便获取新的详情
        mmap.put("chaincodeinfo", chaincodeinfo);                                       //链码（智能合约）(chaincodeinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/chaincodeinfo/detail.html
    }
    private Chaincodeinfo getNewDetailChaincodeinfo(Chaincodeinfo chaincodeinfo) {             //对链码（智能合约）(chaincodeinfo)进行赋值操作,以便获取新的详情
            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }
    //====end 链码（智能合约）(chaincodeinfo).详情


    //====begin 链码（智能合约）(chaincodeinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:chaincodeinfo:export")                  //shiro权限：区块链:链码（智能合约）:导出
    @Log(title = "链码（智能合约）", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据chaincodeinfo条件进行导出接口url:/blockchain/chaincodeinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportChaincodeinfo(Chaincodeinfo chaincodeinfo)           //根据传入的链码（智能合约）(chaincodeinfo)做条件，查询并导出excel，返回结果restful-json
    {
        chaincodeinfo=getNewExportChaincodeinfo(chaincodeinfo);                  //对链码（智能合约）(chaincodeinfo)进行赋值操作,以便获取新的导出条件
        List<Chaincodeinfo> list = chaincodeinfoMapper.selectListByChaincodeinfo(chaincodeinfo);     //查询链码（智能合约）
        list=getNewExportChaincodeinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Chaincodeinfo> util = new ExcelUtil<Chaincodeinfo>(Chaincodeinfo.class);        //创建导出工具
        return util.exportExcel(list, "链码（智能合约）(chaincodeinfo)");                         //执行导出
    }
    private Chaincodeinfo getNewExportChaincodeinfo(Chaincodeinfo chaincodeinfo) {                   //对链码（智能合约）(chaincodeinfo)进行赋值操作,以便获取新的导出条件

            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        return chaincodeinfo;
    }
    private List<Chaincodeinfo> getNewExportChaincodeinfoList(List<Chaincodeinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Chaincodeinfo chaincodeinfo:list){
            //chaincodeinfo.setCcmc(""); //链码名称
            //chaincodeinfo.setCcbh(""); //链码编号
            //chaincodeinfo.setCcbb(""); //链码版本
            //chaincodeinfo.setCclj(""); //链码路径
            //chaincodeinfo.setCcsr(""); //链码输入
            //chaincodeinfo.setCcsm(""); //链码说明
            //chaincodeinfo.setTdmc(""); //通道名称
            //chaincodeinfo.setBz00(""); //备注项00
            //chaincodeinfo.setBz01(""); //备注项01
            //chaincodeinfo.setBz02(""); //备注项02
            //chaincodeinfo.setBz03(""); //备注项03
            //chaincodeinfo.setBz04(""); //备注项04
            //chaincodeinfo.setBz05(""); //备注项05
            //chaincodeinfo.setBz06(""); //备注项06
            //chaincodeinfo.setBz07(""); //备注项07
            //chaincodeinfo.setBz08(""); //备注项08
            //chaincodeinfo.setBz09(""); //备注项09
            //chaincodeinfo.setBz10(""); //备注项10
            //chaincodeinfo.setBz11(""); //备注项11
            //chaincodeinfo.setBz12(""); //备注项12
            //chaincodeinfo.setBz13(""); //备注项13
            //chaincodeinfo.setBz14(""); //备注项14
            //chaincodeinfo.setBz15(""); //备注项15
            //chaincodeinfo.setBz16(""); //备注项16
            //chaincodeinfo.setBz17(""); //备注项17
            //chaincodeinfo.setBz18(""); //备注项18
            //chaincodeinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 链码（智能合约）(chaincodeinfo).导出


    //====begin 链码（智能合约）(chaincodeinfo).测试
    @GetMapping("blockchain/chaincodeinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Chaincodeinfo chaincodeinfo = new Chaincodeinfo();
        List list=null;
//        chaincodeinfoMapper.updateByChaincodeinfo(chaincodeinfo);
//        list=sqlUtilService.list("select * from chaincodeinfo where ccmc like '%5%'");
        return list;
    }
    //====end 链码（智能合约）(chaincodeinfo).测试

}
