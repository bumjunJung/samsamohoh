<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>식당이력</title>
<script src="${pageContext.request.contextPath }/js/Chart-2.1.6.js"></script>
</head>
<body>
<div id="memberAnalysis" class="row">
	<h3 class="page-header">※ 식당이력</h3>
        <span id="adminMsg">(단위:%)</span>
		<div class="analysistable" align="center">
			<table class="table table-bordered table-hover table-srtiped">
				<col width="10%">
		   		<col width="90%">
				<thead>
					<tr>
						<td>검색</td>
						<td id="left_td2">
							<span>대상 :</span> 
								 <select id="member_name">
								 	<option id="nm" value="">-이름-</option>
								 </select>
							<span id="type">구분 :</span> 
								<select id="meal_type">
									<option value="0">점심</option>
									<option value="1">저녁</option>
									<option value="2">간식</option>
									<option value="3">회식</option>
								 </select>
						</td>
					</tr>				
				</thead>
				<tbody>
					<tr>
						<td colspan="2" id="chartTd">
							<canvas id="myChart" width="500" height="500"></canvas>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span>상위 5건씩 조회 됩니다.</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
</div>
<script>
$(document).ready(function() {
	initialize();
	getChart();
	if(sessionStorage.getItem('grade')!= 'admin'){
		$('#member_name option[id="nm"').remove();
	}else{
		$('#adminMsg').append('-관리자는 최초 관리자 기준으로 나타납니다.');
	}
	if(sessionStorage.getItem('id') == null|| sessionStorage.getItem('id') == undefined){
		var nameValues= "";
		var mealValues= "";
		nameValues += '<option value="">- 이름 -</option>';
		mealValues += '<option value="">- 식사 -</option>';
		$('#member_name').append(nameValues);
		$('#meal_type').empty();
		$('#meal_type').append(mealValues);
		alert('로그인 후 사용해주세요.');
		
	}
	
});

	function clearCanvas()
	{
		$('#myChart').remove();
		$('#chartTd').append('<canvas id="myChart" width="500" height="500"></canvas>');
	}

	
	$('#member_name').change(function(){
		clearCanvas()
		getChart()
	});
	$('#meal_type').change(function(){
		clearCanvas()
		getChart()
	});

function initialize(){
	// select - 이름 
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/getMemberName",
		error:function(result){
			alert('사용자명을 불러오지 못했습니다.');
		},
		success:function(result){
			var nameValues= "";
			$.each(result, function(i,v){
				nameValues += '<option value="'+v.id+'">' + v.name + '</option>';
			});
			$('#member_name').append(nameValues);
		}
	});
}


function getChart(){
	//  chart - label
	if($('#meal_type').val()){
		$.ajax({
			type:"post",
			dataType:"json",
			url:"${pageContext.request.contextPath}/getRchartLabel",
			data: {
	            'id':$('#member_name').val(),
	            'meal_type': $('#meal_type').val()
	         },
			error:function(result){
				alert('라벨명을 불러오지 못했습니다.');
			},
			success:function(result){
				getData(result)
			}
		});
	}
}



function getData(result){
//  chart - data
if($('#meal_type').val()){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${pageContext.request.contextPath}/getRChartData",
		data: {
			'id':$('#member_name').val(),
            'meal_type': $('#meal_type').val()
         },
		error:function(data){
			alert('차트데이터를 불러오지 못했습니다.');
		},
		success:function(dataResult){
			drawChart(result, dataResult)
		}
	});
	
}
	
}


function drawChart(result,dataResult){
	var label = [];
	var data = [];
	var bgColor = [
	                'rgba(54, 162, 235, 0.4)', // 파
	                'rgba(255, 206, 86, 0.6)',  // 노
                   'rgba(255,99,132,0.4)',      // 빨
					'rgba(000, 255, 000, 0.6)',//초
	                'rgba(255,153,204,0.2)'   // pink
	               ];
	
	if(result.length < 1 || result == undefined){
		label.push('결과값이 없습니다.');
	}else{
		$.each(result, function(i,v){
			label.push(v.name);
		});
	}
	
	if(dataResult.length < 1 || dataResult == undefined){
		data.push('0');
	}else{
		data = dataResult;
	}
	
	var ctx = document.getElementById("myChart");
	var myChart = new Chart(ctx, {
	    type: 'pie',
	    data: {
	        labels: label, 
	        datasets: [{
			            label: '# of Votes',
			            data: data,
			            backgroundColor: bgColor,
			            borderColor: bgColor,
	        }]
	    },
	    options: {
    	   responsive: true,
    	   maintainAspectRatio: false
	    }
	});
}
</script>

</body>
</html>
