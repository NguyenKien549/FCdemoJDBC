<jsp:include page="default/header.jsp"/>
<div id="content">
	<h3>Information Account</h3>
	<p>Đây là phiên đăng nhập của tài khoản: ${user.getUsername()}</p>
	<p>Người dùng có quyền truy cập như một: ${user.getAuthorities()}</p>
	
</div>
<jsp:include page="default/footer.jsp"></jsp:include>