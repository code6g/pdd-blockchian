package com.ruoyi.code6g;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.framework.util.ShiroUtils;

import com.ruoyi.system.domain.SysUser;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;




//各种demo代码库
public class DemoUtil {


    //前台-根据某个字段值，查询json列表，返回到前台进行赋值
    public void Front_query_json_list(){
        /*
         //--JS--

     <script>
        //根据引用客户信息的客户id,查询客户信息，进行界面赋值
        function select_sbkh_ok(akhid) {
            $("#div_sbkh_ref").css("display","none");
             // alert(akhid);
            //查询客户信息，进行界面赋值
            var url = ctx + "dbutil/querylist/sbkh|khid|"+akhid+"";
            var config = {
                url: url,
                type: "post",
                dataType: "json",
                data: "",
                beforeSend: function () {
                    // $.modal.loading("正在处理中，请稍后...");
                },
                success: function (result) {
                    var code = result.code;
                    var msg = result.msg;
                    var data = result.data;
                    //查询结果客户信息，进行界面赋值
                    show_ref_resultinfo(data);
                }
            };
            $.ajax(config);
        }
        //查询结果客户信息，进行界面赋值
        function show_ref_resultinfo(data){
            //console.log(data);
            var s1="";
            for (var i = 0; i < data.length; i++) {
                $("#jfgs").val(data[i].khmc);//"	甲方公司*
                $("#jfdz").val(data[i].dwdz);//"	甲方地址
                $("#jlxr").val(data[i].lrxm);//"	甲方联系人*
                $("#jfdh").val(data[i].lrdh);//"	甲方联系电话
                $("#jfmc").val(data[i].khmc);//"	甲方名称
                // $("#jfzh").val();//"	甲方账号
                // $("#jfyh").val();//"	甲方开户银行
                // $("#jssh").val();//"	甲方税号
                $("#jfdb").val(data[i].lrxm);//	甲方签字代表

                // data[i].khid;//	单位编码
                // data[i].khbh;//	单位编号
                // data[i].khfl;//	单位分类
                // data[i].khxh;//	序号
                // data[i].khmc;//	单位名称
                // data[i].xydm;//	统一社会信用代码
                // data[i].yyzz;//	营业执照
                // data[i].lrid;//	联系人编码
                // data[i].lrxm;//	联系人
                // data[i].lrdh;//	联系人电话
                // data[i].dwdz;//	单位地址
                // data[i].lrzw;//	联系人职位
                // data[i].lrbm;//	联系人部门
                // data[i].yjdz;//	邮寄地址
                // data[i].czhm;//	传真号
                // data[i].yzbm;//	邮政编码
                // data[i].szqy;//	所在区域
                // data[i].dwsm;//	单位说明
                // data[i].sfyx;//	是否有效
            }

        }
    </script>


         */
    }

    //前台--打印时多大文本换行
    public  void Front_print_huanhang(){
        /*
        th:utext="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(sbbg.zysx),'\n','&lt;br/&gt;'))}"
        */
    }

    //前台--themleaf 循环赋值
    public void Front_themleaf_while(){
        /*
         <tr th:each="prod : ${allProducts}">
          <td th:text="${prod.name}">Oranges</td>
          <td th:text="${#numbers.formatDecimal(prod.price,1,2)}">0.99</td>
         </tr>


        //换行替换
          th:utext="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(sbbg.zysx),'\n','&lt;br/&gt;'))}"


          //打印强制分页
          <div style="page-break-after: always;"></div>
         */
    }



    //前台--子表嵌入
    public void Front_Child_Table_Iframe(){

        /*
             <!-- 子表-设备信息 -->
            <div id="div_sbsbzb" class="form-group col-sm-12" style="display:;background-color: #f3f3f4; border:1px solid lightgray;">
                <label class="label label-info">设备信息</label>
                <iframe id="iframe_sbsbzb" src="" class="col-sm-12" style="height: 500px; margin-top:10px; margin-bottom:20px;" frameborder="none" scrolling="no">
                </iframe>
            </div>
       */
    }

