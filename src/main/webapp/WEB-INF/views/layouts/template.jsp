<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<!-- Bootstrap Core CSS -->
	<link href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/resources/sbadmin/dist/css/timeline.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/sbadmin/dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/morrisjs/morris.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/sbadmin/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- jQuery -->
	<link href="${pageContext.request.contextPath}/css/restaurant.css" rel="stylesheet">	
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script type="text/javascript">
		jQuery.browser = {};
		(function () {
		    jQuery.browser.msie = false;
		    jQuery.browser.version = 0;
		    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
		        jQuery.browser.msie = true;
		        jQuery.browser.version = RegExp.$1;
		    }
		})();
	</script>
	
    
    
    <title>삼삼오오</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
	</nav>
	<div id="page-wrapper">
		<tiles:insertAttribute name="body" />
	</div>
		
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jqueryPaging.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/sbadmin/bower_components/raphael/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/sbadmin/dist/js/sb-admin-2.js"></script>
    
</body>
</html>