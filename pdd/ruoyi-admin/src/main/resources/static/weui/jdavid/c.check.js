// email check
function   isMail(name)
{
   if( ! isEnglish(name))
   {
      return      false;
   }
   i      =      name.indexOf("@");
   j      =      name.lastIndexOf("@");
   if(i      ==      - 1)
   {
      return      false;
   }
   if(i      !=      j)
   {
      return      false;
   }
   if(i      ==      name .length)
   {
      return      false;
   }
   return      true;
}

// Ӣ��ֵ���
function   isEnglish(name)
{
   if(name.length      ==      0)
   {
      return      false;
   }
   for(i      =      0;      i      <      name.length;
   i ++ )
   {
      if(name.charCodeAt(i)      >      128)
      {
         return      false;
      }
   }
   return      true;
}

function isDate(dateval)
{
   var arr = new Array();

   if(dateval.indexOf("-") != - 1)
   {
      arr = dateval.toString().split("-");
   }
   else if(dateval.indexOf("/") != - 1)
   {
      arr = dateval.toString().split("/");
   }
   else
   {
      return false;
   }

   // yyyy - mm - dd || yyyy / mm / dd
   if(arr[0].length == 4)
   {
      var date = new Date(arr[0], arr[1] - 1, arr[2]);
      if(date.getFullYear() == arr[0] && date.getMonth() == arr[1] - 1 && date.getDate() == arr[2])
      {
         return true;
      }
   }
   // dd - mm - yyyy || dd / mm / yyyy
   if(arr[2].length == 4)
   {
      var date = new Date(arr[2], arr[1] - 1, arr[0]);
      if(date.getFullYear() == arr[2] && date.getMonth() == arr[1] - 1 && date.getDate() == arr[0])
      {
         return true;
      }
   }
   // mm - dd - yyyy || mm / dd / yyyy
   if(arr[2].length == 4)
   {
      var date = new Date(arr[2], arr[0] - 1, arr[1]);
      if(date.getFullYear() == arr[2] && date.getMonth() == arr[0] - 1 && date.getDate() == arr[1])
      {
         return true;
      }
   }

   return false;
}

// ������ : jiarry@hotmail.com
// ���� : ����Ƿ�Ϊ�Ǹ�����
// ʾ�� : isDigit(String, allowNull)
// ������� : ��Ҫ���ı���������
// ������� : true�������Ϣ
// b - 1 isDigit(Object, allowNull)
function isDigit_with_hint(id, caption)
{
   // alert(69);
   var obj = document.getElementById(id);
   var allowNull = false;
   obj.value = trim(obj.value);
   var slen = obj.value.length;
   // getLength(obj.value);
   if(slen == 0)
   {
      if( ! allowNull)
      {
         return showMsg(caption + ": must input a mumber!", obj);
      }
      return true;
   }
   for (var i = 0; i < slen; i ++ )
   {
      var cc = obj.value.charAt(i);
      if(cc < "0" || cc > "9")
      {
         return showMsg(caption + ": must input a mumber!", obj);
      }
   }
   return true;
}

function isDigit(id)
{
   // alert(69);
   var obj = document.getElementById(id);
   var allowNull = false;
   obj.value = trim(obj.value);
   var slen = obj.value.length;
   // getLength(obj.value);
   if(slen == 0)
   {
      if( ! allowNull)
      {
         return false;
      }
      return true;
   }
   for (var i = 0; i < slen; i ++ )
   {
      var cc = obj.value.charAt(i);
      if(cc < "0" || cc > "9")
      {
         return false;
      }
   }
   return true;
}
// ������ : jiarry@hotmail.com
// ���� : ȥ���ַ���ǰ��Ŀո񲢷���
// ������� : inputStr(��������ַ���)
// ������� : inputStr(�������ַ���)
// c - 2 trim(String)
function trim(inputStr)
{
   var result = inputStr;
   while (result.substring(0, 1) == " ")
   {
      result = result.substring(1, result.length);
   }
   while (result.substring(result.length - 1, result.length) == " ")
   {
      result = result.substring(0, result.length - 1);
   }
   return result;
}

