<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<style type="text/css">
* {
	box-sizing: border-box;
}

.container {
	width: 500px;
	margin: auto;
}

#myInput {
	background-position: 10px 12px;
	background-repeat: no-repeat;
	width: 100%;
	font-size: 16px;
	padding: 15px;
	border: 1px solid #ddd;
	margin-bottom: 12px;
}

#myTable {
	/*   display: none; */
	border-collapse: collapse;
	width: 100%;
	border: 1px solid #ddd;
	font-size: 18px;
}

#myTable th, #myTable td {
	text-align: left;
	padding: 12px;
}

#myTable tr {
	border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
	background-color: #f1f1f1;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<div class="container">
		<h2 align="center">Filter Table</h2>

		<input type="text" id="myInput" title="Type in a name of Club" placeholder="Search by kind...">

		<table id="myTable">
			<tr class="header">
				<th>Kind</th>
				<th>Name</th>
				<th>Age</th>
				
				<th>Amount</th>
				<th>Description</th>
			</tr>

			<c:forEach var="emp" items="${listEmp}">
				<tr>
					<td>${emp.getKindID()}</td>
					<td>${emp.getName()}</td>
					<td>${emp.getAge()}</td>
					
					<td>${emp.getAmount()}</td>
					<td>${emp.getDescription()}</td>
				</tr>
			</c:forEach>
		</table>
		<div style="background:#972F2B;color: #FFF;"><c:out value="${sum}"/></div>  
	</div>
	<script type="text/javascript">
		// lấy thẻ input
		var input = document.getElementById("myInput");
		// định nghĩa hàm xử lý myFunction
		function myFunction() {
			var filter, table, tr, td, i;
			// lấy giá trị người dùng nhập
			filter = input.value.toUpperCase();

			// lấy phần bảng hiển thị kết quả
			table = document.getElementById("myTable");
			// lấy tất cả các thẻ tr
			tr = table.getElementsByTagName("tr");

			//Nếu filter không có giá trị ẩn các kết quả
			if (!filter) {
				table.style.display = "none";
			} else {
				// lặp qua tất cả các thẻ tr
				for (i = 0; i < tr.length; i++) {
					// lấy giá trị của thẻ td đầu tiên đại diện cho tên club
					td = tr[i].getElementsByTagName("td")[0];
					if (td) {
						// kiểm tra giá trị filter có tồn tại trong thẻ tr không
						if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
							//nếu có hiển thị chúng
							table.style.display = "table";
							tr[i].style.display = "";
						} else {
							// nếu không ẩn chúng
							tr[i].style.display = "none";
						}
					}
				}
			}
		}
		//gán sự kiện cho thẻ input
		input.addEventListener("keyup", myFunction);
	</script>
</body>
</html>