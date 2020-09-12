package com.ruoyi.blockchain.controller;

import com.ruoyi.blockchain.mapperbean.Peerinfo;
import com.ruoyi.blockchain.mapperbean.PeerinfoMapper;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hyperledger.fabric.sdk.Peer;
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
 * 对等节点(peerinfo).控制类
 */

@Controller     //控制类标签注解
@RequestMapping("/blockchain/peerinfo")                      //区块链/对等节点
public class PeerinfoController extends BaseController {

    private String prefix = "blockchain/peerinfo";          //url:区块链/对等节点

    @Autowired
    private PeerinfoMapper peerinfoMapper;              //对等节点Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                  //任意sql工具，在复杂业务的情况下可以使用；

    //====begin 对等节点(peerinfo).列表查询

    //----1. 跳转到列表页面
    //@RequiresPermissions("blockchain:peerinfo:view")      //shiro权限：区块链:对等节点:查看
    @GetMapping()                                           //url:blockchain/peerinfo
    public String toListPeerinfo(ModelMap mmap){          //对等节点(peerinfo).跳转到列表页面。从url:/blockchain/peerinfo跳转-->页面:blockchain/peerinfo/peerinfo.html
        Peerinfo peerinfo =getToListPeerinfoCon();    // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        mmap.put("peerinfo", peerinfo);                 // 对等节点(peerinfo)放入ModelMap
        return prefix + "/peerinfo";                      // 重定向到页面：blockchain/peerinfo/peerinfo.html
    }

    private Peerinfo getToListPeerinfoCon() {           //对等节点(peerinfo).构造查询条件的实体信息，在列表页面进行默认过滤
        Peerinfo peerinfo=new Peerinfo();             //创建 对等节点(peerinfo)
            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }

    //----2. 查询数据，返回给列表页面
    //@RequiresPermissions("blockchain:peerinfo:list")      // shiro权限：区块链:对等节点:列表
    @PostMapping("/list")                                   // url:/blockchain/peerinfo/list
    @ResponseBody                                           //返回restful-json
    public TableDataInfo querylistPeerinfo(Peerinfo peerinfo){    //对等节点(peerinfo)：根据页面的form的数据组装peerinfo，构造查询条件，查询列表信息，返回restful的TableDataInfo
        startPage();                                                    //开始分页
//        peerinfo=getNewPeerinfoCon(peerinfo);                     //修改对等节点实体，构造新的查询条件
//        List<Peerinfo> list = peerinfoMapper.selectListByPeerinfo(peerinfo);   //根据对等节点(peerinfo)的属性，构造条件，查询出对等节点list
//        list=getNewPeerinfoList(list);                                               //对查询出来的list信息进行加工处理，以便传入到前端展现

        List list=new ArrayList();

        Collection C=BlockChainTool.getPeer();

        if(C.iterator().hasNext()){
            Peer peer=(Peer)C.iterator().next();

            Peerinfo aPeerinfo=new Peerinfo();

            aPeerinfo.setJdid(peer.getName()); //节点编码
            aPeerinfo.setJdmc(peer.getName()); //节点名称
            aPeerinfo.setJdlj(peer.getUrl()); //节点链接
            aPeerinfo.setJddz(peer.getUrl()); //节点地址
            aPeerinfo.setSfgb(""); //是否关闭
            aPeerinfo.setTdmc(""); //通道名称
            aPeerinfo.setQkhm(""); //区块号码
            aPeerinfo.setSflj(""); //是否连接
            list.add(aPeerinfo);
        }



        return getDataTable(list);                                                     //构造TableDataInfo格式的数据，返回restful风格的json
    }
    private Peerinfo getNewPeerinfoCon(Peerinfo peerinfo) {                    //修改对等节点实体，构造新的查询条件

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }
    private List<Peerinfo> getNewPeerinfoList(List<Peerinfo> list) {             //对查询出来的list信息进行加工处理，以便传入到前端展现
        for(Peerinfo peerinfo:list){
            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 对等节点(peerinfo).列表查询

    //====begin 对等节点(peerinfo).新增
    //----1.跳转到新增页面
    @GetMapping("/add")                                    //从url:/blockchain/peerinfo/add的get方法， 跳转到url:blockchain/peerinfo/add.html
    public String toAddPeerinfo(ModelMap mmap)
    {
        Peerinfo peerinfo = getInitPeerinfoAdd();    // 创建peerinfo，并赋值，作为新增对等节点的初始化值
        mmap.put("peerinfo", peerinfo);                // 对等节点(peerinfo).放入ModelMap
        return prefix + "/add";                            // 导航到url：blockchain/peerinfo/add
    }

    private Peerinfo getInitPeerinfoAdd() {            // 创建peerinfo，并赋值，作为新增对等节点的初始化值
        Peerinfo peerinfo=new Peerinfo();            //初始化对等节点实体
        String ts=InputUitl.getTimestamp();
        peerinfo.setJdid(ts);                            //jdid

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19

        return peerinfo;
    }

    //----2.新增数据持久化
    //@RequiresPermissions("blockchain:peerinfo:add")                 //shiro权限：区块链:对等节点:新增
    @Log(title = "对等节点", businessType = BusinessType.INSERT)    //记录日志信息
    @PostMapping("/add")                                              //提交peerinfo的接口url:/blockchain/peerinfo/add，采用post方法
    @ResponseBody                                                     //返回restful-json
    public AjaxResult addSavePeerinfo(Peerinfo peerinfo)        //根据传入的peerinfo，持久化，返回结果restful-json
    {
        peerinfo=getNewAddPeerinfo(peerinfo);                    //对新增后的的对等节点对象进行赋值更新操作
        return toAjax(peerinfoMapper.insertPeerinfo(peerinfo));  //持久化
    }

    private Peerinfo getNewAddPeerinfo(Peerinfo peerinfo) {    //对新增后的的对等节点对象进行赋值更新操作

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }
    //====end 对等节点(peerinfo).新增


    //====begin 对等节点(peerinfo).修改

    //----1.跳转到修改页面
    @GetMapping("/edit/{jdid}")                                                        //从url:/blockchain/peerinfo/edit/{jdid}的get方法， 跳转到页面：blockchain/peerinfo/edit.html
    public String toEditPeerinfo(@PathVariable("jdid") String jdid, ModelMap mmap)   //根据传入的jdid，查询数据，放入ModelMap，返回给页面
    {
        Peerinfo peerinfo = peerinfoMapper.selectById(jdid);                     //根据传入的jdid，查询对等节点(peerinfo)
        peerinfo=getNewEditInitPeerinfo(peerinfo);                               //对查询出来的对等节点对象进行赋值操作
        mmap.put("peerinfo", peerinfo);                                            //对等节点(peerinfo)：放入ModelMap
        return prefix + "/edit";                                                       //跳转到url：blockchain/peerinfo/edit.html
    }

    private Peerinfo getNewEditInitPeerinfo(Peerinfo peerinfo) {               //对查询出来的对等节点对象进行赋值操作

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }

    //----2.修改后数据持久化
    //@RequiresPermissions("blockchain:peerinfo:edit")                  //shiro权限：区块链:对等节点:修改
    @Log(title = "对等节点", businessType = BusinessType.UPDATE)      //记录日志信息
    @PostMapping("/edit")                                               //提交peerinfo的接口url:/blockchain/peerinfo/edit，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult editSavePeerinfo(Peerinfo peerinfo)         //根据传入的peerinfo，更新持久化，返回结果restful-json
    {
        peerinfo= getNewAfterEditPeerinfo(peerinfo);                 //对编辑后的对等节点对象进行赋值操作
        return toAjax(peerinfoMapper.updateByPeerinfo(peerinfo));    //返回保存结果
    }
    private Peerinfo getNewAfterEditPeerinfo(Peerinfo peerinfo) {   //对编辑后的对等节点对象进行赋值操作

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19
       
	    
        return peerinfo;
    }
    //====end 对等节点(peerinfo).修改


    //====begin 对等节点(peerinfo).删除
    //----1.删除单条数据
    //@RequiresPermissions("blockchain:peerinfo:remove")                 //shiro权限：区块链:对等节点:删除
    @Log(title = "对等节点", businessType = BusinessType.DELETE)       //记录日志信息
    @GetMapping( "/remove/{jdid}")                                       //提交peerinfo的接口url:/blockchain/peerinfo/remove/{jdid}，采用get方法
    @ResponseBody                                                        //返回restful-json
    public AjaxResult removeOnePeerinfo(@PathVariable("jdid") String jdid)    //根据传入的jdid，删除单条数据，返回结果restful-json
    {
        return toAjax(peerinfoMapper.deleteByJdid(jdid));                     //删除
    }

    //----1.删除多条数据
    //@RequiresPermissions("blockchain:peerinfo:remove")                         //shiro权限：区块链:对等节点:删除
    @Log(title = "对等节点", businessType = BusinessType.DELETE)               //记录日志信息
    @PostMapping( "/remove")                                                     //提交peerinfo的接口url:/blockchain/peerinfo/remove，采用post方法
    @ResponseBody                                                                //返回restful-json
    public AjaxResult removePeerinfo(@RequestParam("ids") String ids)          //根据传入的ids，删除多个数据，返回结果restful-json
    {
        String ids2=InputUitl.getSelectConIn(ids);                               //把1，2，3转化为'1','2','3'
        return toAjax(peerinfoMapper.deleteByJdids(ids2));
    }
    //====end 对等节点(peerinfo).删除


    //====begin 对等节点(peerinfo).详情
    //----1.显示单条数据详情
    //@RequiresPermissions("blockchain:peerinfo:detail")                          //shiro权限：区块链:对等节点:详情
    @GetMapping("/detail/{jdid}")
    public String detail(@PathVariable("jdid") String  jdid, ModelMap mmap)       //查询对等节点(peerinfo),导航到url:blockchain/peerinfo/detail.html
    {
        Peerinfo peerinfo=peerinfoMapper.selectById(jdid);                  //查询对等节点(peerinfo)
        peerinfo=getNewDetailPeerinfo(peerinfo);                            //对对等节点(peerinfo)进行赋值操作,以便获取新的详情
        mmap.put("peerinfo", peerinfo);                                       //对等节点(peerinfo)放入model,传到前台页面
        return prefix + "/detail";                                                //导航到url:blockchain/peerinfo/detail.html
    }
    private Peerinfo getNewDetailPeerinfo(Peerinfo peerinfo) {             //对对等节点(peerinfo)进行赋值操作,以便获取新的详情
            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }
    //====end 对等节点(peerinfo).详情


    //====begin 对等节点(peerinfo).导出
    //----1.导出信息列表
    //@RequiresPermissions("system:peerinfo:export")                  //shiro权限：区块链:对等节点:导出
    @Log(title = "对等节点", businessType = BusinessType.EXPORT)      //记录日志信息
    @PostMapping("/export")                                             //根据peerinfo条件进行导出接口url:/blockchain/peerinfo/export，采用post方法
    @ResponseBody                                                       //返回restful-json
    public AjaxResult exportPeerinfo(Peerinfo peerinfo)           //根据传入的对等节点(peerinfo)做条件，查询并导出excel，返回结果restful-json
    {
        peerinfo=getNewExportPeerinfo(peerinfo);                  //对对等节点(peerinfo)进行赋值操作,以便获取新的导出条件
        List<Peerinfo> list = peerinfoMapper.selectListByPeerinfo(peerinfo);     //查询对等节点
        list=getNewExportPeerinfoList(list);                                           //对查询出来的list信息进行加工处理，以便进行导出
        ExcelUtil<Peerinfo> util = new ExcelUtil<Peerinfo>(Peerinfo.class);        //创建导出工具
        return util.exportExcel(list, "对等节点(peerinfo)");                         //执行导出
    }
    private Peerinfo getNewExportPeerinfo(Peerinfo peerinfo) {                   //对对等节点(peerinfo)进行赋值操作,以便获取新的导出条件

            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        return peerinfo;
    }
    private List<Peerinfo> getNewExportPeerinfoList(List<Peerinfo> list) {          //对查询出来的list信息进行加工处理，以便进行导出
        for(Peerinfo peerinfo:list){
            //peerinfo.setJdid(""); //节点编码
            //peerinfo.setJdmc(""); //节点名称
            //peerinfo.setJdlj(""); //节点链接
            //peerinfo.setJddz(""); //节点地址
            //peerinfo.setSfgb(""); //是否关闭
            //peerinfo.setTdmc(""); //通道名称
            //peerinfo.setQkhm(""); //区块号码
            //peerinfo.setSflj(""); //是否连接
            //peerinfo.setBz00(""); //备注项00
            //peerinfo.setBz01(""); //备注项01
            //peerinfo.setBz02(""); //备注项02
            //peerinfo.setBz03(""); //备注项03
            //peerinfo.setBz04(""); //备注项04
            //peerinfo.setBz05(""); //备注项05
            //peerinfo.setBz06(""); //备注项06
            //peerinfo.setBz07(""); //备注项07
            //peerinfo.setBz08(""); //备注项08
            //peerinfo.setBz09(""); //备注项09
            //peerinfo.setBz10(""); //备注项10
            //peerinfo.setBz11(""); //备注项11
            //peerinfo.setBz12(""); //备注项12
            //peerinfo.setBz13(""); //备注项13
            //peerinfo.setBz14(""); //备注项14
            //peerinfo.setBz15(""); //备注项15
            //peerinfo.setBz16(""); //备注项16
            //peerinfo.setBz17(""); //备注项17
            //peerinfo.setBz18(""); //备注项18
            //peerinfo.setBz19(""); //备注项19


        }
        return list;
    }
    //====end 对等节点(peerinfo).导出


    //====begin 对等节点(peerinfo).测试
    @GetMapping("blockchain/peerinfo/test")    //测试工具路径
    public  List<?>  test() {                  //测试工具
        Peerinfo peerinfo = new Peerinfo();
        List list=null;
//        peerinfoMapper.updateByPeerinfo(peerinfo);
//        list=sqlUtilService.list("select * from peerinfo where jdid like '%5%'");
        return list;
    }
    //====end 对等节点(peerinfo).测试

}