    //后台--子表列表显示
    public void Back_Child_Table_List(){
       /*
            //作为子表显示列表
            @GetMapping("/list/{afuid}")
            public String toListByAfuid(@PathVariable("afuid") String afuid,ModelMap mmap){
                Sbsb sbsb = new Sbsb();//getToListSbsbCon();
                sbsb.setFuid(afuid);
                mmap.put("sbsb", sbsb);
                return prefix + "/sbsb";
            }


        */

    }

    //后台--子表新增时
    public void Front_Child_Table_Add() {
    /*
    在 [enttname]zb/[enttname].html

        1:如下修改
        createUrl:prefix + "/add/{id}",

        2:如下修改
        <a class="btn btn-success" onclick="$.operate.add($('#fuid').val())" >
            <i class="fa fa-plus"></i> 添加检测项目
        </a>

        这样，能在新增时自动带上fuid；

        */

    }
    //后台--子表新增时
    public void Back_Child_Table_Add() {
       /*
        //--子表([enttname]).新增
        //--跳转到新增页面
        @GetMapping("/add/{afuid}")                                                                                                                //从url:/spc/spcjcxm/add的get方法， 跳转到url:spc/spcjcxm/add.html
        public String toAdd_[enttname]_zb(@PathVariable("afuid") String afuid, ModelMap mmap)
        {
            Spcjcxm spcjcxm = new Spcjcxm();
            spcjcxm.setXmid(InputUitl.getTimestamp());
            spcjcxm.setFuid(afuid);
            mmap.put("spcjcxm", spcjcxm);
            return prefix + "/add";
        }

        */

    }



    //后台--新增初始化赋值
    public void Front_ToAdd_Init() {
     /*

        SysUser sysUser = ShiroUtils.getSysUser();
        sbwt.setDwid("115");                            //操作单位编码
        sbwt.setDwmc("西安赛宝工业技术研究院有限公司");    //操作单位名称
        sbwt.setBmid(sysUser.getDeptId().toString());   //操作部门编码
        sbwt.setBmmc(sysUser.getDept().getDeptName());  //操作部门名称
        sbwt.setYhid(sysUser.getUserId().toString());   //操作用户编码
        sbwt.setYhmc(sysUser.getUserName());            //操作用户名称
        sbwt.setYwsj(InputUitl.get_datetime_bz());      //操作业务时间
        sbwt.setYwid("/sb/sbwtgl/add");                 //操作业务编码
        sbwt.setYwmc("委托登记");                        //操作业务名称
        sbwt.setYwzt("登记");                            //操作业务状态
        sbwt.setYwms("登记委托信息");                     //操作业务描述


     */
    }

    //前台--字典下拉框
    public void Front_dict_Input() {
     /*

     //固定下拉框
        <select id="lylx" name="lylx" class="form-control m-b">
            <!--required-->
            <option></option>
            <option value="研制">研制</option>
            <option value="委托单位">委托单位</option>
        </select>


    //后台动态获取字典
        <select id="htlx"  name="htlx" class="form-control m-b" th:with="type=${@dict.getType('sbzdhtlx')}"> <!--required-->
            <option ></option>
            <option th:each="dict : ${type}" th:text="${dict.dictLabel}" th:value="${dict.dictLabel}" th:field="*{htlx}"></option>
        </select>

     */
    }

    //前台--查询条件调整显示与屏蔽
    public void Front_Query_Input() {
     /*
     <ul >


     </ul>


     <ul hidden>


     </ul>

     */
    }

    //前台--编辑内容调整显示与屏蔽
    public void Front_Edit_Input() {
     /*
     <div id="div_info" >


     </div>


     <div style="display:none" id="div_hidden">


     </div>

     */
    }