// ������ : jiarry@hotmail.com
// �����˺��ֵĳ����ж�
// c - 1 getLength(String)
function getLength(str)
{
   var templen = str.length;
   if(navigator.appName == 'Netscape') return templen;
   for(var i = 0; i < str.length; i ++ )
   {
      var rstr = escape(str.substring(i, i + 1));
      if (rstr.substring(0, 2) == "%u")
      {
         templen ++ ;
      }
   }
   return templen;
}
// ������ : jiarry@hotmail.com
// ���� : ��ʾ��ʾ��ϢMsg, ��꽹������Obj��, ����false
//     ��Ҫ���ڼ���Ҫ�ֶ��Ƿ���ȷ
// ʾ�� : showMsg("�û�������Ϊ��.", myform.username)
// ������� : Msg(��ʾ��Ϣ) Obj(��꽹�����)
// ������� : false
// b - 18 showMsg(String, Object)
function showMsg(Msg, Obj)
{
   alert( Msg );
   Obj.focus();
   return true;
}

function is_null_with_caption(id, caption)
{
   var obj = document.getElementById(id);
   var allowNull = false;
   obj.value = trim(obj.value);
   var slen = obj.value.length;
   // getLength(obj.value);
   if(slen == 0)
   {
      return showMsg(caption + ": not allow null!", obj);
   }
   return false;
}

// ������ : jiarry@hotmail.com
// �ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
// b - 13 isMoney(Object, allowNull)
function isMoney_with_caption(id, caption)
{
   var obj = document.getElementById(id);
   var allowNull = false;
   obj.value = trim(obj.value);
   slen = obj.value.length;
   // getLength(obj.value);
   if(slen == 0)
   {
      if( ! allowNull) return showMsg(caption + ": must input the correct money!", obj);
      return true;
   }

   if (ifMoney(obj.value, false))
   {
      return true;
   }
   else
   {
      return showMsg(caption + ": must input the correct money!", obj);
   }
   return true;
}

// ������ : jiarry@hotmail.com
// �ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
// a - 12 ifMoney(String)
function ifMoney(str, allowNull)
{
   if (str.length == 0) return allowNull;
   if ( ( pos = str.indexOf( "." ) ) != - 1 )
   {
      if (str.length == 1)
      return false;
      if ( ( pos = str.indexOf(".", pos + 1) )  != - 1 )
      return false;
   }

   for ( var i = 0 ; i < str.length; i ++ )
   {
      if (( str.charAt(i) < "0" || str.charAt(i) > "9" ) && (str.charAt(i) != "."))
      return false;
   }
   return true;
}
function alertt(){
  alert("sss");
}


/*
	���Զ��κ��ַ���ʹ����������ˣ�
	var str="test����";
	str.length==6;
	str.length2()==8;
    ע�⣺ʹ�� length2() ʱ��Ҫ©�����ţ���Ϊ����һ������ 
*/
String.prototype.length2 = function() {
	var cArr = this.match(/[^\x00-\xff]/ig);
	return this.length + (cArr == null ? 0 : cArr.length);
}



function check_length(s,len,hint){
  //alert("check_length");
  //if(!dvd_get_val(id)){alert("c.check.js..check_length():"+id+"������!");return true;}
  var len_input=s.length2();
  //alert(len_input);
  if(parseInt(len_input,10)>parseInt(len,10)){
     alert(hint+"����ֵ����������Ӧ��С��"+len);
     return false;
  }
  return true;

}

function check_null(id,fieldname){
   if(!document.getElementById(id)){
      alert(id+'is null');

      return false;
   }
    if(document.getElementById(id)==null){
        alert(id+'is null');
        return false;
    }
  var s=Trim(document.getElementById(id).value);
  var len_input=s.length2();
  //alert(len_input);
  if(parseInt(len_input,10)==parseInt(0,10)){
     alert(fieldname+"����Ϊ��!");
     return false;
  }
  return true;

}