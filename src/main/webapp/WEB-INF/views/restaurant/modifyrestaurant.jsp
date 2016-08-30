<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">식당정보 수정</h1>
    </div>
</div>
<img alt="" src="../samsamohoh/img/notice.png" width="30" height="30">
		식당 이름은 필수 입력사항이며, 기존 싱당의 정보를 수정 가능합니다.
	<br/><br/>
<div class="modifyRestaurantForm">
	<form action="${pageContext.request.contextPath}/doModifyRestaurantPage" name="modifyRestaurantForm" id="modifyRestaurantForm" method="POST" enctype="multipart/form-data">
		<div class="addRestaurantForm">
			<div class="inputRestaurant">
				식당이름
				<input class="form-control" name="restaurantName" id="restaurantName" value="" />
				<i class="validateMessage"></i>
			</div>
			<br />
			<div class="inputRestaurant">
				<span id="imageModify"></span> <br /> 
				<input type="file" value="사진 찾기" name="restaurantImage"/><br />
				※메뉴판 또는 음식사진을 찍어서 올려주세요!
			</div>
			<br />
			<div class="inputRestaurant">
				주메뉴 <input class="form-control" name="restaurantMenu"/>
			</div>
			<div class="inputRestaurant">
				태그 <input class="form-control" name="restaurantTag"/>
			</div>
			<div class="inputRestaurant"> 
				영업여부 <br />	
				<input type="radio" name="restaurantUse_Yn" value="Y">Y
				<input type="radio" name="restaurantUse_Yn" value="N">N
			</div>
			<hr />

			<input type="button" class="btn btn-default" id="btnRestaurantModify" value="수정"/>
			</div>	
		<input type="hidden" name="update_id" id="update_id" value="" />
		<input type="hidden" name="CODE" value="" id="CODE"/>
		<input type="hidden" name="originalImage" value="" id="originalImage" />
	</form>
</div>

<script>
	$(document).ready(function(){
		var CODE = getUrlVars();
		$('#CODE').val(CODE);
		getRestaurantInfo(CODE);
	});
	
	function getUrlVars(){
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1);
        hash = hashes.split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
	    return hash[1];
	}
	
	function validate(){
		if($('#restaurantName').val() == "" || $('#restaurantName').val() == null){
			alert("※ 필수 입력 사항입니다.");
			$('#restaurantName').focus();
			return false;
		} else {
			return true;
		}
	}
	
	function getRestaurantInfo(CODE){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/doModifyDetailRestaurantAjax",
			data: {
	               'CODE': CODE
	        },
			error:function(result){
				alert('error');
			},
			success:function(result){				
				var restaurantHtml = "",
				returnValue = "";
				$.each(result, function(index, value){     
					console.log( index + " : " + value.reg_date + " : " + value.reg_id + " : " + value.code);

					restaurantHtml +=	'	<form action="${pageContext.request.contextPath}/doModifyRestaurantPage" name="modifyRestaurantForm" id="modifyRestaurantForm" method="POST" enctype="multipart/form-data">                                                        	 ';  
					restaurantHtml +=	'	<div class="inputRestaurant">                                                          	 ';  							                                                                                 
		 			restaurantHtml +=	'		식당이름                                                   										 ';  						                                                                                          
	 				restaurantHtml +=	'		<input class="form-control" name="restaurantName" id="restaurantName" value="'+value.name+'" />	';    		                                             
 					restaurantHtml +=	'		<i class="validateMessage"></i>                                                    	';  						                                                                                           
 					restaurantHtml +=	'	</div>                                                                                 	';  							                                                                                 
		 			restaurantHtml +=	'	<br />                                                                                	 ';  						                                                                                           
	 				restaurantHtml +=	'	<div class="inputRestaurant">                                                      		 ';
	 				if(value.imageVO.real_name == null){
	 					restaurantHtml +=	'		<span></span> <br />                  	 			';  			
	 				} else {
	 					restaurantHtml +=	'		<span>등록된 사진 : '+value.imageVO.real_name+'</span> <br />                  	 			';  		
	 				}
 					restaurantHtml +=	'		<input type="file" value="사진 찾기" name="restaurantImage"/><br />					';  			
					restaurantHtml +=	'		※메뉴판 또는 음식사진을 찍어서 올려주세요!                                                        ';  							                                                                                 
		 			restaurantHtml +=	'	</div>                                                                                	 ';  						                                                                                           
	 				restaurantHtml +=	'	<br />                                                                              	';    							                                                                                           
 					restaurantHtml +=	'	<div class="inputRestaurant">                                                            ';  						                                                                                           
 					restaurantHtml +=	'		주메뉴 <input class="form-control" name="restaurantMenu" value="'+value.menu+'"/>      ';  							                                                                                 
		 			restaurantHtml +=	'	</div>                                                                                   ';  						                                                                                           
	 				restaurantHtml +=	'	<div class="inputRestaurant">                                                             ';    							                                                                                           
 					restaurantHtml +=	'		태그 <input class="form-control" name="restaurantTag" value="'+value.tag+'"/>            ';  						                                                                                           
 					restaurantHtml +=	'	</div>                                                                                    ';
 					restaurantHtml +=	'	<div class="inputRestaurant">                                                             ';    	
 					restaurantHtml +=	'		영업여부 				<br />																';  	
 					if(value.use_yn == "Y"){
 						restaurantHtml +=	'		<input type="radio" name="restaurantUse_Yn" value="Y" checked="checked">Y   		'; 
 						restaurantHtml +=	'		<input type="radio" name="restaurantUse_Yn" value="N">N   							';  
 					} else{
 						restaurantHtml +=	'		<input type="radio" name="restaurantUse_Yn" value="Y" checked="checked">Y   		';
 						restaurantHtml +=	'		<input type="radio" name="restaurantUse_Yn" value="N" checked="checked">N   		';  
 					}
 					restaurantHtml +=	'	</div>                                                                                      ';
		 			restaurantHtml +=	'	<hr />                                                                                      ';  						                                                                                           
	 				restaurantHtml +=	'	<input type="button" class="btn btn-default" id="btnRestaurantModify" value="수정"/>                      ';  	    
	 				restaurantHtml +=	'	<input type="hidden" name="update_id" id="update_id" value="'+sessionStorage.getItem('id')+'" />                                                                                    ';
		 			restaurantHtml +=	'	<input type="hidden" name="CODE" value="'+value.code+'" id="CODE"/>                                                                                  ';  						                                                                                           
	 				restaurantHtml +=	'	<input type="hidden" name="originalImage" value="" id="originalImage" />                     ';
	 				restaurantHtml +=	'	</form>                    ';
				});				
				$('#modifyRestaurantForm').html(restaurantHtml);
				$('#btnRestaurantModify').click(function(){
					if(validate()){
						if (confirm("수정 하시겠습니까?") == true){
							$('#modifyRestaurantForm').submit();		
						}else{
							return false;
						}	
					} else {
						return false;
					}				
				});
			}		
		});
	}	

</script>
	