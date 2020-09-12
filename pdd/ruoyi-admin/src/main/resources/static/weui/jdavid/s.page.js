         // 获取分包查询的sql语句(for sqlserver) //提供给内部调用,包号"1、 2、 3、... "
		 //eg:
		 /*    select * from t_user where user like '%wang%' order by xm desc;
		     
			   var  s_tableName           ="t_user";
			   var  s_primayKey           ="userid"; 
			   var  s_where_not_with_where="user like '%wang%'";
               var  s_orderBy             ="order by xm";
			   var  s_pageNo              ="2";
			   var  s_pageSize            ="8";
			   var  s_desc                ="desc";
			   
			   var s_sql_fenbao=get_sql_fenBao_query_inner_sqlserver(
								s_tableName,
								s_primayKey, 
								s_where_not_with_where, 
								s_orderBy,
								s_pageNo, 
								s_pageSize, 
								s_desc);
		 
		*/
		 
        function get_sql_fenye_query_inner_sqlserver(
					s_tableName,
					s_primayKey, 
					s_where_not_with_where, 
					s_orderBy,
					s_pageNo, 
					s_pageSize, 
					s_desc,
					s_fieldlist
					)
        {
            // int num_cur= int.Parse(pageSize)*int.Parse(pageNo);

            var num_perior = parseInt(parseFloat(s_pageSize)) * (parseInt(parseFloat(s_pageNo)) - 1);
			var num_cur    = parseInt(parseFloat(s_pageSize)) * (parseInt(parseFloat(s_pageNo)));

            var a_s_desc = s_desc;
			
            if (a_s_desc=="")
            {
                a_s_desc = "asc";
            }
            // if (!isDesc)
            // {
            // s_desc = "asc";
            // }
			var the_fieldlist="*";
			
            if(s_fieldlist){
				
				the_fieldlist=s_fieldlist;
			}else{
				the_fieldlist="*";
				
			}
           
            var sql = 
			        
				"SELECT top " + s_pageSize + " "+the_fieldlist+"   from " + s_tableName
                   
				    + "  WHERE (" + s_where_not_with_where.replace("where", "") +

                    "   and " + s_primayKey + " not in( select top "
                    
					+ (num_perior + "")+" " + s_primayKey + " from "
					
                    + s_tableName

                    + " where " + s_where_not_with_where.replace("where", "")
                    
					+ " order by "+s_orderBy + " " + a_s_desc + ")  )  " +" order by "+ s_orderBy + " " + a_s_desc;
          //alert(sql);
		  return sql;
        }


       
		 
		function  get_page_count(s_count,s_size_per_page){
		   //alert(s_count);
		   var i_max=parseInt(parseFloat(s_count))/parseInt(parseFloat(s_size_per_page)) ;
           //alert("10 模 8 ="+10%8);
		   i_max=parseInt(i_max);
		  // alert(i_max);
		   var  yushu=parseInt(parseFloat(s_count)) % parseInt(parseFloat(s_size_per_page)) ;
		   //("yushu="+ yushu);
		   if(parseFloat(yushu)>0){
              i_max++;
		   }
		   return i_max;
		}
		 
        function get_s_html_down_mark_of_fenye(s_record_count,s_size_per_page){
		 //  1 2 3 4 5 6 7 8 9 .....22
		   var s_html="";
		   var s_count=s_record_count;
		   //alert(s_count);
		   var i_max=parseInt(parseFloat(s_count))/parseInt(parseFloat(s_size_per_page)) ;
           //alert("10 模 8 ="+10%8);
		   i_max=parseInt(i_max);
		   //alert(i_max);
		   var  yushu=parseInt(parseFloat(s_count)) % parseInt(parseFloat(s_size_per_page)) ;
		   ("yushu="+ yushu);
		   if(parseFloat(yushu)>0){
              i_max++;
		   }
		   //alert(i_max);
		   s_html+="<table height=20><tr>";
		   s_html+="<td>";
		   s_html+="〖共"+s_count+"条数据〗";
		   s_html+="〖第<span id='id_span_page_cur'>1</span>/"+i_max+"页〗";
           s_html+="</td>";
		   s_html+="<td>";
		   s_html+="〖<a href=\"javascript:goto_page('1')\">首页</a>.";
		   s_html+="<a href=\"javascript:goto_page_perior()\">前页</a>.";
		   s_html+="</td>";
		   s_html+="";
		   s_html+="";
		   for(var i=1;i<=i_max;i++){
			  
			   if(i>=10){
				   s_html+= "<td>...&nbsp;</td><td><a href=\"javascript:goto_page('"+s_count+"');\">"+ s_count + "</a></td>";
				   break;
			   } 
			   s_html+= "<td><a href=\"javascript:goto_page('"+i+"');\">"+ i + "</a></td>";
		   }
		   s_html+="<td>";
		   s_html+=".<a href=\"javascript:goto_page_next()\">后页</a>.";
		   s_html+="<a href=\"javascript:goto_page('"+i_max+"')\">尾页</a>〗";
		   s_html+="</td>";
		   
		   s_html+="<td>〖";
		   s_html+="<input name='name_input_pageno_input' type='text' "
		         +"style='font-size:12px;width:40px;height:12px;border:1px #cccccc solid;' id='id_input_pageno_input' />";
		   s_html+="<a href=\"javascript:goto_page_input()\">GO!</a>〗";
		   s_html+="</td>";
		   
		   s_html+="</tr></table>";
		   //alert(s_html);
		   
		   return s_html;
		}
		
		
		