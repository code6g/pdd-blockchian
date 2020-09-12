package com.ruoyi.pdd.controller;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.json.JSONObject.JSONArray;

import com.ruoyi.code6g.EasyExcelUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;

import com.ruoyi.pdd.mapperbean.Phyxx;
import com.ruoyi.pdd.mapperbean.PhyxxMapper;
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
 * 会员信息(phyxx).控制类
 */

@Controller                                                                                  //控制类标签注解
@RequestMapping("/pdd/phyxx")                                    //pdd系统/会员信息
public class PhyxxController extends BaseController {

    private String prefix = "pdd/phyxx";                              //url:pdd系统/会员信息

    @Autowired
    private PhyxxMapper phyxxMapper;                           //会员信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；




    //--excel导入-会员信息 phyxx
    @PostMapping("/importexcel")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            ExcelUtil<Phyxx> excelUtil = new ExcelUtil<Phyxx>(Phyxx.class);             //创建excel工具
            List<Phyxx> list = excelUtil.importExcel(file.getInputStream());                       //解析excel，生成list<entt>
            for (Phyxx phyxx : list) {
                phyxxMapper.insertPhyxx(phyxx);                                                //循环插入entt到db
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
    public AjaxResult downloadtemplatePhyxx(Phyxx phyxx)                //根据传入的会员信息(phyxx)做条件，查询并导出excel，返回结果restful-json
    {
        phyxx=getNewDownloadtemplatePhyxx(phyxx);                        //对会员信息(phyxx)进行赋值操作,以便获取新的导出条件
        phyxx.setBz01("999abc");
        List<Phyxx> list = phyxxMapper.selectListByPhyxx(phyxx);         //查询样本数据
        list=getNewExportPhyxxList(list);                                         //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Phyxx> util = new ExcelUtil<Phyxx>(Phyxx.class);          //创建导出工具
        return util.exportExcel(list, "会员信息-模板(phyxx)");               //执行导出
    } 



    private Phyxx getNewDownloadtemplatePhyxx(Phyxx phyxx) {         //对会员信息(phyxx)进行赋值操作,以便获取新的导出条件
            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19



        return phyxx;
    }


    //-- begin 会员信息(phyxx).列表查询

    //-- 跳转到列表页面
    //@RequiresPermissions("pdd:phyxx:view")                          //shiro权限：pdd系统:会员信息:查看
    @GetMapping()                                                                               //url:pdd/phyxx
    public String toListPhyxx(ModelMap mmap){                              //会员信息(phyxx).跳转到列表页面。从url:/pdd/phyxx跳转-->页面:pdd/phyxx/phyxx.html
        Phyxx phyxx =getToListPhyxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("phyxx", phyxx);                                             // 会员信息(phyxx)放入ModelMap
        return prefix + "/phyxx";                                                        // 重定向到页面：pdd/phyxx/phyxx.html
    }

    private Phyxx getToListPhyxxCon() {                                     //会员信息(phyxx).构造查询条件的实体信息，在列表页面进行默认过滤
        Phyxx phyxx=new Phyxx();                                       //创建 会员信息(phyxx)
            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }

    //--查询数据，返回给列表页面
    //@RequiresPermissions("pdd:phyxx:list")                               // shiro权限：pdd系统:会员信息:列表
    @PostMapping("/list")                                                                        // url:/pdd/phyxx/list
    @ResponseBody                                                                               //返回restful-json
    public TableDataInfo querylistPhyxx(Phyxx phyxx){          //会员信息(phyxx)：根据页面的form的数据组装phyxx，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                                                   //开始分页
        phyxx=getNewPhyxxCon(phyxx);                               //修改会员信息实体，构造新的查询条件
        List<Phyxx> list = phyxxMapper.selectListByPhyxx(phyxx);                                  //根据会员信息(phyxx)的属性，构造条件，查询出会员信息list
        list=getNewPhyxxList(list);                                                                                                     //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                                                                              //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Phyxx getNewPhyxxCon(Phyxx phyxx) {                                                    //修改会员信息实体，构造新的查询条件

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }
    private List<Phyxx> getNewPhyxxList(List<Phyxx> list) {                                              //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Phyxx phyxx:list){
            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        }
        return list;
    }
    
	
	
    //--会员信息(phyxx).新增
    //--跳转到新增页面
    @GetMapping("/add")                                                                                                                //从url:/pdd/phyxx/add的get方法， 跳转到url:pdd/phyxx/add.html
    public String toAddPhyxx(ModelMap mmap)
    {
        Phyxx phyxx = getInitPhyxxAdd();                                                                       // 创建phyxx，并赋值，作为新增会员信息的初始化值
        mmap.put("phyxx", phyxx);                                                                                       // 会员信息(phyxx).放入ModelMap



        return prefix + "/add";                                                                                                           // 导航到url：pdd/phyxx/add
    }

