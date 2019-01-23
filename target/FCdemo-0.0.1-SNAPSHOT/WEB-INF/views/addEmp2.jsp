<%-- <%@ include file = "header.jsp" %> --%>
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

			<button id="change">Add Excel File</button>
			<div id="addExcel">
				<h3 align="center">Add File Excel</h3>
				<br>
				<form:form method="POST" action="addExcelRes"
					enctype="multipart/form-data">
					<label>File Excel:</label>
					<input type="file" name="excel" accept=".xlsx, .xls" />
					<br>

					<input type="submit" value="Add" />
				</form:form>
			</div>

			<div id="input">
				<h3>Employee Information</h3>

				<form:form method="POST" action="addEmpResult"
					enctype="multipart/form-data">
			<table>


				<tr>
					<td><label>Name:</label></td>
					<td><input name="Name" placeholder="Input name"
						pattern="[A-Za-z\s]{1,50}" /></td>
				</tr>

				<tr>
					<td><label>Age:</label></td>
					<td><input name="Age" pattern="\d{1,2}"
						title="Tuổi không hợp lệ!!!" /></td>
				</tr>

				<tr>
					<td><label>Sex:</label></td>
					<td><input name="Sex" type="radio" value="Male"
						checked="checked" />Male <input name="Sex" type="radio"
						value="Female" />Female <input name="Sex" type="radio"
						value="Other" />Other</td>
				</tr>

				<tr>
					<td><label>KindID:</label></td>
					<td><select name="KindID">
							<c:forEach items="${listKind}" var="kind">
								<option value="${kind}">${kind}</option>
							</c:forEach>
					</select></td>
					<%-- 			<td><form:input path="KindID"/></td> --%>
				</tr>
				<tr>
					<td><label>Avatar:</label></td>
					<td><input type="file" name="Avatar" /></td>

				</tr>

			</table>

			<input type="submit" value="Add" />
				</form:form>
			</div>
			<div style="background: #972F2B; color: #FFF;">
				<c:out value="${message}" />
			</div>
			<!-- 	<h1 align="center">Employee Information is added:</h1>   -->
			<div id="infor">
				<label>${done}</label> <br> <label>${fail}</label> <br> <label>${time}</label>
			</div>
			<button id="doneEmp">Done</button>
			<button id="failEmp">Fail</button>

			<div id="done">

				<table class="table table-hover">
					<caption align="top">INSERT DONE</caption>
					<thead>
						<tr bgcolor=#99FFFF>

							<th>Name</th>
							<th>Age</th>
							<th>Sex</th>
							<th>KindID</th>
							<th width="50">Avatar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${listDone}">
							<tr>

								<td>${employee.getName()}</td>
								<td>${employee.getAge()}</td>
								<td>${employee.getSex()}</td>
								<td>${employee.getKindID()}</td>
								<td><img
									src="data:image/png;base64,${employee.getAvatar()}"
									height="50px" width="100px" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="fail">

				<table class="table table-hover">
					<caption align="top">INSERT FAIL</caption>
					<thead>
						<tr bgcolor=#99FFFF>

							<th>Name</th>
							<th>Age</th>
							<th>Sex</th>
							<th>KindID</th>
							<th width="50">Avatar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="employee" items="${listFail}">
							<tr>

								<td>${employee.getName()}</td>
								<td>${employee.getAge()}</td>
								<td>${employee.getSex()}</td>
								<td>${employee.getKindID()}</td>
								<td><img
									src="data:image/png;base64,${employee.getAvatar()}"
									height="50px" width="100px" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<script type="text/javascript">
				$("div#addExcel").hide();
				$("div#done").hide();
				$("div#fail").hide();

				$("#change").click(function() {
					$("div#addExcel").slideToggle();
					$("div#input").slideToggle();
				});

				$("#doneEmp").click(function() {
					$("div#done").show();
					$("div#fail").hide();
				});

				$("#failEmp").click(function() {
					$("div#fail").show();
					$("div#done").hide();
				});
			</script>


</div>




<jsp:include page="default/footer.jsp"></jsp:include>