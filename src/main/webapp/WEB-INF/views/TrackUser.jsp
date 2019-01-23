<jsp:include page="default/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
#content ol li{
list-style-position: outside;
}
</style>
<div id="content">
<h3 align="center">List Activity User:</h3>
<ol>
<c:forEach items="${listUser}" var="user">
<li>${user}</li>
</c:forEach>
</ol>
</div>

<jsp:include page="default/footer.jsp"></jsp:include>