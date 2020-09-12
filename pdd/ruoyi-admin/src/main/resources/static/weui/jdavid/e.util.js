// ------------生成GUID----------------------------------------------
function getGuid36() {
	var guid = "{";
	for (var i = 1; i <= 32; i++) {
		var n = Math.floor(Math.random() * 16.0).toString(16);
		guid += n;
		if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
			guid += "-";
	}
	guid += "}";
	return guid;
}

function getGuid32() {
	var guid = "";
	for (var i = 1; i <= 32; i++) {
		var n = Math.floor(Math.random() * 16.0).toString(16);
		guid += n;
		if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
			guid += "";
	}
	guid += "";
	return guid;
}

function get_guid32(){
  return getGuid32();
}
// ---------end---生成GUID-------------------------------------------

// =============读写cookie==================================================
// utility function to retrieve an expiration data in proper format;
function getExpDate(days, hours, minutes) {
	var expDate = new Date();
	if (typeof(days) == "number" && typeof(hours) == "number"
			&& typeof(hours) == "number") {
		expDate.setDate(expDate.getDate() + parseInt(days));
		expDate.setHours(expDate.getHours() + parseInt(hours));
		expDate.setMinutes(expDate.getMinutes() + parseInt(minutes));
		return expDate.toGMTString();
	}
}

// utility function called by getCookie()
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1) {
		endstr = document.cookie.length;
	}
	return unescape(document.cookie.substring(offset, endstr));
}

/*cookie永不过期
// store cookie value with optional details as needed
function setCookie(name, value, expires, path, domain, secure) {
    var the_date = new Date("December 31, 2020"); 
    var expiresDate = the_date.toGMTString(); 
    
    var expires_a=expiresDate;
    
    
    var path_a="/";

	document.cookie = name + "=" + escape(value)
			+ ((expires_a) ? "; expires=" + expires_a : "")
			+ ((path_a) ? "; path=" + path_a : "")
			+ ((domain) ? "; domain=" + domain : "")
			+ ((secure) ? "; secure" : "");
}
*/
 
// store cookie value with optional details as needed
function setCookie(name, value, expires, path, domain, secure) {
	//alert(name+value);
    //var the_date = new Date("December 31, 2020");
	var n=0.0208;//半个小时占整天的比率
	//alert(name+value);
	var the_date = new Date(new Date()-0+n*86400000); 
	//alert(name+value);
	
    var expiresDate = the_date.toGMTString(); 
	//alert(name+value);
    
    var expires_a=expiresDate;
	//alert(name+value);
    
    
    var path_a="/";
	//alert(name+value);

	document.cookie = name + "=" + escape(value)
			+ ((expires_a) ? "; expires=" + expires_a : "")
			+ ((path_a) ? "; path=" + path_a : "")
			+ ((domain) ? "; domain=" + domain : "")
			+ ((secure) ? "; secure" : "");

  //  alert(name+value);

}

// remove the cookie by setting ancient expiration date
function deleteCookie(name, path, domain) {
	var path_a="/";
	if (getCookie(name)) {
		document.cookie = name + "=" + ((path_a) ? "; path=" + path_a : "")
				+ ((domain) ? "; domain=" + domain : "")
				+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
	}
}

// primary function to retrieve cookie by name
function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg) {
			return getCookieVal(j);
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return;
}

// =============end--读写cookie==============================================

// =============读取ｘｍｌ字符串返回根节点=====================================
function get_root_Element_from_str_xml(str_xml, root_name) {
	// alert("get_root_Element_from_str_xml");
	if (window.ActiveXObject) {
		var xmlobject = new ActiveXObject("Microsoft.XMLDOM");
		xmlobject.async = "false";
		xmlobject.loadXML(str_xml);
	}
	// 用于 Mozilla, Firefox, Opera, 等浏览器的代码：
	else {
		var parser = new DOMParser();
		var xmlobject = parser.parseFromString(str_xml, "text/xml");
	}
	if (!(xmlobject)) {
		alert("xmlstr 构造xmlobject失败：" + str_xml + "  ------- " + root_name);
		return null;
	}
	var Elements_root = xmlobject.getElementsByTagName(root_name);
	if ((Elements_root && Elements_root.length > 0)) {
		return Elements_root[0];
	} else {
		return null;
	}
}
// ==========end===读取xml字符串返回根节点==================================