    //前台&后台--弹出层-【引用】另一张表，或大字典，而且能够编辑，查询结果赋值到界面上
    public void Front_and_Back_Show_Ref_Div_MesageBox_assign_values() {
     /*

           //--前台----

           <!--甲方公司*-->
            <div class="form-group col-sm-6">
                <label class="col-sm-4 control-label"><span style="color: red; ">甲方*</span ></label>
                <div class="col-sm-5">
                    <input id="jfgs" name="jfgs" th:field="*{jfgs}" class="form-control" type="text"  required />
                </div>

                <div class="col-sm-3">
                    <button class="btn btn-success" onclick="$('#div_sbkh_ref').css('display','block');" type="button">
                        引用客户
                    </button>
                </div>
            </div>



            <!--引用客户信息-->
            <div id="div_sbkh_ref"
                 class="form-group col-sm-12"
                 style="
                    display: none;background-color: #f3f3f4; border:1px solid lightgray;
                    position: absolute;
                    z-index: 999999;
                    width: 100%;
                    height:100%;
                    left: 0px;
                    top:0px;
                    bottom:0px;
                    background-color: gray;
                    opacity:0.99;
                    text-align:right;
                  " onload="this.height=document.body.scrollHeight+40;">
                <br/>
                <button type="button" onclick="$('#div_sbkh_ref').css('display','none');" style="margin-right:1%; "
                        class="btn btn-danger btn-circle">
                    <i class="fa fa-times"></i></button>
                <iframe src="../../sb/sbkhref" class="col-sm-12"
                        style="height:95%; width:99%; margin-top:0%;  margin-left:0.5%; margin-right:0.5%;  border:0px solid blue; "
                        frameborder="none" scrolling="no">
                </iframe>
            </div>



        //--JS--

         <script>
        //根据引用客户信息的客户id,查询客户信息，进行界面赋值
        function select_sbkh_ok(akhid) {
            $("#div_sbkh_ref").css("display","none");
             // alert(akhid);
            //查询客户信息，进行界面赋值
            var url = ctx + "dbutil/querylist/sbkh|khid|"+akhid+"";
            var config = {
                url: url,
                type: "post",
                dataType: "json",
                data: "",
                beforeSend: function () {
                    // $.modal.loading("正在处理中，请稍后...");
                },
                success: function (result) {
                    var code = result.code;
                    var msg = result.msg;
                    var data = result.data;
                    //查询结果客户信息，进行界面赋值
                    show_ref_resultinfo(data);
                }
            };
            $.ajax(config);
        }
        //查询结果客户信息，进行界面赋值
        function show_ref_resultinfo(data){
            //console.log(data);
            var s1="";
            for (var i = 0; i < data.length; i++) {
                $("#jfgs").val(data[i].khmc);//"	甲方公司*
                $("#jfdz").val(data[i].dwdz);//"	甲方地址
                $("#jlxr").val(data[i].lrxm);//"	甲方联系人*
                $("#jfdh").val(data[i].lrdh);//"	甲方联系电话
                $("#jfmc").val(data[i].khmc);//"	甲方名称
                // $("#jfzh").val();//"	甲方账号
                // $("#jfyh").val();//"	甲方开户银行
                // $("#jssh").val();//"	甲方税号
                $("#jfdb").val(data[i].lrxm);//	甲方签字代表

                // data[i].khid;//	单位编码
                // data[i].khbh;//	单位编号
                // data[i].khfl;//	单位分类
                // data[i].khxh;//	序号
                // data[i].khmc;//	单位名称
                // data[i].xydm;//	统一社会信用代码
                // data[i].yyzz;//	营业执照
                // data[i].lrid;//	联系人编码
                // data[i].lrxm;//	联系人
                // data[i].lrdh;//	联系人电话
                // data[i].dwdz;//	单位地址
                // data[i].lrzw;//	联系人职位
                // data[i].lrbm;//	联系人部门
                // data[i].yjdz;//	邮寄地址
                // data[i].czhm;//	传真号
                // data[i].yzbm;//	邮政编码
                // data[i].szqy;//	所在区域
                // data[i].dwsm;//	单位说明
                // data[i].sfyx;//	是否有效
            }

        }
    </script>


      //--后台 后台--根据某个条件id，查询一个list，变为json，传给客户端,见其他代码段--------------
       DbUtilController.java



     */
    }





    //前台--设置日期/时间 输入框
    public void Front_set_input_datetime() {
     /*

     <!--设置日期/时间 输入框-->
     <script>

         $("#jssj").attr("placeholder","yyyy-MM-dd HH:mm:ss"); $("#jssj").datetimepicker({autoclose:true,format:"yyyy-mm-dd hh:ii:ss" }); //接收时间

         $("#bzsj").attr("placeholder","yyyy-MM-dd"); $("#bzsj").datetimepicker({autoclose:true,format:"yyyy-mm-dd",minView:"day" }); //签订日期

      </script>


     */
    }

