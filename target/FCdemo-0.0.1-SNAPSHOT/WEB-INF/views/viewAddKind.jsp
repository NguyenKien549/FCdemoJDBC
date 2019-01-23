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
		<h3 align="center">Kind Information</h3>
			<form:form method="POST" action="addKindResult" modelAttribute="kind">
			<table>
			<tr>
				<td><form:label path="Name">Name:</form:label></td>
				<td><form:input path="Name"/></td>
			</tr>
			
			<tr>
				<td><form:label path="Description">Description:</form:label></td>
				<td><form:input path="Description"/></td>
			</tr>
			
			<tr>
				<td><form:label path="KindID">KindID:</form:label></td>
				<td><form:input path="KindID"/></td>
			</tr>
			
			</table>
			<input type="submit" value="Add"/>
			</form:form>
			<hr>
			
		
		  <table class="table table-hover">
		  <caption align="top">KIND INFORMATION</caption>
		  <thead>
		  	<tr bgcolor=#99FFFF>
		  		<th>Name</th>
		  		<th>Description</th>
		  		<th>KindID</th>
		  	</tr>
		  	</thead>
		  	<tbody>
		  	<tr>
		  		<td>${kind.getName()}</td>
		  		<td>${kind.getDescription()}</td>
		  		<td>${kind.getKindID()}</td>
		
		  	</tr>
		  	</tbody>
		  </table>
</div>

<jsp:include page="default/footer.jsp"></jsp:include>
