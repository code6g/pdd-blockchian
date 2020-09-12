package com.ruoyi.pdd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.blockchain.ywtool.CartCCTool;
import com.ruoyi.blockchain.ywtool.DdxxCCTool;
import com.ruoyi.blockchain.ywtool.HyxxCCTool;
import com.ruoyi.blockchain.ywtool.SpxxCCTool;
import com.ruoyi.code6g.InputUitl;
import com.ruoyi.code6g.SpcStatUtil;
import com.ruoyi.code6g.sqlUtil.service.SqlUtilService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.pdd.mapperbean.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ruoyi.blockchain.ywtool.CartCCTool.deleteCart;

/**
 * 商品信息(pspxx).控制类 -- 实现主表的管理功能
 */

@Controller                                                                                  //控制类 -- 实现主表的管理功能标签注解
@RequestMapping("/pdd/app")                                    //pdd系统/商品信息
public class PappController extends BaseController {

    private String prefix = "pdd/app";                              //url:pdd系统/商品信息

    @Autowired
    private PspxxMapper pspxxMapper;                           //商品信息Mapper

    @Autowired
    private SqlUtilService sqlUtilService;                                           //任意sql工具，在复杂业务的情况下可以使用；


    @GetMapping("/home")                                                                               //url:pdd/pspxx
    public String toIndex(ModelMap mmap) {                              //商品信息(pspxx).跳转到列表页面。从url:/pdd/pspxx跳转-->页面:pdd/pspxx/pspxx.html
        //Pspxx pspxx =getToListPspxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        //mmap.put("pspxx", pspxx);                                             // 商品信息(pspxx)放入ModelMap
        return prefix + "/home";                                                        // 重定向到页面：pdd/pspxx/pspxx.html
    }


    @PostMapping("/home/spxxlist/{spfldm}")
    @ResponseBody
    public String getHomeSpxxlist(@PathVariable("spfldm") String spfldm, ModelMap mmap)                    //根据传入的spid，查询数据，放入ModelMap，返回给页面
    {
        //query from blockchain
        String sJson = "";
        if (spfldm.equals("全部")) {
            sJson = SpxxCCTool.queryRange(
                    "spxx_flid_", "spxx_flidc");
        } else {
            sJson = SpxxCCTool.queryRange(
                    "spxx_flid_" + spfldm + "_", "spxx_flid_" + (spfldm + "_Z"));
        }
        sJson = sJson.replace("[,", "[");
        //System.out.println("==" + sJson);
        return sJson;
    }


    //-- begin 商品信息(pspxx).详情
    //-- 显示单条数据详情
    //shiro权限：pdd系统:商品信息:详情
    @GetMapping("/spdetail/{spid}")
    public String detail(@PathVariable("spid") String spid, ModelMap mmap)                             //查询商品信息(pspxx),导航到url:pdd/pspxx/detail.html
    {

        //query from blockchain
        String s1 = SpxxCCTool.querySpxxByKey(spid);
        System.out.println("==[BlockChain-Spxx]" + s1);
        Pspxx pspxx = com.alibaba.fastjson.JSONObject.parseObject(s1).toJavaObject(Pspxx.class);
        //Pspxx pspxx=pspxxMapper.selectById(spid);                                               //查询商品信息(pspxx)
        //pspxx = getNewDetailPspxx(pspxx);                                                             //对商品信息(pspxx)进行赋值操作,以便获取新的详情
        mmap.put("pspxx", pspxx);                                                                               //商品信息(pspxx)放入model,传到前台页面
        return prefix + "/spdetail";                                                                                                 //导航到url:pdd/pspxx/detail.html
    }



    @GetMapping("/my")                                                                               //url:pdd/pspxx
    public String toMy(ModelMap mmap) {                              //商品信息(pspxx).跳转到列表页面。从url:/pdd/pspxx跳转-->页面:pdd/pspxx/pspxx.html
        //Pspxx pspxx =getToListPspxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        //mmap.put("pspxx", pspxx);                                             // 商品信息(pspxx)放入ModelMap
        return prefix + "/my";                                                        // 重定向到页面：pdd/pspxx/pspxx.html
    }



    @PostMapping("/loginpost/{hyzh_hymm}")
    @ResponseBody
    public String loginpost(@PathVariable("hyzh_hymm") String  hyzh_hymm, ModelMap mmap)                             //查询会员信息(phyxx),导航到url:pdd/phyxx/detail.html
    {

        String hyzh=hyzh_hymm.split("\\|")[0];
        String hymm=hyzh_hymm.split("\\|")[1];

        //Phyxx phyxx=phyxxMapper.selectById(hyid);                                               //查询会员信息(phyxx)

        //query from blockchain
        String sJson=HyxxCCTool.queryRange("hyxx_hyzh_"+hyzh,"hyxx_hyzh_"+hyzh+"z");// queryHyxxByKey(hyid);

        List<Phyxx> list = JSON.parseArray(sJson, Phyxx.class);
        for(int i=list.size()-1;i>=0;i--){
            Phyxx ahyxx=(Phyxx)list.get(i);
            if(!ahyxx.getHymm().equals(hymm)){
                list.remove(ahyxx);
            }
        }
        if(list.size()==0){return "[]";}

        return sJson;

  }




