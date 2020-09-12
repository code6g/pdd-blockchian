package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Ordererinfo;
import com.ruoyi.blockchain.mapperbean.OrdererinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.sdk.Orderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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
 * 排序节点(ordererinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/ordererinfo")                      //区块链/排序节点
public class OrdererinfoController extends BaseController {

    private String prefix = "blockchain/ordererinfo";          //url:区块链/排序节点

    @Autowired
    private OrdererinfoMapper ordererinfoMapper;              //排序节点Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 排序节点(ordererinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:ordererinfo:view")      //shiro权限：区块链:排序节点:查看
    @GetMapping()                                           //url:blockchain/ordererinfo
    public String toListOrdererinfo(ModelMap mmap){          //排序节点(ordererinfo).跳转到列表页面。从url:/blockchain/ordererinfo跳转-->页面:blockchain/ordererinfo/ordererinfo.html
        Ordererinfo ordererinfo =getToListOrdererinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("ordererinfo", ordererinfo);                 // 排序节点(ordererinfo)放入ModelMap
        return prefix + "/ordererinfo";                      // 重定向到页面：blockchain/ordererinfo/ordererinfo.html
    }

    private Ordererinfo getToListOrdererinfoCon() {           //排序节点(ordererinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Ordererinfo ordererinfo=new Ordererinfo();             //创建 排序节点(ordererinfo)
            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:ordererinfo:list")      // shiro权限：区块链:排序节点:列表
    @PostMapping("/list")                                   // url:/blockchain/ordererinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistOrdererinfo(Ordererinfo ordererinfo){    //排序节点(ordererinfo)：根据页面的form的数据组装ordererinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        ordererinfo=getNewOrdererinfoCon(ordererinfo);                     //修改排序节点实体，构造新的查询条件
//        List<Ordererinfo> list = ordererinfoMapper.selectListByOrdererinfo(ordererinfo);   //根据排序节点(ordererinfo)的属性，构造条件，查询出排序节点list
//        list=getNewOrdererinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现


        Collection<Orderer> COrderer= BlockChainTool.getOrderer();

        List list=new ArrayList();

        for(Orderer aOrderer:COrderer){
            Ordererinfo aOrdererinfo= new Ordererinfo();

            aOrdererinfo.setJdid(aOrderer.getName()); //节点编码
            aOrdererinfo.setJdmc(aOrderer.getName()); //节点名称
            aOrdererinfo.setJdlj(aOrderer.getUrl()); //节点链接
            aOrdererinfo.setJddz(aOrderer.getUrl()); //节点地址
            aOrdererinfo.setTdmc(""); //通道名称
            aOrdererinfo.setFsgb(""); //是否关闭
            list.add(aOrdererinfo);
        }




        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Ordererinfo getNewOrdererinfoCon(Ordererinfo ordererinfo) {                    //修改排序节点实体，构造新的查询条件

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }
    private List<Ordererinfo> getNewOrdererinfoList(List<Ordererinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Ordererinfo ordererinfo:list){
            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 排序节点(ordererinfo).列表查询

    //====begin 排序节点(ordererinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/ordererinfo/add的get方法， 跳转到url:blockchain/ordererinfo/add.html
    public String toAddOrdererinfo(ModelMap mmap)
    {
        Ordererinfo ordererinfo = getInitOrdererinfoAdd();    // 创建ordererinfo，并赋值，作为新增排序节点的初始化值
        mmap.put("ordererinfo", ordererinfo);                // 排序节点(ordererinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/ordererinfo/add
    }

    private Ordererinfo getInitOrdererinfoAdd() {            // 创建ordererinfo，并赋值，作为新增排序节点的初始化值
        Ordererinfo ordererinfo=new Ordererinfo();            //初始化排序节点实体
        String ts=InputUitl.getTimestamp();
        ordererinfo.setJdid(ts);                            //jdid

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19

        return ordererinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:ordererinfo:add")                 //shiro权限：区块链:排序节点:新增
    @Log(title = "排序节点", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交ordererinfo的接口url:/blockchain/ordererinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveOrdererinfo(Ordererinfo ordererinfo)        //根据传入的ordererinfo，持久化，返回结果restful-json
    {
        ordererinfo=getNewAddOrdererinfo(ordererinfo);                    //对新增后的的排序节点对象进行赋值更新操作
        return toAjax(ordererinfoMapper.insertOrdererinfo(ordererinfo));  //持久化
    }

    private Ordererinfo getNewAddOrdererinfo(Ordererinfo ordererinfo) {    //对新增后的的排序节点对象进行赋值更新操作

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }
    //====end 排序节点(ordererinfo).新增


    //====begin 排序节点(ordererinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{jdid}")                                                        //从url:/blockchain/ordererinfo/edit/{jdid}的get方法， 跳转到页面：blockchain/ordererinfo/edit.html
    public String toEditOrdererinfo(@PathVariable("jdid") String jdid, ModelMap mmap)   //根据传入的jdid，查询数据，放入ModelMap，返回给页面
    {
        Ordererinfo ordererinfo = ordererinfoMapper.selectById(jdid);                     //根据传入的jdid，查询排序节点(ordererinfo)
        ordererinfo=getNewEditInitOrdererinfo(ordererinfo);                               //对查询出来的排序节点对象进行赋值操作
        mmap.put("ordererinfo", ordererinfo);                                            //排序节点(ordererinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/ordererinfo/edit.html
    }

    private Ordererinfo getNewEditInitOrdererinfo(Ordererinfo ordererinfo) {               //对查询出来的排序节点对象进行赋值操作

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:ordererinfo:edit")                  //shiro权限：区块链:排序节点:修改
    @Log(title = "排序节点", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交ordererinfo的接口url:/blockchain/ordererinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveOrdererinfo(Ordererinfo ordererinfo)         //根据传入的ordererinfo，更新持久化，返回结果restful-json
    {
        ordererinfo= getNewAfterEditOrdererinfo(ordererinfo);                 //对编辑后的排序节点对象进行赋值操作
        return toAjax(ordererinfoMapper.updateByOrdererinfo(ordererinfo));    //返回保存结果
    }
    private Ordererinfo getNewAfterEditOrdererinfo(Ordererinfo ordererinfo) {   //对编辑后的排序节点对象进行赋值操作

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19
       
	    
        return ordererinfo;
    }
    //====end 排序节点(ordererinfo).修改


    //====begin 排序节点(ordererinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:ordererinfo:remove")                 //shiro权限：区块链:排序节点:删除
    @Log(title = "排序节点", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{jdid}")                                       //提交ordererinfo的接口url:/blockchain/ordererinfo/remove/{jdid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneOrdererinfo(@PathVariable("jdid") String jdid)    //根据传入的jdid，删除单条数据，返回结果restful-json
    {
        return toAjax(ordererinfoMapper.deleteByJdid(jdid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:ordererinfo:remove")                         //shiro权限：区块链:排序节点:删除
    @Log(title = "排序节点", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交ordererinfo的接口url:/blockchain/ordererinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeOrdererinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(ordererinfoMapper.deleteByJdids(ids2));
    }
    //====end 排序节点(ordererinfo).删除


    //====begin 排序节点(ordererinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:ordererinfo:detail")                          //shiro权限：区块链:排序节点:详情
    @GetMapping("/detail/{jdid}")
    public String detail(@PathVariable("jdid") String  jdid, ModelMap mmap)       //查询排序节点(ordererinfo),导航到url:blockchain/ordererinfo/detail.html
    {
        Ordererinfo ordererinfo=ordererinfoMapper.selectById(jdid);                  //查询排序节点(ordererinfo)
        ordererinfo=getNewDetailOrdererinfo(ordererinfo);                            //对排序节点(ordererinfo)进行赋值操作,以便获取新的详情
        mmap.put("ordererinfo", ordererinfo);                                       //排序节点(ordererinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/ordererinfo/detail.html
    }
    private Ordererinfo getNewDetailOrdererinfo(Ordererinfo ordererinfo) {             //对排序节点(ordererinfo)进行赋值操作,以便获取新的详情
            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }
    //====end 排序节点(ordererinfo).详情


    //====begin 排序节点(ordererinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:ordererinfo:export")                  //shiro权限：区块链:排序节点:导出
    @Log(title = "排序节点", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据ordererinfo条件进行导出接口url:/blockchain/ordererinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportOrdererinfo(Ordererinfo ordererinfo)           //根据传入的排序节点(ordererinfo)做条件，查询并导出excel，返回结果restful-json
    {
        ordererinfo=getNewExportOrdererinfo(ordererinfo);                  //对排序节点(ordererinfo)进行赋值操作,以便获取新的导出条件
        List<Ordererinfo> list = ordererinfoMapper.selectListByOrdererinfo(ordererinfo);     //查询排序节点
        list=getNewExportOrdererinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Ordererinfo> util = new ExcelUtil<Ordererinfo>(Ordererinfo.class);        //创建导出工具
        return util.exportExcel(list, "排序节点(ordererinfo)");                         //执行导出
    }
    private Ordererinfo getNewExportOrdererinfo(Ordererinfo ordererinfo) {                   //对排序节点(ordererinfo)进行赋值操作,以便获取新的导出条件

            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        return ordererinfo;
    }
    private List<Ordererinfo> getNewExportOrdererinfoList(List<Ordererinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Ordererinfo ordererinfo:list){
            //ordererinfo.setJdid(""); //节点编码
            //ordererinfo.setJdmc(""); //节点名称
            //ordererinfo.setJdlj(""); //节点链接
            //ordererinfo.setJddz(""); //节点地址
            //ordererinfo.setTdmc(""); //通道名称
            //ordererinfo.setFsgb(""); //是否关闭
            //ordererinfo.setBz00(""); //备注项00
            //ordererinfo.setBz01(""); //备注项01
            //ordererinfo.setBz02(""); //备注项02
            //ordererinfo.setBz03(""); //备注项03
            //ordererinfo.setBz04(""); //备注项04
            //ordererinfo.setBz05(""); //备注项05
            //ordererinfo.setBz06(""); //备注项06
            //ordererinfo.setBz07(""); //备注项07
            //ordererinfo.setBz08(""); //备注项08
            //ordererinfo.setBz09(""); //备注项09
            //ordererinfo.setBz10(""); //备注项10
            //ordererinfo.setBz11(""); //备注项11
            //ordererinfo.setBz12(""); //备注项12
            //ordererinfo.setBz13(""); //备注项13
            //ordererinfo.setBz14(""); //备注项14
            //ordererinfo.setBz15(""); //备注项15
            //ordererinfo.setBz16(""); //备注项16
            //ordererinfo.setBz17(""); //备注项17
            //ordererinfo.setBz18(""); //备注项18
            //ordererinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 排序节点(ordererinfo).导出


    //====begin 排序节点(ordererinfo).测试
    @GetMapping("blockchain/ordererinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Ordererinfo ordererinfo = new Ordererinfo();
        List list=null;
//        ordererinfoMapper.updateByOrdererinfo(ordererinfo);
//        list=sqlUtilService.list("select * from ordererinfo where jdid like '%5%'");
        return list;
    }
    //====end 排序节点(ordererinfo).测试

}
