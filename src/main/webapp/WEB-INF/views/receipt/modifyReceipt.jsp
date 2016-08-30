<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<div class="row">
<h3 class="page-header">※ 영수증 수정</h3>
      <div class="table" align="left" id="receiptInsert">
      <form id="receiptModifyForm" method="post" action="${pageContext.request.contextPath}/modifyReceipt" onsubmit="return valueCheck();">
	      <table class="table table-border table-hover table-srtiped">
	      	<colgroup>
	      		<col width="10%"></col>
	      		<col width="90%"></col>
	      	</colgroup>
	      	<tbody class="leftBody">
	      	<tr>
				<th>일자</th>	      	
				<td>
      				<input type="text" id="settling_date" name="settling_date" value="" >
				</td>
	      	</tr>
	      	<tr>
				<th>식사구분</th>	      	
				<td class="meal">
      				
				</td>
	      	</tr>	
	      	<tr>
	      		<th>인원구분</th>		
	      		<td class="membersInfo">
	      		</td>		
	      	</tr>	
	      	<tr>
	      		<th>기타</th>		
	      		<td>
	      			<input type="text" id="etc" name="etc" value=""  size="35">
	      		</td>
	      	</tr>	
			<tr>
	      		<th>결제 금액</th>		
	      		<td>
	      			<input type="text" id="total_price" name="total_price" value="" placeholder="금액을 입력해주세요.(ex.16000)" size="35">
	      			<input type="hidden" id="settling_date"  name="settling_date" value="">
	      			<input type="hidden" id="seq"  name="seq" value="">
	      		</td>
			<tr>
				<td colspan="2"> 
					<button class="btn btn-outline btn-primary" type="submit">수정</button>
					<button class="btn btn-outline btn-primary" onclick="return goHome();">목록</button>
				</td>
			</tr>
	      </table>
      </form>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){		
	var msgTemp =  decodeURIComponent(getUrlVars().msg)
	var msg = decodeURIComponent(msgTemp);
	
	if(msg.length >0){
		getReceiptInfo(getUrlVars().seq);
		alert(msg);
	}else{
		getReceiptInfo(getUrlVars().seq);
	}
	
	$( "#settling_date" ).datepicker({
	 	showOn: "both", 
        buttonImage: "../samsamohoh/img/calendarButton.png", 
        buttonImageOnly: true,
        dateFormat: "yy-mm-dd"
    });
});	
function getUrlVars(){
	var vars = [], hash;
	var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	 for(var i = 0; i < hashes.length; i++){
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

function goHome(){
	window.location.href = '${pageContext.request.contextPath}/receipt'
	return false;
}

function getReceiptInfo(seq){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/getReceiptInfo",
		data: {
               'seq': seq
            },
		error:function(result){
			alert('영수증 내용을 불러오지 못했습니다.');
		},
		success:function(result){
				var meal_type="";
				var members="";
				$('#seq').val(seq);
				$('#settling_date').val(result.payInfo.settling_date);
				
				if(result.payInfo.meal_type == 0){
					meal_type += '<input type="radio" name="meal_type" id="meal_type" value="0" checked="checked"/>점심'
					meal_type += '<input type="radio" name="meal_type" id="meal_type" value="1"/>저녁'
				}else{
					meal_type += '<input type="radio" name="meal_type" id="meal_type" value="0"/>점심'
					meal_type += '<input type="radio" name="meal_type" id="meal_type" value="1" checked="checked"/>저녁'
				}
				$('.meal').append(meal_type);
				$('#etc').val(result.payInfo.etc);
				$('#total_price').val(result.payInfo.total_price);
				
				 $.each(result.list, function(i,v){
					  members += '<input type="button" id="member'+i+'"  name="member" value="'+v.name+'"></input>&nbsp;<a href="#" id="membera'+i+'" onclick="return removeMember('+i+')">x</a>&nbsp;&nbsp;'
					  members += '<input type="hidden" id="realMember'+i+'"  name="realMember" value="'+v.member_code+'"></input>'
			      });  
				      members += '<input type="button" id="addBtn" value="+" onclick="return addMember()"></input>'  
				      
				  $('.membersInfo').empty();
				  $('.membersInfo').append(members);
				
				
		}					
	});
}

function searchMember(){
	var member_name = $('#search_member').val();
	
	 if(member_name == ""){
		alert('회원명을 입력해주세요');
		return false;
	}else if($("input[value="+member_name+"]").size() > 0){
		alert('이미 있는 회원 입니다 다른 회원 검색해 주세요');
		$('#search').val("");
		return false;
	}
	else{
		var addBtn = '<input type="button" id="addBtn" value="+" onclick="return addMember()"></input>';
		var members = '';
		if(member_name){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/findMemberName",
				data: {
		               'member_name': member_name
		            },
				error:function(result){
					alert('인원추가에 실패하였습니다.');
				},
				success:function(result){
					if(result.code != "no Have Member Code"){
						var code = "'"+result.code+"'";
						var name = "'"+result.name+"'";
						members += '<input type="button" id="member"  name="member" value="'+result.name+'"></input>&nbsp;'
						members += '<a id="realMember" name="membera'+result.code+'" onclick="return addRemove('+code+","+name+');" >x</a>&nbsp;'
						members += '<input type="hidden" id="realMember"  name="realMember" value="'+result.code+'"></input>';                                                            
						$('.membersInfo').append(members);
						var checkConfirm = confirm("인원추가가 끝났습니까?");
						if(checkConfirm == true){
							$('#layerPopup').remove();
							$('#addBtn').remove();
							$('.membersInfo').append(addBtn);
							
						}else if(checkConfirm == false){
							$('#search').val("");
						}
					}else{
						$('#search').val("");
						alert('일치하는 회원이 없습니다 다른 이름으로 검색해주세요');
					}
				}					
			});
		}
	}
}

function addMember(){
	var search = '';
		  search += '<div id="layerPopup">'
	      search +=		'<label> 이름 : </label>'
	  	  search +=		'<input type="text" value="" id="search_member" placeholder="추가할 회원명을 입력해주세요."></input>'
		  search +=		'<input type="button" value="검색" onclick= "return searchMember();"></input>'
		  search +=		'<a href="#" onclick= "return noSearch();">[취소]</a>'
		  search +='</div>'
	  $('#layerPopup').remove();	
	  $('.membersInfo').append(search);	
}

function addRemove(code, name){
	var singlecode = "'"+code+"'";
	var singlename = "'"+name+"'";
	
	$("input[value="+singlecode+"").remove();
	$("input[value="+singlename+"").remove();
	$("a[name=membera"+code+"").remove();
}

function removeMember(no){
	$('#member'+no+'').remove();
	$('#membera'+no+'').remove();
	$('#realMember'+no+'').remove();
}

function noSearch(){
	$('#layerPopup').remove();
}


function valueCheck(){
	var memberSearch = $('#search_member').val();
	if(memberSearch != null && memberSearch != undefined){
		searchMember();
		return false;
	}
	
	if($("input[name=realMember").val() == undefined){
		alert('참여한 인원을 추가해주세요.');
		return false;
	}
	
	if($('#total_price').val() == ""){
		alert('결재금액을 입력해주세요.');
		return false;
	}
	var checkConfirm = confirm("영수증을 수정하시겠습니까?");
	if(checkConfirm == true){
		return true;
	}else if(checkConfirm == false){
		return false;
	}
}
 
function formSubmit(){
	 $("#receiptModifyForm").submit();
 }

</script>