// =============读取ｘｍｌ字符串返回值的记录行数=====================================
function get_rows_count_form_xml_info(str_xml) {
    
    var root_name="rows";
    
	// alert("get_root_Element_from_str_xml");
	if (window.ActiveXObject) {
		var xmlobject = new ActiveXObject("Microsoft.XMLDOM");
		xmlobject.async = "false";
		xmlobject.loadXML(str_xml);
	}
	// 用于 Mozilla, Firefox, Opera, 等浏览器的代码：
	else {
		var parser = new DOMParser();
		var xmlobject = parser.parseFromString(str_xml, "text/xml");
	}
	if (!(xmlobject)) {
		alert("xmlstr 构造xmlobject失败：" + str_xml + "  ------- " + root_name);
		return null;
	}
	var Elements_root = xmlobject.getElementsByTagName(root_name);
	var result=0;
	
	if ((Elements_root && Elements_root.length > 0)) {
	    var Elements_row=Elements_root[0].getElementsByTagName("row");
	    if ((Elements_row && Elements_row.length > 0)) {
		  result=Elements_row.length;
		}
	} 
	return result;
	
}
// ==========end===读取xml字符串返回根节点==================================



// ===根据主键和主键值从xml_entt_info中找到匹配的实体信息节点
function find_Element_from_xml_entt_info_by_pk(xml_entt_info, pkname, pkvalue) {
	
	var Element_entts = get_root_Element_from_str_xml(xml_entt_info, "entts");
	// alert("find_Element_from_xml_entt_info_by_pk.Element_entts"+Element_entts);
	var Elements_entt = Element_entts.getElementsByTagName("entt");
	if (!(Elements_entt && Elements_entt.length > 0)) {
		return null;
	}
	for (var i_entt = 0; i_entt < Elements_entt.length; i_entt++) {
		var Element_entt = Elements_entt[i_entt];
		var Elements_prop = Element_entt.getElementsByTagName("prop");
		for (var i_prop = 0; i_prop < Elements_prop.length; i_prop++) {
			var Element_prop = Elements_prop[i_prop];
			var propname = Element_prop.getAttribute("n");
			var propvalue = Element_prop.getAttribute("v");
			//alert("132: ");
			if ((propname == pkname) && (propvalue == pkvalue)) {
				//alert("主键，和值查找成功："+propname+"=="+pkname+" && "+propvalue+"=="+pkvalue);
				//alert(Element_entt.text);
				return Element_entt;
			}
		}
	}
	return null;
}
// end 根据主键和主键值从xml_entt_info中找到匹配的实体信息节点

function setSelectOption(selectObj, selectedValue) {
	// alert(selectObj);
	// alert(selectedValue);
	var len = selectObj.options.length;
	for (var i = 0; i < len; i++) { // 设置 option
		if (selectedValue == selectObj.options[i].value) {
			selectObj.options[i].selected = true;
		}
	}
}

// 根据属性名从实体定义文档根节点的子节点中获取属性名匹配的属性定义节点
function get_Element_PROPERTY_from_Element_ENTT(def_Element_ENTT, propname) {
	var def_Elements_PROPERTY = def_Element_ENTT
			.getElementsByTagName("PROPERTY");
	for (var i_prop = 0; i_prop < def_Elements_PROPERTY.length; i_prop++) {
		var Element_prop = def_Elements_PROPERTY[i_prop];
		if (propname == Element_prop.getAttribute("PROPERTYNAME")) {
			return Element_prop;
		}
	}
}

