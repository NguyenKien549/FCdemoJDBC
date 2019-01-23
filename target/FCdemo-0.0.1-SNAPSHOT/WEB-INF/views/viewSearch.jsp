<jsp:include page="default/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<link href="<c:url value="/resources/css/otherpage.css"/>" rel="stylesheet"/>
	<link href="<c:url value="/resources/css/input_Table.css"/>" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="<c:url value="/resources/js/filterSearch.js" />"></script>
<%-- 		<script src="<c:url value="/resources/js/page.js" />"></script> --%>
<style type="text/css">

#pages{
margin:5px;
height:50px;
float:right;
line-height: 50px;
}
#pages li{
list-style-type:none;
display: inline-table;
}
#pages li button{
height: 30px;
width:80px;
}
#pages li input{
height: 25px;
width:80px;
text-align: center;
}
 #content #paging #rows li{
 list-style-type: none;
 }
 #pagingNumb{
 height: 50px;}
</style>
</head>
<body bgcolor="#B5B5B5">
<div id="content" align="center">

		<h2 align="center">My Employees</h2>
<label>Search by Name:</label>
<br>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">
<br>
<label>Search by Sex:</label>
<br>
<!-- <input type="text" id="myInput1" onkeyup="myFunction()" placeholder="Search for type.." title="Type in a name"> -->
<select id="myInput1" onchange="myFunction()">
					<c:forEach items="${listSex}" var="sex" >
						    <option  value="${sex}">${sex}</option>
					</c:forEach>
</select>
<!-- <br> -->
<!-- <label>Sum:</label> -->
<%-- <input type="text" value="${sum}"> --%>

<div id="paging">
	<div id="rows">
	<div id="data">
				<table id="myTable"> 
				     <thead> 
				      <tr> 
				       		<th>ID</th>
					  		<th>Name</th>
					  		<th>Age</th>
					  		<th>Sex</th>
					  		<th>KindID</th>
					  		<th width="50">Avatar</th>
				      </tr> 
				     </thead> 
				     <tbody id="bodyTable">
				      <c:forEach var="employee" items="${list}">
								<tr>
							<td>${employee.getID()} </td>
					  		<td>${employee.getName()}</td>
					  		<td>${employee.getAge()}</td>
					  		<td>${employee.getSex()}</td>
					  		<td>${employee.getKindID()}</td>
					  		<td><a href="${pageContext.request.contextPath}/Employee/getimage/${employee.getID()}" target="_blank">View</a></td>
								</tr>
							</c:forEach>
				    </tbody>
				    </table> 
			    
				    
		</div>
<!-- 		<div id="pagingNumb"> -->
<!-- 		<ul id="pages"> -->
<!-- 		    		<li >  -->
<!-- 		    			<label class="pageInfo">Page 1 of 6 </label>  -->
<!-- 		    		</li> -->
<!-- 		    		<li class="goPrevious">  -->
<!-- 		    			<button> << Previous </button>  -->
<!-- 		    		</li> -->
<!-- 		    		<li> -->
<!-- 		    			<input class="currentPage" type="text" id="currentPage" /> -->
<!-- 		    		</li> -->
<!-- 		    		<li class="goNext"><button>Next >> </button> </li> -->
		    	
<!-- 		    	</ul>	 -->
<!-- 		    	</div> -->
			
    </div>
</div>
</div>
	<jsp:include page="default/footer.jsp"></jsp:include>