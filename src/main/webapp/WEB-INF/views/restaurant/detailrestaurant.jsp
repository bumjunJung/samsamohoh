<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">식당상세</h1>
	</div>
</div>
<div class="addRestaurantBigBox">
	<div class="detailBox">
		<img alt="" src="../samsamohoh/img/notice.png" width="30" height="30">
			식당의 상세정보를 알 수 있고, 정보가 실제와 다를 경우 수정과 삭제가 가능합니다.
		<br/><br/>
		<input type="button" value="수정하기" id="goModifyPage" class="btn btn-default"/>
		<input type="button" value="삭제하기" id="goDeletePage" class="btn btn-default"/>
		<br /><br />
		<table border="1" class="table table-bordered table-hover table-srtiped restaurantDetailTable" id="restaurantDetailTable">
			<tr>
				<th>등록날짜</th>
				<td></td>
			</tr>
			<tr>
				<th>식당이름</th>
				<td></td>
			</tr>
			<tr>
				<th>식당 사진</th>
				<td></td>
			</tr>
			<tr>
				<th>주메뉴</th>
				<td></td>
			</tr>
			<tr>
				<th>태그</th>
				<td></td>
			</tr>
			<tr>
				<th>영업여부</th>
				<td></td>
			</tr>
		</table>
		<div class="replyBox">
			<hr />
			<h3>댓글</h3>
			<br />
			<div class="wirteReplyBox">                 
					<input type="text" id="reply_content" class="reply_content" placeholder="댓글을 작성해주세요." value=""/>                                      
					<input type="button" value="댓글입력" id="replySubmit" class="btn btn-default replySubmit"/> 
			</div>
			<div class="replyListBox">
			        <span></span><br />							
			        <span></span><br />
			        <span></span><br />
			</div>	
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){		
		var RESTAURANT_CODE = getUrlVars().CODE;		
		getRestaurantInfo(RESTAURANT_CODE);
		getRestaurantReply(RESTAURANT_CODE);
		goModifyPage(RESTAURANT_CODE);
		goDelete(RESTAURANT_CODE);
		replyEvent(RESTAURANT_CODE);
	});	
	
	function replyEvent(RESTAURANT_CODE){
		$('#reply_content').keypress(function(e){
			if(e.keyCode == 13){
				replyValidate(RESTAURANT_CODE, $('#reply_content').val(), sessionStorage.getItem('code'));	
			}
		})
		
		$('#replySubmit').on("click", function(){
			replyValidate(RESTAURANT_CODE, $('#reply_content').val(), sessionStorage.getItem('code'));	
		});	
	}
	
	function goModifyPage(RESTAURANT_CODE){
		$('#goModifyPage').on("click", function(){
			location.href = "${pageContext.request.contextPath}/modifyRestaurantPage?CODE="+RESTAURANT_CODE;
		});
	}
	
	function goDelete(RESTAURANT_CODE){
		$('#goDeletePage').on("click", function(){
			if (confirm("정말 삭제 하시겠습니까?")){
				location.href = "${pageContext.request.contextPath}/deleteRestaurant?CODE="+RESTAURANT_CODE;
			}else{
				return false;
			}			
		});
	}
	
	function replyValidate(RESTAURANT_CODE, content, member_code){
		if(content == ""){
			alert("댓글을 입력해주세요.");
		} else {
			setRestaurantReply(RESTAURANT_CODE, content, member_code);
		}		
	}
	
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
	
	function getRestaurantInfo(RESTAURANT_CODE){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/doDetailRestaurantAjax",
			data: {
	               'CODE': RESTAURANT_CODE
	        },
			error:function(result){
				alert('error');
			},
			success:function(result){				
				var restaurantHtml = "";
				$.each(result, function(index, value){
					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>등록날짜</th>						';
	 				restaurantHtml +=	'		<td>'+value.reg_date+'</td>			';
 					restaurantHtml +=	'	</tr>									';
 					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>식당이름</th>						';
	 				restaurantHtml +=	'		<td>'+value.name+'</td>				';
 					restaurantHtml +=	'	</tr>									';
 					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>식당사진</th>						';
		 			restaurantHtml +=	'		<td>								';
		 			if(value.imageVO.change_name != null){
		 				restaurantHtml +=	'			<img src="${pageContext.request.contextPath}/image/'+value.imageVO.change_name+'" class="image">		';
		 			} else {
		 				restaurantHtml +=	'			저장된 사진이 존재하지 않습니다.		';
		 			}
	 				restaurantHtml +=	'		</td>								';
 					restaurantHtml +=	'	</tr>									';
 					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>주메뉴</th>						';
	 				restaurantHtml +=	'		<td>'+value.menu+'</td>				';
 					restaurantHtml +=	'	</tr>									';
 					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>태그</th>							';
	 				restaurantHtml +=	'		<td>'+value.tag+'</td>				';
 					restaurantHtml +=	'	</tr>									';
 					restaurantHtml +=	'	<tr>									';			
		 			restaurantHtml +=	'		<th>영업여부</th>						';
	 				restaurantHtml +=	'		<td>'+value.use_yn+'</td>			';
 					restaurantHtml +=	'	</tr>									';
				});				
				$('#restaurantDetailTable').html(restaurantHtml);
			}	
		});
	}
	
	function setRestaurantReply(RESTAURANT_CODE, CONTENT, MEMBER_CODE, idToken){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/doSetRestaurantReplyAjax",
			data: {
	               'RESTAURANT_CODE' : RESTAURANT_CODE,
	               'CONTENT' : CONTENT,
	               'MEMBER_CODE' : MEMBER_CODE,
	               'idToken' : idToken
	        },
			error:function(result){
				alert('error');
			},
			success:function(result){
				getRestaurantReply(RESTAURANT_CODE);
				$('#reply_content').val("");
				$('#reply_content').focus();	
			}	
		});
	}
	
	function getRestaurantReply(RESTAURANT_CODE){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/doGetRestaurantReplyAjax",
			data: {
	               'CODE' : RESTAURANT_CODE
	        },
			error:function(result){
				alert('error');
			},
			success:function(result){				
				var replyHtml = "", i = 0;
				$.each(result, function(index, value){
					if(value.id != sessionStorage.getItem('id')){
						replyHtml +=	'	<div class="replyListBox"> 											';
						replyHtml +=	'	<div class="replyListBox1"> 										';
						replyHtml +=	'		<span>['+value.write_date+']'+value.name+'->'+value.content+' </span> ';	
						replyHtml +=	'	</div>																';
						replyHtml +=	'	</div>																';	
					} else {
						replyHtml +=	'	<div class="replyListBox"> 											';
						replyHtml +=	'	<div class="replyListBox2"> 										';
						replyHtml +=	'		<input type="button" value="삭제" id="deleteReplyButton'+value.seq+'" class="btn btn-default deleteReplyButton"/>';
						replyHtml +=	'		<span>'+value.content+' <- '+value.name+'['+value.write_date+']</span><br /> ';	
						replyHtml +=	'	</div>																';
						replyHtml +=	'	</div>																';	
					}	
				});									
				$('.replyListBox').html(replyHtml);
				setDeleteRestaurantReply(result, RESTAURANT_CODE);				
			}	
		});	
	}
	
	function setDeleteRestaurantReply(result, RESTAURANT_CODE){
		$.each(result, function(index, value){
			$("#deleteReplyButton"+value.seq).on("click", function(){
				if (confirm("정말 삭제 하시겠습니까?")){
					$.ajax({
						type:"post",
						dataType:"json",
						url:"${pageContext.request.contextPath}/doDeleteRestaurantReplyAjax",
						data: {
				               'CODE' : RESTAURANT_CODE,
				               'SEQ' : value.seq
				        },
						error:function(result){
							alert('error');
						},
						success:function(result){
							getRestaurantReply(RESTAURANT_CODE);
						}	
					});				
				}else{
					return false;
				}					
			});
		});	
	}
</script>