    //前台--大文本 输入框
    public void Front_TextArea() {
    /*

        <!--样品说明-->
        <div class="row" >
            <div class="col-sm-12" >
                <div class="form-group" >
                    <label class="col-sm-2 control-label  left" ><!--span style = "color: red; " >*</span--> 样品说明 </label>
                    <div class="col-sm-9" >
                    <textarea id = "ypsm" name = "ypsm" maxlength = "500" class="form-control"  rows = "3" >[[*{ypsm}]]</textarea ><!--required-->
                    </div>
                </div>
            </div>
        </div>

    */
    }
    //前台--设置用户信息
    public void Back_set_userinfo(){
        /*
        SysUser sysUser = ShiroUtils.getSysUser();
        sbwt.setDwid("115"); //操作单位编码
        sbwt.setDwmc("西安赛宝工业技术研究院有限公司"); //操作单位名称
        sbwt.setBmid(sysUser.getDeptId().toString()); //操作部门编码
        sbwt.setBmmc(sysUser.getDept().getDeptName()); //操作部门名称
        sbwt.setYhid(sysUser.getUserId().toString()); //操作用户编码
        sbwt.setYhmc(sysUser.getUserName()); //操作用户名称
        sbwt.setYwsj(InputUitl.get_datetime_bz()); //操作业务时间
        sbwt.setYwid("/sb/sbwtgl/add"); //操作业务编码
        sbwt.setYwmc("委托登记"); //操作业务名称
        sbwt.setYwzt("登记"); //操作业务状态
        sbwt.setYwms("登记委托信息"); //操作业务描述
        */
    }




    //前台--根据某个条件id，查询一个list，显示到界面上
    public void Front_query_and_show_info_by_con() {
     /*--前台--根据某个条件id，查询一个list，显示到界面上

        //查询并显示委托信息
        <script>
            //查询并显示委托信息
            function  queryAndShowSbwt(awtid) {

                var url = ctx + "sb/sbrwfp/querysbwt/"+awtid;
                var config = {
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: "",
                    beforeSend: function () {
                        // $.modal.loading("正在处理中，请稍后...");
                    },
                    success: function (result) {

                        var code=result.code;
                        var msg=result.msg;
                        var data=result.data;

                        show_sbwy(data);
                    }
                };
                $.ajax(config);
            }

          //显示委托信息
          function  show_sbwy(data) {
                console.log(data);
                $('#tbody_sbwy').val("");
                var s1="";
                for (var i = 0; i < data.length; i++) {
                    //alert(data);
                    //console.log(data);
                    //alert(data[i].ypmc);
                    var s2="";
                    //var index=1+i;

                    s2+="<td>"+data[i].ypmc+"</td>";//	样品名称
                    s2+="<td>"+data[i].ypbh+"</td>";//	样品编号
                    s2+="<td>"+data[i].xhgg+"</td>";//	型号规格

                    //alert(s2);
                    s1+=s2;
                }
                $('#tbody_sbwy').html(s1);

          }

        </script>


         */


    }
    //后台--根据某个条件id，查询一个list，变为json，传给客户端
    public void Back_query_by_con() {
        /*--后台--根据某个条件id，查询一个list，变为json，传给客户端


            // var url = ctx + "sb/sbrwfp/querysbwt/"+ypids.split("|")[0];
            //查询委托信息
            @PostMapping("/querysbwt/{aypid}")
            @ResponseBody                                           //返回restful-json
            public JSONObject querysbwt(@PathVariable("aypid") String aypid){

                String sql="select * from sbwt where wtid in (select wtid from sbwy where ypid='"+aypid+"') ";
                ArrayList<HashMap> list=sqlUtilService.listArrayList(sql);
                JSONObject.JSONArray jsonArray=InputUitl.getJsonArrayFromList(list);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("code","200");
                jsonObject.put("msg","操作成功");
                jsonObject.put("data",jsonArray);
                return jsonObject;
            }


        */
    }



