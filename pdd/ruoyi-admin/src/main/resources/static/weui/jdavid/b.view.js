//用户控制用户界面显示的js代码


function set_table_style(id_table){
	     //$("a").css("color","#1A7EB5");
		
		 //$("#"+id_table).css("border","1px #B6CBFE double");
		  //$("#"+id_table).css("border-left","1px #EFEFEF double");
		  //$("#"+id_table).css("border-right","1px #EFEFEF double");
		  //$("#"+id_table).css("border-bottom","2px #EFEFEF  double");
		 
		 
		 //$("#"+id_table+">thead>tr>td").css("border-bottom","1px gray dashed");
		// $("#"+id_table+">thead>tr>td").css("border-top","2px #EFEFEF  double");
		 //$("#"+id_table+">thead>tr>td").css("border-left","1px gray  solid");
		 //$("#"+id_table+">thead>tr>td").css("border-right","0px white double");
		 
		// $("#"+id_table+">thead>tr>td").css("height","30px");
		// $("#"+id_table+">thead>tr>td").css("background-color","#5DB7E8");
		// $("#"+id_table+">thead>tr>td").css("color","#D6EDF9");
		// $("#"+id_table+">thead>tr>td").css("font-weight","bold");
		// $("#"+id_table+">thead>tr>td").css("font-size","12px");
		// $("#"+id_table+">thead>tr>td").css("font-family","Arial, Helvetica, sans-serif");
		
		// $("#"+id_table+">thead>tr>td").css("text-align","center");
		 
		 //$("#"+id_table+">thead>tr>td").css("border-right","1px white solid");
		 //$("#"+id_table+">thead>tr>td").css("border-bottom","1px #B6CBFE solid");
		 
		 // $("#"+id_table+">tbody>tr").css("color","#1A7EB5");
		 
		 
		 // $("#"+id_table+">tbody>tr:odd").addClass("odd");
		 //$("#"+id_table+">tbody>tr:odd").css("background-color","#F9F7F4");
		 
		// $("#"+id_table+">tbody>tr:even").addClass("even");
		 //$("#"+id_table+">tbody>tr:even").css("background-color","white");	 
		 
		// $("#"+id_table+">tbody>tr>td").css("height","28px");
        // $("#"+id_table+">tbody>tr>td").css("text-align","center");
        // $("#"+id_table+">tbody>tr>td").css("font-family","Arial");
        // $("#"+id_table+">tbody>tr>td").css("font-size","12px");
         //$("#"+id_table+">tbody>tr>td").css("border-bottom","1px gray dashed");
		
		 //$("#"+id_table+">tbody>tr>td").css("border-right","1px #B6CBFE solid");
		 
		  // $("#"+id_table+">tbody>tr>td").css("border-top","1px #EFEFEF solid");

			$("#"+id_table+">tbody>tr:even>td").addClass("qry_td2");   
			$("#"+id_table+">tbody>tr:odd>td").addClass("qry_td1");   
			
			$("#"+id_table+">tbody>tr").hover( 
				  function(){
					// $(this).css("font-weight","bold");
					 //$(this).css("color","#256EB1");
					 $(this).children().css("color","brown");
					 //$(this).children("a").css("color","red");
					 // $("#"+id_table+">tbody>tr>td").css("color","red");
					 //$(this).css("background-color","#E2EECA");
					  
					  
					  //$(this).children("td").css("background-color","#E8FFE8"); 
					  //$(this).children("td").css("background-color","#FFF1E8"); 
					   //$(this).children("td").css("background-color","#FFFAF7");
					   $(this).children("td").css("background-color","#FFF8E3"); 
				  }, 
				  function(){
					 //$(this).css("font-weight","normal");
					 //$("#"+id_table+">tbody>tr>td").css("color","");
					 //$(this).css("color","green");
					 $(this).children().css("color","");
					 //$(this).children("a").css("color","");
					 //$(this).css("background-color","");
					  $(this).children("td").css("background-color",""); 
				  } 
			);
}



 
    