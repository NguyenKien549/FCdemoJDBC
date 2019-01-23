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


		<h3>Employee Information</h3>
	
		<form:form method="POST" action="updateEmpResult" enctype="multipart/form-data">
		<table>
		<tr>
			<td><label >ID:</label></td>
			<td><input name="ID"  required title="ID la so nguyen"/></td>
		</tr>
		
		<tr>
			<td><label>Name:</label></td>
			<td><input name="Name" required/></td>
		</tr>
		
		<tr>
			<td><label >Age:</label></td>
			<td><input name="Age" required pattern="\d{1,2}" title="tuoi la so nguyen"/></td>
		</tr>
		
		<tr>
			<td><label>Sex:</label></td>
			<td>
				<input name="Sex" type="radio" value="Male" checked />Male
				<input name="Sex" type="radio" value="Female" />Female
				<input name="Sex" type="radio" value="Other"  />Other
			</td>
		</tr>
		
		<tr>
			<td><label >KindID:</label></td>
					<td><select  name="KindID">
					<c:forEach items="${listKind}" var="kind" >
						    <option value="${kind}">${kind}</option>
					</c:forEach>
				  	</select></td>
<%-- 			<td><form:input path="KindID"/></td> --%>
		</tr>
		<tr>
			<td><label>Avatar:</label></td>
			<td><input name="Avatar" type="file" required/></td>
			
		</tr>
		
		</table>
		<input type="submit" value="Update"/>
		</form:form>
		<hr>
		<div style="background:#972F2B;color: #FFF;"><c:out value="${message}"/></div>  
	
	  <table class="table table-hover">
	  <caption align="top">EMPLOYEE INFORMATION</caption>
	  <thead>
	  	<tr bgcolor=#99FFFF>
	  		<th>ID</th>
	  		<th>Name</th>
	  		<th>Age</th>
	  		<th>Sex</th>
	  		<th>KindID</th>
	  		<th>Avatar</th>
	  	</tr>
	  	</thead>
	  	<tbody>
	  	<c:forEach var="employee" items="${list}">
	  	<tr>
	  		<td>${employee.getID()} </td>
	  		<td>${employee.getName()}</td>
	  		<td>${employee.getAge()}</td>
	  		<td>${employee.getSex()}</td>
	  		<td>${employee.getKindID()}</td>
	  		<td><a href="getimage/${employee.getID()}" target="_blank">View</a></td>
	  	</tr>
	  	</c:forEach>
	  	</tbody>
	  </table>
	 
</div>

	<jsp:include page="default/footer.jsp"></jsp:include>
	