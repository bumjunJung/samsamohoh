<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
<h3 class="page-header">※ 영수증 등록</h3>
      <div class="table" align="left" id="receiptInsert">
      <form id="receiptForm" method="post">
	      <table class="table table-border table-hover table-srtiped">
	      	<colgroup>
	      		<col width="10%"></col>
	      		<col width="90%"></col>
	      	</colgroup>
	      	<thead class="leftBody">
	      	<tr>
				<th>날짜</th>	      	
				<td>
      				<input type="text" id="searchDate" name="searchDate" value="" >
				</td>
	      	</tr>
	      	<tr>
				<th>식사구분</th>	      	
				<td>
	      			<select id="meal_type" name="meal_type">
						<option value="0">점심</option>
						<option value="1">저녁</option>
					 </select>
				</td>
	      	</tr>
	      	</thead>
	      	<tbody class="leftBody">
	      	<tr>
	      		<th>결제구분</th>		
	      		<td>
	      			<input type="radio" name="card_type" id="card_type" value="1" />법인카드
	      			<input type="radio" name="card_type" id="card_type" value="2"/>개인카드
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
	      		<th>식당명</th>		
	      		<td class="restaurantInfo">
	      			<input type="text" id="restaurntName" name="restaurntName" value=""  placeholder="식당명을 입력해주세요." size="35" readonly="readonly"></input>
	      			<input type="button" value="수정" onclick="return modifyRestaurant()"/>
	      			
		            <input type="hidden" id="restaurant_code" name="restaurant_code" value=""></input>
	      		</td>
	      	</tr>	
	      	<tr>
	      		<th>카드 번호</th>	
	      		<td>
	      			<input type="text" id="card_no" name="card_no" value="" placeholder="카트번호 마지막 4자리를 입력해주세요." size="35" maxlength="4">
	      		</td>
	      	</tr>	
	      	<tr>
	      		<th>결제 금액</th>		
	      		<td>
	      			<input type="text" id="total_price" name="total_price" value="" placeholder="금액을 입력해주세요.(ex.16000)" size="35">
	      			<input type="hidden" id="reg_code"  name="reg_code" value="">
	      			<input type="hidden" id="settling_date"  name="settling_date" value="">
	      		</td>
	      	</tr>	
			<tr>
				<td colspan="2"> <button class="btn btn-outline btn-primary" onclick="return valueCheck();">등록</button>
								</td>
			</tr>
	      </table>
      </form>
    </div>
</div>

<script type="text/javascript">
$( document ).ready(function() {
	if(sessionStorage.getItem('id') == null|| sessionStorage.getItem('id') == undefined){
		$('#receiptInsert').empty();
		alert('로그인후 사용해주세요.');
	}
	
	  $( "#searchDate" ).datepicker({
		 	showOn: "both", 
	        buttonImage: "../samsamohoh/img/calendarButton.png", 
	        buttonImageOnly: true,
	        dateFormat: "yy-mm-dd"
	    });
	  bindEvent();
	  defaultSetting();
  });


 function defaultSetting(){
	 var today = new Date();
	 var syear = today.getFullYear();
	 var smonth = today.getMonth()+1;
	 var sday = today.getDate();
	 var meal_type = 0;
	 var searchDate = "";
	 
	 if (smonth < 10)
         smonth = "0" + smonth;
     if (sday < 10)
         sday = "0" + sday;
	 
     if($('input[name="card_type"]:checked').val() == undefined){ 
    	 $("input:radio[name='card_type']:radio[value='1']").attr("checked",true);
   	  }
 
     searchDate = syear + "-" + smonth + "-" +sday;
 	$('#meal_type').val(meal_type);
 	$('#searchDate').val(searchDate);
 	
 	 getFamilyInfo(searchDate,meal_type); 
} 


function bindEvent() {
	$('#searchDate').change(function(e){
		if($('#meal_type').val() != ""){
			dateCheck();
		}
	});
	$('#meal_type').change(function(e){
		if($('#searchDate').val() != ""){
			dateCheck();
		}
	});
} 

 function dateCheck(){
	var searchDate = $('#searchDate').val();
	var meal_type = $('#meal_type').val();
	
	if(searchDate == ""){
		alert('날짜를 선택해 주세요');
	}else if(meal_type == ""){
		alert('식사구분을 선택해 주세요');
	}else{
		getFamilyInfo(searchDate,meal_type);
	}
} 

