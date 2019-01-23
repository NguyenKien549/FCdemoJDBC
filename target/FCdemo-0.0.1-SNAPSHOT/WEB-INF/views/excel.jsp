
<jsp:include page="default/header.jsp"></jsp:include>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div id="content" align="center">
	<hr>
	<h3>Employee Information</h3>

	<form:form method="POST" action="addExcelRes"
		enctype="multipart/form-data">


		<label>File Excel:</label>
		<input type="file" name="excel" accept=".xlsx, .xls" />
		<br>

		<input type="submit" value="Add" />
	</form:form>
	<hr>

	<div style="background: #972F2B; color: #FFF;">
		<c:out value="${message}" />
	</div>
	<!-- 	<h1 align="center">Employee Information is added:</h1>   -->

	<table
		class="table table-hover">


		<thead>
			<tr bgcolor="Yellow">
				<th>ID</th>
				<th>Name</th>
				<th>Age</th>
				<th>Sex</th>
				<th>KindID</th>
				<th width="50">Avatar</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employee" items="${list}">
				<tr>
					<td align="center">${employee.getID()}</td>
					<td align="center">${employee.getName()}</td>
					<td align="center">${employee.getAge()}</td>
					<td align="center">${employee.getSex()}</td>
					<td align="center">${employee.getKindID()}</td>
					<td align="center"><a href="getimage/${employee.getID()}"
						target="_blank">View</a></td>
					<td><img src="data:image/png;base64,${employee.getAvatar()}"
						height="50px" width="100px" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<script type="text/javascript">
	$('input[type=file]').change(function() {
		var filePath = $('#upload').val();
		$("#path").text(filePath)
	});
</script>
<jsp:include page="default/footer.jsp"></jsp:include>