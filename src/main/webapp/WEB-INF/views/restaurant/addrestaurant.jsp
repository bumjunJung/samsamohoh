<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">식당추가</h1>
    </div>
</div>

<div class="addRestaurantBigBox">
<img alt="" src="../samsamohoh/img/notice.png" width="30" height="30">
			식당이름은 필수 입력 사항이며, 새로운 식당을 추가 가능합니다.
			<br/><br/>
<form action="${pageContext.request.contextPath}/doAddRestaurant" name="addRestaurantForm" id="addRestaurantForm" method="POST" enctype="multipart/form-data">
	<div class="addRestaurantForm">
		<div class="inputRestaurant">
			식당이름
			<input class="form-control" name="restaurantName" id="restaurantName" value="" placeholder="식당이름을 입력해주세요."/>
		</div>
		<hr />
		<div class="inputRestaurant">
			<input type="file" value="사진 찾기" name="restaurantImage" /><br />
			※메뉴판 또는 음식사진을 수정하여서 올려주세요!
		</div>
		<hr />
		<div class="inputRestaurant">
			주메뉴 <input class="form-control" name="restaurantMenu" placeholder="주메뉴를 입력해주세요."/>
		</div>
		<div class="inputRestaurant">
			태그 <input class="form-control" name="restaurantTag" placeholder="태그를 입력해주세요."/>
		</div>
		<hr />
		<input type="button" class="btn btn-default" id="btnRestaurantRegister" value="등록">
	</div>	
	<input type="hidden" name="reg_id" id="reg_id" value=""/>
</form>
</div>

<script>
	var addRestaurantForm = $('#addRestaurantForm'),
	restaurantName = $('#restaurantName');
	
	$(document).ready(function(){
		addRestaurant();
	});	
	
	function addRestaurant() {
		$('#btnRestaurantRegister').on("click", function() {
			validate(function(vResult) {
				if (vResult) {
					if (confirm("등록 하시겠습니까?") == true) {
						addRestaurantForm.submit();
					} else {
						return false;
					}
				} else {
					return false;
				}
			});				
		});
	}

	function validate(vCallback) {
		var blank_pattern = /^\s+|\s+$/g;

		if (restaurantName.val().replace(blank_pattern, '') == "") {
			alert("※필수 입력 사항입니다.");
			vCallback(false);
			return false;
		}

		if (restaurantName.val() == "") {
			alert("※ 필수 입력 사항입니다.");
			vCallback(false);
			return false;
		}
		
		restaurantNameCheck(restaurantName.val(), function(result) {
			if (result > 0) {
				alert("※ 이미 등록된 이름 입니다.");
				vCallback(false);
				return false;
			} else {
				vCallback(true);
			}
		});

	}


	function restaurantNameCheck(restaurant_name, callback) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "${pageContext.request.contextPath}/doRestaurantNameCheckAjax",
			data : {
				'RESTAURANT_NAME' : restaurant_name
			},
			error : function(result) {
				alert('관리자에게 문의해주세요.');
			},
			success : function(result) {
				callback(result);
			}
		});
	}
</script>