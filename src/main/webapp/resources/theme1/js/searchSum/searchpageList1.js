(function($){

  $.fn.paging=function(options){
    var key=true;
    
    $("#search").click(function() {
      $("div#input").slideToggle();
      $("div#tong").slideToggle();
      if(key==true) key=false;
      else key=true;
      init();
    });
    $("#searchbutton").click(function(){
    	init();
    });
    $(".goPre").click(function(){
    	goPre();
    });
    $(".goNext").click(function(){
    	goNext();
    });
    $(".showNumb").change(function() {
		init();
    });
    
    $("#currentPage").on("keyup", function(e) {
    	if(key==false){
                if (e.keyCode == 13) {
                  var currentPageSearchValue = parseInt(txtCurrentPage.val());
                  console.log(currentPageSearchValue);
                  if (isNaN(currentPageSearchValue)
                      || currentPageSearchValue <= 0
                      || currentPageSearchValue > options.total) {
                    alert("Gia tri nhap vao khong phu hop");
                  } else {
                    loadDataSearch(currentPageSearchValue);
                    options.currentPage = currentPageSearchValue;
                    setInfo();
                  }
                }
    	}else{
                  if (e.keyCode == 13) {
                    var currentPageValue = parseInt(txtCurrentPage.val());
                    console.log("currentPageValue:"+currentPageValue);
                    if (isNaN(currentPageValue) || currentPageValue <= 0
                        || currentPageValue > options.total) {
                      alert("Gia tri nhap vao khong phu hop");
                    } else {
                      loadData(currentPageValue);
                      options.currentPage = currentPageValue;
                      setInfo();
                    }
                  }
        }
    });
    var temp={
        "numb"    					:$(".showNumb").val(),
        "total"   					:0,
        "currentPage"            	: 1,
        "btnPre"           			: ".goPre",
        "btnNext"          			: ".goNext",
        "txtCurrentPage"    		: ".currentPage",
        "lbPageInfo"       			: ".pageInfo",
        "data"                    	: "#data",
        "name"                   	: "#myInput",
        "type"                   	: "#myInput1"
    }
    options=$.extend(temp,options);
      var btnPre          = $(options.btnPre);
      var btnNext         = $(options.btnNext);
      var txtCurrentPage  = $(options.txtCurrentPage);
      var lbpageInfo      = $(options.lbPageInfo);
      var dataPage        = $(options.data);

      init();

      function init(){
    	  
        if(key==true){
        options.currentPage=1;
          $.ajax({
              url : "http://localhost:8080/FCdemo/countSum",
              type : "GET",
              dataType : "json",
              cache : false,
              data : {
                "items" : $(".showNumb").val(),// $(options.numb).val(),
                "total" : options.total
              }
          })
          .done(function(data) {
              console.log("data: "+data);
              options.total=data;
              loadData(options.currentPage);
              setInfo();
          })
          .fail(function() {
            console.log("error list");
          });
          
          
          }else{
            options.currentPage=1;
            options.total=0;
              $.ajax({
              url : "http://localhost:8080/FCdemo/countSearch",
              type : "GET",
              dataType : "json",
              cache : false,
              data : {
                  "items" : $(".showNumb").val(),// $(options.numb).val(),
                  "total" : options.total,
                  "name" : $(options.name).val(),
                  "type" : $(options.type).val()
              }
            }).done(function(data) {
              console.log("data:" + options.total);
              options.total = data;
              setInfo();
              loadDataSearch(options.currentPage);
            });
          }
        	
          setCurrentPage(options.currentPage);
          setInfo();
        }
          

      function goPre(){
         if(key==true){
           // btnPre.on("click", function(e) {
              console.log("goPrevious Sum: " + options.currentPage);
              if (options.currentPage != 1) {
                var p = options.currentPage - 1;
                loadData(p);
                setCurrentPage(p);
                options.currentPage = p;
                setInfo();
              }     
            //});
          }else{
            //btnPre.on("click", function(e) {
                console.log("goPrevious Search: " + options.currentPage);
                if (options.currentPage != 1) {
                  var p = options.currentPage - 1;
                  setCurrentPage(p);
                  options.currentPage = p;
                  loadDataSearch(p);
                  setInfo();
                }

            //});
          }
      }

    function goNext(){
        if (key==true) {
          console.log("go Next Sum:" + options.currentPage + "  total:" + options.total);
          if (options.currentPage != options.total) {
            var page = options.currentPage + 1;
            loadData(page);
            setCurrentPage(page);
            options.currentPage = page;
            setInfo();
          }
      } else {
        // search
        console.log("goNext Search: " + options.currentPage);
        console.log(options);
        if (options.currentPage != options.total) {
          var p = options.currentPage + 1;
          loadDataSearch(p);
          setCurrentPage(p);
          options.currentPage = p;
          setInfo();
        }
      }
    }
    function loadData(page){
        console.log("load trang list");
        console.log(options);
        $.ajax({
          url : "http://localhost:8080/FCdemo/loadSum",
          type : "GET",
          dataType : "json",
          data : {
            "items" : $(".showNumb").val(),
            "currentPage" : page,
            "total" : options.total
          },
          error : function(e) {
            console.log(e);
          }
        }).done(function(data) {
              console.log("success");
              if (data != null) {
            	  dataPage.empty();
            	var sum;
                var temp = "";
                $.each(data, function(i, val) {
                	if(i>0){
                		var str = "<tr>" + "<td align=\"center\">"
                        + val.name + "</td>"
                        + "<td align=\"center\">" + val.age
                        + "</td>" + "<td align=\"center\">"
                        + val.kindID + "</td>"
                        + "<td align=\"center\">"
                        + val.amount + "</td>"
                        + "<td align=\"center\">"
                        + val.description + "</td>"
                        + "</tr>";
                    temp = temp + str;
                	}else{
                		sum=Number(val);
                	}
                  
                });

                $("#sum").text(sum + " $");
                dataPage.append(temp);

              } else {
                alert("No data");
              }

            }).fail(function() {
          console.log("error");
        });

      }
    function loadDataSearch(page) {
		console.log("load trang search");
		console.log(options);
		$.ajax({

			url : "http://localhost:8080/FCdemo/loadSearch",
			type : "GET",
			dataType : "json",
			cache : false,
			data : {
				"items" : $(".showNumb").val(),// $(options.numb).val(),
				"currentPageSearch" : page,
				"total" : options.total,
				"name" : $(options.name).val(),
				"type" : $(options.type).val()
			}
		}).done(
				function(data) {
					console.log(data);
					if (data != null) {
						dataPage.empty();

						var sum = 0;
						var temp = "";
						$.each(data, function(i, val) {

							sum = sum + val.amount;

							var str = "<tr>" + "<td align=\"center\">"
									+ val.name + "</td>"
									+ "<td align=\"center\">" + val.age
									+ "</td>" + "<td align=\"center\">"
									+ val.kindID + "</td>"
									+ "<td align=\"center\">"
									+ val.amount + "</td>"
									+ "<td align=\"center\">"
									+ val.description + "</td>"
									+ "</tr>";
							temp = temp + str;
						});

						$("#sum").text(sum + " $");
						dataPage.append(temp);
					} else
						alert("k co du lieu");
				}).fail(function() {
			console.log("error");
		});

	}
    function setCurrentPage(page){
        txtCurrentPage.val(page);
      }

      function setInfo(){
        lbpageInfo.text("Page " + options.currentPage + " of "
            + options.total);
      }
}
})(jQuery);