    private Phyxx getInitPhyxxAdd() {                                                                                 // 创建phyxx，并赋值，作为新增会员信息的初始化值
        Phyxx phyxx=new Phyxx();                                                                                //初始化会员信息实体
        String ts=InputUitl.getTimestamp();
        phyxx.setHyid(ts);                                                                                                            //hyid

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19

        return phyxx;
    }

    //--新增数据持久化
    //@RequiresPermissions("pdd:phyxx:add")                                                                   //shiro权限：pdd系统:会员信息:新增
    @Log(title = "会员信息", businessType = BusinessType.INSERT)                                                      //记录日志信息
    @PostMapping("/add")                                                                                                          //提交phyxx的接口url:/pdd/phyxx/add，采用post方法
    @ResponseBody                                                                                                                   //返回restful-json
    public AjaxResult addSavePhyxx(Phyxx phyxx)                                                    //根据传入的phyxx，持久化，返回结果restful-json
    {
        phyxx=getNewAddPhyxx(phyxx);                                                                   //对新增后的的会员信息对象进行赋值更新操作
        return toAjax(phyxxMapper.insertPhyxx(phyxx));                                               //持久化
    }

    private Phyxx getNewAddPhyxx(Phyxx phyxx) {                                            //对新增后的的会员信息对象进行赋值更新操作

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }
    

    //-- 会员信息(phyxx).修改

    //-- 跳转到修改页面
    @GetMapping("/edit/{hyid}")                                                                                                    //从url:/pdd/phyxx/edit/{hyid}的get方法， 跳转到页面：pdd/phyxx/edit.html
    public String toEditPhyxx(@PathVariable("hyid") String hyid, ModelMap mmap)                    //根据传入的hyid，查询数据，放入ModelMap，返回给页面
    {
        Phyxx phyxx = phyxxMapper.selectById(hyid);                                                  //根据传入的hyid，查询会员信息(phyxx)
        phyxx=getNewEditInitPhyxx(phyxx);                                                               //对查询出来的会员信息对象进行赋值操作
        mmap.put("phyxx", phyxx);                                                                                   //会员信息(phyxx)：放入ModelMap



        return prefix + "/edit";                                                                                                        //跳转到url：pdd/phyxx/edit.html
    }

    private Phyxx getNewEditInitPhyxx(Phyxx phyxx) {                                            //对查询出来的会员信息对象进行赋值操作

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }

    //-- 修改后数据持久化
    //@RequiresPermissions("pdd:phyxx:edit")                                                           //shiro权限：pdd系统:会员信息:修改
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)                                        //记录日志信息
    @PostMapping("/edit")                                                                                                   //提交phyxx的接口url:/pdd/phyxx/edit，采用post方法
    @ResponseBody                                                                                                            //返回restful-json
    public AjaxResult editSavePhyxx(Phyxx phyxx)                                             //根据传入的phyxx，更新持久化，返回结果restful-json
    {
        phyxx= getNewAfterEditPhyxx(phyxx);                                                      //对编辑后的会员信息对象进行赋值操作
        return toAjax(phyxxMapper.updateByPhyxx(phyxx));                                 //返回保存结果
    }
    private Phyxx getNewAfterEditPhyxx(Phyxx phyxx) {                                //对编辑后的会员信息对象进行赋值操作

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19
       
	    
        return phyxx;
    }
    

    //-- 会员信息(phyxx).删除
    //-- 删除单条数据
    //@RequiresPermissions("pdd:phyxx:remove")                                                  //shiro权限：pdd系统:会员信息:删除
    @Log(title = "会员信息", businessType = BusinessType.DELETE)                                       //记录日志信息
    @GetMapping( "/remove/{hyid}")                                                                                   //提交phyxx的接口url:/pdd/phyxx/remove/{hyid}，采用get方法
    @ResponseBody                                                                                                          //返回restful-json
    public AjaxResult removeOnePhyxx(@PathVariable("hyid") String hyid)                       //根据传入的hyid，删除单条数据，返回结果restful-json
    {
        return toAjax(phyxxMapper.deleteByHyid(hyid));                                                  //删除
    }

    //-- 删除多条数据
    //@RequiresPermissions("pdd:phyxx:remove")                                                      //shiro权限：pdd系统:会员信息:删除
    @Log(title = "会员信息", businessType = BusinessType.DELETE)                                           //记录日志信息
    @PostMapping( "/remove")                                                                                              //提交phyxx的接口url:/pdd/phyxx/remove，采用post方法
    @ResponseBody                                                                                                              //返回restful-json
    public AjaxResult removePhyxx(@RequestParam("ids") String ids)                                //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                                                                       //把1，2，3转化为'1','2','3'
        return toAjax(phyxxMapper.deleteByHyids(ids2));
    }
     

    //-- begin 会员信息(phyxx).详情
    //-- 显示单条数据详情
    //@RequiresPermissions("pdd:phyxx:detail")                                                          //shiro权限：pdd系统:会员信息:详情
    @GetMapping("/detail/{hyid}")
    public String detail(@PathVariable("hyid") String  hyid, ModelMap mmap)                             //查询会员信息(phyxx),导航到url:pdd/phyxx/detail.html
    {
        Phyxx phyxx=phyxxMapper.selectById(hyid);                                               //查询会员信息(phyxx)
        phyxx=getNewDetailPhyxx(phyxx);                                                             //对会员信息(phyxx)进行赋值操作,以便获取新的详情
        mmap.put("phyxx", phyxx);                                                                               //会员信息(phyxx)放入model,传到前台页面
        return prefix + "/detail";                                                                                                 //导航到url:pdd/phyxx/detail.html
    }
    private Phyxx getNewDetailPhyxx(Phyxx phyxx) {                                          //对会员信息(phyxx)进行赋值操作,以便获取新的详情
            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }
     

    //-- 会员信息(phyxx).导出
    //-- 导出信息列表
    //@RequiresPermissions("system:phyxx:export")                                                  //shiro权限：pdd系统:会员信息:导出
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)                                   //记录日志信息
    @PostMapping("/export")                                                                                          //根据phyxx条件进行导出接口url:/pdd/phyxx/export，采用post方法
    @ResponseBody                                                                                                       //返回restful-json
    public AjaxResult exportPhyxx(Phyxx phyxx)                                            //根据传入的会员信息(phyxx)做条件，查询并导出excel，返回结果restful-json
    {
        phyxx=getNewExportPhyxx(phyxx);                                                      //对会员信息(phyxx)进行赋值操作,以便获取新的导出条件
        List<Phyxx> list = phyxxMapper.selectListByPhyxx(phyxx);              //查询会员信息
        list=getNewExportPhyxxList(list);                                                                        //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Phyxx> util = new ExcelUtil<Phyxx>(Phyxx.class);                     //创建导出工具
        return util.exportExcel(list, "会员信息(phyxx)");                                                      //执行导出
    }
    private Phyxx getNewExportPhyxx(Phyxx phyxx) {                              //对会员信息(phyxx)进行赋值操作,以便获取新的导出条件

            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        return phyxx;
    }
    private List<Phyxx> getNewExportPhyxxList(List<Phyxx> list) {                  //对查询出来的list信息进行加工处理，以便进行导出
        for(Phyxx phyxx:list){
            //phyxx.setHyid(""); //会员编码
            //phyxx.setHyzh(""); //会员账号
            //phyxx.setHymm(""); //会员密码
            //phyxx.setHymc(""); //会员名称
            //phyxx.setHydz(""); //会员地址
            //phyxx.setHydh(""); //会员电话
            //phyxx.setHyzt(""); //会员状态
            //phyxx.setZcrq(""); //注册时间
            //phyxx.setBz00(""); //备注项00
            //phyxx.setBz01(""); //备注项01
            //phyxx.setBz02(""); //备注项02
            //phyxx.setBz03(""); //备注项03
            //phyxx.setBz04(""); //备注项04
            //phyxx.setBz05(""); //备注项05
            //phyxx.setBz06(""); //备注项06
            //phyxx.setBz07(""); //备注项07
            //phyxx.setBz08(""); //备注项08
            //phyxx.setBz09(""); //备注项09
            //phyxx.setBz10(""); //备注项10
            //phyxx.setBz11(""); //备注项11
            //phyxx.setBz12(""); //备注项12
            //phyxx.setBz13(""); //备注项13
            //phyxx.setBz14(""); //备注项14
            //phyxx.setBz15(""); //备注项15
            //phyxx.setBz16(""); //备注项16
            //phyxx.setBz17(""); //备注项17
            //phyxx.setBz18(""); //备注项18
            //phyxx.setBz19(""); //备注项19


        }
        return list;
    }
     

    //-- 会员信息(phyxx).测试
    @GetMapping("pdd/phyxx/test")                                 //测试工具路径
    public  List<?>  test() {                                                             //测试工具
        Phyxx phyxx = new Phyxx();
        List list=null;
//        phyxxMapper.updateByPhyxx(phyxx);
//        list=sqlUtilService.list("select * from phyxx where hyid like '%5%'");
        return list;
    }
    
}
