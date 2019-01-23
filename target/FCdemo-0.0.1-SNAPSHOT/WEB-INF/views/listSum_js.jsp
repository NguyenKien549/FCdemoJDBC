<jsp:include page="default/header.jsp"></jsp:include>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<link href="<c:url value="/resources/css/otherpage.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/filter1.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/sort.js" />"></script>

<script src="<c:url value="/resources/js/searchSum/managerSearch.js" />"></script>
<script
	src="<c:url value="/resources/js/searchSum/searchpageList1.js" />"></script>
<style>
#content .sum {
	font-size: 40px;
}

#sum {
	height: 50px;
	color: red;
	line-height: 50px;
	font-family: serif;
	font-size: 40px;
	text-align: center;
}
</style>
</head>
<body bgcolor="#B5B5B5">

	<div id="content">

		<h2 align="center">My Employees</h2>
		<%-- <form  action="${pageContext.request.contextPath}/searchAll" method="POST"> --%>
		<input type="submit" id="search" value="search" />
		<%-- 	</form> --%>
		<%-- 	<form  action="${pageContext.request.contextPath}/searchSumAll" method="POST"> --%>
		<div id="input">
			<input type="text" id="myInput" name="name"
				placeholder="Search for names.." title="Type in a name">
			<!-- onkeyup="myFunction()" -->

			<br>
			<!-- 					<input type="text" id="myInput1" onkeyup="myFunction()" placeholder="Search for type.." title="Type in a name"> -->
			<select name="type" id="myInput1">
				<c:forEach items="${listKind}" var="kind">
					<option value="${kind}">${kind}</option>
				</c:forEach>
			</select>
			<!-- 					onchange="myFunction() -->

			<button type="button" id="searchbutton">Search</button>
		</div>
		<%-- 	</form> --%>
		<br>

		<div id="dataPage">

			<div id="tong" align="center">
				<label class="sum">Sum:</label>
				<label id="sum"></label>
			</div>

			<div>
				<ul id="shows">
					<li><label>Show:</label></li>
					<li><select class="showNumb">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="25">25</option>
							<option value="50">50</option>
					</select></li>
					<li><label> entries</label></li>
					<!-- 			<li><button id="load">Load</button></li> -->
				</ul>
			</div>


			<div>
				<table id="myTable">
					<thead>
						<tr>
							<th>Name</th>
							<th onclick="sortTable(1)">Age</th>
							<th>Type</th>
							<th>Amount</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody id="data">
						<c:forEach var="emp" items="${listEmp}">
							<tr>
								<td>${emp.getName()}</td>
								<td>${emp.getAge()}</td>
								<td>${emp.getKindID()}</td>
								<td>${emp.getAmount()}</td>
								<td>${emp.getDescription()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div id="page">
				<ul id="pages">
					<li><label class="pageInfo">Page 1 of 6 </label></li>
					<li class="goPre">
						<button><< Previous</button>
					</li>
					<li><input class="currentPage" type="text" id="currentPage" />
					</li>
					<li class="goNext"><button>Next >></button></li>

				</ul>
			</div>

		</div>

	</div>

	<jsp:include page="default/footer.jsp"></jsp:include>