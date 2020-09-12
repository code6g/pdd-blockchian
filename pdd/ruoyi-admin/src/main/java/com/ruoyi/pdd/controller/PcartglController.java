package com.ruoyi.pdd.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.blockchain.ywtool.CartCCTool;
import com.ruoyi.blockchain.ywtool.HyxxCCTool;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.json.JSONObject.JSONArray;

import com.ruoyi.code6g.EasyExcelUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;

import com.ruoyi.pdd.mapperbean.Pcart;
import com.ruoyi.pdd.mapperbean.PcartMapper;
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
 * 购物车(pcart).控制类 -- 实现主表的管理功能
 */

@Controller                                                                                  //控制类 -- 实现主表的管理功能标签注解
@RequestMapping("/pdd/pcartgl")                                    //pdd系统/购物车
public class PcartglController extends BaseController {

    private String prefix = "pdd/pcartgl";                              //url:pdd系统/购物车

    @Autowired
    private PcartMapper pcartMapper;                           //购物车Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；


    //--excel导入-购物车 pcart
    @PostMapping("/importexcel")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            ExcelUtil<Pcart> excelUtil = new ExcelUtil<Pcart>(Pcart.class);             //创建excel工具
            List<Pcart> list = excelUtil.importExcel(file.getInputStream());                       //解析excel，生成list<entt>
            for (Pcart pcart : list) {
                pcartMapper.insertPcart(pcart);                                                //循环插入entt到db
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
    @ResponseBody                                                        //返回restful-json
    public AjaxResult downloadtemplatePcart(Pcart pcart)                 //根据传入的购物车(pcart)做条件，查询并导出excel，返回结果restful-json
    {
        pcart=getNewDownloadtemplatePcart(pcart);                        //对购物车(pcart)进行赋值操作,以便获取新的导出条件
        pcart.setBz01("999abc");
        List<Pcart> list = pcartMapper.selectListByPcart(pcart);         //查询样本数据
        list=getNewExportPcartList(list);                                         //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pcart> util = new ExcelUtil<Pcart>(Pcart.class);           //创建导出工具
        return util.exportExcel(list, "购物车-模板(pcart)");    //执行导出
    } 



    private Pcart getNewDownloadtemplatePcart(Pcart pcart) {         //对购物车(pcart)进行赋值操作,以便获取新的导出条件
            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19



        return pcart;
    }


    //-- begin 购物车(pcart).列表查询

    //-- 跳转到列表页面
    //@RequiresPermissions("pdd:pcart:view")                          //shiro权限：pdd系统:购物车:查看
    @GetMapping()                                                                               //url:pdd/pcart
    public String toListPcart(ModelMap mmap){                              //购物车(pcart).跳转到列表页面。从url:/pdd/pcart跳转-->页面:pdd/pcart/pcart.html
        Pcart pcart =getToListPcartCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("pcart", pcart);                                             // 购物车(pcart)放入ModelMap
        return prefix + "/pcart";                                                        // 重定向到页面：pdd/pcart/pcart.html
    }

    private Pcart getToListPcartCon() {                                     //购物车(pcart).构造查询条件的实体信息，在列表页面进行默认过滤
        Pcart pcart=new Pcart();                                       //创建 购物车(pcart)
            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }

    //--查询数据，返回给列表页面
    //@RequiresPermissions("pdd:pcart:list")                               // shiro权限：pdd系统:购物车:列表
    @PostMapping("/list")                                                                        // url:/pdd/pcart/list
    @ResponseBody                                                                               //返回restful-json
    public TableDataInfo querylistPcart(Pcart pcart){          //购物车(pcart)：根据页面的form的数据组装pcart，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                                                   //开始分页
        pcart=getNewPcartCon(pcart);                               //修改购物车实体，构造新的查询条件

        //query from blockchain
        String sJson=CartCCTool.queryRange("cart_hyid", "cart_hyie" );
        System.out.println("=="+sJson);
        List<Pcart> list = JSON.parseArray(sJson, Pcart.class);

 //     List<Pcart> list = pcartMapper.selectListByPcart(pcart);                                  //根据购物车(pcart)的属性，构造条件，查询出购物车list
        list=getNewPcartList(list);                                                                                                     //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                                                                              //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Pcart getNewPcartCon(Pcart pcart) {                                                    //修改购物车实体，构造新的查询条件

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }
    private List<Pcart> getNewPcartList(List<Pcart> list) {                                              //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Pcart pcart:list){
            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        }
        return list;
    }
    
	
	
    //--购物车(pcart).新增
    //--跳转到新增页面
    @GetMapping("/add")                                                                                                                //从url:/pdd/pcart/add的get方法， 跳转到url:pdd/pcart/add.html
    public String toAddPcart(ModelMap mmap)
    {
        Pcart pcart = getInitPcartAdd();                                                                       // 创建pcart，并赋值，作为新增购物车的初始化值
        mmap.put("pcart", pcart);                                                                                       // 购物车(pcart).放入ModelMap



        return prefix + "/add";                                                                                                           // 导航到url：pdd/pcart/add
    }

