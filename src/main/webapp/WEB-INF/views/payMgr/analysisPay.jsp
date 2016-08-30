<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>지출이력</title>
<script src="${pageContext.request.contextPath }/js/Chart-2.1.6.js"></script>
</head>
<body>
<div id="memberAnalysis" class="row">
  <h3 class="page-header">※ 지출이력</h3>
    <span id="adminMsg">(단위:원)</span>
    <div class="analysistable" align="center">
      <table class="table table-bordered table-hover table-srtiped">
        <col width="10%">
           <col width="90%">
        <thead>
          <tr>
            <td class="searchTd">검색</td>
            <td id="left_td2">
              <span>일자 :</span> 
                  <select id="year"><option value="">YYYY</option></select>년
                  <select id="month"><option value="">MM</option></select>월
              <span class="subName">대상 :</span> 
                 <select id="member_name">
                  <option id="nm" value="">-이름-</option>
                 </select>
              <span id="type" class="subName">구분 :</span> 
                <select id="meal_type">
                  <option value="">전체</option>
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
        </tbody>
      </table>
    </div>
</div>
<script>
$(document).ready(function() {
  getDate(new Date());
  initialize();
  getChart();
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
  
  
  
function getDate(date){
  $('#year').append(getYearsOptions(date));
  $('#month').append(getMonthOptions());
}  

function clearCanvas(){
  $('#myChart').remove();
  $('#chartTd').append('<canvas id="myChart" width="500" height="500"></canvas>');
}


$('#year').change(function(){
  clearCanvas()
  getChart()
});
$('#month').change(function(){
  clearCanvas()
  getChart()
});
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
  //  chart - label & data
  $.ajax({
    type:"post",
    dataType:"json",

    url:"${pageContext.request.contextPath}/getPriceLabel",
    data: {
            'id':$('#member_name').val(),
            'meal_type': $('#meal_type').val(),
            'searchYear': $('#year').val(),
            'searchMonth': $('#month').val()
         },
    error:function(result){
      alert('라벨명을 불러오지 못했습니다.');
    },
    success:function(result){
      getData(result)
    }
  });
}

function getData(result){
//  chart - data
  $.ajax({
    type:"post",
    dataType:"json",
    url:"${pageContext.request.contextPath}/getPriceData",
    data: {
            'id':$('#member_name').val(),
            'meal_type': $('#meal_type').val(),
            'searchYear': $('#year').val(),
            'searchMonth': $('#month').val()
         },
    error:function(dataresult){
      alert('차트데이터를 불러오지 못했습니다.');
    },
    success:function(dataresult){
      drawChart(result, dataresult)
    }
  });
}

function drawChart(result,dataresult){
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
    label = result;
  }
  
  if(dataresult.length < 1 || dataresult == undefined){
    data.push('0');
  }else{
    $.each(dataresult, function(i,v){
      data.push(v.price);
    });
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