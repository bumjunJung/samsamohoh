﻿﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html itemscope itemtype="http://schema.org/Article">
<html>
<head>
<meta name="google-signin-client_id"
	content="612052011653-47ojtc1q72o9bftvfqquus15m1rseq29.apps.googleusercontent.com">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header" style="text-align: center;">
			<h1 class="navbar-brand">삼삼오오</h1>
		</div>
		<form id="loginForm" action="${pageContext.request.contextPath}/doLogin" method="post">
			<input type="hidden" name="idtoken" value="" id="idtoken">
		</form>
	</nav>
	<div class="loginForm">
		로그인
		<hr />
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
		<h4>버튼을 누르시면 Google<br /> sign_in으로 로그인 됩니다.</h4>
	</div>
	<script>		
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile(), 
			id_token = googleUser.getAuthResponse().id_token;	
			
			$('#idtoken').val(id_token);
			
			// ajax로 이동
		    compareIdOrStateCheck($('#idtoken').val()); 
		}
		/*중복체크*/
		function compareIdOrStateCheck(idToken){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/compareIdAjax",
				data: {
		               'idtoken': idToken
		            },
				error:function(retVal){
					alert('error');
				},
				success:function(retVal){
						var values = [];
						if(retVal.memberInfoList != undefined){
							values = retVal.memberInfoList;
							$.each(values, function(index, value){
								idCheckTransferPage(value);
							});				
						}else{
								alert('로그인에 실패하였습니다.회사계정으로만 로그인 해주세요');
						}
				}		
			});
		}
			
		function idCheckTransferPage(value){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/loginCheck",
				data: {
					'id': value.id,
		            },
				error:function(retVal){
					alert('error');
				},
				success:function(retVal){
					if (value.code != "absence") {
						if(emailCheck(value.id)){
							if(value.state == "Y"){
								sessionStorage.setItem('code', value.code);
								sessionStorage.setItem('id', value.id);
								sessionStorage.setItem('name', value.name);
								sessionStorage.setItem('grade', value.grade);
								location.href = "${pageContext.request.contextPath}/memberLogin";
								
							} else {
								location.href = "${pageContext.request.contextPath}/error";
							}			
						} else {
							location.href = "${pageContext.request.contextPath}/error";				
						}	
					} else{
						$('#loginForm').submit();
					}	
				}		
			});
		}
		
		/* email validation */
		function emailCheck(id){
			var oursEmail = "@ex2i.com";
			var mailIndex = id.indexOf('@');
			var userEmail = id.substring(mailIndex);
			if(userEmail == oursEmail){
				return true;
			} else {
				return false;
			}
		}
	</script>


	<!-- google sign in -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/bootstrap/dist/js/bootstrap.min.js">
	</script>
</body>
</html>