    //前台--根据某个条件，更新委托信息
    public void  Front_update_by_con() {
        /*--前台--根据某个条件，更新委托信息

        <script>
            function quxiao(obj, ahtid){
                var url = ctx + "sb/sbhtgl/updateHtztQuxiao/" + ahtid;
                var config = {
                        url:url,
                        type:"post",
                        dataType:"json",
                        data:"",
                        beforeSend:function() {
                            // $.modal.loading("正在处理中，请稍后...");
                        },
                success:
                    function(result) {
                        //console.log(result);
                        var code = result.code;
                        var msg = result.msg;
                        var data = result.data;
                        alert(msg);
                    }
                };
                $.ajax(config);
            }
        </script>


       */

    }

    //后台--根据某个条件，更新委托信息
    public void Back_update_by_con() {

        /*--后台--根据某个条件，更新委托信息

        // var url = ctx + "sb/sbrwfp/updatesbwt/"+aypid;

        //更新委托信息
        @PostMapping("/updatesbwt/{aypid}")
        @ResponseBody                                           //返回restful-json
        public JSONObject updatesbwt(@PathVariable("aypid") String aypid){

            String sql="update sbwt set wtzt='完成' where ypid='"+aypid+"' ";
            sqlUtilService.update(sql);

            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code","200");
            jsonObject.put("msg","操作成功");
            jsonObject.put("data","");
            return jsonObject;
        }

    */

    }



    //前台--文件上传
    public void Front_file_upload(){
        /*

            <!--CSS-->
            <th:block th:include="include :: bootstrap-fileinput-css"/>

            <!--HTML-->
            <!--合同扫描件 附件-->
            <div class="form-group col-sm-12">
                <label class="col-sm-2 control-label"><!--span style="color: red; ">*</span-->合同扫描件</label>
                <div class="col-sm-10">


                    <div id="divFileShow">

                    </div>
                    <input id="htsm" name="htsm" style="width: 100%;" th:field="*{htsm}" class="hidden"></input>

                    <div class="file-loading">
                        <input id="inputFile" name="inputFile" type="file" multiple>
                    </div>
                </div>

            </div>



            <!-- JS -->
            <th:block th:include="include :: bootstrap-fileinput-js"/>
            <!-- 文件上传 -->
            <script>
                var fj_fieldname="bgfj"; //附件字段名称

                function deleteFJ(msg){
                    //alert(msg);
                    var s1=$('#'+fj_fieldname).val();
                    s1=s1.replace(msg,"");
                    $('#'+fj_fieldname).val(s1);
                    showFJ();
                }

                function showFJ(){
                    var s=$('#'+fj_fieldname).val();
                    var s1=s.split(';');
                    var s3="";
                    for(var i=0;i<s1.length;i++){
                        if(s1[i]==""){continue;}
                        var s2=s1[i].split('|');
                        s3+="<a  target='_blank'  style='margin: 2px;' href='"+s2[1]+"'>"+s2[0]+"</a>"
                            +"&nbsp;&nbsp;<button onclick=\"deleteFJ('"+s1[i]+";')\" style='margin: 2px;' type='button' class='btn btn-circle btn-xs btn-danger' value=''>"
                            +"<i class=\"fa fa-times\"></i>"
                            +"</button>"+"<br/>";
                    }
                    $("#divFileShow").html(s3);
                }

                $(document).ready(function () {
                    $("#inputFile").fileinput({
                        'theme': 'explorer-fas',
                        //'uploadUrl': ctx + 'demo6g/fileupload/insert',
                        //'uploadUrl': ctx + 'yc/ycypgl/fileupload/insert',
                        'uploadUrl': ctx + 'sb/sbhtgl/fileupload/insert',
                        previewFileType: 'text',
                        initialPreviewFileType: 'text',
                        showPreview: false,
                        overwriteInitial: false,
                        initialPreviewAsData: false,
                        enctype: 'multipart/form-data',
                        initialPreview: ""
                    });
                    $("#inputFile").on("filepreajax", function (event, data) {
                        // console.log(event);
                        // console.log(data);
                        debugger;

                    });

                    // fileuploaded(event,data , previewId , index):上传并完成时触发(无论是点击的哪个按钮)
                    $('#inputFile').on('fileuploaded', function (event, data, previewId, index) {
                        //console.log(event);
                        console.log(data);
                        //console.log(previewId);
                        console.log(index);

                        var msg = data.response.msg;
                        $('#'+fj_fieldname).val($('#'+fj_fieldname).val() + msg);

                        showFJ();

                    });



                });


            </script>

         */
    }

}
