package com.ruoyi.pdd.controller;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.json.JSONObject.JSONArray;

import com.ruoyi.code6g.EasyExcelUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;

import com.ruoyi.pdd.mapperbean.Pspxx;
import com.ruoyi.pdd.mapperbean.PspxxMapper;
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
 * 商品信息(pspxx).控制类
 */

@Controller                                                                                  //控制类标签注解
@RequestMapping("/pdd/pspxx")                                    //pdd系统/商品信息
public class PspxxController extends BaseController {

    private String prefix = "pdd/pspxx";                              //url:pdd系统/商品信息

    @Autowired
    private PspxxMapper pspxxMapper;                           //商品信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；




    //--excel导入-商品信息 pspxx
    @PostMapping("/importexcel")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            ExcelUtil<Pspxx> excelUtil = new ExcelUtil<Pspxx>(Pspxx.class);             //创建excel工具
            List<Pspxx> list = excelUtil.importExcel(file.getInputStream());                       //解析excel，生成list<entt>
            for (Pspxx pspxx : list) {
                pspxxMapper.insertPspxx(pspxx);                                                //循环插入entt到db
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
    public AjaxResult downloadtemplatePspxx(Pspxx pspxx)                //根据传入的商品信息(pspxx)做条件，查询并导出excel，返回结果restful-json
    {
        pspxx=getNewDownloadtemplatePspxx(pspxx);                        //对商品信息(pspxx)进行赋值操作,以便获取新的导出条件
        pspxx.setBz01("999abc");
        List<Pspxx> list = pspxxMapper.selectListByPspxx(pspxx);         //查询样本数据
        list=getNewExportPspxxList(list);                                         //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pspxx> util = new ExcelUtil<Pspxx>(Pspxx.class);          //创建导出工具
        return util.exportExcel(list, "商品信息-模板(pspxx)");               //执行导出
    } 



    private Pspxx getNewDownloadtemplatePspxx(Pspxx pspxx) {         //对商品信息(pspxx)进行赋值操作,以便获取新的导出条件
            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19



        return pspxx;
    }


    //-- begin 商品信息(pspxx).列表查询

    //-- 跳转到列表页面
    //@RequiresPermissions("pdd:pspxx:view")                          //shiro权限：pdd系统:商品信息:查看
    @GetMapping()                                                                               //url:pdd/pspxx
    public String toListPspxx(ModelMap mmap){                              //商品信息(pspxx).跳转到列表页面。从url:/pdd/pspxx跳转-->页面:pdd/pspxx/pspxx.html
        Pspxx pspxx =getToListPspxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("pspxx", pspxx);                                             // 商品信息(pspxx)放入ModelMap
        return prefix + "/pspxx";                                                        // 重定向到页面：pdd/pspxx/pspxx.html
    }

    private Pspxx getToListPspxxCon() {                                     //商品信息(pspxx).构造查询条件的实体信息，在列表页面进行默认过滤
        Pspxx pspxx=new Pspxx();                                       //创建 商品信息(pspxx)
            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }

    //--查询数据，返回给列表页面
    //@RequiresPermissions("pdd:pspxx:list")                               // shiro权限：pdd系统:商品信息:列表
    @PostMapping("/list")                                                                        // url:/pdd/pspxx/list
    @ResponseBody                                                                               //返回restful-json
    public TableDataInfo querylistPspxx(Pspxx pspxx){          //商品信息(pspxx)：根据页面的form的数据组装pspxx，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                                                   //开始分页
        pspxx=getNewPspxxCon(pspxx);                               //修改商品信息实体，构造新的查询条件
        List<Pspxx> list = pspxxMapper.selectListByPspxx(pspxx);                                  //根据商品信息(pspxx)的属性，构造条件，查询出商品信息list
        list=getNewPspxxList(list);                                                                                                     //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                                                                              //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Pspxx getNewPspxxCon(Pspxx pspxx) {                                                    //修改商品信息实体，构造新的查询条件

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }
    private List<Pspxx> getNewPspxxList(List<Pspxx> list) {                                              //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Pspxx pspxx:list){
            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        }
        return list;
    }
    
	
	
    //--商品信息(pspxx).新增
    //--跳转到新增页面
    @GetMapping("/add")                                                                                                                //从url:/pdd/pspxx/add的get方法， 跳转到url:pdd/pspxx/add.html
    public String toAddPspxx(ModelMap mmap)
    {
        Pspxx pspxx = getInitPspxxAdd();                                                                       // 创建pspxx，并赋值，作为新增商品信息的初始化值
        mmap.put("pspxx", pspxx);                                                                                       // 商品信息(pspxx).放入ModelMap



        return prefix + "/add";                                                                                                           // 导航到url：pdd/pspxx/add
    }