    @GetMapping("/cart")                                                                               //url:pdd/pspxx
    public String toCart(ModelMap mmap) {                              //商品信息(pspxx).跳转到列表页面。从url:/pdd/pspxx跳转-->页面:pdd/pspxx/pspxx.html
        //Pspxx pspxx =getToListPspxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        //mmap.put("pspxx", pspxx);                                             // 商品信息(pspxx)放入ModelMap
        return prefix + "/cart";                                                        // 重定向到页面：pdd/pspxx/pspxx.html
    }


    @GetMapping("/order")                                                                               //url:pdd/pspxx
    public String toOrder(ModelMap mmap) {                              //商品信息(pspxx).跳转到列表页面。从url:/pdd/pspxx跳转-->页面:pdd/pspxx/pspxx.html
        //Pspxx pspxx =getToListPspxxCon();                         // 构造查询条件的实体信息，在列表页面赋值，形成默认过滤条件
        //mmap.put("pspxx", pspxx);                                             // 商品信息(pspxx)放入ModelMap
        return prefix + "/order";                                                        // 重定向到页面：pdd/pspxx/pspxx.html
    }


    @PostMapping("/cart/cartlist/{hyid}")
    @ResponseBody
    public String getCartCartlist(@PathVariable("hyid") String hyid, ModelMap mmap)                    //根据传入的spid，查询数据，放入ModelMap，返回给页面
    {
        //query from blockchain
        String sJson = "";
        sJson = CartCCTool.queryRange(
                    "cart_hyid_" + hyid + "_", "cart_hyid_" + (hyid + "_z"));
        sJson = sJson.replace("[,", "[");
        //System.out.println("==" + sJson);
        return sJson;
    }


    //var url =  '/pdd/app/addtocart/'+spid+"|"+hyid+"|"+spsl;

    @PostMapping("/addtocart/{spid_hyid_spsl}")
    @ResponseBody
    public String addtocart(@PathVariable("spid_hyid_spsl") String  spid_hyid_spsl, ModelMap mmap)                             //查询会员信息(phyxx),导航到url:pdd/phyxx/detail.html
    {
       // spid_hyid_spsl
        String spid=spid_hyid_spsl.split("\\|")[0];
        String hyid=spid_hyid_spsl.split("\\|")[1];
        String spsl=spid_hyid_spsl.split("\\|")[2];

        //query spxx
        String s_spxx=SpxxCCTool.querySpxxByKey(spid);
        Pspxx pspxx=com.alibaba.fastjson.JSONObject.parseObject(s_spxx).toJavaObject(Pspxx.class);

        Pcart pcart=new Pcart();

        pcart.setGwid(InputUitl.getTimestamp()); //购物车编码
        pcart.setHyid(hyid); //会员编码
        pcart.setSpid(pspxx.getSpid()); //商品编码
        pcart.setSpmc(pspxx.getSpmc()+";"+pspxx.getSpms()); //商品名称
        pcart.setSpsl(spsl); //商品数量
        pcart.setSpjg(pspxx.getSpjg()); //商品价格
        pcart.setJgxj(SpcStatUtil.cheng(pspxx.getSpjg(),spsl)); //价格小计
        pcart.setMxsm(pspxx.getSptp()); //明细说明
        //============================================


        String[] arrayValue=getArrayValue_cart(pcart);
        int res=CartCCTool.insertCart(arrayValue)?1:0;



//        gwid	购物车编码
//        hyid	会员编码
//        spid	商品编码
//        spmc	商品名称
//        spsl	商品数量
//        spjg	商品价格
//        jgxj	价格小计
//        mxsm	明细说明

        return String.valueOf(res);
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



    //var url =  '/pdd/app/deletecart/'+gwid ;
    @PostMapping("/deletecart/{gwid}")
    @ResponseBody
    public String deletecart(@PathVariable("gwid") String  gwid, ModelMap mmap)                             //查询会员信息(phyxx),导航到url:pdd/phyxx/detail.html
    {

        boolean b= CartCCTool.deleteCart(gwid);

        return  b?"1":"0";
    }



    public static String createDDBH() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return "dd"+formatter.format(new Date());
    }

    //var url = '/pdd/app/submitorder/' + hyid;

