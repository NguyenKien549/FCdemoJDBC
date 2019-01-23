//(function($) {
//	$.fn.pagination = function(options, check) {
//		if (!check) {
//			console.log("hello. I'm search");
//			var temp = {
//				"numb" : 5,// Number($(".showNumb").val()),
//				"total" : 0,
//				"currentPageSearch" : 1,
//				"btnPreSearch" : ".goPre",
//				"btnNextSearch" : ".goNext",
//				"txtCurrentPageSearch" : ".currentPage",
//				"lbPageInfoSearch" : ".pageInfo",
//				"data" : "#data",
//				"name" : "#myInput",
//				"type" : "#myInput1"
//			};
//
//			options = $.extend(temp, options);
//
//			// khai bao bien
//
//			var btnPre = $(options.btnPreSearch);
//			var btnNext = $(options.btnNextSearch);
//			var txtcurrentPageSearch = $(options.txtCurrentPageSearch);
//			var lbpageInfo = $(options.lbPageInfoSearch);
//			var dataPage = $(options.data);
//
//			init();
//
//			function init() {
//				$.ajax({
//					url : "http://localhost:8080/FCdemo/countSearch",
//					type : "GET",
//					dataType : "json",
//					cache : false,
//					data : {
//						"items" : options.numb,// $(options.numb).val(),
//						"total" : options.total,
//						"name" : $(options.name).val(),
//						"type" : $(options.type).val()
//					}
//				}).done(function(data) {
//					console.log("data:" + options.total);
//					options.total = data;
//					pageInfo();
//					loadDataSearch(options.currentPageSearch);
//				});
//
//				setcurrentPageSearch(options.currentPageSearch);
//
//				
//
//				txtcurrentPageSearch.on("keyup", function(e) {
//
//					if (e.keyCode == 13) {
//						var currentPageSearchValue = parseInt($(this).val());
//						console.log(currentPageSearchValue);
//						if (isNaN(currentPageSearchValue)
//								|| currentPageSearchValue <= 0
//								|| currentPageSearchValue > options.total) {
//							alert("Gia tri nhap vao khong phu hop");
//						} else {
//							loadDataSearch(currentPageSearchValue);
//							options.currentPageSearch = currentPageSearchValue;
//							pageInfo();
//						}
//					}
//
//				});
//			}
//
//			function setcurrentPageSearch(numbPage) {
//				txtcurrentPageSearch.val(numbPage);
//			}
//
//			function pageInfo() {
//				lbpageInfo.text("Page " + options.currentPageSearch + " of "
//						+ options.total);
//			}
//
//			function loadDataSearch(page) {
//				console.log("load trang search");
//				console.log(options);
//				$.ajax({
//
//					url : "http://localhost:8080/FCdemo/loadSearch",
//					type : "POST",
//					dataType : "json",
//					cache : false,
//					data : {
//						"items" : options.numb,// $(options.numb).val(),
//						"currentPageSearch" : page,
//						"total" : options.total,
//						"name" : $(options.name).val(),
//						"type" : $(options.type).val()
//					}
//				}).done(
//						function(data) {
//							console.log(data);
//							if (data != null) {
//								dataPage.empty();
//
//								var sum = 0;
//								var temp = "";
//								$.each(data, function(i, val) {
//
//									sum = sum + val.amount;
//
//									var str = "<tr>" + "<td align=\"center\">"
//											+ val.name + "</td>"
//											+ "<td align=\"center\">" + val.age
//											+ "</td>" + "<td align=\"center\">"
//											+ val.kindID + "</td>"
//											+ "<td align=\"center\">"
//											+ val.amount + "</td>"
//											+ "<td align=\"center\">"
//											+ val.description + "</td>"
//											+ "</tr>";
//									temp = temp + str;
//								});
//
//								$("#sum").text(sum + " $");
//								dataPage.append(temp);
//							} else
//								alert("k co du lieu");
//						}).fail(function() {
//					console.log("error");
//				});
//
//			}
//		} else {
//			console.log("hi list");
//
//			// Cac gia tri mac dinh cua option
//			var temp1 = {
//				"data" : "#data",
//				"pages" : "#pages",
//				"currentPage" : 3,
//				"numb" : 5, // Number($(".showNumb").val()),
//				"total" : 0,
//				"btnPrevious" : ".goPre",
//				"btnNext" : ".goNext",
//				"lbPageInfo" : ".pageInfo",
//				"txtCurrentPage" : "#currentPage"
//			};
//			options = $.extend(temp1, options);
//
//			// cac bien se sd trong pluggin
//			var bodyTable = $(options.data);
//			var pages = $(options.pages);
//			var btnPre = $(options.btnPrevious);
//			var btnNext = $(options.btnNext);
//			var txtCurr = $(options.txtCurrentPage);
//			var lbpageInfo = $(options.lbPageInfo);
//
//			// cac ham khoi tao khi load trang
//
//			init();
//
//			// ham khoi dong
//			function init() {
//				console.log(options);
//				$.ajax({
//					url : "http://localhost:8080/FCdemo/countSum",
//					type : "GET",
//					dataType : "json",
//					data : {
//						"items" : options.numb,// $(options.numb).val(),
//						"total" : options.total
//					},
//					error : function(e) {
//						console.log(e);
//					}
//				}).done(function(data) {
//
//					options.total = Number(data);
//					console.log("total:" + options.total);
//					// options.numb = Number($(".showNumb").val());
//					pageInfo();
//					console.log(options);
//					loadData(options.currentPage);
//				});
//
//				setCurrentPage(options.currentPage);
//
//				txtCurr.on("keyup", function(e) {
//
//					if (e.keyCode == 13) {
//						var currentPageValue = parseInt($(this).val());
//						console.log(currentPageValue);
//						if (isNaN(currentPageValue) || currentPageValue <= 0
//								|| currentPageValue > options.total) {
//							alert("Gia tri nhap vao khong phu hop");
//						} else {
//							loadData(currentPageValue);
//							options.currentPage = currentPageValue;
//							pageInfo();
//						}
//					}
//
//				});
//			}
//
//			function setCurrentPage(page) {
//				txtCurr.val(page);
//			}
//
//			function pageInfo() {
//				lbpageInfo.text("Page " + options.currentPage + " of "
//						+ options.total);
//			}
//
//			var ajax_sendding = false;
//			
//			
//			function loadData(page) {
//				console.log("load trang list");
//				console.log("numb:" + options.numb);
//				console.log("total:" + options.total);
//
//				// if (ajax_sendding == true){
//				// alert('Dang Load Ajax');
//				// return false;
//				// }
//				//				 
//				// ajax_sendding = true;
//				$.ajax({
//					url : "http://localhost:8080/FCdemo/loadSum",
//					type : "POST",
//					dataType : "json",
//					data : {
//						"items" : options.numb,// $(options.numb).val(),
//						"currentPage" : page,
//						"total" : options.total
//					},
//					error : function(e) {
//						console.log(e);
//					}
//				}).done(
//						function(data) {
//							console.log("success");
//							console.log(options);
//							if (data != null) {
//								bodyTable.empty();
//
//								var sum = 0;
//								var temp = "";
//								$.each(data, function(i, val) {
//
//									sum = sum + val.amount;
//
//									var str = "<tr>" + "<td align=\"center\">"
//											+ val.name + "</td>"
//											+ "<td align=\"center\">" + val.age
//											+ "</td>" + "<td align=\"center\">"
//											+ val.kindID + "</td>"
//											+ "<td align=\"center\">"
//											+ val.amount + "</td>"
//											+ "<td align=\"center\">"
//											+ val.description + "</td>"
//											+ "</tr>";
//									temp = temp + str;
//								});
//
//								$("#sum").text(sum + " $");
//								bodyTable.append(temp);
//
//							} else {
//								alert("No data");
//							}
//
//						}).fail(function() {
//					console.log("error");
//				});
//
//			}
//		}
//
//		function goPrevious(type) {
//			if (type) {
//				console.log("goPrevious: " + options.currentPage);
//				if (options.currentPage != 1) {
//					var p = options.currentPage - 1;
//					loadData(p);
//					setCurrentPage(p);
//					options.currentPage = p;
//					pageInfo();
//				}
//			} else {
//				console.log("goPrevious: " + options.currentPageSearch);
//
//				if (options.currentPageSearch != 1) {
//					var p = options.currentPageSearch - 1;
//					loadDataSearch(p);
//					setcurrentPageSearch(p);
//					options.currentPageSearch = p;
//					pageInfo();
//				}
//			}
//
//		}
//		
//
//		function goNext(type) {
//			if (type) {
//				console.log("go Next:" + options.currentPage + "	total:"
//						+ options.total);
//
//				if (options.currentPage != options.total) {
//					var page = options.currentPage + 1;
//					loadData(page);
//					setCurrentPage(page);
//					options.currentPage = page;
//					pageInfo();
//				}
//			} else {
//				// search
//				console.log("goNext: " + options.currentPageSearch);
//				console.log(options);
//				if (options.currentPageSearch != options.total) {
//					var p = options.currentPageSearch + 1;
//					loadDataSearch(p);
//					setcurrentPageSearch(p);
//					options.currentPageSearch = p;
//					pageInfo();
//				}
//			}
//
//		}
//
//		btnPre.on("click", function(e) {
//			goPrevious(check);
//			
//		});
//
//		btnNext.on("click", function(e) {
//			goNext(check);
//			
//		});
//	}
//})(jQuery);
