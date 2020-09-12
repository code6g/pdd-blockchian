// JScript 文件
/*
做中英转换的时候，要准确的获取参数并取出，所以做了一个简单的html中用js获取当取地址栏的一个Object。 
里面有三个方法： 
1、request.QueryString("参数")//获取指定参数，返回字符串; 
2、request.QueryStrings();//获取全部参数，并返回数组; 
3、request.setQuery("参数","参数的值");//如果当前地址栏有此参数，那么将更新此参数，否则返回一个新的地址栏参数字符串。 
例如： 
当前地址栏参数字符串为：?name=a&site=never_online 
alert(request.setQuery("name","blueDestiny")) 
如果地址栏参数中有"name"，那么返回?name=blueDestiny&site=never_online 
setQuery方法有自动追加参数的功能。如： 
当前地址栏参数字符串为：?site=never_online 
alert(request.setQuery("name","blueDestiny")) 
则返回?site=never_online&name=blueDestiny 
同理，如果地址栏没有参数，也会自动追加参数 
alert(request.setQuery("name","blueDestiny")) 
返回?name=blueDestiny 
[复制此代码]CODE:<SCRIPT LANGUAGE="JavaScript"> 

<!-- 
// author: never-online 
// web: never-online.net 

*/


var request = { 
    QueryString : function(val) { 
        var uri = window.location.search; 
        var re = new RegExp("" +val+ "\=([^\&\?]*)", "ig"); 
        return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); 
    }, 
    QueryStrings : function() { 
        var uri = window.location.search; 
        var re = /\w*\=([^\&\?]*)/ig; 
        var retval=[]; 
        while ((arr = re.exec(uri)) != null) 
        retval.push(arr[0]); 
        return retval; 
    }, 
    setQuery : function(val1, val2) { 
        var a = this.QueryStrings(); 
        var retval = ""; 
        var seted = false; 
        var re = new RegExp("^" +val1+ "\=([^\&\?]*)$", "ig"); 
        for(var i=0; i<a.length; i++) { 
                if (re.test(a[i])) { 
                        seted = true; 
                        a[i] = val1 +"="+ val2; 
                } 
        } 
        retval = a.join("&"); 
        return "?" +retval+ (seted ? "" : (retval ? "&" : "") +val1+ "=" +val2); 
    } 
} 

/*
alert(request.setQuery("e","b")) 
//--> 
</SCRIPT> 
*/