    private Pcart getInitPcartAdd() {                                                                                 // 创建pcart，并赋值，作为新增购物车的初始化值
        Pcart pcart=new Pcart();                                                                                //初始化购物车实体
        String ts=InputUitl.getTimestamp();
        pcart.setGwid(ts);                                                                                                            //gwid

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19

        return pcart;
    }

    //--新增数据持久化
    //@RequiresPermissions("pdd:pcart:add")                                                                   //shiro权限：pdd系统:购物车:新增
    @Log(title = "购物车", businessType = BusinessType.INSERT)                                                      //记录日志信息
    @PostMapping("/add")                                                                                                          //提交pcart的接口url:/pdd/pcart/add，采用post方法
    @ResponseBody                                                                                                                   //返回restful-json
    public AjaxResult addSavePcart(Pcart pcart)                                                    //根据传入的pcart，持久化，返回结果restful-json
    {


//        pcart=getNewAddPcart(pcart);                                                                   //对新增后的的购物车对象进行赋值更新操作
//        return toAjax(pcartMapper.insertPcart(pcart));                                               //持久化



        String[] arrayValue=getArrayValue_cart(pcart);
        int res=CartCCTool.insertCart(arrayValue)?1:0;

        //phyxx=getNewAddPhyxx(phyxx);                                                                   //对新增后的的会员信息对象进行赋值更新操作
        //return toAjax(phyxxMapper.insertPhyxx(phyxx));

        return toAjax(res);
    }

    private String[] getArrayValue_cart(Pcart pcart) {
        String val[]=new String[8];
        val[0]=pcart.getGwid();
        val[1]=pcart.getHyid();
        val[2]=pcart.getSpid();
        val[3]=pcart.getSpmc();
        val[4]=pcart.getSpsl();
        val[5]=pcart.getSpjg();
        val[6]=pcart.getJgxj();
        val[7]=pcart.getMxsm();
        return val;
    }

    private Pcart getNewAddPcart(Pcart pcart) {                                            //对新增后的的购物车对象进行赋值更新操作

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }
    

    //-- 购物车(pcart).修改

    //-- 跳转到修改页面
    @GetMapping("/edit/{gwid}")                                                                                                    //从url:/pdd/pcart/edit/{gwid}的get方法， 跳转到页面：pdd/pcart/edit.html
    public String toEditPcart(@PathVariable("gwid") String gwid, ModelMap mmap)                    //根据传入的gwid，查询数据，放入ModelMap，返回给页面
    {
        Pcart pcart = pcartMapper.selectById(gwid);                                                  //根据传入的gwid，查询购物车(pcart)
        pcart=getNewEditInitPcart(pcart);                                                               //对查询出来的购物车对象进行赋值操作
        mmap.put("pcart", pcart);                                                                                   //购物车(pcart)：放入ModelMap



        return prefix + "/edit";                                                                                                        //跳转到url：pdd/pcart/edit.html
    }

    private Pcart getNewEditInitPcart(Pcart pcart) {                                            //对查询出来的购物车对象进行赋值操作

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }

    //-- 修改后数据持久化
    //@RequiresPermissions("pdd:pcart:edit")                                                           //shiro权限：pdd系统:购物车:修改
    @Log(title = "购物车", businessType = BusinessType.UPDATE)                                        //记录日志信息
    @PostMapping("/edit")                                                                                                   //提交pcart的接口url:/pdd/pcart/edit，采用post方法
    @ResponseBody                                                                                                            //返回restful-json
    public AjaxResult editSavePcart(Pcart pcart)                                             //根据传入的pcart，更新持久化，返回结果restful-json
    {
        pcart= getNewAfterEditPcart(pcart);                                                      //对编辑后的购物车对象进行赋值操作
        return toAjax(pcartMapper.updateByPcart(pcart));                                 //返回保存结果
    }
    private Pcart getNewAfterEditPcart(Pcart pcart) {                                //对编辑后的购物车对象进行赋值操作

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19
       
	    
        return pcart;
    }
    

    //-- 购物车(pcart).删除
    //-- 删除单条数据
    //@RequiresPermissions("pdd:pcart:remove")                                                  //shiro权限：pdd系统:购物车:删除
    @Log(title = "购物车", businessType = BusinessType.DELETE)                                       //记录日志信息
    @GetMapping( "/remove/{gwid}")                                                                                   //提交pcart的接口url:/pdd/pcart/remove/{gwid}，采用get方法
    @ResponseBody                                                                                                          //返回restful-json
    public AjaxResult removeOnePcart(@PathVariable("gwid") String gwid)                       //根据传入的gwid，删除单条数据，返回结果restful-json
    {
        return toAjax(pcartMapper.deleteByGwid(gwid));                                                  //删除
    }

