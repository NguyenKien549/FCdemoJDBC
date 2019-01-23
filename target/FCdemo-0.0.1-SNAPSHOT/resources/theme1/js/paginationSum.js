(function($){
    		$.fn.pagination=function(options){
    			console.log("hello. I'm pagination");

    			var temp={
    				"table"			:"#myTable",
    				"pages"			:"#pages",
    				"numb"			:5,
    				"total"			:0,
    				"currentPage"	:1,
    				"btnPre"		:".goPre",
    				"btnNext"		:".goNext",
    				"txtCurrentPage":".currentPage",
    				"lbPageInfo"	:".pageInfo",
    				"data"			:"#data"
    				
    			};

    			options=$.extend(temp,options);
    			
    			
    			//khai bao bien
    			var table=$(options.table);
    			var pages=$(options.pages);
    			var btnPre=$(options.btnPre);
    			var btnNext=$(options.btnNext);
    			var txtCurrentPage 	= $(options.txtCurrentPage);
    			var lbpageInfo=$(options.lbPageInfo);
    			var dataPage=$(options.data);
    			
    			
    			init();


    			function init(){
    				$.ajax({
    					url:"http://localhost:8080/FCdemo/countSum",
    					type: "GET",
    					dataType: "json",
    					data:{
    						"items": options.numb,
    						"total": options.total
    					}
    				}).done(function(data){
    					console.log(options);
    					options.total=data;
    					pageInfo();
    					loadData(options.currentPage);
    				});

    				setCurrentPage(options.currentPage);

    				btnPre.on("click",function(e){
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
    						if(isNaN(currentPageValue) || currentPageValue <= 0 
    										|| currentPageValue > options.total){
    							alert("Gia tri nhap vao khong phu hop");
    						}else{
    							loadData(currentPageValue);
    							options.currentPage = currentPageValue;
    							pageInfo();
    						}
    					}
    					
    				});
    			}

    			//click btnPre
    			function goPrevious(){
    				console.log("goPrevious: " + options.currentPage);
    				if(options.currentPage!=1){
    					var p=options.currentPage-1;
    					loadData(p);
    					setCurrentPage(p);
    					options.currentPage=p;
    					pageInfo();
    				}
    			}

    			//click btnNext
    			function goNext(){
    				console.log("goNext: " + options.currentPage);
    				if(options.currentPage!=options.total){
    					var p=options.currentPage+1;
    					loadData(p);
    					setCurrentPage(p);
    					options.currentPage=p;
    					pageInfo();
    				}
    			}


    			function setCurrentPage(numbPage){
    				txtCurrentPage.val(numbPage);
    			}

    			function pageInfo(){
    				lbpageInfo.text("Page " + options.currentPage + " of " + options.total);
    			}

    			function loadData(page){
    				console.log(options);
    				$.ajax({
    					
    					url: "http://localhost:8080/FCdemo/loadSum",
    					type: "POST",
    					dataType:"json",
    					cache: false,
    					data: {
    						"items": options.numb,
    						"currentPage"	: page
    					}
    				}).done(function(data) {
    					console.log(data);
    					
    					if(data.length>0){
    						dataPage.empty();	
    						
    						var sum=0;
    						var temp="";
    						$.each(data,function(i, val){
    							
    							sum=sum + val.amount;
    							
    							var str ="<tr>"
    										+"<td align=\"center\">"+val.name+"</td>"
    										+"<td align=\"center\">"+val.age+"</td>"
    										+"<td align=\"center\">"+val.kindID+"</td>"
    										+"<td align=\"center\">"+val.amount+"</td>"
    										+"<td align=\"center\">"+val.description+"</td>"
    										+"</tr>";
    							temp=temp+str;
    						});
    						
    						$("#sum").text(sum+" $");
    						dataPage.append(temp);
    					}
    				})
    				.fail(function() {
    					console.log("error");
    				});
    				
    			}
    		}
    	})(jQuery);

$(document).ready(function(e) {
	 var obj = {};
	$("#myTable").pagination(obj);
});
