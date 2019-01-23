//JavaScript Document
(function($){
	$.fn.zPaging = function(options){
		console.log("Hi zPaging");	
		
		//=============================================
		//Cac gia mac cua options
		//=============================================
		var defaults = {
				"rows"				: "#rows",
				"pages"				: "#pages",
				"items"				: 7,
				"currentPage"		: 1,
				"total"				: 0,
				"btnPrevious"		: ".goPrevious",
				"btnNext"			: ".goNext",
				"txtCurrentPage"	: "#currentPage",
				"pageInfo"			: ".pageInfo",
				"data"				: "#data",
				
			};
		options = $.extend(defaults,options);
		
		//=============================================
		//Cac bien se su dung trong Plugin
		//=============================================
		var rows 			= $(options.rows);
		var pages 			= $(options.pages);
		var btnPrevious 	= $(options.btnPrevious);
		var btnNext 		= $(options.btnNext);
		var txtCurrentPage 	= $(options.txtCurrentPage);
		var lblPageInfo		= $(options.pageInfo);
		var body			= $(options.data);
		
		
		
		//=============================================
		//Khoi tao cac ham can thi khi Plugin duoc su dung
		//=============================================
		init();
		//=============================================
		//Ham khoi dong
		//=============================================
		function init(){
			//Lay tong so trang 
			$.ajax({
				url		: "http://localhost:8080/FCdemo/count",
				type	: "GET",
				dataType: "json",
				data:{
					total	:options.total,
					items	:options.items
				}
			}).done(function(data){
				options.total = data;
				console.log(options);
				pageInfo();
				loadData(options.currentPage);
			});
			
			//Gan su kien vao cho btnPrevious - btnNext - txtCurrentPage
			setCurrentPage(options.currentPage);
			
			btnPrevious.on("click",function(e){
				goPrevious();
				e.stopImmediatePropagation();
			});
			
			btnNext.on("click",function(e){
				goNext();
				e.stopImmediatePropagation();
			});
			
			txtCurrentPage.on("keyup",function(e){

				if(e.keyCode == 13){
					var currentPageValue = parseInt($(this).val());
					console.log(currentPageValue);	
					if(isNaN(currentPageValue) || currentPageValue <= 0 || currentPageValue > options.total){
						alert("Gia tri nhap vao khong phu hop");
					}else{
						loadData(currentPageValue);
						options.currentPage = currentPageValue;
						pageInfo();
					}
				}
				
			});
			
			
			
		}
		
		//=============================================
		//Ham xu ly khi nhan vao nut btnPrevious
		//=============================================
		function goPrevious(){
			console.log("goPrevious: " + options.currentPage);
			if(options.currentPage != 1){
				var p = options.currentPage - 1;
				loadData(p);	
				setCurrentPage(p);
				options.currentPage = p;
				pageInfo();
			}
		}
		
		//=============================================
		//Ham xu ly khi nhan vao nut btnNext
		//=============================================
		function goNext(){
			console.log("goNext: " + options.currentPage);
			if(options.currentPage != options.total){
				var p = options.currentPage + 1;
				loadData(p);	
				setCurrentPage(p);
				options.currentPage = p;
				pageInfo();
			}
		}
		
		//=============================================
		//Ham xu ly gan gia tri vao 
		//trong o textbox currentPage
		//=============================================
		function setCurrentPage(value){
			txtCurrentPage.val(value);
		}
		
		//=============================================
		//Ham hien thi thong tin phan trang
		//=============================================
		function pageInfo(){
			lblPageInfo.text("Page " + options.currentPage + " of " + options.total);
		}
		
		
		//=============================================
		//Ham load cac thong trong database va dua vao #row
		//=============================================
		function loadData(page){
			console.log("loadData");
			$.ajax({
				url: "http://localhost:8080/FCdemo/loadpage",
				type: "post",
				dataType: "json",
				cache: false,
				data: {
						"items"			: options.items,
						"currentPage"	: page
					}	
			}).done(function(data){
				console.log(data);	
				var html="<table id=\"myTable\"><thead><tr>"
		       		+"<th>ID</th>"
		       		+"<th>Name</th>"
		       		+"<th>Age</th>"
		       		+"<th>Sex</th>"
		       		+"<th>KindID</th>"
		       		+"<th>Avatar</th>"
		       		+"</tr></thead>"; //nhan dien string
		       	
		       	
				if(data.length > 0){
					body.empty();	
					
					var temp="";
					$.each(data,function(i, val){
						var str ="<tr>"
									+"<td>"+val.id+"</td>"
									+"<td>"+val.name+"</td>"
									+"<td>"+val.age+"</td>"
									+"<td>"+val.sex+"</td>"
									+"<td>"+val.kindID+"</td>"
									+"<td><a href=\"Employee/getimage/"+val.id+"\" target=\"_blank\">View</a></td>"
									+"</tr>";
					temp=temp+str;
							
					});
					body.append(html+"<tbody>"+temp+"</tbody>");
// 					
				}
			});
		}
		
	}	
})(jQuery);

$(document).ready(function(e) {
    var obj = {};
	$("#paging").zPaging(obj);
});

