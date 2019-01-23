<jsp:include page="default/header.jsp"></jsp:include>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link href="<c:url value="/resources/css/input_Table.css"/>" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/filterDelete.js" />"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	
	<div id="content" align="center">
		<h3>Finance Information</h3>
	
<label>Search by Name:</label>
<br>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">

		<hr>
	<div id="data">
			
				 <table id="myTable"> 
				  <caption align="bottom">FINANCE INFORMATION</caption>
				  <thead  bgcolor=#99FFFF> 
					  	<tr>
					  		<th>ID</th>
					  		<th>Name</th>
					  		<th>Amount</th>
					  		<th>KindID</th>
<!-- 					  		<th><input type="checkbox" id="select_all"/></th> -->
					  		<th><button id="button">Delete</button></th>
					  	</tr>
				  	</thead> 
				  	<tbody id="bodyTable">
					  	<c:forEach var="fin" items="${list}">
					  	<tr>
					  		<td align="center">${fin.getID()} </td>
					  		<td align="center">${fin.getName()}</td>
					  		<td align="center">${fin.getAmount()}</td>
					  		<td align="center">${fin.getKindID()}</td>
					  		<td><input type="checkbox" name="delete" value="${fin.getID()}"></td>
			<%-- 		  		<td align="center"><a href="${pageContext.request.contextPath}/deleteFinanceResult/${fin.getID()}">Delete</a></td> --%>
					  	</tr>
					  	</c:forEach>
					  </tbody>
				  </table>
			 
	  </div>
	  <script type="text/javascript">
	  $(document).ready(function(){
			 $('#select_all').change(function() {
				    var checkboxes = $(this).closest('table').find(':checkbox'); /* co the tuy chinh theo block code khac nhau vi du thay form bang table */
				    checkboxes.prop('checked', $(this).is(':checked'));
				});
			 var deleted =  new Array();
				$("#button").click(function(){
					
					$.each($("input[name='delete']:checked"), function(){      
			                deleted.push($(this).val());
			            });
					loadData(deleted);
					deleted.splice(0,deleted.length);
			        });
				
				function loadData(arr) {
					console.log(arr);
					console.log("vao ajax");
					
					$.ajax({
						url: "http://localhost:8080/FCdemo/deleteFinanceAjax",
						type: "get",
						dataType: "json",
						data: {
							id	: arr 
							},
						error: function(e) {
						    console.log(e);
						  }
						//contentType: "application/json"
					}).done(function(data) {
						console.log("success");
						console.log(data);
						if(data.length>0){
							$("#bodyTable").empty();	

							var temp="";
							$.each(data,function(i, val){
								var str ="<tr>"
											+"<td align=\"center\">"+val.id+"</td>"
											+"<td align=\"center\">"+val.name+"</td>"
											+"<td align=\"center\">"+val.amount+"</td>"
											+"<td align=\"center\">"+val.kindID+"</td>"
											+"<td><input type=\"checkbox\" name=\"delete\" value="+val.id+"></td>"
											+"</tr>";
							temp=temp+str;
									
							});
							$("#bodyTable").append(temp);
						}

					}).fail(function() {
						console.log("error");
						alert("chọn mục để xóa...");
						console.log(deleted);
					});
				}
			 
	  });
	  </script>
</div>
<jsp:include page="default/footer.jsp"></jsp:include>
