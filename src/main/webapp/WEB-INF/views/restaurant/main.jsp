<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Home</h1>
    </div>
</div>
<div class="mainBigBox" id="mainBigBox">
	<div class="topBigBox" id="topBigBox">
		<img alt="" src="../samsamohoh/img/notice.png" width="30" height="30">
			아래의 식당 목록에서 마음에 드는 식당이 있으면 패밀리를 추가하거나 이동할 수 있습니다.
			<br/><br/>
		<div id="topBox" class="topBox">
			<div class="familyListBox" id="familyListBox">
				
			</div>
		</div>
	</div>
	<hr />
	<div class="middleBox">
		<input type="button" value="식당추가" class="btn btn-default addRestaurant" id="addRestaurant"/>
		<div class="searchBox">
			<input type="text" class="searchText" id="searchText" placeholder="Search...">
			<button class="btn btn-default btn-restaurantSearch" type="button" id="searchButton">
				<i class="fa fa-search"></i>
			</button>
		</div>		
	</div>
	<div class="bottomBigBox" id="bottomBigBox">
		<img alt="" src="../samsamohoh/img/notice.png" width="30" height="30">
			식당 목록입니다. 오른쪽 식당추가 버튼을 클릭하여 식당 정보를 추가 할 수 있습니다. 사진을 클릭하면 식당정보를 상세히 볼 수 있습니다.
			<br/><br/>
		<div class="bottomBox" id="bottomBox">
			<div class="mainListBox" id="mainListBox">
				<div class="boxTitle">
					<a href="${pageContext.request.contextPath}/detailRestaurant"><span></span></a>
				</div>
				<div class="boxImage">
				
				</div>
				<div class="joinButtonBox">
					<input type="button" class="joinButton" id="joinButton" value="생성하기" />
				</div>
			</div>
		</div>
	</div>
	<hr />
</div>

<script>
var family;
$(document).ready(function(){
	if(!sessionStorage.getItem('id')){
		location.href="${pageContext.request.contextPath}/logout";
	} else {
		searchEvent();
		getFamily();
		$('#searchText').focus();
		addRestaurant();
	}	
});

function addRestaurant(){
	$('#addRestaurant').click(function(){
		location.href="${pageContext.request.contextPath}/addRestaurantPage";
	});
}

function searchEvent(){
	$('#searchText').keypress(function(e){
		if(e.keyCode == 13){
			if($('#searchText').val()){
				searchRestaurant($('#searchText').val());
			} else {
				getRestaurantList();
			}
		}
	})
	$('#searchButton').click(function(){
		if($('#searchText').val()){
			searchRestaurant($('#searchText').val());
		}else {
			getRestaurantList();
		}
	});
}

function getRestaurantList(){
	var membersInMy, mainHtml = "";
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/doGetListRestaurantPageAjax",
		error:function(result){
			alert('관리자에게 문의해주세요.');
		},
		success:function(result){	
			membersInMy = result[0].myFam;
			$.each(result, function(index, rValue){
				if(index > 0){
					mainHtml += '       <div class="mainListBox" id="mainListBox">                                                     		';
					mainHtml += '       	<div class="boxTitle">                                                                    		 ';
					mainHtml += '       			<span>'+rValue.name+'</span>                                                        		';
					mainHtml += '       	</div>                                                                                     		';
					mainHtml += '       <a href="${pageContext.request.contextPath}/detailRestaurant?CODE='+rValue.code+'">     				'; 
					mainHtml += '       	<div class="boxImage">                                                                     		';
					if(rValue.change_name != null){
						mainHtml += '       		<img src="${pageContext.request.contextPath}/image/'+rValue.change_name+'" width="398" height="253">     		';
					} else {
						mainHtml += '이미지를 등록해주세요^^     ';
						mainHtml += '<br /><br />   <img src="${pageContext.request.contextPath}/img/noImage.png" width="200" height="200">     					';
					}				
					mainHtml += '       	</div>                                                                                     		';
					mainHtml += '       	</a>                                                                   					   		';
					mainHtml += '       	<div class="joinButtonBox">  ';
					if(membersInMy == "no" || family == ""){
						mainHtml += '<input class="btn btn-outline btn-primary joinButton" id="joinButton'+rValue.code+'" type="button" value="참여하기"/>';
					} else {
						$.each(family, function(index, fValue){	
							if(findMemOfMembers(fValue.members)){
								if(fValue.code == rValue.code){
									mainHtml += '<input class="btn btn-outline btn-default joinButton" id="joinButton'+rValue.code+'" type="button" value="★참여중☆" disabled/>';
								} else {
									mainHtml += '<input class="btn btn-outline btn-primary joinButton moveButton" id="moveButton'+rValue.code+'" type="button" value="참여하기"/>';
								}
							}
						});
					}
				}
				mainHtml += '       	</div>                                                                                     		';
				mainHtml += '       </div>                                                                             					';
			});			
			$('#bottomBox').html(mainHtml);
			moveButtonClick(result);
			joinFamily(result);
		}		
	});
}