// 根据属性名从xml_entt_info的Element_entt节点下取出某属性的属性值
function get_v_from_Element_entt(Element_entt, propname) {
	Elements_prop = Element_entt.getElementsByTagName("prop");
	for (var i = 0; i < Elements_prop.length; i++) {
		Element_prop = Elements_prop[i];
		n = Element_prop.getAttribute("n");
		v = Element_prop.getAttribute("v");
		//alert(n+"--"+v);
		if (propname == n) {
			//alert("176:查找propvalue成功=["+v+"]");
			if(!v){v="";}
			return v;
		}
	}
	return "";
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
			// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
				- RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1
					? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
// //上面的函数必须在上面(先定义后使用),不然无法识别format()
// var ddd = new Date();
// alert(ddd.format('yyyyMMdd'));

function test_get_date() {
	var ddd = new Date();
	alert(ddd.format('yyyyMMddhhmmss'));
}

function get_datetime14() {
	var ddd = new Date();
	var datetime14 = (ddd.format('yyyyMMddhhmmss'));
	return datetime14;
}

function get_datetime14_chinese() {
	var ddd = new Date();
	var datetime14 = (ddd.format('yyyy年MM月dd日 hh:mm:ss'));
	return datetime14;
}

function get_date8() {
	var ddd = new Date();
	var date8 = (ddd.format('yyyyMMdd'));
	return date8;
}


function get_datetime14_standred() {
	var ddd = new Date();
	var datetime14 = (ddd.format('yyyy-MM-dd hh:mm:ss'));
	return datetime14;
}

function get_datetime14_bz() {
	var ddd = new Date();
	var datetime14 = (ddd.format('yyyy-MM-dd hh:mm:ss'));
	return datetime14;
}


// ====从一批名为radioName的radio框中取得选中的值==========
function getRadioValue(radioName) {
	var obj = document.getElementsByName(radioName);
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			return obj[i].value;
		}
	}
}
// =end=从一批名为radioName的radio框中取得选中的值==========

// ==显示放大的图片信息===================
function show_img_big(src) {
	var txt = "<div left='0' top='0'><img src=\"" + src + "\" /></div> ";
	$.prompt(txt, {
		buttons : {
			关闭 : true
		},
		submit : function(v, m) {
			return true;
		},
		callback : function(v, m) {
		}
	});
}

// end 显示放大的图片信息===================

// ===根据填写的数据内容自动调节Input框的宽度
function auto_width(id_input) {
	// var oTextCount = document.getElementById("char");
	// alert(id_input);
	// var iCount =
	// document.getElementById(id_input).value.replace(/[^\u0000-\u00ff]/g,"aa").length;
	// which.size=iCount+2;
	if (!document.getElementById(id_input).value) {
		document.getElementById(id_input).size = 4;
		return;
	}
	if (document.getElementById(id_input).value == "") {
		document.getElementById(id_input).size = 4;
		return;
	}

	document.getElementById(id_input).size = (document.getElementById(id_input).value
			.replace(/[^\u0000-\u00ff]/g, "aa").length);
}

function auto_width_all() {
	var all_input = document.getElementsByTagName("INPUT");
	for (i = 0; i < all_input.length; i++) {
		a_input = all_input[i];
		if (!a_input.value) {
			a_input.size = 2;
			continue;
		}
		if (a_input.value == "") {
			a_input.size = 2;
			continue;
		}
		a_input.size = (a_input.value.replace(/[^\u0000-\u00ff]/g, "aa").length);
	}
}

// =end=根据填写的数据内容自动调节Input框的宽度

 

function get_value_of_select(id_select){
 // alert(document.getElementById(id_select).options.selectedIndex);
  if(document.getElementById(id_select).options.selectedIndex==(-1)){return "";}
  return   document.getElementById(id_select).options[document.getElementById(id_select).options.selectedIndex].value;
}

function get_text_of_select(id_select){
  if(document.getElementById(id_select).options.selectedIndex==(-1)){return "";}
  return   document.getElementById(id_select).options[document.getElementById(id_select).options.selectedIndex].text;
}

