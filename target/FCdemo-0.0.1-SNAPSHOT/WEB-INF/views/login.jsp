<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE >
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<style type="text/css">
body {
	background-image:
		url('https://i.ytimg.com/vi/fWcIj8u9sgU/maxresdefault.jpg');
	color: white;
	margin: 0;
	min-height: 100%;
	width: 100%;
	position: relative;
}

#body {
	height: 400px;
	width: 100%;
	padding: 60px 0 60px 0;
}

#footer {
	text-align: center;
	clear: both;
	width: 100%;
	height: 60px;
	background-color: #666;
}

#footer p {
	line-height: 30px;
	margin: 0;
}

#image, h1 {
	margin: 0;
	align-items: center;
}

#login {
	width: 60px;
	height: 25px;
}

#loginLable, #createLable {
	font-size: 25px;
	cursor: pointer;
}

#createLable:hover, #loginLable:hover {
	color: red;
}
</style>
</head>
<body>

	<h1 align="center">Chào mừng bạn đến với</h1>
	<p align="center" id="image">
		<a href="http://www.bongda.com.vn"> <img
			src="http://www.bongdathethao24h.com/wp-content/uploads/2017/11/logo-white-9.png" />
		</a>
	</p>

	<div id="body" align="center">
		<p>
			<label id="loginLable">Login</label><label id="ngancach">/</label><label
				id="createLable">Register</label>
		</p>
		<div id="loginform">
			<h2>Xin mời đăng nhập để truy cập trang web</h2>
			<form name="loginForm"
				action="<c:url value="j_spring_security_login" />" method="POST">
				<table>
					<tr>
						<td><label>User:</label></td>
						<td><input name="username" value="" type="text" required /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input name="password" value="" type="password" required /></td>
					</tr>

					<!-- 			            <tr> -->
					<!-- 			                <td></td> -->
					<!-- 			                <td><input type="radio" name="role" value="ADMIN" checked="checked" />ADMIN -->
					<!-- 			                <input type="radio" name="role" value="USER" />USER</td> -->
					<!-- 			            </tr> -->
					<!-- 					<tr><td><input type="checkbox" value="yes" name="_spring_security_remember_me"/> Remember me</td></tr> -->
					<!-- co the thay name bang ten khac -->
				</table>
				<div style="background: #972F2B; color: #FFF;">
					<c:out value="${message}" />
				</div>
				<p align="center">
					<input id="login" type="submit" value="Login" />
				</p>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

		</div>

		<div id="register">
			<h2>Xin mời nhập thông tin tài khoản</h2>
			<form action="#">
				<table>
					<tr>
						<td><label>Name:</label></td>
						<td><input id="name" type="text" placeholder="Nhập tên"
							required /></td>
					</tr>

					<tr>
						<td><label>Mail:</label></td>
						<td><input id="mail" type="text" placeholder="Nhập email"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
							title="nhap dung dinh dang email" required /></td>
					</tr>
					<tr>
						<td><label>Role:</label></td>
						<td><select id="role">
								<c:forEach var="role" items="${listRole}">
									<option value="${role}">${role}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>User:</label></td>
						<td><input id="username" type="text"
							placeholder="Nhập tên đăng nhập" required /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input id="password" type="password"
							placeholder="Nhập mật khẩu" required /></td>
					</tr>
					<tr>
						<td><label>Input password again:</label></td>
						<td><input id="passwordAgain" type="password"
							placeholder="Nhập lại mật khẩu" required /></td>
					</tr>

				</table>
				<div style="background: #972F2B; color: #FFF;">
					<c:out value="${message}" />
				</div>
				<p align="center">
					<input id="create" type="submit" value="Create" />
				</p>
			</form>
		</div>


	</div>
	<script type="text/javascript">
		$("document")
				.ready(
						function(e) {
							$("div#register").hide();
							$("#createLable").click(function() {
								$("div#loginform").hide();
								$("div#register").show();
							});

							$("#loginLable").click(function() {
								$("div#register").hide();
								$("div#loginform").show();
							});

							$("#create").click(function() {
								if ($("#password").val() === $(
										"#passwordAgain").val()
										&& $("#name").val() !== ""
										&& $("#mail").val() !== ""
										&& $("#username").val() !== ""
										&& $("#password").val() !== "") {
									$.ajax({
											url : "http://localhost:8080/FCdemo/register",
											type : "get",
											dataType : "json",
											data : {
												name : $("#name").val(),
												mail : $("#mail").val(),
												role : $("#role").val(),
												username : $("#username").val(),
												password : $("#password").val()
											},
											error : function(e) {
												console
														.log(e);
											}
										}).done(function(
												data) {
											console
													.log(data);
											if (data == "done") {
												console
														.log("success,will redirect");
												window.location = "http://localhost:8080/FCdemo/";
												alert("Create Account done. Login please.");
											} else
												alert("create Account fail");
											window.location = "http://localhost:8080/FCdemo/";
											$("div#loginform").hide();
											$("div#register").show();
										})
								.fail(function() {
											console.log("error");
											alert("that bai. xin thu lai");
										});
								} else
									alert("lỗi đăng kí");
							});
						});
	</script>
	<jsp:include page="default/footer.jsp"></jsp:include>