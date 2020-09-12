package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Channelinfo;
import com.ruoyi.blockchain.mapperbean.ChannelinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.sdk.Channel;
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
 * 通道信息(channelinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/channelinfo")                      //区块链/通道信息
public class ChannelinfoController extends BaseController {

    private String prefix = "blockchain/channelinfo";          //url:区块链/通道信息

    @Autowired
    private ChannelinfoMapper channelinfoMapper;              //通道信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 通道信息(channelinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:channelinfo:view")      //shiro权限：区块链:通道信息:查看
    @GetMapping()                                           //url:blockchain/channelinfo
    public String toListChannelinfo(ModelMap mmap){          //通道信息(channelinfo).跳转到列表页面。从url:/blockchain/channelinfo跳转-->页面:blockchain/channelinfo/channelinfo.html
        Channelinfo channelinfo =getToListChannelinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("channelinfo", channelinfo);                 // 通道信息(channelinfo)放入ModelMap
        return prefix + "/channelinfo";                      // 重定向到页面：blockchain/channelinfo/channelinfo.html
    }

    private Channelinfo getToListChannelinfoCon() {           //通道信息(channelinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Channelinfo channelinfo=new Channelinfo();             //创建 通道信息(channelinfo)
            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10getToListChannelinfoCon
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:channelinfo:list")      // shiro权限：区块链:通道信息:列表
    @PostMapping("/list")                                   // url:/blockchain/channelinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistChannelinfo(Channelinfo channelinfo){    //通道信息(channelinfo)：根据页面的form的数据组装channelinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo



        startPage();                                                    //开始分页
//        channelinfo=getNewChannelinfoCon(channelinfo);                     //修改通道信息实体，构造新的查询条件
//        List<Channelinfo> list = channelinfoMapper.selectListByChannelinfo(channelinfo);   //根据通道信息(channelinfo)的属性，构造条件，查询出通道信息list
//        list=getNewChannelinfoList(list);


        Channelinfo aChannelinfo= new Channelinfo();
        Channel aChannel= BlockChainTool.getChannel();
        aChannelinfo.setTdid(aChannel.getName());

        aChannelinfo.setTdid(aChannel.getName()); //通道编码
        aChannelinfo.setTdmc(aChannel.getName()); //通道名称
        aChannelinfo.setPxjd(aChannel.getOrderers().toString()); //排序节点
        aChannelinfo.setDdjd(aChannel.getPeers().toString()); //对等节点
        aChannelinfo.setSfgb(String.valueOf(aChannel.isShutdown())); //是否关闭
        aChannelinfo.setSfcs(String .valueOf(aChannel.isInitialized())); //是否初始化
        aChannelinfo.setCsjk(""); //创世纪块
        aChannelinfo.setQkxx(""); //区块信息

          ArrayList<Channelinfo> list=new ArrayList<Channelinfo>();
          list.add(aChannelinfo);

        //对查询出来的list信息进行加工处理，以便传入到前端展现
        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Channelinfo getNewChannelinfoCon(Channelinfo channelinfo) {                    //修改通道信息实体，构造新的查询条件

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }
    private List<Channelinfo> getNewChannelinfoList(List<Channelinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Channelinfo channelinfo:list){
            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 通道信息(channelinfo).列表查询

    //====begin 通道信息(channelinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/channelinfo/add的get方法， 跳转到url:blockchain/channelinfo/add.html
    public String toAddChannelinfo(ModelMap mmap)
    {
        Channelinfo channelinfo = getInitChannelinfoAdd();    // 创建channelinfo，并赋值，作为新增通道信息的初始化值
        mmap.put("channelinfo", channelinfo);                // 通道信息(channelinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/channelinfo/add
    }

    private Channelinfo getInitChannelinfoAdd() {            // 创建channelinfo，并赋值，作为新增通道信息的初始化值
        Channelinfo channelinfo=new Channelinfo();            //初始化通道信息实体
        String ts=InputUitl.getTimestamp();
        channelinfo.setTdid(ts);                            //tdid

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19

        return channelinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:channelinfo:add")                 //shiro权限：区块链:通道信息:新增
    @Log(title = "通道信息", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交channelinfo的接口url:/blockchain/channelinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSaveChannelinfo(Channelinfo channelinfo)        //根据传入的channelinfo，持久化，返回结果restful-json
    {
        channelinfo=getNewAddChannelinfo(channelinfo);                    //对新增后的的通道信息对象进行赋值更新操作
        return toAjax(channelinfoMapper.insertChannelinfo(channelinfo));  //持久化
    }

    private Channelinfo getNewAddChannelinfo(Channelinfo channelinfo) {    //对新增后的的通道信息对象进行赋值更新操作

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }
    //====end 通道信息(channelinfo).新增


    //====begin 通道信息(channelinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{tdid}")                                                        //从url:/blockchain/channelinfo/edit/{tdid}的get方法， 跳转到页面：blockchain/channelinfo/edit.html
    public String toEditChannelinfo(@PathVariable("tdid") String tdid, ModelMap mmap)   //根据传入的tdid，查询数据，放入ModelMap，返回给页面
    {
        Channelinfo channelinfo = channelinfoMapper.selectById(tdid);                     //根据传入的tdid，查询通道信息(channelinfo)
        channelinfo=getNewEditInitChannelinfo(channelinfo);                               //对查询出来的通道信息对象进行赋值操作
        mmap.put("channelinfo", channelinfo);                                            //通道信息(channelinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/channelinfo/edit.html
    }

    private Channelinfo getNewEditInitChannelinfo(Channelinfo channelinfo) {               //对查询出来的通道信息对象进行赋值操作

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:channelinfo:edit")                  //shiro权限：区块链:通道信息:修改
    @Log(title = "通道信息", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交channelinfo的接口url:/blockchain/channelinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSaveChannelinfo(Channelinfo channelinfo)         //根据传入的channelinfo，更新持久化，返回结果restful-json
    {
        channelinfo= getNewAfterEditChannelinfo(channelinfo);                 //对编辑后的通道信息对象进行赋值操作
        return toAjax(channelinfoMapper.updateByChannelinfo(channelinfo));    //返回保存结果
    }
    private Channelinfo getNewAfterEditChannelinfo(Channelinfo channelinfo) {   //对编辑后的通道信息对象进行赋值操作

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19
       
	    
        return channelinfo;
    }
    //====end 通道信息(channelinfo).修改


    //====begin 通道信息(channelinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:channelinfo:remove")                 //shiro权限：区块链:通道信息:删除
    @Log(title = "通道信息", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{tdid}")                                       //提交channelinfo的接口url:/blockchain/channelinfo/remove/{tdid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOneChannelinfo(@PathVariable("tdid") String tdid)    //根据传入的tdid，删除单条数据，返回结果restful-json
    {
        return toAjax(channelinfoMapper.deleteByTdid(tdid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:channelinfo:remove")                         //shiro权限：区块链:通道信息:删除
    @Log(title = "通道信息", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交channelinfo的接口url:/blockchain/channelinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removeChannelinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(channelinfoMapper.deleteByTdids(ids2));
    }
    //====end 通道信息(channelinfo).删除


    //====begin 通道信息(channelinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:channelinfo:detail")                          //shiro权限：区块链:通道信息:详情
    @GetMapping("/detail/{tdid}")
    public String detail(@PathVariable("tdid") String  tdid, ModelMap mmap)       //查询通道信息(channelinfo),导航到url:blockchain/channelinfo/detail.html
    {
        Channelinfo channelinfo=channelinfoMapper.selectById(tdid);                  //查询通道信息(channelinfo)
        channelinfo=getNewDetailChannelinfo(channelinfo);                            //对通道信息(channelinfo)进行赋值操作,以便获取新的详情
        mmap.put("channelinfo", channelinfo);                                       //通道信息(channelinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/channelinfo/detail.html
    }
    private Channelinfo getNewDetailChannelinfo(Channelinfo channelinfo) {             //对通道信息(channelinfo)进行赋值操作,以便获取新的详情
            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }
    //====end 通道信息(channelinfo).详情


    //====begin 通道信息(channelinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:channelinfo:export")                  //shiro权限：区块链:通道信息:导出
    @Log(title = "通道信息", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据channelinfo条件进行导出接口url:/blockchain/channelinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportChannelinfo(Channelinfo channelinfo)           //根据传入的通道信息(channelinfo)做条件，查询并导出excel，返回结果restful-json
    {
        channelinfo=getNewExportChannelinfo(channelinfo);                  //对通道信息(channelinfo)进行赋值操作,以便获取新的导出条件
        List<Channelinfo> list = channelinfoMapper.selectListByChannelinfo(channelinfo);     //查询通道信息
        list=getNewExportChannelinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Channelinfo> util = new ExcelUtil<Channelinfo>(Channelinfo.class);        //创建导出工具
        return util.exportExcel(list, "通道信息(channelinfo)");                         //执行导出
    }
    private Channelinfo getNewExportChannelinfo(Channelinfo channelinfo) {                   //对通道信息(channelinfo)进行赋值操作,以便获取新的导出条件

            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        return channelinfo;
    }
    private List<Channelinfo> getNewExportChannelinfoList(List<Channelinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Channelinfo channelinfo:list){
            //channelinfo.setTdid(""); //通道编码
            //channelinfo.setTdmc(""); //通道名称
            //channelinfo.setPxjd(""); //排序节点
            //channelinfo.setDdjd(""); //对等节点
            //channelinfo.setSfgb(""); //是否关闭
            //channelinfo.setSfcs(""); //是否初始化
            //channelinfo.setCsjk(""); //创世纪块
            //channelinfo.setQkxx(""); //区块信息
            //channelinfo.setBz00(""); //备注项00
            //channelinfo.setBz01(""); //备注项01
            //channelinfo.setBz02(""); //备注项02
            //channelinfo.setBz03(""); //备注项03
            //channelinfo.setBz04(""); //备注项04
            //channelinfo.setBz05(""); //备注项05
            //channelinfo.setBz06(""); //备注项06
            //channelinfo.setBz07(""); //备注项07
            //channelinfo.setBz08(""); //备注项08
            //channelinfo.setBz09(""); //备注项09
            //channelinfo.setBz10(""); //备注项10
            //channelinfo.setBz11(""); //备注项11
            //channelinfo.setBz12(""); //备注项12
            //channelinfo.setBz13(""); //备注项13
            //channelinfo.setBz14(""); //备注项14
            //channelinfo.setBz15(""); //备注项15
            //channelinfo.setBz16(""); //备注项16
            //channelinfo.setBz17(""); //备注项17
            //channelinfo.setBz18(""); //备注项18
            //channelinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 通道信息(channelinfo).导出


    //====begin 通道信息(channelinfo).测试
    @GetMapping("blockchain/channelinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Channelinfo channelinfo = new Channelinfo();
        List list=null;
//        channelinfoMapper.updateByChannelinfo(channelinfo);
//        list=sqlUtilService.list("select * from channelinfo where tdid like '%5%'");
        return list;
    }
    //====end 通道信息(channelinfo).测试

}
