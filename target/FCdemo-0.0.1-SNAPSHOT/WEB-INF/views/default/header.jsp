<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link href="<c:url value="/resources/css/otherpage.css" />"
	rel="stylesheet">
</head>
<body bgcolor="#B5B5B5">

	<div id="header">
		<p id="image">
			<a href="${pageContext.request.contextPath}/FCmanager"> <img
				src="http://www.bongdathethao24h.com/wp-content/uploads/2017/11/logo-white-9.png" />
			</a>
		</p>
	</div>
	<div id="menu">
		<ul>
			<li><a href="#">Employee Manager</a>
				<ul class="sub-menu">
					<li><a href="${pageContext.request.contextPath}/Employee/addEmployee">Add
							Employee</a></li>
					<li><a
						href="${pageContext.request.contextPath}/Employee/updateEmployee">Update
							Employee</a></li>
					<li><a
						href="${pageContext.request.contextPath}/Employee/deleteEmployee">Delete
							Employee</a></li>
				</ul></li>

			<li><a href="#">Type Manager </a>

				<ul class="sub-menu">
					<li><a href="${pageContext.request.contextPath}/type/addKind">Add Type</a></li>
					<li><a href="${pageContext.request.contextPath}/type/updateKind">Update Type</a></li>
					<li><a href="${pageContext.request.contextPath}/type/deleteKind">Delete Type</a></li>
				</ul></li>

			<li><a href="#">Finance Manager</a>
				<ul class="sub-menu">
					<li><a href="${pageContext.request.contextPath}/finance/addFinance">Add Finance</a></li>
					<li><a href="${pageContext.request.contextPath}/finance/updateFinance">Update Finance</a></li>
					<li><a href="${pageContext.request.contextPath}/finance/deleteFinance">Delete Finance</a></li>
				</ul></li>

			<li><a href="${pageContext.request.contextPath}/search">Search</a></li>
			<li><a href="${pageContext.request.contextPath}/listfinance">Get List Employee</a></li>
			
			<c:choose>
            	<c:when test="${user !=null}">
	            	<li>
	            		<a href="#">${user.getUsername()}</a>
	            		<ul class="sub-menu">
	            			<li>
	                			<a href="${pageContext.request.contextPath}/getAccount">Track user</a>
	            			</li>
	            			<li>
	                			<a href="${pageContext.request.contextPath}/information">Information</a>
	            			</li>
	            			<li>
	                			<a href="${pageContext.request.contextPath}/logout">Logout</a>
	            			</li>
            			</ul>
            		</li>
            	
            	</c:when>
            	<c:otherwise>
            	<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
            </c:otherwise>
            	
            </c:choose>
		</ul>
	</div>