    //-- 删除多条数据
    //@RequiresPermissions("pdd:pcart:remove")                                                      //shiro权限：pdd系统:购物车:删除
    @Log(title = "购物车", businessType = BusinessType.DELETE)                                           //记录日志信息
    @PostMapping( "/remove")                                                                                              //提交pcart的接口url:/pdd/pcart/remove，采用post方法
    @ResponseBody                                                                                                              //返回restful-json
    public AjaxResult removePcart(@RequestParam("ids") String ids)                                //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                                                                       //把1，2，3转化为'1','2','3'
        return toAjax(pcartMapper.deleteByGwids(ids2));
    }
     

    //-- begin 购物车(pcart).详情
    //-- 显示单条数据详情
    //@RequiresPermissions("pdd:pcart:detail")                                                          //shiro权限：pdd系统:购物车:详情
    @GetMapping("/detail/{gwid}")
    public String detail(@PathVariable("gwid") String  gwid, ModelMap mmap)                             //查询购物车(pcart),导航到url:pdd/pcart/detail.html
    {


        //query from blockchain
        String s1=CartCCTool.queryCartByKey(gwid);
        System.out.println("==[BlockChain-Cart]"+s1);
        Pcart pcart=com.alibaba.fastjson.JSONObject.parseObject(s1).toJavaObject(Pcart.class);

        //Pcart pcart=pcartMapper.selectById(gwid);                                               //查询购物车(pcart)
        pcart=getNewDetailPcart(pcart);                                                             //对购物车(pcart)进行赋值操作,以便获取新的详情
        mmap.put("pcart", pcart);                                                                               //购物车(pcart)放入model,传到前台页面
        return prefix + "/detail";                                                                                                 //导航到url:pdd/pcart/detail.html
    }
    private Pcart getNewDetailPcart(Pcart pcart) {                                          //对购物车(pcart)进行赋值操作,以便获取新的详情
            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }
     

    //-- 购物车(pcart).导出
    //-- 导出信息列表
    //@RequiresPermissions("system:pcart:export")                                                  //shiro权限：pdd系统:购物车:导出
    @Log(title = "购物车", businessType = BusinessType.EXPORT)                                   //记录日志信息
    @PostMapping("/export")                                                                                          //根据pcart条件进行导出接口url:/pdd/pcart/export，采用post方法
    @ResponseBody                                                                                                       //返回restful-json
    public AjaxResult exportPcart(Pcart pcart)                                            //根据传入的购物车(pcart)做条件，查询并导出excel，返回结果restful-json
    {
        pcart=getNewExportPcart(pcart);                                                      //对购物车(pcart)进行赋值操作,以便获取新的导出条件
        List<Pcart> list = pcartMapper.selectListByPcart(pcart);              //查询购物车
        list=getNewExportPcartList(list);                                                                        //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pcart> util = new ExcelUtil<Pcart>(Pcart.class);                     //创建导出工具
        return util.exportExcel(list, "购物车(pcart)");                                                      //执行导出
    }
    private Pcart getNewExportPcart(Pcart pcart) {                              //对购物车(pcart)进行赋值操作,以便获取新的导出条件

            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        return pcart;
    }
    private List<Pcart> getNewExportPcartList(List<Pcart> list) {                  //对查询出来的list信息进行加工处理，以便进行导出
        for(Pcart pcart:list){
            //pcart.setGwid(""); //购物车编码
            //pcart.setHyid(""); //会员编码
            //pcart.setSpid(""); //商品编码
            //pcart.setSpmc(""); //商品名称
            //pcart.setSpsl(""); //商品数量
            //pcart.setSpjg(""); //商品价格
            //pcart.setJgxj(""); //价格小计
            //pcart.setMxsm(""); //明细说明
            //pcart.setBz00(""); //备注项00
            //pcart.setBz01(""); //备注项01
            //pcart.setBz02(""); //备注项02
            //pcart.setBz03(""); //备注项03
            //pcart.setBz04(""); //备注项04
            //pcart.setBz05(""); //备注项05
            //pcart.setBz06(""); //备注项06
            //pcart.setBz07(""); //备注项07
            //pcart.setBz08(""); //备注项08
            //pcart.setBz09(""); //备注项09
            //pcart.setBz10(""); //备注项10
            //pcart.setBz11(""); //备注项11
            //pcart.setBz12(""); //备注项12
            //pcart.setBz13(""); //备注项13
            //pcart.setBz14(""); //备注项14
            //pcart.setBz15(""); //备注项15
            //pcart.setBz16(""); //备注项16
            //pcart.setBz17(""); //备注项17
            //pcart.setBz18(""); //备注项18
            //pcart.setBz19(""); //备注项19


        }
        return list;
    }
     

    //-- 购物车(pcart).测试
    @GetMapping("pdd/pcart/test")                                 //测试工具路径
    public  List<?>  test() {                                                             //测试工具
        Pcart pcart = new Pcart();
        List list=null;
//        pcartMapper.updateByPcart(pcart);
//        list=sqlUtilService.list("select * from pcart where gwid like '%5%'");
        return list;
    }
    
}