    @PostMapping("/submitorder/{hyid}")
    @ResponseBody
    public String submitorder(@PathVariable("hyid") String  hyid, ModelMap mmap)                             //查询会员信息(phyxx),导航到url:pdd/phyxx/detail.html
    {



        //query cart info
        String sJsonCart = "";
        sJsonCart = CartCCTool.queryRange("cart_hyid_" + hyid + "_", "cart_hyid_" + (hyid + "_z"));
        List<Pcart> listCart=  JSON.parseArray(sJsonCart, Pcart.class);

        if(listCart.size()==0){
            return "0: 购物车为空!";
        }

        //query user info
        String sJsonHyxx=HyxxCCTool.queryHyxxByKey(hyid);
        Phyxx phyxx = com.alibaba.fastjson.JSONObject.parseObject(sJsonHyxx).toJavaObject(Phyxx.class);

        //goods detail of order
        String  ddmx=sJsonCart.replaceAll("\"","^");

        //价格总计
        String  Jgzj="0";
        for(Pcart pcart:listCart){
            Jgzj= SpcStatUtil.jia(Jgzj,pcart.getJgxj());
        }

        //create ddxx and assign value
        Pddxx pddxx=new Pddxx();
        pddxx.setDdid(InputUitl.getTimestamp()); //订单编码
        //dd202008181212120001
        pddxx.setDdbh(createDDBH()); //订单编号
        pddxx.setHyid(hyid); //会员编码
        pddxx.setHymc(phyxx.getHymc()); //会员名称
        pddxx.setSpsl(String.valueOf(listCart.size())); //商品数量
        pddxx.setJgzj(Jgzj); //价格总计
        pddxx.setDdzt("未支付"); //订单状态
        pddxx.setXdsj(InputUitl.get_datetime_bz()); //下单时间
        pddxx.setXdsm(""); //下单说明
        pddxx.setZfqd(""); //支付渠道
        pddxx.setZfzh(""); //支付账号
        pddxx.setZfry(""); //支付人
        pddxx.setZfsj(""); //支付时间
        pddxx.setZfsm(""); //支付说明
        pddxx.setShry(""); //收货人
        pddxx.setShdz(""); //收货地址
        pddxx.setShdh(""); //收货人电话
        pddxx.setPsry(""); //配送人
        pddxx.setPssj(""); //配送时间
        pddxx.setPssm(""); //配送说明
        pddxx.setQsry(""); //签收人
        pddxx.setQssj(""); //签收时间
        pddxx.setQssm(""); //签收说明
        pddxx.setPjnr(""); //评价内容
        pddxx.setPjsj(""); //评价时间

        //peer chaincode invoke -o orderer.example.com:7050 -C mychannel -n ddxxcc --peerAddresses peer0.org1.example.com:7051 -c '{"Args":["insertDdxx","dd003","dd20200820212120001","001","aidata100","3","16","2","2020-08-20 12:12:12","","zfb","code6g","niuge","2020-08-20 12:12:12","","niuge","zhongsan road No.128","18916933331","","","","","","","","","[{^mxid^:^mx001^,^ddid^:^dd001^,^ddbh^:^dd20200820212120001^,^hyid^:^001^,^spid^:^sp001^,^spmc^:^apple^,^spsl^:^2^,^spjg^:^5^,^jgxj^:^10^,^mxsm^:^^},{^mxid^:^mx002^,^ddid^:^dd001^,^ddbh^:^dd20200820212120001^,^hyid^:^001^,^spid^:^sp002^,^spmc^:^banana^,^spsl^:^1^,^spjg^:^6^,^jgxj^:^6^,^mxsm^:^^}]"]}'

        //insert ddxx
        String[] arrayValue=getArrayValue_ddxx(pddxx,ddmx);
        int res=DdxxCCTool.insertDdxx(arrayValue)?1:0;

        //delete cartxx;
        for(Pcart pcart:listCart){
            CartCCTool.deleteCart(pcart.getGwid());
        }

        return String.valueOf(res);
    }

    private String[] getArrayValue_ddxx(Pddxx pddxx, String ddmx) {
        String val[]=new String[26];
        val[0]=pddxx.getDdid();
        val[1]=pddxx.getDdbh();
        val[2]=pddxx.getHyid();
        val[3]=pddxx.getHymc();
        val[4]=pddxx.getSpsl();
        val[5]=pddxx.getJgzj();
        val[6]=pddxx.getDdzt();
        val[7]=pddxx.getXdsj();
        val[8]=pddxx.getXdsm();
        val[9]=pddxx.getZfqd();
        val[10]=pddxx.getZfzh();
        val[11]=pddxx.getZfry();
        val[12]=pddxx.getZfsj();
        val[13]=pddxx.getZfsm();
        val[14]=pddxx.getShry();
        val[15]=pddxx.getShdz();
        val[16]=pddxx.getShdh();
        val[17]=pddxx.getPsry();
        val[18]=pddxx.getPssj();
        val[19]=pddxx.getPssm();
        val[20]=pddxx.getQsry();
        val[21]=pddxx.getQssj();
        val[22]=pddxx.getQssm();
        val[23]=pddxx.getPjnr();
        val[24]=pddxx.getPjsj();
        val[25]=ddmx;
        return val;

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

    }


    // var url = '/pdd/app/orderlist/' + hyid;

    @PostMapping("/orderlist/{hyid}")
    @ResponseBody
    public String getOrderlist(@PathVariable("hyid") String hyid, ModelMap mmap)                    //根据传入的spid，查询数据，放入ModelMap，返回给页面
    {
        //query from blockchain
        String sJson = "";
        sJson = DdxxCCTool.queryRange(
                "ddxx_hyid_" + hyid + "_", "ddxx_hyid_" + (hyid + "_z"));
        //sJson = sJson.replace("[,", "[");
        //System.out.println("==" + sJson);
        return sJson;
    }

}