//6.设置select中指定text的第一个Item为选中
function select_SelectItemByText(obj,text){    
    var isExit = false;
    for(var i=0;i<obj.options.length;i++){
        if(obj.options[i].text == text){
            obj.options[i].selected = true;
            return true;
        }
    }
 return false;
 
}
//7.设置select中指定value的第一个Item为选中
function select_SelectItemByValue(obj,value){    
    var isExit = false;
    for(var i=0;i<obj.options.length;i++){
        if(obj.options[i].value == value){
            obj.options[i].selected = true;
            return true;
        }
    }
 return false;
 
}

 
function replace_piliang(s_src,s1,s2){
	//alert(s_src);
	
	//var reg=new RegExp(s1,"g");
	//创建正则RegExp对象
	
	//var newstr=s_src.replace(reg,s2); 
	
	//alert(newstr);
	//return newstr;
	
	// alert(s_src+"  "+s1+"   "+s2);
	 
	 var regS = new RegExp(s1,"gi");

	 //alert(s.replace("test","Hello")); //只替换一个
	 
	 var s3=s_src.replace(regS,s2);
	 //alert(s3);
	 return s3;
	

	
}
  

function document_getElementById_value(id){
   if(document.getElementById(id)==null){
	   alert("属性ID  "+id+"  不存在!"); 
	   return;
   }
   
   var val_tagName=document.getElementById(id).tagName;
   var s=document.getElementById(id).value;
   //alert(val_tagName+s);
   if(val_tagName.toUpperCase()=="SELECT"){
	 s=get_value_of_select(id);
   }
   
　　s=pack_char(s);　
   //alert(s);
   return s;

}
　
 
 
// 去掉字符串的头空格（左空格）
function LTrim(str){ 
    var i;
    for(i=0;i<str.length; i++) {
        if(str.charAt(i)!=" ") break;
    }
    str = str.substring(i,str.length);
    return str;
}

// 去掉字符串的尾空格（右空格）
function RTrim(str){
    var i;
    for(i=str.length-1;i>=0;i--){
        if(str.charAt(i)!=" ") break;
    }
    str = str.substring(0,i+1);
    return str;
}

// 去掉字符串的头尾空格（左右空格）
function Trim(str){
    return LTrim(RTrim(str));
}



/*
	可以对任何字符串使用这个方法了：
	var str="test测试";
	str.length==6;
	str.length2()==8;
    注意：使用 length2() 时不要漏了括号，因为这是一个方法 
*/
String.prototype.length2 = function() {
	var cArr = this.match(/[^\x00-\xff]/ig);
	return this.length + (cArr == null ? 0 : cArr.length);
}



/*
中英文混合计数的字符串截取函数
var str             = "呵呵he哈哈嘿嘿and";  
str.substr(0, 10)   ： 呵呵he哈哈嘿嘿an
str.substring(0, 10)： 呵呵he哈哈嘿嘿an
substr2(str, 10)    ： 呵呵he哈哈
substr2(str, 9)     ： 呵呵he哈
*/

function substr2(str, len) {     
	if(!str || !len) { return ''; }      
	//预期计数：中文2字节，英文1字节  
	var a = 0;    //循环计数     
	var i = 0;   //临时字串     
	var temp = '';      
	for (i=0;i<str.length;i++)     
	{        
	   if (str.charCodeAt(i)>255){
				//按照预期计数增加2             
		   a+=2;         
		}else{             
		   a++;        
		}         
		//如果增加计数后长度大于限定长度，就直接返回临时字符串         
		if(a > len) { return temp; }          
		//将当前内容加到临时字符串        
		temp += str.charAt(i);     
	 }     
	 //如果全部是单字节字符，就直接返回源字符串     
	 return str; 
}  

/*

*/
function substr_add_dot(str,len_en){
   if(str.length2()<=len_en){return str;}
   var s=substr2(str,len_en)+"...";
   return s;
}

function substr_add_dot_to_len(str,len_en_wenzi,len_en_whole){
   var  s=""; 
   if(str.length2()<=len_en_whole){
	   s=str;
   }
   else{
      s=substr2(str,len_en_whole);
   }
   var len_text=s.length2();
   for (var i=0;i<(len_en_whole-len_text);i++){
	   s+=".";
   }
   return s;
}
		 
    //整除
function div(exp1, exp2)
{
    var n1 = Math.round(exp1); //四舍五入
    var n2 = Math.round(exp2); //四舍五入
    
    var rslt = n1 / n2; //除
    
    if (rslt >= 0)
    {
        rslt = Math.floor(rslt); //返回值为小于等于其数值参数的最大整数值。
    }
    else
    {
        rslt = Math.ceil(rslt); //返回值为大于等于其数字参数的最小整数。
    }
    
    return rslt;
}
 