    private Pspxx getInitPspxxAdd() {                                                                                 // 创建pspxx，并赋值，作为新增商品信息的初始化值
        Pspxx pspxx=new Pspxx();                                                                                //初始化商品信息实体
        String ts=InputUitl.getTimestamp();
        pspxx.setSpid(ts);                                                                                                            //spid

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19

        return pspxx;
    }

    //--新增数据持久化
    //@RequiresPermissions("pdd:pspxx:add")                                                                   //shiro权限：pdd系统:商品信息:新增
    @Log(title = "商品信息", businessType = BusinessType.INSERT)                                                      //记录日志信息
    @PostMapping("/add")                                                                                                          //提交pspxx的接口url:/pdd/pspxx/add，采用post方法
    @ResponseBody                                                                                                                   //返回restful-json
    public AjaxResult addSavePspxx(Pspxx pspxx)                                                    //根据传入的pspxx，持久化，返回结果restful-json
    {
        pspxx=getNewAddPspxx(pspxx);                                                                   //对新增后的的商品信息对象进行赋值更新操作
        return toAjax(pspxxMapper.insertPspxx(pspxx));                                               //持久化
    }

    private Pspxx getNewAddPspxx(Pspxx pspxx) {                                            //对新增后的的商品信息对象进行赋值更新操作

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }
    

    //-- 商品信息(pspxx).修改

    //-- 跳转到修改页面
    @GetMapping("/edit/{spid}")                                                                                                    //从url:/pdd/pspxx/edit/{spid}的get方法， 跳转到页面：pdd/pspxx/edit.html
    public String toEditPspxx(@PathVariable("spid") String spid, ModelMap mmap)                    //根据传入的spid，查询数据，放入ModelMap，返回给页面
    {
        Pspxx pspxx = pspxxMapper.selectById(spid);                                                  //根据传入的spid，查询商品信息(pspxx)
        pspxx=getNewEditInitPspxx(pspxx);                                                               //对查询出来的商品信息对象进行赋值操作
        mmap.put("pspxx", pspxx);                                                                                   //商品信息(pspxx)：放入ModelMap



        return prefix + "/edit";                                                                                                        //跳转到url：pdd/pspxx/edit.html
    }

    private Pspxx getNewEditInitPspxx(Pspxx pspxx) {                                            //对查询出来的商品信息对象进行赋值操作

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }

