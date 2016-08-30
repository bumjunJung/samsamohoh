<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="navbar-default sidebar" role="navigation">
	    <div class="sidebar-nav navbar-collapse">
	        <ul class="nav" id="side-menu">
	            <li>
	                <a href="${pageContext.request.contextPath}/memberLogin"><i class="fa fa-dashboard fa-fw"></i> 홈</a>
	            </li>
	            <li>
	                <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 이력<span class="fa arrow"></span></a>
	                <ul class="nav nav-second-level">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/member/member/analysis">멤버 이력</a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/member/restaurant/analysis">식당 이력</a>
	                    </li>
	                </ul>
	                <!-- /.nav-second-level -->
	            </li>
	            <li>
	                <a href="${pageContext.request.contextPath}/insertReceiptForm"><i class="fa fa-table fa-fw"></i> 영수증</a>
	            </li>
	            <!--  관리자 영역  -->
	            <li class="admin_menu">
	                <a href="${pageContext.request.contextPath}/memberList"><i class="fa fa-edit fa-fw"></i> 회원관리</a>
	            </li>
	            <li class="admin_menu">
	                <a  href="${pageContext.request.contextPath}/familyList"><i class="fa fa-edit fa-fw"></i> 팸 기록 관리</a>
	            </li>
	            <li class="admin_menu">
	                <a href="#"><i class="fa fa-wrench fa-fw"></i> 이력 관리<span class="fa arrow"></span></a>
	                <ul class="nav nav-second-level">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/admin/member/analysis">멤버 이력</a>

	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/admin/restaurant/analysis">식당 이력</a>
	                    </li>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/admin/pay/analysis">지출 이력</a>
	                    </li>
	                </ul>
	                <!-- /.nav-second-level -->
	            </li>
				<li class="admin_menu">
	                <a href="${pageContext.request.contextPath}/receipt"><i class="fa fa-edit fa-fw"></i> 영수증 관리</a>
	            </li>
	        </ul>
	    </div>
	    <!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
<script type="text/javascript">

 if(sessionStorage.getItem('grade')!='admin'){
 		$(".admin_menu").hide();
 }else{
 	$(".admin_menu").show();
 }

</script>