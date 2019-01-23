<jsp:include page="default/header.jsp"></jsp:include>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<div id="content" align="center">
		<h3>Kind Information</h3>
	
		<form:form method="POST" action="updateKindResult" modelAttribute="kind">
		<table>
		<tr>
			<td><form:label path="ID">ID:</form:label></td>
			<td><input name="ID" required 
			pattern="(?<=\s|^)\d+(?=\s|$)"  title="ID la so nguyen"/></td>
		</tr>
		
		<tr>
			<td><form:label path="Name">Name:</form:label></td>
			<td><input name="Name" required /></td>
		</tr>
		
		<tr>
			<td><form:label path="Description">Description:</form:label></td>
			<td><input name="Description" required /></td>
		</tr>
		
		<tr>
			<td><form:label path="KindID">KindID:</form:label></td>
<%-- 					<td><form:select  path="KindID"> --%>
<%-- 						    <form:option value="NONE"> --SELECT--</form:option> --%>
<%-- 						    <form:options items="${listKind}"></form:options> --%>
<%-- 				  	</form:select></td> --%>
			<td><input name="KindID" required pattern="[A-Za-z]{1,10}" title="KindID gom chu hoa hoac chu thuong va nho hon 10 ki tu"/></td>
		</tr>
<!-- 		<tr> -->
<%-- 			<td><form:label path="Avatar">Avatar:</form:label></td> --%>
<%-- 			<td><form:input path="Avatar"/></td> --%>
			
<!-- 		</tr> -->
		
		</table>
		<input type="submit" value="Update"/>
		</form:form>
		<hr>
		<div style="background:#972F2B;color: #FFF;"><c:out value="${message}"/></div>  
	<!-- 	<h1 align="center">Employee Information is added:</h1>   -->
	
	  <table class="table table-hover">
	  <caption align="bottom">EMPLOYEE INFORMATION</caption>
	  <thead>
	  	<tr bgcolor=#99FFFF>
	  		<th>ID</th>
	  		<th>Name</th>
	  		<th>Description</th>
	  		<th>KindID</th>
	  	</tr>
	  	</thead>
	  	<tbody>
	  	<tr>
	  		<td align="center">${kind.getID()} </td>
	  		<td align="center">${kind.getName()}</td>
	  		<td align="center">${kind.getDescription()}</td>
	  		<td align="center">${kind.getKindID()}</td>
	  	</tr>
	  	</tbody>
	  </table>
</div>

<jsp:include page="default/footer.jsp"></jsp:include>
	