    //-- 修改后数据持久化
    //@RequiresPermissions("pdd:pspxx:edit")                                                           //shiro权限：pdd系统:商品信息:修改
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)                                        //记录日志信息
    @PostMapping("/edit")                                                                                                   //提交pspxx的接口url:/pdd/pspxx/edit，采用post方法
    @ResponseBody                                                                                                            //返回restful-json
    public AjaxResult editSavePspxx(Pspxx pspxx)                                             //根据传入的pspxx，更新持久化，返回结果restful-json
    {
        pspxx= getNewAfterEditPspxx(pspxx);                                                      //对编辑后的商品信息对象进行赋值操作
        return toAjax(pspxxMapper.updateByPspxx(pspxx));                                 //返回保存结果
    }
    private Pspxx getNewAfterEditPspxx(Pspxx pspxx) {                                //对编辑后的商品信息对象进行赋值操作

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19
       
	    
        return pspxx;
    }
    

    //-- 商品信息(pspxx).删除
    //-- 删除单条数据
    //@RequiresPermissions("pdd:pspxx:remove")                                                  //shiro权限：pdd系统:商品信息:删除
    @Log(title = "商品信息", businessType = BusinessType.DELETE)                                       //记录日志信息
    @GetMapping( "/remove/{spid}")                                                                                   //提交pspxx的接口url:/pdd/pspxx/remove/{spid}，采用get方法
    @ResponseBody                                                                                                          //返回restful-json
    public AjaxResult removeOnePspxx(@PathVariable("spid") String spid)                       //根据传入的spid，删除单条数据，返回结果restful-json
    {
        return toAjax(pspxxMapper.deleteBySpid(spid));                                                  //删除
    }

    //-- 删除多条数据
    //@RequiresPermissions("pdd:pspxx:remove")                                                      //shiro权限：pdd系统:商品信息:删除
    @Log(title = "商品信息", businessType = BusinessType.DELETE)                                           //记录日志信息
    @PostMapping( "/remove")                                                                                              //提交pspxx的接口url:/pdd/pspxx/remove，采用post方法
    @ResponseBody                                                                                                              //返回restful-json
    public AjaxResult removePspxx(@RequestParam("ids") String ids)                                //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                                                                       //把1，2，3转化为'1','2','3'
        return toAjax(pspxxMapper.deleteBySpids(ids2));
    }
     

    //-- begin 商品信息(pspxx).详情
    //-- 显示单条数据详情
    //@RequiresPermissions("pdd:pspxx:detail")                                                          //shiro权限：pdd系统:商品信息:详情
    @GetMapping("/detail/{spid}")
    public String detail(@PathVariable("spid") String  spid, ModelMap mmap)                             //查询商品信息(pspxx),导航到url:pdd/pspxx/detail.html
    {
        Pspxx pspxx=pspxxMapper.selectById(spid);                                               //查询商品信息(pspxx)
        pspxx=getNewDetailPspxx(pspxx);                                                             //对商品信息(pspxx)进行赋值操作,以便获取新的详情
        mmap.put("pspxx", pspxx);                                                                               //商品信息(pspxx)放入model,传到前台页面
        return prefix + "/detail";                                                                                                 //导航到url:pdd/pspxx/detail.html
    }
    private Pspxx getNewDetailPspxx(Pspxx pspxx) {                                          //对商品信息(pspxx)进行赋值操作,以便获取新的详情
            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }
     

    //-- 商品信息(pspxx).导出
    //-- 导出信息列表
    //@RequiresPermissions("system:pspxx:export")                                                  //shiro权限：pdd系统:商品信息:导出
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)                                   //记录日志信息
    @PostMapping("/export")                                                                                          //根据pspxx条件进行导出接口url:/pdd/pspxx/export，采用post方法
    @ResponseBody                                                                                                       //返回restful-json
    public AjaxResult exportPspxx(Pspxx pspxx)                                            //根据传入的商品信息(pspxx)做条件，查询并导出excel，返回结果restful-json
    {
        pspxx=getNewExportPspxx(pspxx);                                                      //对商品信息(pspxx)进行赋值操作,以便获取新的导出条件
        List<Pspxx> list = pspxxMapper.selectListByPspxx(pspxx);              //查询商品信息
        list=getNewExportPspxxList(list);                                                                        //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pspxx> util = new ExcelUtil<Pspxx>(Pspxx.class);                     //创建导出工具
        return util.exportExcel(list, "商品信息(pspxx)");                                                      //执行导出
    }
    private Pspxx getNewExportPspxx(Pspxx pspxx) {                              //对商品信息(pspxx)进行赋值操作,以便获取新的导出条件

            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        return pspxx;
    }
    private List<Pspxx> getNewExportPspxxList(List<Pspxx> list) {                  //对查询出来的list信息进行加工处理，以便进行导出
        for(Pspxx pspxx:list){
            //pspxx.setSpid(""); //商品编码
            //pspxx.setFlid(""); //分类编码
            //pspxx.setFlmc(""); //分类名称
            //pspxx.setSpmc(""); //商品名称
            //pspxx.setSpjg(""); //商品价格
            //pspxx.setSpms(""); //商品描述
            //pspxx.setSptp(""); //商品图片
            //pspxx.setSpzt(""); //商品状态
            //pspxx.setBz00(""); //备注项00
            //pspxx.setBz01(""); //备注项01
            //pspxx.setBz02(""); //备注项02
            //pspxx.setBz03(""); //备注项03
            //pspxx.setBz04(""); //备注项04
            //pspxx.setBz05(""); //备注项05
            //pspxx.setBz06(""); //备注项06
            //pspxx.setBz07(""); //备注项07
            //pspxx.setBz08(""); //备注项08
            //pspxx.setBz09(""); //备注项09
            //pspxx.setBz10(""); //备注项10
            //pspxx.setBz11(""); //备注项11
            //pspxx.setBz12(""); //备注项12
            //pspxx.setBz13(""); //备注项13
            //pspxx.setBz14(""); //备注项14
            //pspxx.setBz15(""); //备注项15
            //pspxx.setBz16(""); //备注项16
            //pspxx.setBz17(""); //备注项17
            //pspxx.setBz18(""); //备注项18
            //pspxx.setBz19(""); //备注项19


        }
        return list;
    }
     

    //-- 商品信息(pspxx).测试
    @GetMapping("pdd/pspxx/test")                                 //测试工具路径
    public  List<?>  test() {                                                             //测试工具
        Pspxx pspxx = new Pspxx();
        List list=null;
//        pspxxMapper.updateByPspxx(pspxx);
//        list=sqlUtilService.list("select * from pspxx where spid like '%5%'");
        return list;
    }
    
}
