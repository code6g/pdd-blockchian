package com.ruoyi.pdd.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.blockchain.ywtool.DdxxCCTool;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.json.JSONObject.JSONArray;

import com.ruoyi.code6g.EasyExcelUtil;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.server.Sys;

import com.ruoyi.pdd.mapperbean.Pddmx;
import com.ruoyi.pdd.mapperbean.Pddxx;
import com.ruoyi.pdd.mapperbean.PddxxMapper;
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
 * 订单信息(pddxx).控制类 -- 实现主表的管理功能
 */

@Controller                                                                                  //控制类 -- 实现主表的管理功能标签注解
@RequestMapping("/pdd/pddxxgl")                                    //pdd系统/订单信息
public class PddxxglController extends BaseController {

    private String prefix = "pdd/pddxxgl";                              //url:pdd系统/订单信息

    @Autowired
    private PddxxMapper pddxxMapper;                           //订单信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；




    //--excel导入-订单信息 pddxx
    @PostMapping("/importexcel")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            ExcelUtil<Pddxx> excelUtil = new ExcelUtil<Pddxx>(Pddxx.class);             //创建excel工具
            List<Pddxx> list = excelUtil.importExcel(file.getInputStream());                       //解析excel，生成list<entt>
            for (Pddxx pddxx : list) {
                pddxxMapper.insertPddxx(pddxx);                                                //循环插入entt到db
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
    public AjaxResult downloadtemplatePddxx(Pddxx pddxx)                //根据传入的订单信息(pddxx)做条件，查询并导出excel，返回结果restful-json
    {
        pddxx=getNewDownloadtemplatePddxx(pddxx);                        //对订单信息(pddxx)进行赋值操作,以便获取新的导出条件
        pddxx.setBz01("999abc");
        List<Pddxx> list = pddxxMapper.selectListByPddxx(pddxx);         //查询样本数据
        list=getNewExportPddxxList(list);                                         //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pddxx> util = new ExcelUtil<Pddxx>(Pddxx.class);          //创建导出工具
        return util.exportExcel(list, "订单信息-模板(pddxx)");               //执行导出
    } 



    private Pddxx getNewDownloadtemplatePddxx(Pddxx pddxx) {         //对订单信息(pddxx)进行赋值操作,以便获取新的导出条件
            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19



        return pddxx;
    }


    //-- begin 订单信息(pddxx).列表查询

    //-- 跳转到列表页面
    //@RequiresPermissions("pdd:pddxx:view")                          //shiro权限：pdd系统:订单信息:查看
    @GetMapping()                                                                               //url:pdd/pddxx
    public String toListPddxx(ModelMap mmap){                              //订单信息(pddxx).跳转到列表页面。从url:/pdd/pddxx跳转-->页面:pdd/pddxx/pddxx.html
        Pddxx pddxx =getToListPddxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件


        pddxx.setBz19("2020-08-10");
        pddxx.setXdsj(InputUitl.get_date_bz());


        mmap.put("pddxx", pddxx);                                             // 订单信息(pddxx)放入ModelMap
        return prefix + "/pddxx";                                                        // 重定向到页面：pdd/pddxx/pddxx.html
    }

    private Pddxx getToListPddxxCon() {                                     //订单信息(pddxx).构造查询条件的实体信息，在列表页面进行默认过滤
        Pddxx pddxx=new Pddxx();                                       //创建 订单信息(pddxx)
            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }

    //--查询数据，返回给列表页面
    //@RequiresPermissions("pdd:pddxx:list")                               // shiro权限：pdd系统:订单信息:列表
    @PostMapping("/list")                                                                        // url:/pdd/pddxx/list
    @ResponseBody                                                                               //返回restful-json
    public TableDataInfo querylistPddxx(Pddxx pddxx){          //订单信息(pddxx)：根据页面的form的数据组装pddxx，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                                                   //开始分页


        //query from blockChain
        String sJson=DdxxCCTool.queryRange("ddxx_xdsj_"+pddxx.getBz19()+" 00:00:00","ddxx_xdsj_"+pddxx.getXdsj()+" 23:59:59");
        System.out.println("=="+sJson);
        List<Pddxx> list = JSON.parseArray(sJson, Pddxx.class);


//        pddxx=getNewPddxxCon(pddxx);                               //修改订单信息实体，构造新的查询条件
//        List<Pddxx> list = pddxxMapper.selectListByPddxx(pddxx);                                  //根据订单信息(pddxx)的属性，构造条件，查询出订单信息list
        list=getNewPddxxList(list);                                                                                                     //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                                                                              //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Pddxx getNewPddxxCon(Pddxx pddxx) {                                                    //修改订单信息实体，构造新的查询条件

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }
    private List<Pddxx> getNewPddxxList(List<Pddxx> list) {                                              //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Pddxx pddxx:list){
            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        }
        return list;
    }
    
	
	