function searchRestaurant(search_word){
	var membersInMy, mainHtml = "";
	$.ajax({
		type:"post",
		dataType:"json",
		data: {
            'SEARCH_WORD' : search_word
     	},
		url:"${pageContext.request.contextPath}/doSearchRestaurantAjax",
		error:function(result){
			alert('관리자에게 문의 해주세요.');
		},
		success:function(result){
			membersInMy = result[0].myFam;
			if(result == ""){
				mainHtml = "결과가 없습니다.<br /> 다시 한 번 확인 바랍니다.";
			} 
			$.each(result, function(index, rValue){	
				console.log(index + " ," + rValue.code);
				if(index > 0){
					mainHtml += '       <div class="mainListBox" id="mainListBox">                                                     		';
					mainHtml += '       	<div class="boxTitle">                                                                    		 ';
					mainHtml += '       			<span>'+rValue.name+'</span>                                                        		';
					mainHtml += '       	</div>                                                                                     		';
					mainHtml += '       <a href="${pageContext.request.contextPath}/detailRestaurant?CODE='+rValue.code+'">     				'; 
					mainHtml += '       	<div class="boxImage">                                                                     		';
					if(rValue.change_name){
						mainHtml += '       		<img src="${pageContext.request.contextPath}/image/'+rValue.change_name+'" width="400" height="253">     		';
					} else {
						mainHtml += '이미지를 등록해주세요^^     ';
						mainHtml += '<br /><br />   <img src="${pageContext.request.contextPath}/img/noImage.png" width="200" height="200">     					';
					}				
					mainHtml += '       	</div>                                                                                     		';
					mainHtml += '       	</a>                                                                   					   		';
					mainHtml += '       	<div class="joinButtonBox">  ';
					if(membersInMy == "no" || family == ""){
						mainHtml += '<input class="btn btn-outline btn-primary joinButton" id="joinButton'+rValue.code+'" type="button" value="참여하기"/>';
					} else {
						$.each(family, function(index, fValue){	
							if(findMemOfMembers(fValue.members)){
								if(fValue.code == rValue.code){
									mainHtml += '<input class="btn btn-outline btn-default joinButton" id="joinButton'+rValue.code+'" type="button" value="★참여중☆" disabled/>';
								} else {
									mainHtml += '<input class="btn btn-outline btn-primary joinButton moveButton" id="moveButton'+rValue.code+'" type="button" value="참여하기"/>';
								}
							}
						});
					}			
					mainHtml += '       	</div>                                                                                     		';
					mainHtml += '       </div>                                                                             					';
				}
			});			
			$('#bottomBox').html(mainHtml);
			moveButtonClick(result);
			joinFamily(result);
		}		
	});		
}

function moveButtonClick(result){
	$.each(result, function(index, value){
		$('#moveButton'+value.code).on("click", function(){
			doMoveButtonClick(value.code);
		});
		
		$('#moveButton'+value.seq).on("click", function(){
			doMoveButtonClick(value.code);
		});
	});	
}

function doMoveButtonClick(code){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/doMoveButtonClickAjax",
		data: {
               'RESTAURANT_CODE' : code,
               'MEMBER_CODE' : sessionStorage.getItem('code')
        },
		error:function(result){
			alert('관리자에게 문의 해주세요.');
		},
		success:function(result){
			getFamily();	
		}			
	});			
}

function joinFamily(result){
	$.each(result, function(index, value){
		$('#joinButton'+value.code).on("click", function(){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/doJoinSetFamilyAjax",
				data: {
		               'RESTAURANT_CODE' : value.code,
		               'MEMBER_CODE' : sessionStorage.getItem('code')
		        },
				error:function(result){
					alert('관리자에게 문의 해주세요.');
				},
				success:function(result){
					getFamily();
				}		
			});
		});
	});	
}

