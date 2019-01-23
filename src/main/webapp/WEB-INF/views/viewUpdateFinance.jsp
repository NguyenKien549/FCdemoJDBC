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
		<h3>Finance Information</h3>
	
		<form:form method="POST" action="updateFinanceResult" modelAttribute="finance">
		<table>
		<tr>
			<td><form:label path="ID">ID:</form:label></td>
			<td><input name="ID" required 
			pattern="(?<=\s|^)\d+(?=\s|$)" title="ID la so nguyen"
			/></td>
		</tr>
		
		<tr>
			<td><form:label path="Name">Name:</form:label></td>
			<td><input name="Name" required   /></td>
<!-- 			 pattern="{1,15}" -->
<!-- 			 title="Tên phải là chữ,không có số và nhỏ hơn 15 kí tự!!" -->
			 
		</tr>
		
		<tr>
			<td><form:label path="Amount">Amount:</form:label></td>
			<td><input name="Amount" required pattern="^[1-9]\d*(\.\d+)?$" 
			title="Amount must be a number!!!"/></td>
		</tr>
		
		<tr>
			<td><label >KindID:</label></td>
					<td><form:select  path="KindID">
					<c:forEach items="${listKind}" var="kind" >
						    <form:option value="${kind}">${kind}</form:option>
					</c:forEach>
				  	</form:select></td>
		</tr>
		</table>
		
		<input type="submit" value="Update"/>
		</form:form>
		<hr>
		<div style="background:#972F2B;color: #FFF;"><c:out value="${message}"/></div>  
	<!-- 	<h1 align="center">Employee Information is added:</h1>   -->
	
	  <table class="table table-hover">
	  <caption align="bottom">FINANCE INFORMATION</caption>
	  	<tr bgcolor=#99FFFF>
	  		<th>ID</th>
	  		<th>Name</th>
	  		<th>Amount</th>
	  		<th>KindID</th>
<!-- 	  		<th>Avatar</th> -->
	  	</tr>
	  	<c:forEach items="${list}" var="finance">
	  	<tr>
	  		<td>${finance.getID()} </td>
	  		<td>${finance.getName()}</td>
	  		<td>${finance.getAmount()}</td>
	  		<td>${finance.getKindID()}</td>
<%-- 	  		<td align="center">${employee.getAvatar()}</td> --%>
	  	</tr>
	  	</c:forEach>
	  </table>
</div>

	<jsp:include page="default/footer.jsp"></jsp:include>
	