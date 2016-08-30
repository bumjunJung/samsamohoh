
﻿﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="../../../../session.jsp"%>

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
			<a href="#" id="namimg">
				<i id="name"></i> 님
			</a>
		</li>
		<!-- /.dropdown -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"> 
				<i class="fa fa-user fa-fw"></i> 
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li id="login">
					<a onclick="return signOut();">로그아웃</a>
				</li>
			</ul>
		</li>
       </ul>
  		<a class="navbar-brand" href="${pageContext.request.contextPath}/memberLogin">삼삼오오</a>
  		
<!-- google sign in -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
<script>
$( document ).ready(function() {
	if(!sessionStorage.getItem("id")){
		$('#namimg').empty();
		$('#login').empty();
		var login = "";
			login += '<a href="${pageContext.request.contextPath}/login" onclick="return sign();">로그인</a>'
		$('#login').append(login);
	}
  });
  
 	 function sign() {
		location.href = '${pageContext.request.contextPath}/login';
	}  
 	 function signOut() {
		sessionStorage.clear(); 
		sessionStorage.removeItem("id");
		sessionStorage.removeItem("grade");
		sessionStorage.removeItem("name");
		sessionStorage.removeItem("code");
		
		location.href = '${pageContext.request.contextPath}/logout';
	}  
</script>


