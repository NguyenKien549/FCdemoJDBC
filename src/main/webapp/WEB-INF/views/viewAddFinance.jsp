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
<h2 align="center">Finance Information</h2>
	<form:form method="POST" action="addFinanceResult" modelAttribute="finance">
	<table>
	
	<tr>
		<td><label>Name:</label></td>
		<td><input name="Name" required /></td>
	</tr>
	
	<tr>
		<td><label>Amount:</label></td>
		<td><input name="Amount" required pattern="^[1-9]\d*(\.\d+)?$" title="Amount must be a number!!!"/></td>
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
	<input type="submit" value="Add"/>
	<div style="background:#972F2B;color: #FFF;"><c:out value="${message}"/></div>  
	</form:form>
	
  <table class="table table-hover">
  <caption align="left">FINANCE INFORMATION</caption>
  <thead>
  	<tr bgcolor=#99FFFF>
  		<th>Name</th>
  		<th>Amount</th>
  		<th>KindID</th>
  		
  	</tr>
  	</thead>
  	<tbody>
  	<tr>
  		<td>${finance.getName()}</td>
  		<td>${finance.getAmount()}</td>
  		<td>${finance.getKindID()}</td>

  	</tr>
  	</tbody>
  </table>
		
</div>

<jsp:include page="default/footer.jsp"></jsp:include>