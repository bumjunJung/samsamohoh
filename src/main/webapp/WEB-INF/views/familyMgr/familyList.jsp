<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
<h3 class="page-header">※ 팸기록관리</h3>
      <div class="table" align="center">
      <table class="table table-bordered table-hover table-srtiped">
      	<col width="25%">
      	<col width="25%">
      	<col width="25%">
      	<col width="25%">
      	<thead>
      	<tr>
      		<td id="left_td" colspan="2">
				<span id="Count"></span>
      		</td>
      		<td id="right_td" colspan="2">
              	<select id="year"><option value="">YYYY</option></select>년
	  			<select id="month"><option value="">MM</option></select>월
      			<select id="day"><option value="">DD</option></select>일
      		</td>
      	</tr>
      	</thead>
      	<thead>
      		<tr>
      			<th>일자</th>
      			<th>식당명</th>
      			<th>참가자</th>
      			<th>구분</th>
      		</tr>
      	</thead>
      	<tbody id="content">
      		<tr>
      			<td colspan="4">조회결과가 없습니다.</td>
      		</tr>
 
    		</tbody>
    	</table>
    </div>
    <div id="pageNavigation" align="center"></div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	initialize(new Date());
	bindEvent();  
	
});

function getYearsOptions(today){
	var options = '';
	for(var i=today.getFullYear(); i>=today.getFullYear()-60; i--) {
		options += '<option value="'+i+'">' + i + '</option>';
	}
	return options;
}

function getMonthOptions(){
	var month = '';
	for(var i=1; i<=12; i++){
		if(i < 10){
			month += '<option value="0'+i+'">' + '0'+i + '</option>';
		}else{
			month += '<option value="'+i+'">' + i + '</option>';
		}
	}
	return month;
}

function getSelectDayOptions(getYear,getMonth){
	var dayScript="";
	var lastDay =  ( new Date( getYear, getMonth, 0) ).getDate();
	for (var i = 1; i <= lastDay; i++) {
		if(i<10){
			dayScript += '<option value="'+'0'+i+'">' + '0'+i + '</option>';
		}else{
			dayScript += '<option value="'+i+'">' + i + '</option>';
		}
	}
	return dayScript;
} 

function initialize(date) {
	$('#year').append(getYearsOptions(date));
	$('#month').append(getMonthOptions());
	$('#day').append(getSelectDayOptions(date));
	famlist()
}

function bindEvent() {
	$('#year').change(function(e){
		famlist();
	});
	
	$('#month').change(function(){
		var getYear = $('#year').val();
		var getMonth = $('#month').val();
		$('#day').empty();
		$('#day').append('<option value="">DD</option>');
		$('#day').append(getSelectDayOptions(getYear,getMonth));
		famlist();
	});
	
	$('#day').change(function(e){
		famlist();
	});
}

function famlist(pageNo){
	if(pageNo == null || pageNo == undefined){
		pageNo = 1;
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/familyListAjax",
		data: {
               'searchYear': $('#year').val(),
               'searchMonth': $('#month').val(),
               'searchDay': $('#day').val(),
               'page' : pageNo
            },
		error:function(result){
			alert('팸 기록 목록을 불러오지 못했습니다.');
		},
		success:function(result){
			var familyDiv = "";
			if(result.list != ''){
		      $.each(result.list, function(i,v){
					     familyDiv +='			<tr class="familyname'+v.family_seq+'">'
					     familyDiv +=	'			<td>'+v.reg_date+'</td>'
					     familyDiv +=	'			<td>'+v.name+'</td>'
					     familyDiv +=	'			<td><a href="#" onclick="return familyMembers('+v.family_seq+');">' + v.cnt + '명</a></td>'
							 if(v.meal_type == '0'){
								 familyDiv +=	'			<td>점심</td>'	 
							 }else if(v.meal_type == '1'){
								 familyDiv +=	'			<td>저녁</td>'
							 }
					     familyDiv +='			</tr>'
			      });  
			}else{
				familyDiv +='			<tr>'
				familyDiv +=	'			<td colspan="4">조회결과가 없습니다.</td>'
				familyDiv +='			</tr>'
			}
			var pageNav = "";
			var startPage = "";
			
			for(var i=1; i<=result.pageLimit; i++){

				if((result.page) == (result.startPage + i - 1)){
					pageNav += '<strong>' + (result.startPage + i -1)+'</strong> | ';
				}else{
					pageNav += '<a href="javascript:;" onclick="return famlist('+(result.startPage + i -1)+');">'+ (result.startPage + i -1) +'</a> | ';
				}
			}
			if(result.totalPage > (result.startPage+result.defaultPageLimit)-1){
				pageNav += '<a href="javascript:;" onclick="return famlist('+(result.startPage+result.defaultPageLimit)+');">&gt;</a> | ';
				pageNav += '<a href="javascript:;" onclick="return famlist('+result.totalPage+');">&gt;&gt;</a> | ';
			}
			if((result.startPage -1) > 0 && result.page > 10){
				var prevNav = '';
				prevNav += '<a href="javascript:;" onclick="return famlist('+1+');">&lt;&lt;</a> | ';
				prevNav += '<a href="javascript:;" onclick="return famlist('+(result.startPage - result.defaultPageLimit)+');">&lt;</a> | ';
				pageNav = prevNav + pageNav;
			}
		     $('#Count').html('총 '+result.totalCnt+' 건');
		     $('#content').html(familyDiv);
		     $('#pageNavigation').html(pageNav);
		}					
	});
}

function familyMembers(family_seq){
 	var names = "";
	names = $('.names'+family_seq+'').val();
	if(names == undefined){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/getFamilyMembersName",
			data: {
	                 'family_seq' : family_seq,
	              },
			error:function(result){
				alert('참가자를 불러오지 못했습니다.');
			},
			success:function(result){
				$('.familyname'+family_seq+'').after('<tr class = "names'+family_seq+'"><th>참가자 명</th><td colspan="3">'+result+'</td></tr>');
			}					
		});
	}else{
		$('.names'+family_seq+'').remove();
	} 
}

</script>
