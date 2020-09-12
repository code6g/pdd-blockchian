<%@ WebHandler Language="C#" Class="detail" %>

using System;
using System.Web; 
using System.Data; 
using Newtonsoft.Json;
using Newtonsoft.Json.Linq.JsonPath;
using Newtonsoft.Json.Linq;

public class detail : IHttpHandler
{
    static string tablename = "shop_cart";
    static string pk = "gwid";
    /*
     http头：
            Authentication: appid|timestamp|accessid|userid
           "appid":"001001001",
           "timestamp":"201802021212324",
           "accessid":"asd001",
           "userid":"001001"
      input:
      {
           "flist":"*",
           "xxid" :"3001001"
       }
       return:
       {
           "code":"200",
           "msg":"查询成功",
           "data":[
              {
               "hyid" :"1",
               "hymc" :"1",
               "sjhm" :"1"
               }
           ]
       }
       */

    public void ProcessRequest (HttpContext context) {
        
       
        string code="200";
        string msg="操作成功";
        string s_res_data = "";
        //context.Request.setCharacterEncoding("UTF-8");
        //response.setContentType("application/json;charset=UTF-8");
        try {
            if(!resful_util.check_head_authentication(context.Request)){
                code="1008";
                msg="操作不成功:无操作权限";
            }else{
                string s_json=JsonOper.get_request_json_str(context);  //获取传入的json字符串
                JObject obj_all=(JObject)JsonConvert.DeserializeObject(s_json); 
              
              
             
                string s_flist = obj_all["flist"].ToString(); 
                string s_xxid = obj_all["xxid"].ToString();

                string sql_query = "select " + s_flist + " from " + tablename + " where " + pk + "='" + s_xxid + "'";
                DataTable dt = dao_sqlserver.query_sql_and_return_dataTable(sql_query); 
                s_res_data = JsonOper.get_s_json_data_by_datatble(dt);
                
            }
        }catch (Exception e) {
            //e.printStackTrace();
             code="1014";
             msg="操作不成功:数据格式不为json:"+e.ToString();
        }

        context.Response.ContentType = "application/json;charset=utf-8";
        context.Response.Write(resful_util.get_s_json_result(code, msg, s_res_data)); 
         
  
        // context.Response.AddHeader("Access-Control-Allow-Origin", "*");
        // context.Response.AppendHeader("Content-type", "application/json"); 
        //context.Response.AddHeader("Access-Control-Allow-Headers", "X-Requested-With");
        //context.Response.AddHeader ("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        //context.Response.AddHeader ("X-Powered-By", " 3.2.1");
        //context.Response.AddHeader("Content-Type", "application/json;charset=utf-8"); 
        
    }  
    public bool IsReusable
    {
        get {
            return false;
        }
    }

} 