function getFamily(){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/doGetFamilyAjax",
		error:function(result){
			alert('관리자에게 문의 해주세요.');
		},
		success:function(result){
			var familyHtml = "", members;
			if(!result){
				familyHtml = "아직 패밀리가 없습니다. <br />패밀리를 등록해주세요.";
			} else {
				family = result;
			}
			$.each(result, function(index, value){
				if(value.members){	
					familyHtml += '       <div class="familyListBox" id="familyListBox">                                                         ';
					familyHtml += '       	<div class="familyTitle">                                                                            ';
					familyHtml += '       		<div class="boxTitleFamily">                                                                     ';
					familyHtml += '       				<span>'+value.restaurant_name+'</span>                                                   ';
					familyHtml += '       		</div>                                                                                           ';
					familyHtml += '       		<div class="boxMembers">                                                                         ';
					familyHtml += '       			<input type="button" value="▽" id="membersList'+value.seq+'" class="btnMembers btn btn-default"/>';
					familyHtml += '       		</div>                                                                                           ';
					familyHtml += '       	</div>                                                                                               ';
					familyHtml += '       		<a href="${pageContext.request.contextPath}/detailRestaurant?CODE='+value.code+'">               '; 
					familyHtml += '       	<div class="boxFamliyImage" id="boxFamliyImage'+value.seq+'">                                                                         ';
					if(value.change_name){
						familyHtml += '       		<img src="${pageContext.request.contextPath}/image/'+value.change_name+'" width="398" height="253">               ';
					} else {
						familyHtml += '이미지를 등록해주세요^^                                                                                          ';
						familyHtml += '<br /><br />   <img src="${pageContext.request.contextPath}/img/noImage.png" width="200" height="200">                         ';
					}				
					familyHtml += '       	</div>                                                                                     ';
					familyHtml += '       	</a>                                                                   					   ';
					familyHtml += '       	<div class="joinButtonBox">                                                                ';
					if(findMemOfMembers(value.members)){
						familyHtml += '       		<input class="btn btn-outline btn-default famlilyJoinButton" id="famlilyJoinButton'+value.seq+'" type="button" value="★참여중☆" disabled/>';
					} else{
						familyHtml += '       		<input class="btn btn-outline btn-primary famlilyJoinButton" id="moveButton'+value.seq+'" type="button" value="참여하기" />';
					}					
					familyHtml += '       	</div>                                                                                       ';
					familyHtml += '       </div>                                                                                         ';
				} else{
					familyHtml += '';
				}
			});					
			$('#topBox').html(familyHtml);
			getMembers(result);
			moveButtonClick(result);
			getRestaurantList();
		}			
	});
}

function findMemOfMembers(members){
	var result = "",
	member = [];
	if(members){
		member = members.split(', ');
		for(var i = 0; i < members.length; i++){
			if(member[i] == sessionStorage.getItem('code')){
				return true;
			} else {
				result = false;
			}	
		}
	} else {
		result = false;
	}
	return result;
}

function getMembers(result){
	$.each(result, function(index, value){
		var count = 0,
		membersName = new Array(),
		seq = value.seq;
		$('#membersList'+value.seq).on("click", function(){
			count++;
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/doGetMembersAjax",
				data: {
		               'FAMILY_SEQ' : value.seq
		        },
				error:function(result){
					alert('관리자에게 문의해주세요.');
				},
				success:function(result){
					var familyHtml = "",
					members;
					$.each(result, function(index, value){
						if(count % 2 != 0){
							if(value.members != null){
								members = value.members.split(', ');
								for(var i = 0; i < members.length; i++){
									membersName[i] = getMembersName(members[i]);
								}
								for(var j = 0; j < members.length; j++){
									familyHtml += ''+membersName[j]+'    <br />                                                                    ';
								}
							}		
						} else {
							if(value.change_name != null){
								familyHtml += '       		<img src="${pageContext.request.contextPath}/image/'+value.change_name+'" width="400" height="253">     ';
							} else {
								familyHtml += '이미지를 등록해주세요^^                                                                                ';
								familyHtml += '<br /><br />   <img src="${pageContext.request.contextPath}/img/noImage.png" width="200" height="200">               ';
							}		
						}		
					});
					$('#boxFamliyImage'+seq).html(familyHtml);	
				}		
			});
		});
	});	
}

function getMembersName(code){
	var memberName = "";
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/doGetMembersNameAjax",
		async:false,
		data: {
               'MEMBER_CODE' : code
        },
		error:function(result){
			alert('관리자에게 문의 해주세요.');
		},
		success:function(result){
			var familyHtml = "";
			$.each(result, function(index, value){
				memberName = value.name;
			});
		}
	});	
	return memberName;
}



</script>