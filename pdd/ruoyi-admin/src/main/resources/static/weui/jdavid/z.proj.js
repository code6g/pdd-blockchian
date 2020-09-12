//var serverURL = 'http://localhost:81/fxshopMysql/JsonMid/';
//var serverURL = 'http://localhost:1297/JsonMid/';
//var serverURL = 'http://localhost:64687/fxshopMysql/JsonMid/';
//var serverURL = '../../JsonMid/';
//var serverURL="http://localhost:8084/";

var resfulURL = '../../';
//var curHyid;
//var curHymc;


proj_init();

function proj_init(){
    var css_head=h5app_head_css();
    $(".demos-header-lw").css("display",css_head);
}







/*

function call_qq(qq){
  var s_url
    ="http://wpa.qq.com/msgrd?V=1&Uin="+qq+"&Site=Sciencoo&menu=no";	
      //= "http://wpa.qq.com/msgrd?V=1&Uin=807624849&Site=Sciencoo&menu=no";
  window.open(s_url);
}

function start_process(){
  //$("#id_div_process").html("正在进行数据提交，请稍�??......");
  //$("#id_div_process").html("<img src=\"../jdavid/image/load.gif\" alt=\"\"/>");
  //$("#id_div_process").css("display","");

}
function end_process(){
  //$("#id_div_process").css("display","none");
}

function show_process(id,id_hint){
	
	$("#"+id).addClass("div_cover_trans");
	$("#"+id_hint).addClass("div_cover_trans_hint");
	
	$("#"+id_hint).html("���ڲ�ѯ,���Ժ�......<br> <img src='../jdavid/image/load.gif' alt=''/> ");
	
	$("#"+id).css("display","");
	$("#"+id_hint).css("display","");
	//$("#"+id).fadeIn("slow");
	//$("#"+id_hint).fadeIn("slow");

}
function hide_process(id,id_hint){
	//$("#"+id_hint).css("display","none");
	//$("#"+id).fadeOut("slow");
	$("#"+id_hint).fadeOut("slow");
   	$("#"+id).css("display","none");
	
}
 
 
 
 
 
 //------------select glyxx----------------------------------------------------
 
function ref_glyxx(textbox_coid,textbox_glymc,textbox_glyid){
   //alert("ref glyxx");
   var coid=document.getElementById("textbox_coid").value;
   //alert(coid);
   var a_con=" coid ='"+coid+"' ";
    //set_loading();                                             
    var p0="";                                               
    var p1="jx_glyxx";                                        
    var p2=a_con;                                              
    var p3="order by glybh desc";                             
	var url_ashx_c=get_url_ashx_c(); 
	//alert(url_ashx_c); 
	var xml_data_c=get_xml_data_c(p0,p1,p2,p3); 
	//alert(xml_data_c);
	//start_process();            
	$.post(url_ashx_c,xml_data_c,function(msg_xml_info_return){set_list_info_by_xml_info_jx_glyxx(msg_xml_info_return,textbox_glymc,textbox_glyid);});     
}


 function set_list_info_by_xml_info_jx_glyxx(msg_xml_info_return,textbox_glymc,textbox_glyid){ 
    var s_html=""; 
    //alert(msg_xml_info_return);                  
    var Element_rows=get_root_Element_from_str_xml(msg_xml_info_return,"rows");                                                        
    var Elements_row = Element_rows.getElementsByTagName("row");                                                                       
    _record_count=Elements_row.length;    
    //alert(_record_count);         
    //    if(need_show_count){ 
    //      if(need_show_count=="1"){ 
    //         $("#id_div_fenye").html("��"+_record_count+"������");                                                                          
    //      } 
    //    } 
     
    for (var i_row = 0; i_row < Elements_row.length; i_row++) {                                                                          
            var Element_row = Elements_row[i_row]; 
            //+----ȡֵ--------+                   
            var val_glyid = unpack_char(Element_row.getAttribute("glyid"));                                                                 
            var val_glybh = unpack_char(Element_row.getAttribute("glybh"));                                                                 
            var val_glyxm = unpack_char(Element_row.getAttribute("glyxm"));                                                                 
                                                                          
            //+-end---ȡֵ--------+                
                                                   
            //+---�б�ֵ--------+                
            //s_html += "<tr>";                   
        
            //s_html += "<td>&nbsp;" + val_yhid + "</td>";
            //s_html += "<td>&nbsp;<a href=\"javascript:view_detail_cq_yhxx('" + val_yhid + "');\">"+ val_yhbh + "</a></td>";
            s_html += "<div>&nbsp;<a style='color:brown;' href=\"javascript:select_gly('"+val_glyxm+"','" + val_glyid + "','"+textbox_glymc+"','"+textbox_glyid+"');\">ѡ�����Ա ��" 
                      + val_glyxm + "��</a></div>";
 
 
            //+-end-�б�ֵ--------+              
    }//end of for( ......                            
    //$("#id_table_cq_yhxx>tbody").html(s_html);  
    //alert(s_html);
	show_div_top_big(s_html);
    //$("#label_message").html(s_html);  
      
    //set_table_style("id_table_cq_yhxx");   
    //end_process(); 
} 


function select_gly(val_glyxm,val_glyid,textbox_glymc,textbox_glyid){
	//alert(val_glyxm);
	//$.removePrompt();
	//$("#id_div_select_gly").parent.remove();
	
	document.getElementById(textbox_glymc).value=val_glyxm;
	document.getElementById(textbox_glyid).value=val_glyid;
	
	$("#id_div_select_gly").parent().parent().parent().remove();

}
//----------------------------------------------------------------



 
//--------------------select jlxx--------------------------------------------
function ref_jlxx(textbox_coid,textbox_jlmc,textbox_jlid){
   //alert("ref glyxx");
   var coid=document.getElementById("textbox_coid").value;
   //alert(coid);
   var a_con=" coid ='"+coid+"' ";
    //set_loading();                                             
    var p0="";                                               
    var p1="jx_jlxx";                                        
    var p2=a_con;                                              
    var p3="order by jlbh desc";                             
	var url_ashx_c=get_url_ashx_c(); 
	//alert(url_ashx_c); 
	var xml_data_c=get_xml_data_c(p0,p1,p2,p3); 
	//alert(xml_data_c);
	//start_process();            
	$.post(url_ashx_c,xml_data_c,function(msg_xml_info_return){set_list_info_by_xml_info_jx_jlxx(msg_xml_info_return,textbox_jlmc,textbox_jlid);});     
}


 function set_list_info_by_xml_info_jx_jlxx(msg_xml_info_return,textbox_jlmc,textbox_jlid){ 
    var s_html=""; 
    //alert(msg_xml_info_return);                  
    var Element_rows=get_root_Element_from_str_xml(msg_xml_info_return,"rows");                                                        
    var Elements_row = Element_rows.getElementsByTagName("row");                                                                       
    _record_count=Elements_row.length;    
    //alert(_record_count);         
    //    if(need_show_count){ 
    //      if(need_show_count=="1"){ 
    //         $("#id_div_fenye").html("��"+_record_count+"������");                                                                          
    //      } 
    //    } 
     
    for (var i_row = 0; i_row < Elements_row.length; i_row++) {                                                                          
            var Element_row = Elements_row[i_row]; 
            //+----ȡֵ--------+                   
            var val_jlid = unpack_char(Element_row.getAttribute("jlid"));                                                                 
            var val_jlbh = unpack_char(Element_row.getAttribute("jlbh"));                                                                 
            var val_jlxm = unpack_char(Element_row.getAttribute("jlxm"));                                                                 
                                                                          
            //+-end---ȡֵ--------+                
                                                   
            //+---�б�ֵ--------+                
            //s_html += "<tr>";                   
        
            //s_html += "<td>&nbsp;" + val_yhid + "</td>";
            //s_html += "<td>&nbsp;<a href=\"javascript:view_detail_cq_yhxx('" + val_yhid + "');\">"+ val_yhbh + "</a></td>";
            s_html += "<div>&nbsp;<a style='color:brown;' href=\"javascript:select_jl('"+val_jlxm+"','" + val_jlid + "','"+textbox_jlmc+"','"+textbox_jlid+"');\">ѡ����� ��" 
                      + val_jlxm + "��</a></div>";
 
 
            //+-end-�б�ֵ--------+              
    }//end of for( ......                            
    //$("#id_table_cq_yhxx>tbody").html(s_html);  
    //alert(s_html);
	show_div_top_big(s_html);
    //$("#label_message").html(s_html);  
      
    //set_table_style("id_table_cq_yhxx");   
    //end_process(); 
} 


function select_jl(val_jlxm,val_jlid,textbox_jlmc,textbox_jlid){
	//alert(val_glyxm);
	//$.removePrompt();
	//$("#id_div_select_gly").parent.remove();
	
	document.getElementById(textbox_jlmc).value=val_jlxm;
	document.getElementById(textbox_jlid).value=val_jlid;
	
	$("#id_div_select_gly").parent().parent().parent().remove();

}
//----------------------------------------------------------------



//---------------show the transpert div --------------------------

function show_div_top_big(s_html) {
	var txt = "<div id='id_div_select_gly' left='0' top='0' style='background-color:white; line-height:25px;color:brown;'>"+s_html+"</div> ";
	$.prompt(txt, {
		buttons : {
			�ر� : true
		},
		submit : function(v, m) {
			return true;
		},
		callback : function(v, m) {
		}
	});
 }
 
 //----------------------------------------------------------------


//javascript:ref_xyxx('textbox_coid','textbox_xymc','textbox_xyid','textbox_xybh')
 
//--------------------select xyxx--------------------------------------------
function ref_xyxx(textbox_coid,textbox_xymc,textbox_xyid,textbox_xybh){
   //alert("ref glyxx");
   var coid=document.getElementById(textbox_coid).value;
   //alert(coid);
   var a_con=" coid ='"+coid+"' ";
    //set_loading();                                             
    var p0="";                                               
    var p1="jx_xyxx";                                        
    var p2=a_con;                                              
    var p3="order by xybh desc";                             
	var url_ashx_c=get_url_ashx_c(); 
	//alert(url_ashx_c); 
	var xml_data_c=get_xml_data_c(p0,p1,p2,p3); 
	//alert(xml_data_c);
	//start_process();            
	$.post(url_ashx_c,xml_data_c,function(msg_xml_info_return){set_list_info_by_xml_info_jx_xyxx(msg_xml_info_return,textbox_xymc,textbox_xyid,textbox_xybh);});     
}


 function set_list_info_by_xml_info_jx_xyxx(msg_xml_info_return,textbox_xymc,textbox_xyid,textbox_xybh){ 
    var s_html=""; 
    //alert(msg_xml_info_return);                  
    var Element_rows=get_root_Element_from_str_xml(msg_xml_info_return,"rows");                                                        
    var Elements_row = Element_rows.getElementsByTagName("row");                                                                       
    _record_count=Elements_row.length;    
    //alert(_record_count);         
    //    if(need_show_count){ 
    //      if(need_show_count=="1"){ 
    //         $("#id_div_fenye").html("��"+_record_count+"������");                                                                          
    //      } 
    //    } 
     
    for (var i_row = 0; i_row < Elements_row.length; i_row++) {                                                                          
            var Element_row = Elements_row[i_row]; 
            //+----ȡֵ--------+                   
            var val_xyid = unpack_char(Element_row.getAttribute("xyid"));                                                                 
            var val_xybh = unpack_char(Element_row.getAttribute("xybh"));                                                                 
            var val_xymc = unpack_char(Element_row.getAttribute("xymc"));                                                                 
                                                                          
            //+-end---ȡֵ--------+                
                                                   
            //+---�б�ֵ--------+                
            //s_html += "<tr>";                   
        
            //s_html += "<td>&nbsp;" + val_yhid + "</td>";
            //s_html += "<td>&nbsp;<a href=\"javascript:view_detail_cq_yhxx('" + val_yhid + "');\">"+ val_yhbh + "</a></td>";
            s_html += "<div>&nbsp;<a style='color:brown;' href=\"javascript:select_xy('"+val_xyid+"','" + val_xybh + "','"+val_xymc+"','"+textbox_xyid+"','"+textbox_xybh+"','"+textbox_xymc+"');\">ѡ��ѧԱ ��" 
                      + val_xymc + "��</a></div>";
 
 
            //+-end-�б�ֵ--------+              
    }//end of for( ......                            
    //$("#id_table_cq_yhxx>tbody").html(s_html);  
    //alert(s_html);
	show_div_top_big(s_html);
    //$("#label_message").html(s_html);  
      
    //set_table_style("id_table_cq_yhxx");   
    //end_process(); 
} 


function select_xy(val_xyid,val_xybh,val_xymc,textbox_xyid,textbox_xybh,textbox_xymc){
	//alert(val_glyxm);
	//$.removePrompt();
	//$("#id_div_select_gly").parent.remove();
	
	document.getElementById(textbox_xyid).value=val_xyid;
	document.getElementById(textbox_xybh).value=val_xybh;
	document.getElementById(textbox_xymc).value=val_xymc;
	
	$("#id_div_select_gly").parent().parent().parent().remove();
	//$("#id_div_select_gly").parent().parent().parent().removePrompt();
	//$.removePrompt();
	//jQuery.ImpromptuClose();
	//$("#id_div_select_gly").parent().parent().parent().html("");
	
    
        

}
//----------------------------------------------------------------



   function correctPNG() // correctly handle PNG transparency in Win IE 5.5 & 6. 
        { 
            var arVersion = navigator.appVersion.split("MSIE") 
            var version = parseFloat(arVersion[1]) 
            if ((version >= 5.5) && (document.body.filters)) 
            { 
               for(var j=0; j<document.images.length; j++) 
               { 
                  var img = document.images[j] 
                  var imgName = img.src.toUpperCase() 
                  if (imgName.substring(imgName.length-3, imgName.length) == "PNG") 
                  { 
                     var imgID = (img.id) ? "id='" + img.id + "' " : "" 
                     var imgClass = (img.className) ? "class='" + img.className + "' " : "" 
                     var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' " 
                     var imgStyle = "display:inline-;" + img.style.cssText 
                     if (img.align == "left") imgStyle = "float:left;" + imgStyle 
                     if (img.align == "right") imgStyle = "float:right;" + imgStyle 
                     if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle 
                     var strNewHTML = "<span " + imgID + imgClass + imgTitle 
                     + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";" 
                     + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader" 
                     + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
                     img.outerHTML = strNewHTML 
                     j = j-1 
                  } 
               } 
            }     
        } 
        
        
        
    function png_ok(){
        correctPNG();
    }
    
    
    //ѡ����Ʒ��Ϣ
    function select_spxx(id_div_select_spxx){
       //
       var s="";
       s+="<div id='id_div_temp0' ";
       s+=" style='width:100%;height:100%; background-color:black; display:none;filter:alpha(Opacity=50);-moz-opacity:0.5;opacity: 0.2;color:white; z-index:50;position:absolute;left:0px;top:0px;' > ";
       s+=" </div>";
       s+=" <div id='id_div_temp' style='border:4px solid #E8CEAE;left:30px;top:30px;width:900px;height:500px;background-color:white; display:none;position:absolute;color:gray;z-index:100;'> ";

       $("#"+id_div_select_spxx).html(s);
       
       var url="";
	
	   url= "../vm_store_spxx_select/spfl_for_spxx.aspx?_flid=123"+"&_z="+get_guid32();
	 
       var s_html="";
	        //"<div style='height:30px;text-align:right;width:400px;color:white;font-size:12px;color:navy;'><button onclick='caozuo_cancel_div();'>�ر�</button></div>";
       s_html+= "<div><iframe style='width:900px;height:500px;' src='"+url+"'></iframe></div>";


        $("#id_div_temp").html(s_html);
        $("#id_div_temp").css("display","");
        $("#id_div_temp0").css("display","");
 

    }
*/


// function logout(){
//     var storage = window.localStorage;
//     storage.setItem("dlzh","");
// }


function get_storage_val(itemname){
    var storage = window.localStorage;
    var s=storage.getItem(itemname);
    if(!s){return ""};
    return s;
}
function set_storage_val(itemname,val){
    var storage = window.localStorage;
    storage.setItem(itemname,val);

}


function is_login() {
    var storage = window.localStorage;
    var pdd_userinfo = storage.getItem("pdd_userinfo");

    if (pdd_userinfo) {
        if (pdd_userinfo === "") {
            return false;
        } else {
            return true;
        }
    } else {
        return false;
    }
}
function logout(){
    var storage = window.localStorage;
    storage.setItem("pdd_userinfo","");
}