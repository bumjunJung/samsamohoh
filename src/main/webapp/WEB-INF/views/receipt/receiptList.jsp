<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
<h3 class="page-header">※ 영수증 관리</h3>
      <div class="table" align="center">
      <table class="table table-bordered table-hover table-srtiped">
      	<col width="5%">
      	<col width="19%">
      	<col width="19%">
      	<col width="19%">
      	<col width="19%">
      	<col width="19%">
<!--       	<col width="*"> -->
      	<thead>
      	<tr>
      		
      		<td id="left_td" colspan="2">
				<input type="button" id="modifyBtn" name="code" value="수정">
				<input type="button" id="deleteBtn" name="code" value="삭제">
      		</td>
      		<td id="right_td" colspan="4">
              	<select id="year"><option value="">YYYY</option></select>년
	  			<select id="month"><option value="">MM</option></select>월
      		</td>
      	</tr>
      	</thead>
      	<thead>
      		<tr>
      			<th>선택</th>
      			<th>일자</th>
      			<th>구분</th>
      			<th>인원</th>
      			<th>금액</th>
      			<th>비고</th>
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
	function initialize(date) {
		$('#year').append(getYearsOptions(date));
		$('#month').append(getMonthOptions());
		receiptlist()
	}

	$(document).ready(function() {
		initialize(new Date());
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

	$('#year').change(function(e) {
		receiptlist();
	});

	$('#month').change(function() {
		receiptlist();
	});
	
	$("#modifyBtn").click(function() {
		 var seq = "";
		 $("input[name=seq]:checked").each(function() {
		 seq = $(this).val();
		 });
		 if(seq){
			 window.location.href = '${pageContext.request.contextPath}/modifyReceiptForm?seq='+seq+'&msg='+"";
		 }else{
			 alert('수정할 영수증을 선택해주세요');
		 }
	});
	 
	$("#deleteBtn").click(function() {
		 var seq = "";
		 $("input[name=seq]:checked").each(function() {
		 seq = $(this).val();
		 });
		 
		 deleteReceipt(seq);
	});
	
	function receiptlist(page) {
		if(page == null || page == undefined){
			page = 1;
		}
		$.ajax({
					type : "post",
					dataType : "json",
					url : "${pageContext.request.contextPath}/receiptListAjax",
					data : {
						'searchYear' : $('#year').val(),
						'searchMonth' : $('#month').val(),
						'page' : page
					},
					error : function(result) {
						alert('영수증 목록을 불러오지 못했습니다.');
					},
					success : function(result) {
						var payDiv = "";
						if (result.list != '') {
							$.each(result.list,function(i, v) {
												payDiv += '			<tr class="participantname'+v.pay_seq+'">'
												payDiv += '			<td><input type="checkbox" id="seq" name= "seq" value="'+v.pay_seq+'"/></td>'
												payDiv += '			<td>'+ v.settling_date + '</td>'
												if (v.meal_type == '0') {
													payDiv += '			<td>점심</td>'
												} else if (v.meal_type == '1') {
													payDiv += '			<td>저녁</td>'
												} 
												payDiv += '			<td><a href="#" onclick="return participantMembers('+v.pay_seq+');">' + v.cnt + '명</a></td>'
												payDiv += '			<td>' + v.total_price + '원</td>'
												payDiv += '			<td>' + v.etc + '</td>'
												payDiv += '			</tr>'
											});
						} else {
							payDiv += '			<tr>'
							payDiv += '			<td colspan="6">조회결과가 없습니다.</td>'
							payDiv += '			</tr>'
						}
						var pageNav = "";
						var startPage = "";
						
						for(var i=1; i<=result.pageLimit; i++){

							if((result.page) == (result.startPage + i - 1)){
								pageNav += '<strong id="no">' + (result.startPage + i -1)+'</strong> | ';
							}else{
								pageNav += '<a href="javascript:;" onclick="return receiptlist('+(result.startPage + i -1)+');">'+ (result.startPage + i -1) +'</a> | ';
							}
						}
						if(result.totalPage > (result.startPage+result.defaultPageLimit)-1){
							pageNav += '<a href="javascript:;" onclick="return receiptlist('+(result.startPage+result.defaultPageLimit)+');">&gt;</a> | ';
							pageNav += '<a href="javascript:;" onclick="return receiptlist('+result.totalPage+');">&gt;&gt;</a> | ';
						}
						if((result.startPage -1) > 0 && result.page > 10){
							var prevNav = '';
							prevNav += '<a href="javascript:;" onclick="return receiptlist('+1+');">&lt;&lt;</a> | ';
							prevNav += '<a href="javascript:;" onclick="return receiptlist('+(result.startPage - result.defaultPageLimit)+');">&lt;</a> | ';
							pageNav = prevNav + pageNav;
						}
						
						$('#content').html(payDiv);
						$('#pageNavigation').html(pageNav);
					}
				});
	}

	function deleteReceipt(seq,page){
		var no = $('#no').text();
		if(page == null || page == undefined){
			page = parseInt(no);
		}
		if(seq){
			$.ajax({
				type : "post",
				dataType : "json",
				url : "${pageContext.request.contextPath}/deleteReceipt",
				data : {
					'seq' : seq,
					'searchYear' : $('#year').val(),
					'searchMonth' : $('#month').val(),
					'page' : page
				},
				error : function(result) {
					alert('영수증이 삭제 되지않았습니다.');
				},
				success : function(result) {
					var payDiv = "";
					if (result != '') {
						$.each(result,function(i, v) {
											payDiv += '			<tr class="participantname'+v.pay_seq+'">'
											payDiv += '			<td><input type="checkbox" id="seq" name= "seq" value="'+v.pay_seq+'"/></td>'
											payDiv += '			<td>'+ v.settling_date + '</td>'
											if (v.meal_type == '0') {
												payDiv += '			<td>점심</td>'
											} else if (v.meal_type == '1') {
												payDiv += '			<td>저녁</td>'
											} 
											payDiv += '			<td><a href="#" onclick="return participantMembers('+v.pay_seq+');">' + v.cnt + '명</a></td>'
											payDiv += '			<td>' + v.total_price + '원</td>'
											payDiv += '			<td>' + v.etc + '</td>'
											payDiv += '			</tr>'
										});
					} else {
						payDiv += '			<tr>'
						payDiv += '			<td colspan="5">조회결과가 없습니다.</td>'
						payDiv += '			</tr>'
					}
	
					$('#content').html(payDiv);
					alert('삭제되었습니다.'); 
				}
			});
		}else{
			alert('삭제할 영수증을 선택해주세요.');
		}
	}
	
	function participantMembers(pay_seq){
	
		 var names = "";
		names = $('.names'+pay_seq+'').val();
		if(names == undefined){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"${pageContext.request.contextPath}/getParticipantNames",
				data: {
		                 'pay_seq' : pay_seq,
		              },
				error:function(result){
					alert('참가자를 불러오지 못했습니다.');
				},
				success:function(result){
					$('.participantname'+pay_seq+'').after('<tr class = "names'+pay_seq+'"><td colspan="2">참가자 명</td><td colspan="4">'+result+'</td></tr>');
				}					
			});
		}else{
			$('.names'+pay_seq+'').remove();
		} 
	}	
</script>
