<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link
	href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet">
    
    
    
<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header" style="text-align: center;">
			<h1 class="navbar-brand">삼삼오오</h1>
		</div>
</nav>
<body>
<div align="center">
	<h2>로그아웃 페이지</h2>
	<br />
	<a href="${pageContext.request.contextPath}/login">다시 로그인을 하려면 여기를 눌러주세요.</a>
</div>
<script type="text/javascript">
$(document).ready(function() {
	sessionStorage.removeItem('id');
	sessionStorage.removeItem('code');
	sessionStorage.removeItem('grade');
	sessionStorage.removeItem('name');
});
</script>