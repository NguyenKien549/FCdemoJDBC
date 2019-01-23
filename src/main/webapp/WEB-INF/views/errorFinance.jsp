<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>error</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/FCmanager">Back</a>
<h1 align="center">Error Information</h1>

<c:forEach items="${error}" var="error">
	<h3 align="center">${error}</h3>
</c:forEach>
</body>
</html>