function valueCheck(){
	var memberSearch = $('#search_member').val();
	if(memberSearch != null && memberSearch != undefined){
		searchMember();
		return false;
	}
	
	var restaurantSearch = $('#search_restaurant').val();
	if(restaurantSearch != null && restaurantSearch != undefined){
		searchRestaurant();
		return false;
	}
	
	if(!($('input:radio[name=card_type]').is(':checked'))){
		alert('결제하신 카드를 선택해주세요');
		return false;
	}
	if($("input[name=realMember").val() == undefined){
		alert('참여한 인원을 추가해주세요.');
		return false;
	}
	if($('#restaurntName').val() == ""){
		alert('식당명을 입력해주세요.');
		return false;
	}
    var noCheck = /^[0-9]+$/;
	if($('#card_no').val() == ""){
		alert('카드번호 뒷 4자리를 입력해주세요.');
		return false;
	}else if(!noCheck.test($('#card_no').val())){
        alert("숫자값만 넣어주세요.");
        return false;
	}
	
	if($('#total_price').val() == ""){
		alert('결재금액을 입력해주세요.');
		return false;
	}
	var settling_date = $('#searchDate').val(); 
	$('#settling_date').val(settling_date);
	var checkConfirm = confirm("위의 정보로 영수증을 등록하시겠습니까?");
	if(checkConfirm == true){
		formSubmit();
	}else if(checkConfirm == false){
		return false;
	}
}

 function formSubmit(){
	var formData = $("#receiptForm").serialize();
	
    jQuery.ajax({
        type: 'post',
        dataType: 'json',
        url: '${pageContext.request.contextPath}/addReceipt',
        data: formData,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
        success: function (result) {
        	if(result){
	            alert(result.year+'년'+result.month+'월'+result.day+'일 영수증이 등록 되었습니다.');
        	}
        }
    });
} 
 
function getFamilyInfo(searchDate,meal_type){
	if(searchDate){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/getFamilyMembers",
			data: {
	               'searchDate': searchDate,
	               'meal_type': meal_type
	            },
			error:function(result){
				alert('팸 인원목록을 불러오지 못했습니다.');
			},
			success:function(result){
					var members = '';
					if(result.check == 'yes'){
						if(result.list.length > 0){
							$('#reg_code').val(result.code);
							$('#restaurntName').val(result.r_name);
							$('#restaurant_code').val(result.r_code);
							$('#restaurntName').text(result.r_name);
							
							  $.each(result.list, function(i,v){
								  members += '<input type="button" id="member'+i+'"  name="member" value="'+v.name+'"></input>&nbsp;<a href="#" id="membera'+i+'" onclick="return removeMember('+i+')">x</a>&nbsp;&nbsp;'
								  members += '<input type="hidden" id="realMember'+i+'"  name="realMember" value="'+v.member_code+'"></input>'
						      });  
							      members += '<input type="button" id="addBtn" value="+" onclick="return addMember()"></input>'  
							      
							  $('.membersInfo').empty();
							  $('.membersInfo').append(members);
						}else{
							 members += '<input type="button" id="addBtn" value="+" onclick="return addMember()"></input>'  
								  $('.membersInfo').empty();
								  $('.membersInfo').append(members);
						}
					}else{
						alert('해당날짜로 이미 영수증을 등록하였습니다.\n날짜와 식사구분을 다르게 선택해주세요.');
						$('#searchDate').val("");
						$('#meal_type').val("0");
					}	
			}					
		});
	}else{
		alert('날짜를 선택해주세요');
	}
}

function searchMember(){
	var member_name = $('#search_member').val();
	if($("input[value="+member_name+"").size() > 0){
		alert('이미 있는 회원 입니다 다른 회원 검색해 주세요');
		$('#search').val("");
		return false;
	}else if(member_name == ""){
		alert('회원명을 입력해주세요');
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

function searchRestaurant(){
	var restaurant_name = $('#search_restaurant').val();

	if(restaurant_name != '' && restaurant_name == $('#restaurntName').val()){
		alert('식당이름이 일치합니다 다른 식당명을 입력해주세요');
		$('#search').val("");
		return false;
	}else if(restaurant_name == '' || restaurant_name == undefined){
		alert('식당명을 입력해주세요');
		return false;
	}
	var restaurants = '';
	if(restaurant_name){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/findRestaurantName",
			data: {
	               'restaurant_name': restaurant_name
	            },
			error:function(result){
				alert('식당명을 찾지못했습니다.');
			},
			success:function(result){
				if(result.code != "no Have Restaurant Code"){
					$('#restaurant_code').val(result.code);
					$('#restaurntName').val(result.name);
					$('#restaurantModify').remove();
				}else{
					alert('일치하는 식당명이 없습니다 다른 식당명을 입력해주세요');
					$('#search').val("");
				}
			}					
		});
	}
}


function modifyRestaurant(){
	var search = '';
	  search += '<div id="restaurantModify">'
   	  search +=		'<label> 식당명 : </label>'
	  search +=		'<input type="text" value="" id="search_restaurant" placeholder="수정할 식당명을 입력해주세요."></input>'
	  search +=		'<input type="button" value="검색" onclick= "return searchRestaurant();"></input>'
	  search +=		'<a href="#" onclick= "return noRestaurantModify();">[취소]</a>'
	  search +='</div>'
	  $('#restaurantModify').remove();
	  $('.restaurantInfo').append(search);	
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

function noRestaurantModify(){
	$('#restaurantModify').remove();
}

</script>
