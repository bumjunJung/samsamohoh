<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원관리</title>
</head>
<body>
<div id="memberList" class="row">
	 <h3 class="page-header">※ 회원관리</h3>
			   <div class="table" align="center">
			   	<table class="table table-bordered table-hover table-srtiped">
			   		<col width="10%">
			   		<col width="20%">
			   		<col width="20%">
			   		<col width="20%">
			   		<col width="*">
			   		<thead>
			   		<tr>
			   			<td id="left_td" colspan="2" >
			   				<input type="button" id="modifyButton" class="btn" value="변경사항 수정"/>
			   				<input type="button" id="refreshButton" class="btn" value="되돌리기"/>
			   			</td>
			   			<td id="right_td" colspan="4">
			   				<input type="text" size="30px;" id="searchNm" value=""> 
			   				<input type="button" id="searchButton" class="btn" value="검색" />
			   			</td>
			   		</tr>
			   		</thead>
			   		<thead>
			   			<tr>
			   				<th>가입일</th>
			   				<th>아이디</th>
			   				<th>회원명</th>
			   				<th>권한</th>
			   				<th>상태</th>
			   			</tr>
			   		</thead>
			   		<tbody id="content">
							<tr>
				      			<td colspan="6">조회결과가 없습니다.</td>
				      		</tr>			 
			    		</tbody>
			    	</table>
			    </div>
			    <div id="pageNavigation" align="center"></div>
	</div>
<script type="text/javascript">
$( document ).ready(function() {
	memlist();
	$('#modifyButton').attr('disabled',true);
	$('#refreshButton').attr('disabled',true);
	bindEvent();
  });

function bindEvent(){
	$('#searchButton').click(function(){
		memlist($('#searchNm').val());
	});
	$('#refreshButton').click(function(){
		if(isChanged()){
			memlist($('#searchNm').val());
			$('#modifyButton').attr('disabled',true);
			$('#refreshButton').attr('disabled',true);
		}
	});
	$('#modifyButton').click(function () {
		modifyMember();
	});
}

var originalData;
var cloneData;

function memlist(searchNm,page){
	if(page == null || page == undefined){
		page = 1;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/memberListAjax",
		data: {
            'searchNm': searchNm,
            'page' : page
         },
		error:function(result){
			alert('회원목록을 불러오지 못했습니다.');
		},
		success:function(result){
				var memberDiv = "";
				if(result){
					
					originalData = result.list;
					
					// 객체를 쉽게 클론하는 꼼수
					cloneData = JSON.parse(JSON.stringify(originalData));
					
					$.each(result.list, function(i,v){
					    memberDiv +='			<tr id="'+v.code+'">'
					    memberDiv +=	'			<td>'+v.reg_date+'</td>'
					    memberDiv +=	'			<td>'+v.id+'</td>'
					    memberDiv +=	'			<td>'+v.name+'</td>'
					    
					    if(v.grade == "user"){
						    memberDiv +=	'			<td>'
						    memberDiv +=	'				<select id="grade'+i+'" onchange="changeState('+i+',this)">'
						    memberDiv +=	'				<option value = "user" selected="selected">일반회원</option>'
						    memberDiv +=	'				<option value = "admin">관리자</option>'
						    memberDiv +=	'				</select>'
						    memberDiv +=	'			</td>'
					    }else{
					    	memberDiv +=	'			<td>'
						    memberDiv +=	'				<select id="grade'+i+'" onchange="changeState('+i+',this)">'
						    memberDiv +=	'				<option value = "user">일반회원</option>'
						    memberDiv +=	'				<option value = "admin" selected="selected">관리자</option>'
						    memberDiv +=	'				</select>'
						    memberDiv +=	'			    </td>'
					    }
					    if(v.state != "Y"){
						    memberDiv +=	'			<td>'
						    memberDiv +=	'				<select id="state'+i+'" onchange="changeState('+i+',this)">'
						    memberDiv +=	'				<option value = "N" selected="selected">해지(삭제)</option>'
						    memberDiv +=	'				<option value = "Y">정상</option>'
						    memberDiv +=	'				</select>'
						    memberDiv +=	'			</td>'					    	
					    }else{
						    memberDiv +=	'			<td>'
						    memberDiv +=	'				<select id="state'+i+'" onchange="changeState('+i+',this)">'
						    memberDiv +=	'				<option value = "N">해지(삭제)</option>'
						    memberDiv +=	'				<option value = "Y" selected="selected">정상</option>'
						    memberDiv +=	'				</select>'
						    memberDiv +=	'			</td>'	
					    }
					    memberDiv +='			</tr>'  	
				    });
				}else{
					    memberDiv +='			<tr>'
					    memberDiv +=	'			<td colspan="5">조회결과가 없습니다.</td>'
					    memberDiv +='			</tr>'  	
				}
				var pageNav = "";
				var startPage = "";
				for(var i=1; i<=result.pageLimit; i++){

					if((result.page) == (result.startPage + i - 1)){
						pageNav += '<strong id="no">' + (result.startPage + i -1)+'</strong> | ';
					}else{
						pageNav += '<a href="javascript:;" onclick="return memlist('+searchNm+','+(result.startPage + i -1)+');">'+ (result.startPage + i -1) +'</a> | ';
					}
				}
				if(result.totalPage > (result.startPage+result.defaultPageLimit)-1){
					pageNav += '<a href="javascript:;" onclick="return memlist('+searchNm+','+(result.startPage+result.defaultPageLimit)+');">&gt;</a> | ';
					pageNav += '<a href="javascript:;" onclick="return memlist('+searchNm+','+result.totalPage+');">&gt;&gt;</a> | ';
				}
				if((result.startPage -1) > 0 && result.page > 10){
					var prevNav = '';
					prevNav += '<a href="javascript:;" onclick="return memlist('+1+');">&lt;&lt;</a> | ';
					prevNav += '<a href="javascript:;" onclick="return memlist('+searchNm+','+(result.startPage - result.defaultPageLimit)+');">&lt;</a> | ';
					pageNav = prevNav + pageNav;
				}
				
				$('#content').html(memberDiv);
				$('#pageNavigation').html(pageNav);
		}
	});
}

function changeState(i,v){
	var style = "";
	style = getValueStyle(v.value);
	
	cloneData[i][style] = v.value;
	
	var changed = (!isChanged());
	
	$('#modifyButton').attr('disabled', changed);
	$('#refreshButton').attr('disabled', changed);
}

function getValueStyle(v){
	var result;
	if(v != "admin" && v != "user"){
		result = "state";
	}else{
		result = "grade";
	}
	return result;
}

function isChanged() {
	// 꼼수의 극
	return JSON.stringify(originalData) != JSON.stringify(cloneData);
}

function createClone(source) {
	var result;
	
	// TBD 차주 숙제
	
	return result;
}

function getChangedData() {
	var result = [];

	for (var i = 0; i < cloneData.length; i++) {
		if(JSON.stringify(originalData[i]) != JSON.stringify(cloneData[i])){
			result.push(cloneData[i]);
		}
	}
	return result;
}

function modifyMember() {
	$.ajax({
		type : "post",
		contentType : "application/json",
		dataType : "json",
		url : "${pageContext.request.contextPath}/modifyMember",
		data : JSON.stringify(getChangedData()),
		error : function(result) {
			alert('수정되지 않았습니다.');
		},
		success : function(result) {
			originalData = JSON.parse(JSON.stringify(cloneData));
			$('#modifyButton').attr('disabled',true);
			$('#refreshButton').attr('disabled',true);
			alert('수정되었습니다.');
		}
	});
}
</script>
</body>
</html>