    //--订单信息(pddxx).新增
    //--跳转到新增页面
    @GetMapping("/add")                                                                                                                //从url:/pdd/pddxx/add的get方法， 跳转到url:pdd/pddxx/add.html
    public String toAddPddxx(ModelMap mmap)
    {
        Pddxx pddxx = getInitPddxxAdd();                                                                       // 创建pddxx，并赋值，作为新增订单信息的初始化值
        mmap.put("pddxx", pddxx);                                                                                       // 订单信息(pddxx).放入ModelMap



        return prefix + "/add";                                                                                                           // 导航到url：pdd/pddxx/add
    }

    private Pddxx getInitPddxxAdd() {                                                                                 // 创建pddxx，并赋值，作为新增订单信息的初始化值
        Pddxx pddxx=new Pddxx();                                                                                //初始化订单信息实体
        String ts=InputUitl.getTimestamp();
        pddxx.setDdid(ts);                                                                                                            //ddid

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19

        return pddxx;
    }

    //--新增数据持久化
    //@RequiresPermissions("pdd:pddxx:add")                                                                   //shiro权限：pdd系统:订单信息:新增
    @Log(title = "订单信息", businessType = BusinessType.INSERT)                                                      //记录日志信息
    @PostMapping("/add")                                                                                                          //提交pddxx的接口url:/pdd/pddxx/add，采用post方法
    @ResponseBody                                                                                                                   //返回restful-json
    public AjaxResult addSavePddxx(Pddxx pddxx)                                                    //根据传入的pddxx，持久化，返回结果restful-json
    {
        pddxx=getNewAddPddxx(pddxx);                                                                   //对新增后的的订单信息对象进行赋值更新操作
        return toAjax(pddxxMapper.insertPddxx(pddxx));                                               //持久化
    }

    private Pddxx getNewAddPddxx(Pddxx pddxx) {                                            //对新增后的的订单信息对象进行赋值更新操作

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }
    

    //-- 订单信息(pddxx).修改

    //-- 跳转到修改页面
    @GetMapping("/edit/{ddid}")                                                                                                    //从url:/pdd/pddxx/edit/{ddid}的get方法， 跳转到页面：pdd/pddxx/edit.html
    public String toEditPddxx(@PathVariable("ddid") String ddid, ModelMap mmap)                    //根据传入的ddid，查询数据，放入ModelMap，返回给页面
    {
        Pddxx pddxx = pddxxMapper.selectById(ddid);                                                  //根据传入的ddid，查询订单信息(pddxx)
        pddxx=getNewEditInitPddxx(pddxx);                                                               //对查询出来的订单信息对象进行赋值操作
        mmap.put("pddxx", pddxx);                                                                                   //订单信息(pddxx)：放入ModelMap



        return prefix + "/edit";                                                                                                        //跳转到url：pdd/pddxx/edit.html
    }

    private Pddxx getNewEditInitPddxx(Pddxx pddxx) {                                            //对查询出来的订单信息对象进行赋值操作

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }

    //-- 修改后数据持久化
    //@RequiresPermissions("pdd:pddxx:edit")                                                           //shiro权限：pdd系统:订单信息:修改
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)                                        //记录日志信息
    @PostMapping("/edit")                                                                                                   //提交pddxx的接口url:/pdd/pddxx/edit，采用post方法
    @ResponseBody                                                                                                            //返回restful-json
    public AjaxResult editSavePddxx(Pddxx pddxx)                                             //根据传入的pddxx，更新持久化，返回结果restful-json
    {
        pddxx= getNewAfterEditPddxx(pddxx);                                                      //对编辑后的订单信息对象进行赋值操作
        return toAjax(pddxxMapper.updateByPddxx(pddxx));                                 //返回保存结果
    }
    private Pddxx getNewAfterEditPddxx(Pddxx pddxx) {                                //对编辑后的订单信息对象进行赋值操作

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19
       
	    
        return pddxx;
    }
    

    //-- 订单信息(pddxx).删除
    //-- 删除单条数据
    //@RequiresPermissions("pdd:pddxx:remove")                                                  //shiro权限：pdd系统:订单信息:删除
    @Log(title = "订单信息", businessType = BusinessType.DELETE)                                       //记录日志信息
    @GetMapping( "/remove/{ddid}")                                                                                   //提交pddxx的接口url:/pdd/pddxx/remove/{ddid}，采用get方法
    @ResponseBody                                                                                                          //返回restful-json
    public AjaxResult removeOnePddxx(@PathVariable("ddid") String ddid)                       //根据传入的ddid，删除单条数据，返回结果restful-json
    {
        return toAjax(pddxxMapper.deleteByDdid(ddid));                                                  //删除
    }

    //-- 删除多条数据
    //@RequiresPermissions("pdd:pddxx:remove")                                                      //shiro权限：pdd系统:订单信息:删除
    @Log(title = "订单信息", businessType = BusinessType.DELETE)                                           //记录日志信息
    @PostMapping( "/remove")                                                                                              //提交pddxx的接口url:/pdd/pddxx/remove，采用post方法
    @ResponseBody                                                                                                              //返回restful-json
    public AjaxResult removePddxx(@RequestParam("ids") String ids)                                //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                                                                       //把1，2，3转化为'1','2','3'
        return toAjax(pddxxMapper.deleteByDdids(ids2));
    }
     

    //-- begin 订单信息(pddxx).详情
    //-- 显示单条数据详情
    //@RequiresPermissions("pdd:pddxx:detail")                                                          //shiro权限：pdd系统:订单信息:详情
    @GetMapping("/detail/{ddid}")
    public String detail(@PathVariable("ddid") String  ddid, ModelMap mmap)                             //查询订单信息(pddxx),导航到url:pdd/pddxx/detail.html
    {

        //query from blockchain
        String s1=DdxxCCTool.queryDdxxByKey(ddid);
        System.out.println("==[BlockChain-ddxx]"+s1);
        Pddxx pddxx=com.alibaba.fastjson.JSONObject.parseObject(s1).toJavaObject(Pddxx.class);

        String sJson_ddmx=pddxx.getDdmx();
        System.out.println("sJson_ddmx=="+sJson_ddmx);
        String sJson_ddmx2=sJson_ddmx.replaceAll("\\^","'");
        System.out.println("sJson_ddmx2=="+sJson_ddmx2);
        List<Pddmx> pddmxs = JSON.parseArray(sJson_ddmx2, Pddmx.class);

        mmap.put("pddxx", pddxx);                                                                               //订单信息(pddxx)放入model,传到前台页面
        mmap.put("pddmxs", pddmxs);                                                                               //订单信息(pddxx)放入model,传到前台页面

        //Pddxx pddxx=pddxxMapper.selectById(ddid);                                               //查询订单信息(pddxx)
        //pddxx=getNewDetailPddxx(pddxx);                                                             //对订单信息(pddxx)进行赋值操作,以便获取新的详情\


        return prefix + "/detail";                                                                                                 //导航到url:pdd/pddxx/detail.html
    }
    private Pddxx getNewDetailPddxx(Pddxx pddxx) {                                          //对订单信息(pddxx)进行赋值操作,以便获取新的详情
            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }
     

    //-- 订单信息(pddxx).导出
    //-- 导出信息列表
    //@RequiresPermissions("system:pddxx:export")                                                  //shiro权限：pdd系统:订单信息:导出
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)                                   //记录日志信息
    @PostMapping("/export")                                                                                          //根据pddxx条件进行导出接口url:/pdd/pddxx/export，采用post方法
    @ResponseBody                                                                                                       //返回restful-json
    public AjaxResult exportPddxx(Pddxx pddxx)                                            //根据传入的订单信息(pddxx)做条件，查询并导出excel，返回结果restful-json
    {
        pddxx=getNewExportPddxx(pddxx);                                                      //对订单信息(pddxx)进行赋值操作,以便获取新的导出条件
        List<Pddxx> list = pddxxMapper.selectListByPddxx(pddxx);              //查询订单信息
        list=getNewExportPddxxList(list);                                                                        //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Pddxx> util = new ExcelUtil<Pddxx>(Pddxx.class);                     //创建导出工具
        return util.exportExcel(list, "订单信息(pddxx)");                                                      //执行导出
    }
    private Pddxx getNewExportPddxx(Pddxx pddxx) {                              //对订单信息(pddxx)进行赋值操作,以便获取新的导出条件

            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        return pddxx;
    }
    private List<Pddxx> getNewExportPddxxList(List<Pddxx> list) {                  //对查询出来的list信息进行加工处理，以便进行导出
        for(Pddxx pddxx:list){
            //pddxx.setDdid(""); //订单编码
            //pddxx.setDdbh(""); //订单编号
            //pddxx.setHyid(""); //会员编码
            //pddxx.setHymc(""); //会员名称
            //pddxx.setSpsl(""); //商品数量
            //pddxx.setJgzj(""); //价格总计
            //pddxx.setDdzt(""); //订单状态
            //pddxx.setXdsj(""); //下单时间
            //pddxx.setXdsm(""); //下单说明
            //pddxx.setZfqd(""); //支付渠道
            //pddxx.setZfzh(""); //支付账号
            //pddxx.setZfry(""); //支付人
            //pddxx.setZfsj(""); //支付时间
            //pddxx.setZfsm(""); //支付说明
            //pddxx.setShry(""); //收货人
            //pddxx.setShdz(""); //收货地址
            //pddxx.setShdh(""); //收货人电话
            //pddxx.setPsry(""); //配送人
            //pddxx.setPssj(""); //配送时间
            //pddxx.setPssm(""); //配送说明
            //pddxx.setQsry(""); //签收人
            //pddxx.setQssj(""); //签收时间
            //pddxx.setQssm(""); //签收说明
            //pddxx.setPjnr(""); //评价内容
            //pddxx.setPjsj(""); //评价时间
            //pddxx.setBz00(""); //备注项00
            //pddxx.setBz01(""); //备注项01
            //pddxx.setBz02(""); //备注项02
            //pddxx.setBz03(""); //备注项03
            //pddxx.setBz04(""); //备注项04
            //pddxx.setBz05(""); //备注项05
            //pddxx.setBz06(""); //备注项06
            //pddxx.setBz07(""); //备注项07
            //pddxx.setBz08(""); //备注项08
            //pddxx.setBz09(""); //备注项09
            //pddxx.setBz10(""); //备注项10
            //pddxx.setBz11(""); //备注项11
            //pddxx.setBz12(""); //备注项12
            //pddxx.setBz13(""); //备注项13
            //pddxx.setBz14(""); //备注项14
            //pddxx.setBz15(""); //备注项15
            //pddxx.setBz16(""); //备注项16
            //pddxx.setBz17(""); //备注项17
            //pddxx.setBz18(""); //备注项18
            //pddxx.setBz19(""); //备注项19


        }
        return list;
    }
     

    //-- 订单信息(pddxx).测试
    @GetMapping("pdd/pddxx/test")                                 //测试工具路径
    public  List<?>  test() {                                                             //测试工具
        Pddxx pddxx = new Pddxx();
        List list=null;
//        pddxxMapper.updateByPddxx(pddxx);
//        list=sqlUtilService.list("select * from pddxx where ddid like '%5%'");
        return list;
    }
    
}