function pack_char(s_src){
   var s=s_src;	
   s=s.replace(/\'/g,"‘");
   s=s.replace(/\"/g,"＃");
   
   s=s.replace(/\>/g,"）");
   s=s.replace(/\</g,"（");
   
   s=s.replace(/\--/g,"－－");
   s=s.replace(/\=/g,"＝");
   s=s.replace(/\&nbsp;/g,"～");
   
   s=s.replace(/\r\n/ig,"");
   
   s=s.replace(/\&/g,"＆");
   s=s.replace(/\?/g,"？");
   return s;
	
}

function unpack_char(s_src){
   var s=s_src;
   s=s.replace(/\‘/g,"'");
   s=s.replace(/\＃/g,"\"");
   
   s=s.replace(/\）/g,">");
   s=s.replace(/\（/g,"<");
   
   s=s.replace(/\－－/g,"--");
   s=s.replace(/\＝/g,"=");
   s=s.replace(/\～/g,"&nbsp;");
   
   s=s.replace(/\＆/g,"&");
   s=s.replace(/\？/g,"?");
   

   s = s.replace(/＞/g, '>')
   s = s.replace(/＜/g, '<')
   s = s.replace(/“/g, '"')
   s = s.replace(/\//g, '/')

   return s;
}


function get_db_val(val_s_db){
    var val_s1 = val_s_db;
    val_s1 = val_s1.replace(/\＇/g, "'");

    val_s1 = val_s1.replace(/\＜/g,"<");
    val_s1 = val_s1.replace(/\＞/g,">");

    val_s1 = val_s1.replace(/\＂/g,'"');
    val_s1 = val_s1.replace(/\＆/g,"&");
    val_s1 = val_s1.replace(/\；/g,";");

    val_s1=val_s1.replace(/\＃/g,"#");

    val_s1=val_s1.replace(/\－－/g,"--");
    val_s1=val_s1.replace(/\＝/g,"=");
    val_s1=val_s1.replace(/\～/g,"~");
    val_s1=val_s1.replace(/\？/g,"?");
    val_s1=val_s1.replace(/\：/g,":");





    val_s1 = val_s1.replace(/\＼/g, "\\");


    //s = s.replace(/\//g, '/')

    // val_s1=val_s1.replace("'","＇");
    // val_s1=val_s1.replace("'","＇");
    // val_s1=val_s1.replace("'","＇");

    return val_s1;
}



function dvd_set_val(id_input,val){
    document.getElementById(id_input).value=val;
}

function dvd_get_val(id_input){
    return document_getElementById_value(id_input);
}

//
// 
/*
var b = new Base64();
var str = b.encode("admin:admin");
alert("base64 encode:" + str);
//解密
str = b.decode(str);
alert("base64 decode:" + str);
*/
function Base64() {

    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}

//=========判断浏览器类型，决定是否需要显示h5app的头部==========
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
function get_browser_type() {
    if (browser.versions.mobile) {//判断是否是移动设备打开。browser代码在下面
        var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            //在微信中打开
			return "wx";
        }
        if (ua.match(/WeiBo/i) == "weibo") {
            //在新浪微博客户端打开
			return "wb";
        }
        if (ua.match(/QQ/i) == "qq") {
            //在QQ空间打开
			return "qq";
        }
        if (browser.versions.ios) {
            //是否在IOS浏览器打开
			return "ios";
        }
        if (browser.versions.android) {
            //是否在安卓浏览器打开
			return "android";
        }
    } else {
        //否则就是PC浏览器打开
		return "pc";
    }
}
function h5app_head_css(){
    var browser_type=get_browser_type();
    if(browser_type==="wx"){return "none";} //微信：无头
    if(browser_type==="wb"){return "none";}	//微博：无头
    if(browser_type==="qq"){return "none";}	//QQ：无头
    if(browser_type==="ios"){return "";}    //苹果：有头
    if(browser_type==="android"){return "";}//安卓：有头
    if(browser_type==="pc"){return "";}		//PC：有头
    return "";
}
//=========end 判断浏览器类型，决定是否需要显示h5app的头部==========