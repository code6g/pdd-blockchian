package com.ruoyi.pdd.controller;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.json.JSONObject.JSONArray;

import com.ruoyi.code6g.EasyExcelUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;

import com.ruoyi.pdd.mapperbean.Pddmx;
import com.ruoyi.pdd.mapperbean.PddmxMapper;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 订单明细(pddmx).控制类 -- 实现主表的管理功能
 */

@Controller                                                                                  //控制类 -- 实现主表的管理功能标签注解
@RequestMapping("/pdd/pddmxgl")                                    //pdd系统/订单明细
public class PddmxglController extends BaseController {

    private String prefix = "pdd/pddmxgl";                              //url:pdd系统/订单明细

    @Autowired
    private PddmxMapper pddmxMapper;                           //订单明细Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；




    //--excel导入-订单明细 pddmx
    @PostMapping("/importexcel")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            ExcelUtil<Pddmx> excelUtil = new ExcelUtil<Pddmx>(Pddmx.class);             //创建excel工具
            List<Pddmx> list = excelUtil.importExcel(file.getInputStream());                       //解析excel，生成list<entt>
            for (Pddmx pddmx : list) {
                pddmxMapper.insertPddmx(pddmx);                                                //循环插入entt到db
            }
            //System.out.println(list);
            return success("导入成功,新增【" + list.size() + "】条记录！");
        }catch (Exception e){
            System.out.println(e.toString());
            return error("导入失败！"+e.toString());
        }
    }


    //---导出excel模板
    @Log(title = "样本数据模板", businessType = BusinessType.EXPORT)
    @PostMapping("/downloadtemplate")
    @ResponseBody                                                             //返回restful-json
    public AjaxResult downloadtemplatePddmx(Pddmx pddmx)                //根据传入的订单明细(pddmx)做条件，查询并导出excel，返回结果restful-json
    {
        pddmx=getNewDownloadtemplatePddmx(pddmx);                        //对订单明细(pddmx)进行赋值操作,以便获取新的导出条件
        pddmx.setBz01("999abc");
        List<Pddmx> list = pddmxMapper.selectListByPddmx(pddmx);         //查询样本数据
        list=getNewExportPddmxList(list);                                         //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pddmx> util = new ExcelUtil<Pddmx>(Pddmx.class);          //创建导出工具
        return util.exportExcel(list, "订单明细-模板(pddmx)");               //执行导出
    } 



    private Pddmx getNewDownloadtemplatePddmx(Pddmx pddmx) {         //对订单明细(pddmx)进行赋值操作,以便获取新的导出条件
            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19



        return pddmx;
    }


    //-- begin 订单明细(pddmx).列表查询

    //-- 跳转到列表页面
    //@RequiresPermissions("pdd:pddmx:view")                          //shiro权限：pdd系统:订单明细:查看
    @GetMapping()                                                                               //url:pdd/pddmx
    public String toListPddmx(ModelMap mmap){                              //订单明细(pddmx).跳转到列表页面。从url:/pdd/pddmx跳转-->页面:pdd/pddmx/pddmx.html
        Pddmx pddmx =getToListPddmxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("pddmx", pddmx);                                             // 订单明细(pddmx)放入ModelMap
        return prefix + "/pddmx";                                                        // 重定向到页面：pdd/pddmx/pddmx.html
    }

    private Pddmx getToListPddmxCon() {                                     //订单明细(pddmx).构造查询条件的实体信息，在列表页面进行默认过滤
        Pddmx pddmx=new Pddmx();                                       //创建 订单明细(pddmx)
            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }

    //--查询数据，返回给列表页面
    //@RequiresPermissions("pdd:pddmx:list")                               // shiro权限：pdd系统:订单明细:列表
    @PostMapping("/list")                                                                        // url:/pdd/pddmx/list
    @ResponseBody                                                                               //返回restful-json
    public TableDataInfo querylistPddmx(Pddmx pddmx){          //订单明细(pddmx)：根据页面的form的数据组装pddmx，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                                                   //开始分页
        pddmx=getNewPddmxCon(pddmx);                               //修改订单明细实体，构造新的查询条件
        List<Pddmx> list = pddmxMapper.selectListByPddmx(pddmx);                                  //根据订单明细(pddmx)的属性，构造条件，查询出订单明细list
        list=getNewPddmxList(list);                                                                                                     //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                                                                              //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Pddmx getNewPddmxCon(Pddmx pddmx) {                                                    //修改订单明细实体，构造新的查询条件

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }
    private List<Pddmx> getNewPddmxList(List<Pddmx> list) {                                              //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Pddmx pddmx:list){
            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        }
        return list;
    }
    
	
	
    //--订单明细(pddmx).新增
    //--跳转到新增页面
    @GetMapping("/add")                                                                                                                //从url:/pdd/pddmx/add的get方法， 跳转到url:pdd/pddmx/add.html
    public String toAddPddmx(ModelMap mmap)
    {
        Pddmx pddmx = getInitPddmxAdd();                                                                       // 创建pddmx，并赋值，作为新增订单明细的初始化值
        mmap.put("pddmx", pddmx);                                                                                       // 订单明细(pddmx).放入ModelMap



        return prefix + "/add";                                                                                                           // 导航到url：pdd/pddmx/add
    }

    private Pddmx getInitPddmxAdd() {                                                                                 // 创建pddmx，并赋值，作为新增订单明细的初始化值
        Pddmx pddmx=new Pddmx();                                                                                //初始化订单明细实体
        String ts=InputUitl.getTimestamp();
        pddmx.setMxid(ts);                                                                                                            //mxid

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19

        return pddmx;
    }

    //--新增数据持久化
    //@RequiresPermissions("pdd:pddmx:add")                                                                   //shiro权限：pdd系统:订单明细:新增
    @Log(title = "订单明细", businessType = BusinessType.INSERT)                                                      //记录日志信息
    @PostMapping("/add")                                                                                                          //提交pddmx的接口url:/pdd/pddmx/add，采用post方法
    @ResponseBody                                                                                                                   //返回restful-json
    public AjaxResult addSavePddmx(Pddmx pddmx)                                                    //根据传入的pddmx，持久化，返回结果restful-json
    {
        pddmx=getNewAddPddmx(pddmx);                                                                   //对新增后的的订单明细对象进行赋值更新操作
        return toAjax(pddmxMapper.insertPddmx(pddmx));                                               //持久化
    }

    private Pddmx getNewAddPddmx(Pddmx pddmx) {                                            //对新增后的的订单明细对象进行赋值更新操作

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }
    

    //-- 订单明细(pddmx).修改

    //-- 跳转到修改页面
    @GetMapping("/edit/{mxid}")                                                                                                    //从url:/pdd/pddmx/edit/{mxid}的get方法， 跳转到页面：pdd/pddmx/edit.html
    public String toEditPddmx(@PathVariable("mxid") String mxid, ModelMap mmap)                    //根据传入的mxid，查询数据，放入ModelMap，返回给页面
    {
        Pddmx pddmx = pddmxMapper.selectById(mxid);                                                  //根据传入的mxid，查询订单明细(pddmx)
        pddmx=getNewEditInitPddmx(pddmx);                                                               //对查询出来的订单明细对象进行赋值操作
        mmap.put("pddmx", pddmx);                                                                                   //订单明细(pddmx)：放入ModelMap



        return prefix + "/edit";                                                                                                        //跳转到url：pdd/pddmx/edit.html
    }

    private Pddmx getNewEditInitPddmx(Pddmx pddmx) {                                            //对查询出来的订单明细对象进行赋值操作

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }

    //-- 修改后数据持久化
    //@RequiresPermissions("pdd:pddmx:edit")                                                           //shiro权限：pdd系统:订单明细:修改
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)                                        //记录日志信息
    @PostMapping("/edit")                                                                                                   //提交pddmx的接口url:/pdd/pddmx/edit，采用post方法
    @ResponseBody                                                                                                            //返回restful-json
    public AjaxResult editSavePddmx(Pddmx pddmx)                                             //根据传入的pddmx，更新持久化，返回结果restful-json
    {
        pddmx= getNewAfterEditPddmx(pddmx);                                                      //对编辑后的订单明细对象进行赋值操作
        return toAjax(pddmxMapper.updateByPddmx(pddmx));                                 //返回保存结果
    }
    private Pddmx getNewAfterEditPddmx(Pddmx pddmx) {                                //对编辑后的订单明细对象进行赋值操作

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19
       
	    
        return pddmx;
    }
    

    //-- 订单明细(pddmx).删除
    //-- 删除单条数据
    //@RequiresPermissions("pdd:pddmx:remove")                                                  //shiro权限：pdd系统:订单明细:删除
    @Log(title = "订单明细", businessType = BusinessType.DELETE)                                       //记录日志信息
    @GetMapping( "/remove/{mxid}")                                                                                   //提交pddmx的接口url:/pdd/pddmx/remove/{mxid}，采用get方法
    @ResponseBody                                                                                                          //返回restful-json
    public AjaxResult removeOnePddmx(@PathVariable("mxid") String mxid)                       //根据传入的mxid，删除单条数据，返回结果restful-json
    {
        return toAjax(pddmxMapper.deleteByMxid(mxid));                                                  //删除
    }

    //-- 删除多条数据
    //@RequiresPermissions("pdd:pddmx:remove")                                                      //shiro权限：pdd系统:订单明细:删除
    @Log(title = "订单明细", businessType = BusinessType.DELETE)                                           //记录日志信息
    @PostMapping( "/remove")                                                                                              //提交pddmx的接口url:/pdd/pddmx/remove，采用post方法
    @ResponseBody                                                                                                              //返回restful-json
    public AjaxResult removePddmx(@RequestParam("ids") String ids)                                //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                                                                       //把1，2，3转化为'1','2','3'
        return toAjax(pddmxMapper.deleteByMxids(ids2));
    }
     

    //-- begin 订单明细(pddmx).详情
    //-- 显示单条数据详情
    //@RequiresPermissions("pdd:pddmx:detail")                                                          //shiro权限：pdd系统:订单明细:详情
    @GetMapping("/detail/{mxid}")
    public String detail(@PathVariable("mxid") String  mxid, ModelMap mmap)                             //查询订单明细(pddmx),导航到url:pdd/pddmx/detail.html
    {
        Pddmx pddmx=pddmxMapper.selectById(mxid);                                               //查询订单明细(pddmx)
        pddmx=getNewDetailPddmx(pddmx);                                                             //对订单明细(pddmx)进行赋值操作,以便获取新的详情
        mmap.put("pddmx", pddmx);                                                                               //订单明细(pddmx)放入model,传到前台页面
        return prefix + "/detail";                                                                                                 //导航到url:pdd/pddmx/detail.html
    }
    private Pddmx getNewDetailPddmx(Pddmx pddmx) {                                          //对订单明细(pddmx)进行赋值操作,以便获取新的详情
            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }
     

    //-- 订单明细(pddmx).导出
    //-- 导出信息列表
    //@RequiresPermissions("system:pddmx:export")                                                  //shiro权限：pdd系统:订单明细:导出
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)                                   //记录日志信息
    @PostMapping("/export")                                                                                          //根据pddmx条件进行导出接口url:/pdd/pddmx/export，采用post方法
    @ResponseBody                                                                                                       //返回restful-json
    public AjaxResult exportPddmx(Pddmx pddmx)                                            //根据传入的订单明细(pddmx)做条件，查询并导出excel，返回结果restful-json
    {
        pddmx=getNewExportPddmx(pddmx);                                                      //对订单明细(pddmx)进行赋值操作,以便获取新的导出条件
        List<Pddmx> list = pddmxMapper.selectListByPddmx(pddmx);              //查询订单明细
        list=getNewExportPddmxList(list);                                                                        //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pddmx> util = new ExcelUtil<Pddmx>(Pddmx.class);                     //创建导出工具
        return util.exportExcel(list, "订单明细(pddmx)");                                                      //执行导出
    }
    private Pddmx getNewExportPddmx(Pddmx pddmx) {                              //对订单明细(pddmx)进行赋值操作,以便获取新的导出条件

            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        return pddmx;
    }
    private List<Pddmx> getNewExportPddmxList(List<Pddmx> list) {                  //对查询出来的list信息进行加工处理，以便进行导出
        for(Pddmx pddmx:list){
            //pddmx.setMxid(""); //明细编码
            //pddmx.setDdid(""); //订单编码
            //pddmx.setDdbh(""); //订单编号
            //pddmx.setHyid(""); //会员编码
            //pddmx.setSpid(""); //商品编码
            //pddmx.setSpmc(""); //商品名称
            //pddmx.setSpsl(""); //商品数量
            //pddmx.setSpjg(""); //商品价格
            //pddmx.setJgxj(""); //价格小计
            //pddmx.setMxsm(""); //明细说明
            //pddmx.setBz00(""); //备注项00
            //pddmx.setBz01(""); //备注项01
            //pddmx.setBz02(""); //备注项02
            //pddmx.setBz03(""); //备注项03
            //pddmx.setBz04(""); //备注项04
            //pddmx.setBz05(""); //备注项05
            //pddmx.setBz06(""); //备注项06
            //pddmx.setBz07(""); //备注项07
            //pddmx.setBz08(""); //备注项08
            //pddmx.setBz09(""); //备注项09
            //pddmx.setBz10(""); //备注项10
            //pddmx.setBz11(""); //备注项11
            //pddmx.setBz12(""); //备注项12
            //pddmx.setBz13(""); //备注项13
            //pddmx.setBz14(""); //备注项14
            //pddmx.setBz15(""); //备注项15
            //pddmx.setBz16(""); //备注项16
            //pddmx.setBz17(""); //备注项17
            //pddmx.setBz18(""); //备注项18
            //pddmx.setBz19(""); //备注项19


        }
        return list;
    }
     

    //-- 订单明细(pddmx).测试
    @GetMapping("pdd/pddmx/test")                                 //测试工具路径
    public  List<?>  test() {                                                             //测试工具
        Pddmx pddmx = new Pddmx();
        List list=null;
//        pddmxMapper.updateByPddmx(pddmx);
//        list=sqlUtilService.list("select * from pddmx where mxid like '%5%'");
        return list;
    }
    
}
