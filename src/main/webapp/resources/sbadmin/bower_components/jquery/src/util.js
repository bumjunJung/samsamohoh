/*************************************************************************
 * 파  일  명   : Util.js
 * 작  성  일   : 2015. 10. 20
 * 작  성  자   : 강환석 
 * 프로그램 용도 : 
 * <수 정 내 역>
 * 수  정  일   : 
 * 수  정  자   : 
 * 수 정 내 용  :
 *************************************************************************/
var util = {
		list : {}   //테이블 리스트 관련 처리 함수
		/*
		 *  setListContent : 리스트에 데이터 출력 (리스트데이터, 리스트를 출력할 오브젝트명, 템블릿이 들어있는 스크립트 오브젝트 명, 리스트데이터에 데이터가 없을 경우 출력할 멘트)
		 *  paging : 리스트 페이징 ()  
		 *  addRow : 행추가
		 *  removeRow : 행삭제
		 *  getCheckedData : 리스트에서 체크박스또는 라디오에 체크된 항목의 데이터를 array로 출력
		 *  getLastData : 리스트의 가장 마지막 데이터json 을 리턴
		 *  getSelectedData : 선택된 행(index) 의 데이터를 json 으로 리턴
		 */
		,string : {}   //문자열 관련 처리 함수
		/*
		 *  isNull : 내용이 null 일경우 대체 (내용, null일경우 대체할 단어)
		 *  isEmpty : 내용이 비어있는지 여부 확인 (내용)  
		 *  checkString : 문자열의 형이 맞는지 여부 체크 (문자열, 형, 추가 체크 공식)
		 *  isNumber : 숫자인지 여부 체크 (문자열)
		 *  stripTags : 문자의 html Tag 를 벗겨냄 (문자열, 잘라낼 길이)
		 *  getTelNumArray : 전화번호의 각 항목값을 array 로 리턴 getTelNumArray('01022553366')
		 *  isValidSSN  : 주민등록번호가 유효한지 확인
		 */		
		,number : {}   //숫자형 관련 처리 함수
		/*
		 *  getInt : Integer 로 변환 (문자열)
		 */	
		,format : {}   //문자열 format 변경 관련 함수
		/*
		 *  formatting : 문자열을 pattern 형태의 정규표현 형태로 변환 ( 문자열, 패턴, 반복여부)
		 *  formatRsnoXXX : 사업자 또는 주민번호 형태를 특정 위치(뒷자리)부터 XXX 형태로 리턴한다 (주민(사업자) 번호)
		 *  formatRsno : 사업자 또는 주민번호 형태로 리턴, (주민(사업자)번호, 뒷자리 전체 출력 또는 1자리만 출력 옵션)
		 *  formatTelNo : 문자열을 전화번호 형태로 변환 (문자열)
		 *  formatDate : 문자열을 날짜형태로 변환 ( 문자열, 구분패턴)
		 *  formatTime : 문자열을 시간형태로 변환 (문자열, 구분패턴)
		 *  formatDateTime : 문자열을 DateTime 형태로 변환 (문자열) 
		 *  removeFormat : 패턴 제거 ( 문자열, 구분패턴)
		 *  formatComma : 숫자에 콤마 삽입 (문자열)    
		 *  removeFormat : 패턴 제거 ( 문자열, 구분패턴)  
		 */						
		,array : {}    //array 처리 함수
		/*
		 *  getIndexOfArr : 배열리스트에서 특정문자와 일치하는 배열의 index를 반환 ( 문자, 배열)
		 *  deleteArrIndex : 배열리스트에서 특정 Index 의 항복을 삭제 (배열, 인덱스)
		 *  
		 */		
		,link : {}
		/*
		 *  goLink : 페이지 링크를 json데이터로 post 전송 (타겟페이지, 파라메터) 
		 */
		,form : {}
		/*
		 *  checkboxSet : 체크박스에서 입력된 값과 동일한 값이 있는경우 체크설정
		 *  checkboxGet : 체크박스에서 체크된 값을 가져옴 (여러개의 체크가 있기 때문에 array 로 리턴)
		 *  radioSet : 라디오에서 입력된 값과 동일한 값이 있는 경우 체크  
		 *  radioGet : 라디오에서 체크된 값을 가져옴 (String 으로 리턴)
		 *  selectSet : 셀렉트 에 값을 셋팅
		 *  selectSetJsonArray : JsonArray 를 이용해서 셀렉트에 값을 셋팅
		 *  selectGet : 셀렉트에서 selected 된 값을 가져옴 (멀티셀렉트 불가함. String 으로 리턴)
		 *  selectCheck : 셀렉트에서 특정 값이 있는 경우 selected 처리
		 *  checkInput : input 에 값이 있는지 여부 확인 (inpuID, 얼럿메시지) , 값이 없을 경우 inputID 에 focus
		 *  setCardYear  : 카드 유효기간의 연도를 select 에 지정
		 *  goNextForm : input 박스에 maxlength 에 설정된 위치에 도달하면 다음 object 에 포커스를 준다. init 시에 선언해 주거나 하면 됨.
		 *  calanederCreate : input 박스에 달력 이미지 생성 
		 *  inputSetNumber : input 에 숫자형만 지정 되도록 처리 
		 */
		,data : {}
		/*
		 *  jsonGetVal : jsonData 에서 특정지어진 항목의 대응값을 가져옴(항목명에 속한 값을 모두 array 로 리턴)
		 *  jsonGetIndex : jsonData 에서 특정 index의 데이터를 모두 가져옴 (항목:값 형태의 json data 로 리턴
		 *  clearJson : Json 객체의 value 값을 빈값으로 치환 
		 *  arrayToJson : array 로 되어 있는 데이터를 Json 데이터로 변환
		 */		
		,date : {}
		/*
		 *  today : 오늘날짜 가져오기
		 *  dateAdd : 지정한 날짜만큼의 날짜 추가 (기준일(20120101), 타입(month, day), 추가일/월 (-6) )
		 *  dateDiff : 두 날짜 사이의 기간(일)
		 *  weekDiff : 두 날짜 사이의 기간(주)
		 *  monthDiff : 두 날짜 사이의 기간(월)
		 *  yearDiff : 두 날짜 사이의 기간(연)
		 *  setDateCombo : 연월에 맞는 날짜를 콤보에 등록 (연월(201210), $('#selectID'))
		 */	
		,popup : {}
		/* 
		 *  openPopup : 레이어 팝업을 띄움 (popupID, width, height, link, param)
		 *  closePopup : 레이어 팝업을 닫음 (popupID)
		 *  popReturnVal: 팝업을 종료했을 때 받는 변수
		 *  returnValue : 레이어 팝업 닫을때 값을 리턴해 줌 (retVal, popupID)
		 */
		,validation : {}		 
		/* 
		 * 값 체크
		 */ 		

		/* XSS 필터링*/
		,xss: {}
		,keyControl:{}

};

/**************************************************************** 
 * 리스트 출력시 사용되는 함수
 ****************************************************************/ 
util.list = {
		
	/**************************************************************** 
	 * 리스트 콘텐츠를 출력한다.
	 * @param listData List data
	 * @param listObject 출력할 Object 
	 * @param templeatObject  템플릿이 있는 Object
	 * @param nodataMessage  리스트데이터에 데이터가 없을 경우 출력할 멘트
	 ****************************************************************/		
	setListContent:function(listData, listObject, templeatObject){
		util.list.setListContent(listData, listObject, templeatObject, "");
	}	
	,setListContent:function(listData, listObject, templeatObject, nodataMessage){
		$(templeatObject).tmpl(listData).appendTo(listObject);	
	}
	/**************************************************************** 
	 * 행추가
	 * @param listObject 행 추가할 object
	 * @param templeatObject 템플릿이 있는 object
	 ****************************************************************/			
	,addRow:function(listObject, templeatObject){
		var listData = {};
		util.list.addRow(listObject, templeatObject, listData);	
	}
	/**************************************************************** 
	 * 행추가
	 * @param listObject 행 추가할 object
	 * @param templeatObject 템플릿이 있는 object
	 * @param listData		 json data
	 ****************************************************************/			
	,addRow:function(listObject, templeatObject, listData){
		$(templeatObject).tmpl(listData).appendTo(listObject);	
	}
	/**************************************************************** 
	 * 행삭제
	 * @param listObject 행 추가할 object
	 * @param listIndex 삭제할 행의 index
	 ****************************************************************/		
	,removeRow:function(checkBoxName){
		$(":checkbox[name=" + checkBoxName + " ]:checked").each(					
			    function() {
			    	$(this).parent().parent().remove();				        
			    }
		);			
		
		
	}
	,setRowData:function(listObject, listIndex){
	
	}
	/******************************************************************
	 *  리스트에서 체크가 있는 경우 체크된 Row 의 json data 를 모아서 출력
	 * 
	 ******************************************************************/
	,getCheckedData:function(checkBoxName, type){
		var retVal = new Array();
		
		if (type = "radio"){

			$(":radio[name=" + checkBoxName + " ]:checked").each(					



				    function() {
				    	retVal.push($(this).parent().tmplItem().data);				        
				    }
			);			
		}else{
			$(":checkbox[name=" + checkBoxName + " ]:checked").each(					
				    function() {
				    	retVal.push($(this).parent().tmplItem().data);				        
				    }
			);			
		}
	
		return retVal;
	}
	/******************************************************************
	 *  템플릿의 가장 마지막 행의 데이터를 값을 구함. 
	 *  행이 없을 경우 빈 json 데이터가 리턴됨
	 ******************************************************************/
	,getLastData:function(listObject, templeatType){
		var retVal = {};		
		retVal = $(listObject + " " + templeatType + ":last").tmplItem().data;		
		return retVal;
	}
	/******************************************************************
	 *  템플릿의 선택된 행의 데이터를 가져옴
	 *  행이 없을 경우 빈 json 데이터가 리턴됨
	 ******************************************************************/	
	,getSelectedData:function(listObject, templeatType,index){
		var retVal = {};		
		retVal = $(listObject + " " + templeatType).get(index).tmplItem().data;		
		return retVal;		
	}
	
	/**************************************************************** 
	 * 페이징 처리
	 * @param objectID 페이징리스트가 출력될 div	 
	 * @param pStart  선택된 페이지
	 * @param pDisplay 한 화면에 보여줄 페이지 수
	 * @param pTotalCnt 전체 글 수 
	 * @param goFunction 클릭시 호출할 function
	 * @param nextPage 페이지 변수명
	 ****************************************************************/			
	,paging:function(objectID, pStart, pDisplay, pTotalCnt , goFunction, nextPage){
		
		//전체 페이지수를 구한다
		var pCount = parseInt(pTotalCnt /	pDisplay );	
		if ((pTotalCnt % pDisplay) > 0){
			pCount++;
		}		
		
		//지운다
		$(objectID).empty();
		
		if (pCount == 0){
			pCount = 1;
		}
		if (pStart == ""){
			pStart = 1;
		}
		
		var divelement = $(objectID);
		var goUrl = "";		
		//처음으로, 이전페이지로 추가
		goUrl += "<a class='btn_first' href='#'><img src='/images/bbs/prev2.gif' alt='처음' /></a> \n";
		goUrl += "<a class='btn_prev' href='#'><img src='/images/bbs/prev.gif' class='mR' alt='이전' /></a> \n";

		//전체 페이지 그룹의 갯수를 구한다.
		var pageCount = 1;
		if (pCount % pDisplay > 0){
			pageCount = (pCount / pDisplay) + 1;
		}else{
			pageCount = pCount / pDisplay;
		}
		
		//페이지 그룹의 시작 위치를 구한다.
		var pageStart = ( parseInt(pStart / pDisplay)*pDisplay) + 1;

		//페이지 그룹의 종료 위치를 구한다.
		var pageEnd = pCount;		
		if ( (pageStart + pDisplay - 1) > pCount  ){			
			pageEnd = pCount;
		}else{
			pageEnd = pageStart + pDisplay - 1 ;
		}
		
		
		for (var i = pageStart; i <= pageEnd; i++){
			//루프돌면서 스트링을 생성한다.
			
			if (i == pStart){
				goUrl += "<span class='current'>" + i + "</span> \n";				
			} else {
				goUrl += "<span><a href='#' link='" + i + "'>" + i + "</a></span> \n";
			}
		}
		
		//다음으로, 마지막으로 추가
		goUrl += "<a class='btn_next' href='#'><img src='/images/bbs/next.gif' class='mL' alt='다음' /></a> \n";
		goUrl += "<a class='btn_end' href='#'><img src='/images/bbs/next2.gif' alt='마지막' /></a> \n";		
		
		divelement.html(goUrl);		

		//click 이벤트 처리
		divelement.find("a").on("click", function(){
			
			if ($(this).attr("class") == "btn_first"){
				//처음으로		
				eval(nextPage + "=1");
			} else if ($(this).attr("class") == "btn_prev"){
				//이전페이지
				var prevPG = 1;
				if ( parseInt(pStart / pDisplay) > 0){
					prevPG = pageStart - pDisplay;					
				}else{
					prevPG = 1;										
				}
				eval(nextPage + "=" + prevPG);				
			} else if ($(this).attr("class") == "btn_next"){
				//다음페이지
				var nextPG = pCount;
				
				if (pageEnd == pCount){
					nextPG = pCount;
				}else{
					nextPG = pageEnd + 1;
				}
				eval(nextPage + "=" + nextPG);				
			} else if ($(this).attr("class") == "btn_end"){
				//마지막으로
				eval(nextPage + "=" + pCount);				
			} else {
				//페이지	
				eval(nextPage + "=" + $(this).attr("link"));				
			}

			
			//함수 호출
			eval(goFunction);
			return false;

			
		});		
		
	}

};



util.string = {
	/**************************************************************** 
	 * str에 내용이 null이면 selDef의 내용을 넘겨준다.
	 * @param str 입력문자열
	 * @param selDef NULL 일경우 리턴값.
	 ****************************************************************/
	isNull : function(str,selDef) 
	{
			if(util.string.isEmpty(str))
			{
				return selDef; 
			}
			else
			{
				return str;
			}
	}
	/****************************************************************
	 * 입력된 문자가 NULL 인지 확인한다.
	 * @param selValue 문자
	 ****************************************************************/
	,isEmpty:function(selValue)
	{
		//2012.04.02 황주연 - selValue가 0 일 경우 0 == "" 는 true 값으로 인식 되므로 type이 동일 하게 체크 되도록 "===" 사용
		if(selValue == null || typeof(selValue) =="undefined" || selValue === "" || selValue == "NULL" || selValue == "null" || selValue == "")
		{
			return true;		
		}
		return false;
	}	
	/****************************************************************
	 * 문자열 값을 체크하여 준다.
	 * @param strValue   문자열
	 * @param optValue   N:정수형숫자,F:실수형숫자, E:영문,A:영문대문자 ,a:영문소문자, H:한글 opt는 중복허용 예 : "NEAaH" 숫자,영대문자,소문자,한글
	 *                   // N : 숫자만 있을 경우 true
					     // E : 영문만 있을 경우 true
					     // A : 영문 대문자만 있을 경우 true
					     // a : 영문 소문자만 있을 경우 true
					    // H : 한글만 있을 경우 true
	 * @param optExp     추가 체크 정규식
	 ****************************************************************/
	,checkString:function (strValue, optValue,optExp) 
	{
        
        var rtnValue = false;
        var flag_N = false;
        var flag_F = false;
        var flag_E = false;
        var flag_A = false;
        var flag_a = false;
        var flag_H = false;
        var optValueLength = optValue.length;
        if(optValue ==null || optValue =="")
        {
            return true;
        }
        for(var i=0;i<optValue.length;i++)
        {
            if(optValue.charAt(i).toUpperCase()=="N")
            {
                flag_N = true;
            }
            if(optValue.charAt(i).toUpperCase()=="F")
            {
                flag_F = true;
            }
            if(optValue.charAt(i).toUpperCase()=="E")
            {
                flag_E = true;
            }
            if(optValue.charAt(i) =="A")
            {
                flag_A = true;
            }
            if(optValue.charAt(i)  =="a")
            {
                flag_a = true;
            }
            if(optValue.charAt(i).toUpperCase()=="H")
            {
                flag_H = true;
            }
        }
        var tempRegExp = "";
        if(optExp == null)
        {
            optExp = "";
        }
        tempRegExp  = optExp;
        if(flag_N)  //정수형
        {
            tempRegExp = tempRegExp + "0-9\-\+";
        }
        if(flag_F)  //실수형
        {
            tempRegExp = tempRegExp + "0-9\-\+\.";
        }
        if(flag_E)  //영문자(대소문자구분없음,공백허용)
        {
            tempRegExp = tempRegExp + "A-Za-z\\s";
        }
        if(flag_A)  //영문대문자
        {
            tempRegExp = tempRegExp + "A-Z\\s";
        }
        if(flag_a)  //영문소문자.
        {
            tempRegExp = tempRegExp + "a-z\\s";
        }
        
       
        if( (flag_N  || flag_F) && !flag_E && !flag_A && !flag_a && !flag_H)//정수또는 실수형일 경우.
        { 
            var re  = new RegExp("^["+tempRegExp+"]+$","g");
        		if(strValue !="")
        		{
        			if(re.test(strValue))
        			{
        				rtnValue =true;
        			}
        		}
        		if(optExp == "")
        		{
        			rtnValue = util.string.isNumber(strValue);
        		}
        }
        else if( !flag_N  && !flag_F && !flag_E && !flag_A && !flag_a && flag_H )//한글만 있는경우.
        {
          
            
            var tempChar = null;
            if(strValue == null || strValue == "")
            {
                return true;
            }
            var tempStrValue = strValue;
            tempStrValue  = tempStrValue.replace(optExp,"");
            var tempStrValueLength = tempStrValue.length;
             for(var i=0;i<tempStrValueLength;i++)
             {
                tempChar = tempStrValue.charCodeAt(i);
                if(!((0xAC00 <= tempChar && tempChar <=0xD7A3) || (0x3131 <=tempChar && tempChar <= 0x318E)))
                {
                    rtnValue = false;
                    break;
                }
                else
                {
                    rtnValue = true;
                }
            }
        }
        else if( (flag_N  || flag_F || flag_E || flag_A || flag_a) && !flag_H )  //한글만 없는경우..
        {
           var re  = new RegExp("^["+tempRegExp+"]+$","g");
        		if(strValue !="")
        		{
        			if(re.test(strValue))
        			{
        				rtnValue =true;
        			}
        		} 
        }
        else if( (flag_N  || flag_F || flag_E || flag_A || flag_a) && flag_H )
        {
            var strValueLength = strValue.length;
            var tempCharCode = null;
            var tempChar  = null;
            var tempStrValueHangul = "";
            var tempStrValue  = "";
            if(strValue == null || strValue == "")
            {
                return true;
            }
             for(var i=0;i<strValueLength;i++)
             {
                tempCharCode = strValue.charCodeAt(i);
                tempChar = strValue.charAt(i);
                if(!((0xAC00 <= tempCharCode && tempCharCode <=0xD7A3) || (0x3131 <=tempCharCode && tempCharCode <= 0x318E)))
                {
                    tempStrValue = tempStrValue +tempChar;
                }
             }
            var re  = new RegExp("^["+tempRegExp+"]+$","g");
        		if(tempStrValue !="")
        		{
        			if(re.test(tempStrValue))
        			{
        				rtnValue =true;
        			}
        		} 
        }
        else
        {
            rtnValue = true;
        }
        return rtnValue;
    }
	 /**
	 * 숫자 검사.
	 * @param selValue   문자열
	 */
    ,isNumber:function(selValue)
    {
          if(selValue == null || selValue=="")
          {
        	  	return false;
          }
          else if (isNaN(selValue)) 
          {
              return false;
          }
          else
          {
              return true;
          }
    } 


	 /**
	 * html Tag 를 벗겨내고 지정된 글자수 이상인 경우 ...으로 표시 (리스트에서 내용의 일부를 보여줄 경우 사용)
	 * @param selValue   문자열
	 * @param stringLength   문자길이
	 */    
    ,stripTags : function(selValue, stringLength){
    	var retVal = $(selValue).text();
    	if ($(retVal).length > stringLength){
    		retVal = retVal.substring(stringLength) + "..." ;
    	}
    	return retVal;
    }
    
    /**
     * 선택된 글에서 숫자를 뽑아냄 
     */
	,getNumber:function(strValue){
		var resultValue = "";
		//입력된 값이 숫자가 아닌 경우 한글자씩 잘라서 앞부터 숫자만 뽑아서 폼에 등록
		if(!util.string.isNumber(strValue)){
			for(var i = 0 ; i < strValue.length; i++){
				var changeVal = strValue.substring(i, i + 1);
				if(util.string.isNumber(changeVal)){
					resultValue += changeVal;
				}					
			}
		}else{
			resultValue = strValue;
		}
		
		return resultValue;

	} 
	/**
	 * 전화번호형의 문자에서 값을 잘라 배열로 리턴
	 */
    ,getTelNumArray:function(strValue){
		var rtnValue = new Array();
		var telNum = util.format.formatTelNo(strValue);
		rtnValue = telNum.split("-");
		
		return rtnValue;
	}	
    /**
     * 문자 대치
     */
	,replace: function(str,str1, str2) {
	    
		var temp_str = str+"";
		
		if ( temp_str.trim() != "" && str1 != str2) {
		//if (temp_str != "" && str1 != str2) {	         
			//temp_str = temp_str.trim;
			while (temp_str.indexOf(str1) > -1){	                
				temp_str = temp_str.replace(str1, str2);
	        }
	    }
	    return temp_str;
	}
	/**
	 * 주민등록번호 양식에 맞는지 체크
	 */
	,isValidSSN:function(strValue){
		
		  var num = strValue;
		  if(num==''){
			  util.popup.alert("주민등록번호를 정확하게 입력해주세요.");
			  numObj.focus();
			  return false;
		  }
		  if(num.length!=13){
			  util.popup.alert ("주민등록번호를 '-' 를 제외한 13자리 숫자로 입력하세요.");
			   numObj.focus();
			   return false;
		  }
		  if(isNaN(num)){
			  util.popup.alert("주민등록번호는 숫자만 입력이 가능합니다.");
			   numObj.focus();
			   return false;
		  } 
		  var ssn1 = num.substring(0, 6);
		  var ssn2 = num.substring(6, 13);
		  if((ssn1.length==6) &&(ssn2.length==7)){
			  var ssn=ssn1+ssn2;
			  a = new Array(13);
			  for (var i=0; i < 13; i++) {
				  a[i] = parseInt(ssn.charAt(i));
			  }
			  var k = 11 - (((a[0] * 2) + (a[1] * 3) + (a[2] * 4) + (a[3] * 5) + (a[4] * 6) + (a[5] * 7) + (a[6] * 8) + (a[7] * 9) + (a[8] * 2) + (a[9] * 3) + (a[10] * 4) + (a[11] * 5)) % 11);
			  if (k > 9){
				  k -= 10;
			  }
			  if (k == a[12]){
				  return true;
			  }else{
				  util.popup.alert ("잘못된 주민등록번호 입니다.\n다시 입력해 주세요.");
				  numObj.value ="";
				  numObj.focus();
				  return false;
			  }
		  }
	}
	/**
	 * 지정된 문자의 앞뒤공백 제거
	 */
	,trim:function(str)
	{	
		 return this.replace(str,/(^\s*)|(\s*$)/g, "");
	}
	// 외국인 주민번호 체크 san24 20080704
	,isRegNo_fgnno:function(fgnno) { 
        var sum=0; 
        var odd=0; 
        buf = new Array(13); 
        for(i=0; i<13; i++) { buf[i]=parseInt(fgnno.charAt(i)); } 
        odd = buf[7]*10 + buf[8]; 
        if(odd%2 != 0) { 	return false; 		} 
        if( (buf[11]!=6) && (buf[11]!=7) && (buf[11]!=8) && (buf[11]!=9) ) {			    
                return false; 
        } 
        multipliers = [2,3,4,5,6,7,8,9,2,3,4,5]; 
        for(i=0, sum=0; i<12; i++) { sum += (buf[i] *= multipliers[i]); } 
        sum = 11 - (sum%11); 
        if(sum >= 10) { sum -= 10; } 
        sum += 2; 
        if(sum >= 10) { sum -= 10; } 
        if(sum != buf[12]) { 			
			return false; 
		} 
        return true; 

	}		
};

util.number = {
	/**
	 * int 숫자 변환한다. 값이 없거나 문자열등 숫자로 변환할수 없을 경우 0 을 돌려준다.
	 *  @param selValue : 값
	 */
	getInt:function (selValue)
	{
		var rtnValue = 0;
		var charValue = selValue + "";
		var tempValue = charValue.replace(/,/g, "");
		if(util.string.isNumber(tempValue)==false)
		{
		 return rtnValue;
		}
		try
		{
		 rtnValue = parseInt(tempValue-0);
		 
		}
		catch(e)
		{
		 rtnValue = 0;
		}
		return rtnValue;
	} 
	/**
	 * float 숫자 변환한다. 값이 없거나 문자열등 숫자로 변환할수 없을 경우 0 을 돌려준다.
	 *  @param selValue : 값
	 */
	,getFloat:function (selValue)
	{
		var rtnValue = 0;
		var charValue = selValue + "";
		var tempValue = charValue.replace(/,/g, "");
		if(util.string.isNumber(tempValue)==false)
		{
		 return rtnValue;
		}
		try
		{
		 rtnValue = parseFloat(tempValue-0);
		 
		}
		catch(e)
		{
		 rtnValue = 0;
		}
		return rtnValue;
	} 
};

util.format = {

	/***********************************************************************************************
	* selVal 를 pattern 형태의 정규표현 형태로 변경 해 리턴
	* selVal 변경 대상 문자
	* @pattern ####,##,## 등 형태로 이뤄진 패턴 값
	* @doRoof boolean값. true 일 경우 대상 문자의 형태가 정규표현식에 부합 할때 까지 계속 해서 변경
	************************************************************************************************/
	 formatting:function(selVal, selPattern, doRoof)
	 {
		  
			var patternArr = new Array(); //패턴의 각 자리별 숫자의 갯수를 저장하는 배열
			var currentArrSize = 0; //현재 패턴의 자릿수
			var patternArg = ""; //정규표현식을 문자로 표현하는 패턴
			var splitIdx = 1; //패턴 정의를 위한 변수 인덱스

			for(var i = 0; i < selPattern.length; i++)
			{
				var tmpChar = selPattern.charAt(i); //입력된 문자의 각 자리 순차 대입

				if(tmpChar == '#')
				{
					currentArrSize++; //#의 갯수
				}
				else
				{
					if(currentArrSize > 0)
					{
						patternArr[patternArr.length] = currentArrSize; //자릿수 별 #의 갯수를 배열에 대입
						currentArrSize = 0; //갯수 0으로 초기화

						patternArg = patternArg + '$'+(splitIdx++); //패턴 생성
					}

					patternArg = patternArg + tmpChar; //#이외의 문자 패턴에 추가
				}

				if(i == selPattern.length-1)
				{
					//마지막 문자가 #으로 끝날 경우 마지막 패턴 자릿수 및 문자 패턴 생성
					if(currentArrSize > 0)
					{
						patternArr[patternArr.length] = currentArrSize;
						patternArg = patternArg + '$'+(splitIdx++);
					}
				}
			}

			var patternExpStr = ""; //정규 표현식 변수
			for(var i = 0; i < patternArr.length; i++)
			{
				var numAmount = util.number.getInt(patternArr[i]); //패턴의 각 자리 별 자릿수
				if(doRoof == true && i == 0)
				{
					patternExpStr = patternExpStr+'([0-9]+)'; //정규 표현식 변수 생성
				}
				else
				{
					patternExpStr = patternExpStr+'([0-9]{'+numAmount+'})'; //정규 표현식 변수 생성
				}
			}
			var patternExp  = new RegExp(patternExpStr);
			if(doRoof == true)
			{
				//루프 변수가 true 일 경우 패턴 테스트를 해 계속해서 변환
				while (patternExp.test(selVal)) 
				{ 
					selVal = selVal.replace(patternExp,patternArg);
			    }
			}
			else
			{
				selVal = selVal.replace(patternExp,patternArg);
			}
			return selVal;

	}		
	
	/***********************************************************************************************
	* 사업자또는 주민번호를 xxx형태로 리턴한다.
	* selValue    변경 대상 문자
	* selToggle   옵션 값에 따라서 뒷자리 전체 또는 1자리만 돌려준다.
	************************************************************************************************/
	,formatRsnoXXX:function(selRsno)
	{
		  var rtnValue = selRsno;
		  if(selRsno != null)
		  {
			  if(selRsno.length==10)
			  {
					rtnValue = selRsno.substring(0,3)+"-"+selRsno.substring(3,6) +"****";
			  }
			  else if(selRsno.length>=13)
			  {
					rtnValue = selRsno.substring(0,6)+"-"+selRsno.substring(6,7) +"******";
			  }
			  else
			  {
				  rtnValue = selRsno.substring(0,selRsno.length-3)+"***";
			  }
		  } 
	     return rtnValue;
			
	}
	
	/***********************************************************************************************
	* selRsno 를  주민번호 형태로 리턴한다.
	* selValue    변경 대상 문자
	* selToggle   옵션 값에 따라서 뒷자리 전체 또는 1자리만 돌려준다.
	************************************************************************************************/
	,formatRsno:function(selRsno,selToggle)
	{
		  var rtnValue = selRsno;
		  if( selToggle == null)
		  {
			  selToggle = true;
		  }
			if(selRsno != null && selRsno.length>=7)
			{
				if(selToggle)
				{
					rtnValue = selRsno.substring(0,6)+"-"+selRsno.substring(6);
				}
				else
				{
					rtnValue = selRsno.substring(0,6)+"-"+selRsno.substring(6,7);
				}
			}
	     return rtnValue;
			
	}
	/**
	 * 전화번호 형식으로 변환합니다.
	 * @param str 전화번호 문자열
	 */
	,formatTelNo: function(str) 
	{
		if (!str)
			return "";
		if (str.length < 3)
			return str;
		else if (str.length >= 3 & str.length < 5) 
		{
			return str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+$)/, "$1-$2");
		} 
		else if (str.length >= 5 & str.length < 8) 
		{
			return str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]{3,4})([0-9]+$)/,
					"$1-$2-$3");
		} 
		else 
		{
			return str.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3");			
		}
	}
	/**
	 * selDate 날짜를 formatStr를 넣어서 변환한다.
	 * @param selDate 날짜문자열 YYYYMMDD
	 * @param formatStr 구분값.
	 */
	,formatDate:function(selDate,formatStr)
	{
		  var rtnValue = selDate;
			if(selDate != null && selDate.length>=2)
			{
				if(formatStr == null)
				{
					formatStr = "-";
				}
				rtnValue = util.format.formatting(selDate,"####"+formatStr+"##"+formatStr+"##");
			}
			return rtnValue;
	}
	/**
	 * selTime 날짜를 formatStr를 넣어서 변환한다.
	 * @param selDate 날짜문자열 HHMM  또는 HHMMSS
	 * @param formatStr 구분값.
	 */
	,formatTime:function(selTime,formatStr)
	{
					  var rtnValue = selTime;
						if(selTime != null)
						{
							if(formatStr == null)
							{
								formatStr = ":";
							}
							if(selTime.length==4)
							{
								rtnValue = util.format.formatting(selTime,"##"+formatStr+"##");
							}
							else if(selTime.length==6)
							{
								rtnValue = util.format.formatting(selTime,"##"+formatStr+"##"+formatStr+"##");
							}
						}
				     return rtnValue;
	}
	/**
	 * 지정된 값을 날짜 및 시간형태로 포맷을 변경해 줌
	 */
	,formatDateTime:function(selValue)
	{
		return util.format.formatting(selValue,"####-##-## ##:##:##");
	}
	/**
	 * 지정된 값에서 포맷값을 지워줌
	 */
	,removeFormat:function(selValue, formatStr){

		return  eval("selValue.replace(/" + formatStr + "/g,'')");
	}
	,formatComma:function(setValue){
		var rtnValue =setValue;
		var tempValue = "";
		if(util.string.isNumber(setValue))
		{	 
			 var jumPos = (rtnValue+"").indexOf(".");
			 var jumBefValue = "";
			 var jumAftValue = "";
			 
			 if(jumPos>=0)
			 {
				 jumBefValue = (rtnValue+"").substring(0,jumPos); 	
				 jumAftValue = (rtnValue+"").substring(jumPos);
			 }
			 else
			 {
				 jumBefValue = (rtnValue+""); 	
				 jumAftValue = "";
			 }
		    var Re = /[+-][^0-9]/g;
		     var ReN = /(-?[0-9]+)([0-9]{3})/;
			    rtnValue = jumBefValue.replace(Re,'');              
			    while (ReN.test(rtnValue)) 
			    { 
			    	rtnValue = rtnValue.replace(ReN, "$1,$2"); 
			   }
			    rtnValue = rtnValue + jumAftValue;
		}
	 return rtnValue;

	}
	/**
	 * 인풋박스에서 값을 넣을 때 자동으로 숫자 포멧팅이 되도록하는 함수 
	 */
	,keyUpFormatting : function(flgcd, value){
		var rtnRslt = "";
		var tmpValue = util.format.removeFormat(value, "[^0-9]");
		var tmpLen = tmpValue.length;

		
		if (flgcd == "number") {		//숫자만
			if (tmpLen > 15) {
				rtnRslt = tmpValue.substring(0, 15);
			}
			else {
				rtnRslt = tmpValue;
			}
		}
		else if (flgcd == "money") {
			if (tmpLen > 15) {
				rtnRslt = util.format.formatting(tmpValue.substring(0, 15), "###,###",  true);		//금액
			}
			else {
				rtnRslt = util.format.formatting(tmpValue, "###,###",  true);	
			}
			
		}
		else if (flgcd == "date") {		//날짜
			if (tmpLen > 8) {
				rtnRslt = util.format.formatDate(tmpValue.substring(0, 8), "-");
			}
			else {
				rtnRslt = util.format.formatDate(tmpValue, "-");	
			}
		}
		else if (flgcd == "rsno") {		//주민번호
			if (tmpLen > 13) {
				rtnRslt = util.format.formatRsno(tmpValue.substring(0, 13), true);
			}
			else {
				rtnRslt = util.format.formatRsno(tmpValue, true);	
			}
		}
		else if (flgcd == "zipcode") {		//우편번호
			if (tmpLen > 6) {
				rtnRslt = util.format.formatRsno(tmpValue.substring(0, 6), true);
			}
			else {
				rtnRslt = util.format.formatZipCode(tmpValue, true);	
			}
		}

		return rtnRslt;

	}
};

util.array = {
		/**************************************************************** 
		 * 함 수 명 	: getIndexOfArr
		 * 함수설명	: 배열리스트에서 특정문자와 일치하는 배열 index를 (없으면 -1) 반환
		 * str      : 특정문자
		 * arr      : Array
		 ****************************************************************/
		getIndexOfArr:function(str, arr)
		{
			for(var i=0; i<arr.length; i++)
			{
				if(arr[i] == str)
				{
					return i;
				};
			};
			return -1;
		}
		/**************************************************************** 
		 * 함 수 명 	: getIndexOfArr
		 * 함수설명	: 배열리스트에서 특정 index 항목 삭제
		 *            삭제처리된 Array 반환
		 * arr      : Array
		 * idx      : 배열 index
		 ****************************************************************/
		,deleteArrIndex:function(arr, idx)
		{
			var nwArr = new Array();
			var nwIdx = 0;
			
			for(var i=0; i<arr.length; i++)
			{
				if(i == idx) continue;
				nwArr[nwIdx++] = arr[i];
			};
			return nwArr;
		}
};

util.link = {
	/**
	 * 링크 처리 변수를 post 로 form action으로 처리해줌
	 */	
	goLink:function(url, parm, target){
			  var f = document.createElement('form');
			  f.setAttribute('data-ajax', 'false');
			  
			  var objs, value;
			  for (var key in parm) {

			    value = parm[key];
			    if ( value.constructor === Array){
			    	for (var i = 0; i < value.length ; i++){
					    objs = document.createElement('input');
					    objs.setAttribute('type', 'hidden');
					    objs.setAttribute('name', key);
					    objs.setAttribute('value', value[i]);
					    f.appendChild(objs);		
			    	}
			    }else{
				    objs = document.createElement('input');
				    objs.setAttribute('type', 'hidden');
				    objs.setAttribute('name', key);
				    objs.setAttribute('value', value);
				    f.appendChild(objs);					    
			    }

			  }
			        
			  if (target) {
				  f.setAttribute('target', target);  
			  }else{} 

			  f.setAttribute('method', 'post');
			  f.setAttribute('action', url);
			  document.body.appendChild(f);
			  f.submit();			
	}
};

util.form = {
		/*
		 *  checkboxSet : 체크박스에서 입력된 값과 동일한 값이 있는경우 체크설정
		 *  checkboxGet : 체크박스에서 체크된 값을 가져옴 (여러개의 체크가 있기 때문에 array 로 리턴)
		 *  checkedIndex : 체크박스에서 체크된 인덱스를 가져옴 ( 여러개의 체크가 있기 때문에 index를 array 로 리턴)
		 *  radioSet : 라디오에서 입력된 값과 동일한 값이 있는 경우 체크  
		 *  radioGet : 라디오에서 체크된 값을 가져옴 (String 으로 리턴)
		 *  selectSet : 셀렉트 에 값을 셋팅
		 *  selectSetJsonArray : json Array 를 이용해서 셀렉트에 값을 셋팅
		 *  selectGet : 셀렉트에서 selected 된 값을 가져옴 (멀티셀렉트 불가함. String 으로 리턴)
		 *  selectCheck : 셀렉트에서 특정 값이 있는 경우 selected 처리
		 */
		checkboxSet:function(checkboxName, setVal){
			$('input[name=' + checkboxName + ' ]:checkbox:input[value='+setVal+']').attr('checked', 'checked'); // from value
		}
	
		,checkboxGet:function(checkboxName){
			var retVal = new Array() ;
			$(":checkbox[name=" + checkboxName + " ]:checked").each(					
				    function() {
				    	retVal.push($(this).val());
				    }
				);	
			return retVal;
		}
		
		,checkedIndex:function(checkboxName){
			var indexi = 0;
			var retVal = new Array() ;

			$(":checkbox[name=" + checkboxName + " ]").each(						
				    function() {
				    	if ($(this).is(":checked")){
				    		retVal.push(indexi);	
				    	}
				    	indexi++;
				    					        
				    }				    
				);	
			return retVal;		
		}		
		,radioSet:function(radioName, setVal){
			 $('input[name=' + radioName + ' ]:radio:input[value='+setVal+']').attr('checked', 'checked'); // from value
		}
		,radioGet:function(radioName){
			var retVal = $(":radio[name=" + radioName + " ]:checked").val();
			return retVal;
		}
		,selectSetJsonArray:function(selectObject, setVal, selectedVal){
			//기존의 셀렉트 변수를 지운다
			selectObject.find('option').remove() ;
			//값을 추가
			var setValCnt = setVal.length;
			
			//최초 값이 있는 경우
			
			if (selectedVal.defaultText != "" && selectedVal.defaultText != undefined) {
				selectObject.append($('<option>', { value : selectedVal.defaultVal })
						.text(selectedVal.defaultText));
				if (selectedVal.selectedVal != undefined){
					selectedVal = "";
				}else{
					selectedVal = selectedVal.selectedVal;
				}
			}else{				
			}

			for (var i = 0; i < setValCnt ; i++){
				var optionVal = setVal[i];
				
				selectObject.append($('<option>', { value : optionVal.code })
										.text(optionVal.value));
				
			}

			util.form.selectCheck(selectObject, selectedVal);	

		}
		,selectSet:function(selectObject, setVal, selectedVal){
			//기존의 셀렉트 변수를 지운다
			selectObject.find('option').remove() ;

			//최초 값이 있는 경우
			if (selectedVal.defaultText != "" && selectedVal.defaultText != undefined) {
				selectObject.append($('<option>', { value : selectedVal.defaultVal })
						.text(selectedVal.defaultText));
				if (selectedVal.selectedVal != undefined){
					selectedVal = "";
				}else{
					selectedVal = selectedVal.selectedVal;
				}
				
			}else{				
			}			
			
			//값을 추가			
			$.each(setVal, function(key, text) {
				selectObject.append($('<option>', { value : key })
			 .text(text));
			});			
			
			util.form.selectCheck(selectObject, selectedVal);
		}		
		,selectGet:function(selectObject){
			return selectObject.find('option :selected').val();			
		}
		,selectCheck:function(selectObject, selectedVal){
			//입력된 값과 동일한 값에 선택
			selectObject.find("option[value="+selectedVal+"]").attr("selected", "selected");
		}
		,selectCheckText:function(selectObject, selectedText){
			
				selectObject.find('option').each(function() {
					if ($(this).text() == selectedText){
						$(this).attr("selected", "selected");
					}
				  });
			
		}
		,checkInput:function(objectID, alertMsg){
			var retVal = false;
			if(util.string.isEmpty($(objectID).val())){
				util.popup.alert(alertMsg);
				$(objectID).focus();
			}else{
				retVal = true;
			}
			
			return retVal;
			
		}
		,setCardYear:function(selectObject){
			var thisYear = new Date().getFullYear();
			var setVal = new Array();
			for(var i = 0 ; i < 8 ; i++){
				writeYear = thisYear + i ;
				var setParam = {"code" : writeYear , "value" : writeYear};
				setVal.push(setParam);
			}
			util.form.selectSetJsonArray(selectObject, setVal, "");
		}
		,goNextForm:function(fromObject, toObject){

			var fromMaxlength = fromObject.attr("maxlength");
			
			if (fromMaxlength == "" || fromMaxlength == undefined || fromMaxlength == null){				
				return true;
			}else{
				//maxlength 가 있다면 keyup 이벤트를 통해서 다음 오브젝트에 포커스를 준다. 
				fromObject.on("keyup", function(e){
					if (fromObject.val().length >= fromMaxlength){
						toObject.focus();
						return true;
					}else{
						return true;
					}
					
				});



			}
		}
		,callFunction:function(ckObject, callbackFunc){
				//keyup 이벤트에서 엔터라면 지정한 함수 실행 
				fromObject.on("keyup", function(e){
					var keyCode = (window.Event) ? e.which : e.keyCode;

					if ( keyCode > 13 ) {
						callbackFunc.call();						
						return true;
					}else{
						return true;
					}					
					
				});

		}

};

util.data = {
		/*
		 *  jsonGetVal : jsonData 에서 특정지어진 항목의 대응값을 가져옴(항목명에 속한 값을 모두 array 로 리턴)
		 *    - Key 만 받는 경우 array 로 리턴
		 *    - Key, Value 받는 경우 데이터가 있으면 True, 없으면 False
		 *    - Index 를 받는 경우 json 으로 리턴
		 *  jsonGetIndex : jsonData 에서 특정 index의 데이터를 모두 가져옴 (항목:값 형태의 json data 로 리턴
		 */			
		
		jsonGetVal:function(jsonObject, keyname){
			//jsonObject 에서 루프를 돌면서 key 에 대응하는 value 를 리턴.
			var retVal = "";
			
			$.each(jsonObject, function(key, value){
			    if (key == keyname) {
			    	retVal = value;
			    }			    
			});
			
			
			return retVal;
		}
		,getCodeValue:function(arrayObject, codeValue){
			//jsonObject 에서 루프를 돌면서 key 에 대응하는 value 를 리턴.
			var retVal = "";
			var arrLength = arrayObject.length ;
			
			for(var i = 0; i < arrLength ; i++){
				if (arrayObject[i].code == codeValue){
					retVal = arrayObject[i].value;
				};
			};
			if (retVal == ""){
				retVal = codeValue;
			}
			return retVal;
		}
		,clearJson:function(jsonObject){
			var retVal = {};
			$.each(jsonObject, function(key, value){
				/*
				 *  value : string / array / function / object (json , xml) / number
				 * 
				 */
				eval("retVal." + key + " = '' ");  
			});
			
			return retVal;

			
		}
		/**
		 * 지정된 주소에서 파라메터를 보내서 데이터를 받아서 json Array 로 리턴한다.
		 * parameter 는 Array (key, value) 형태임
		 */
		,getDatafromUrl:function(gurl, parameter, callBack, cktest) {
			
			/* XSS 체크*/
			if(!util.xss.chkParam(parameter)){return false;};	
			
			//Json 전송을 위해서 스트일 형태로 변환
			var inputData = JSON.stringify(parameter);
			//데이터 테스트를 한다.
			if (cktest === "yes"){
				alert(inputData);				
			}
			$.ajax( {
				type :'POST'
				,async :true
				,cache	: false
				,headers	: { "cache-control": "no-cache","pragma": "no-cache" }			
				,url : gurl
				,data:inputData
				,dataType :"json"		
				,contentType :"application/json;charset=utf-8"
				,beforeSend : function(xhr){
				}				
				,success : callBack
				,error : function(xhr, textStatus) {
					alert("데이터 전송이 지연되고 있습니다. 잠시 후 다시 시도해 보세요 ");					
			        return;		
				}
				,complete : function(xhr, textStatus) {
				}
			});	
		}

};

// 날짜관련 함수
util.date = {
		/*
		 *  today : 오늘날짜 가져오기
		 *  dateAdd : 지정한 날짜만큼의 날짜 추가
		 *  dateDiff : 두 날짜 사이의 기간(일)
		 *  weekDiff : 두 날짜 사이의 기간(주)
		 *  monthDiff : 두 날짜 사이의 기간(월)
		 *  yearDiff : 두 날짜 사이의 기간(연)
		 *  setDateCombo : 연월에 맞는 일수를 combo 에 등록 (연월, 콤보ID)
		 */	
		
		 		nowTime:""
				,setDateInterval:function(){
					 
					
					var tempNow = new Date(util.date.nowTime.substring(0,4)
					    		            ,util.date.nowTime.substring(4,6)-1
					    		            ,util.date.nowTime.substring(6,8)
					    		            ,util.date.nowTime.substring(8,10)
					    		            ,util.date.nowTime.substring(10,12)
					    		            ,util.date.nowTime.substring(12,14)
					    		            );
					tempNow.setSeconds(tempNow.getSeconds()+5);
					var tempYear = tempNow.getFullYear()+"";
					var tempMonth = ((tempNow.getMonth()-0)+1)+"";
					var tempDate = tempNow.getDate()+"";
					var tempHours = tempNow.getHours()+"";
					var tempMinutes = tempNow.getMinutes()+"";
					var tempSeconds = tempNow.getSeconds()+"";
					if(tempMonth.length<=1 )
					{
						tempMonth = "0"+tempMonth;
					}
					if(tempDate.length<=1 )
					{
						tempDate = "0"+tempDate;
					}
					if(tempHours.length<=1 )
					{
						tempHours = "0"+tempHours;
					}
					if(tempMinutes.length<=1 )
					{
						tempMinutes = "0"+tempMinutes;
					}
					if(tempSeconds.length<=1 )
					{
						tempSeconds = "0"+tempSeconds;
					}
					
					util.date.nowTime = tempYear+ tempMonth+tempDate+tempHours+tempMinutes+tempSeconds;
					 
				}
				//오늘 날짜 리턴
				,today:function(){
					var retDate = "";
					 var now = new Date(util.date.nowTime.substring(0,4)
		 		            ,util.date.nowTime.substring(4,6)-1
		 		            ,util.date.nowTime.substring(6,8));
				     
				    var thisMonth = now.getMonth() + 1;
				    if (thisMonth < 10){
				    	thisMonth = "0" + String(thisMonth);
				    }else{
				    	thisMonth = String(thisMonth);
				    }
				    
				    var thisDay = now.getDate();
				    if (thisDay < 10){
				    	thisDay = "0" + String(thisDay);
				    }else{
				    	thisDay = String(thisDay);
				    }		    
				    
					retDate = String(now.getFullYear()) + thisMonth + thisDay;			
					return retDate ;
				}
		
		// 날짜 더하기 빼기  (입력 형식 : 20120101,  출력형식 : 20120101)
		,dateAdd:function(orgDate, dateType, addDate){
			var retDate = "";
			orgDate = util.format.formatDate(orgDate);			
			var date = util.date.newDate(orgDate);
	        var newdate = util.date.newDate(orgDate);
			
			//연/월/일의 경우에 따라서 대처
			if ("month" == dateType){
				//날짜 계산
		        newdate.setMonth(newdate.getMonth() + addDate);
		        var nd = newdate;	
		        
			    var thisMonth = nd.getMonth() + 1;
			    var thisDay = nd.getDate();			    

			    if (thisDay != date.getDate()){
				    
			    	if (thisMonth < 10){
				    	thisMonth = "0" + String(thisMonth);
				    }else{
				    	thisMonth = String(thisMonth);
				    }
				    
			    	newdate = util.date.newDate(util.format.formatDate(String(nd.getFullYear()) + thisMonth + "01"));
			    	
			    	newdate.setDate(newdate.getDate() - 1);
			    	
			        nd = newdate;	
			        
				    thisMonth = nd.getMonth() + 1;
				    thisDay = nd.getDate();	
			    	
			    }

			    
			    if (thisMonth < 10){
			    	thisMonth = "0" + String(thisMonth);
			    }else{
			    	thisMonth = String(thisMonth);
			    }
			    
			    //출력할 형태로 변환
			    if (thisDay < 10){
			    	thisDay = "0" + String(thisDay);
			    }else{
			    	thisDay = String(thisDay);
			    }
			    

			    
				retDate = String(nd.getFullYear()) + thisMonth + thisDay;					
				
				
			}else if ("year" == dateType){

				
		        newdate.setYear(newdate.getFullYear() + addDate);
		        var nd = newdate;	
		        
			    var thisMonth = nd.getMonth() + 1;
			    var thisDay = nd.getDate();			    

			    if (thisDay != date.getDate()){
				    
			    	if (thisMonth < 10){
				    	thisMonth = "0" + String(thisMonth);
				    }else{
				    	thisMonth = String(thisMonth);
				    }
				    
			    	newdate = util.date.newDate(util.format.formatDate(String(nd.getFullYear()) + thisMonth + "01"));
			    	
			    	newdate.setDate(newdate.getDate() - 1);
			    	
			        nd = newdate;	
			        
				    thisMonth = nd.getMonth() + 1;
				    thisDay = nd.getDate();	
			    	
			    }

			    
			    if (thisMonth < 10){
			    	thisMonth = "0" + String(thisMonth);
			    }else{
			    	thisMonth = String(thisMonth);
			    }
			    
			    //출력할 형태로 변환
			    if (thisDay < 10){
			    	thisDay = "0" + String(thisDay);
			    }else{
			    	thisDay = String(thisDay);
			    }
			    
				retDate = String(nd.getFullYear()) + thisMonth + thisDay;					
					
				
			}else{
				//날짜 계산
				newdate.setDate(newdate.getDate() + addDate);
		        
		        var nd = newdate;	

			    var thisMonth = nd.getMonth() + 1;
			    if (thisMonth < 10){
			    	thisMonth = "0" + String(thisMonth);
			    }else{
			    	thisMonth = String(thisMonth);
			    }
			    
			    //출력할 형태로 변환
			    var thisDay = nd.getDate();
			    if (thisDay < 10){
			    	thisDay = "0" + String(thisDay);
			    }else{
			    	thisDay = String(thisDay);
			    }	
				retDate = String(nd.getFullYear()) + thisMonth + thisDay;			
			}
	        
	        return retDate;
		}		
		
		,dateDiff:function(fromDate, toDate){
			fromDate = util.format.formatDate(fromDate);
			toDate = util.format.formatDate(toDate);
			var fDate = util.date.newDate(fromDate).getTime();
			var tDate = util.date.newDate(toDate).getTime();
			return parseInt((tDate-fDate)/(24*3600*1000));
		}
		,weekDiff:function(fromDate, toDate){
			fromDate = util.format.formatDate(fromDate);
			toDate = util.format.formatDate(toDate);
			var fDate = util.date.newDate(fromDate).getTime();
			var tDate = util.date.newDate(toDate).getTime();
			return parseInt((tDate-fDate)/(24*3600*1000*7));
		}
		,monthDiff:function(fromDate, toDate){
			fromDate = util.format.formatDate(fromDate);
			toDate = util.format.formatDate(toDate);
	        var d1Y = util.date.newDate(fromDate).getFullYear();
	        var d2Y = util.date.newDate(toDate).getFullYear();
	        var d1M = util.date.newDate(fromDate).getMonth();
	        var d2M = util.date.newDate(toDate).getMonth();
	 
	        return (d2M+12*d2Y)-(d1M+12*d1Y);
		}
		,yearDiff:function(fromDate, toDate){
			fromDate = util.format.formatDate(fromDate);
			toDate = util.format.formatDate(toDate);
	        var d1Y = util.date.newDate(fromDate).getFullYear();
	        var d2Y = util.date.newDate(toDate).getFullYear();
	        return d2Y-d1Y;
		}
		,setDateCombo:function(yearMonth, selectObject){
		    
	    	var newdate = util.date.newDate(util.format.formatDate(yearMonth + "01"));
	    	
	    	//현재 월에서 1달을 더하고 1일을 빼면 현재월의 마지막일자가 구해짐
	    	newdate.setMonth(newdate.getMonth() + 1);
	    	newdate.setDate(newdate.getDate() - 1);
	    	
	        nd = newdate;	
		    var thisDay = nd.getDate();	
	    	
		    var comboData = [];
		    
		    for (var i = 1; i <= thisDay ; i++){
			    //출력할 형태로 변환
		    	var toDay = "";
			    if (i < 10){
			    	toDay = "0" + String(i);
			    }else{
			    	toDay = String(i);
			    }	
			    
				var param = {
						"code" : toDay //세부코드
						,"value" : toDay //세부코드명
				};
				comboData.push(param);			
		    }

			util.form.selectSetJsonArray(selectObject, comboData, "");	
			
		}
		,newDate:function(dayVal){
			var arrstr=dayVal.split('-');
	          var date=new Date();
	          date.setUTCFullYear(arrstr[0], arrstr[1]-1, arrstr[2]);
	          date.setUTCHours(0, 0, 0, 0);
	          return date;		
		}
		,getWeekDay:function(dayVal){
			var nowDay = util.date.newDate(util.format.formatDate(dayVal));
			var weekDay = nowDay.getDay();
			return weekDay;
		}
		
};


util.validation = {
		//패스워드 체크
		 MIN_SIZE : 3 //최소 3개의 문자를 비교한다.
		, sequence : new Array( "YTREWQWERTYUIOPASDFGHJKLZXCVBNMNBVCXZLKJHGFDSAPOIUYTREWQWERTY",
		                     "ytrewqwertyuiopasdfghjklzxcvbnmnbvcxzlkjhgfdsapoiuytrewqwerty",
		                     "VWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDE",
		                     "vwxyzabcdefghijklmnopqrstuvwxyzabcde",
		                     "67890123456789098765432109876" )

		,isValidPassword :function ( val ){
		    if ( val.length == 0 ) {
		    	util.popup.alert( "비밀번호를 입력하세요." );
		        return false;
		    }
		    if ( val.length < 5 ) {
		    	util.popup.alert( "비밀번호는 최소 5자 이상이어야 합니다." );
		        return false;
		    }	
			
			//패스워드에 한글 포함 여부
			var regexp = /[0-9a-zA-Z.;\-]/;
			var alpha = /[a-zA-Z]/;
			var numxp = /[0-9]/;
			var firstAl = false;
			var charAl = true;
			var alphanum = 0;
			var numnum = 0;
			
			for( var i=0; i<val.length; i++){
				if(i == 0 && val.charAt(i) != " " &&  alpha.test(val.charAt(i)) == true){
					firstAl = true;
				}				
			
				if(val.charAt(i) != " " && regexp.test(val.charAt(i)) == false ){
					charAl = false;
				}
				if(val.charAt(i) != " " && alpha.test(val.charAt(i)) == true ){
					alphanum++;
				}
				if(val.charAt(i) != " " && numxp.test(val.charAt(i)) == true ){
					numnum++;
				}				
				
			}

			if (!charAl){
				alert("비밀번호로 입력불가능한 문자를 포함하고 있습니다");
				 return false;				
			}
			if (!firstAl){
				alert("비밀번호의 첫글자는 영문자여야 합니다.");
				 return false;				
			}			
			if (alphanum < 1){
				alert("비밀번호에 영문자는 최소 1자 이상 포함되어야 합니다.");

				 return false;				
			}
			if (numnum < 1){
				 alert("비밀번호에 숫자는 최소 1자 이상 포함되어야 합니다.");
				 return false;				
			}			
			
			
		    for ( var i = 0; i < util.validation.sequence.length; i++ ) {
		        for ( var j = util.validation.MIN_SIZE; val.length >= j; j++ ) {
		            if (util.validation.sequence[i].indexOf( val.substring( j - util.validationMIN_SIZE, j ) ) >= 0 ) {
		                alert( "고객님의 정보호호를 위하여 키보드 순서 혹은 연속되는 글자 혹은 숫자를 세자리이상 사용하실 수 없습니다.\n예) abc, 123, qwe 등" );
		                return false;
		            }
		        }
		    }

		    var cnt = 0;
		    var prev = val.charAt( 0 );
		    for ( var i = 1; i < val.length; i++ ) {
		        if ( prev == val.charAt( i ) ) cnt++;
		        else cnt = 0;
		        prev = val.charAt( i );
		        if ( cnt >= util.validation.MIN_SIZE - 1 ) {
		            alert( "고객님의 정보호호를 위하여 동일한 문자를 연속 세번이상 사용하실 수 없습니다.\n예) 1111, qqqq" );
		            return false;
		        }
		    }
		    return true;
		}
		,isValidID :function ( val){
		
			var pattern1 = /^[a-zA-Z]{1}[a-zA-Z0-9_-]{5,11}$/;	
			
			if (val.length < 6 || val.length > 12){
				alert("ID의 첫 문자는 영문이어야 하며\n\n6자이상 12자 이하 영문 또는 숫자 조합이어야 합니다.");
				return false;
			}else if(!pattern1.test(val)) {
				alert("ID의 첫 문자는 영문이어야 하며\n\n6자이상 12자 이하 영문 또는 숫자 조합이어야 합니다.");
				return false;
			} else {
				return true;
			}					

		}
};
/* xss 체크*/
util.xss =
	{
		   isXss : function(chkStr)
		   {
			   var rtnValue = false;
			   var test_str_low = chkStr;
			   if(test_str_low == null || test_str_low =="" || typeof(test_str_low) =="undefined")
				{
				   return rtnValue;
				}
			   if(
				   test_str_low.indexOf("javascript")>=0  || test_str_low.indexOf("script")>=0   ||
				   test_str_low.indexOf("iframe")>=0         || test_str_low.indexOf("document")>=0 ||
				   test_str_low.indexOf("vbscript")>=0       || test_str_low.indexOf("applet")>=0       ||
				   test_str_low.indexOf("embed")>=0        || test_str_low.indexOf("object")>=0       ||
				   test_str_low.indexOf("frame")>=0         || test_str_low.indexOf("grameset")>=0   ||
				   test_str_low.indexOf("layer")>=0          || test_str_low.indexOf("bgsound")>=0    ||
				   test_str_low.indexOf("alert")>=0          ||  test_str_low.indexOf("onblur")>=0      ||
				   test_str_low.indexOf("onchange")>=0  || test_str_low.indexOf("onclick")>=0        ||
				   test_str_low.indexOf("ondblclick")>=0  || test_str_low.indexOf("enerror")>=0       || 
				   test_str_low.indexOf("onfocus")>=0     || test_str_low.indexOf("onload")>=0       ||
				   test_str_low.indexOf("onmouse")>=0   || test_str_low.indexOf("onscroll")>=0      ||
				   test_str_low.indexOf("onsubmit")>=0   || test_str_low.indexOf("onunload")>=0)
			   {
				   
				   rtnValue = true;
			   }
			   
			  
			   return rtnValue;
		   }
          ,chkXss:function(chkStr)
          {
        	  if(util.xss.isXss(chkStr))
        	 {
        		  alert("현재 사이트 보안을 위해서   스크립트 특수 구분은 작성할 수 없습니다. ");
        		  return false;
        	 }
        	 return true;
          }
          ,chkForm:function(formObj)
          {
        	  var inputObj = $(formObj).find("input,textarea");
        	  var tempValue = null;
        	  var  tempChk = false;
        	  var rtnValue = true;
        	 
        	  inputObj.each(function()
        	  {
        		  tempValue = $(this);
        		  tempChk = true;
        	      if($(tempValue).attr("name")==undefined  &&  $(tempValue).attr("id")==undefined)
        		  {
         			 tempChk = false;
        		  }
         		  if(tempChk)
         		  {
	        		  if(!util.xss.chkXss($(tempValue).val()))
	        		  {
	        			  
	        			  rtnValue = false;
	        			  return false;
	        		  }
         		 }
        	  });
        	  if(tempValue != null  && rtnValue == false)
              {
        		  $(tempValue).focus();
              }
        	  return rtnValue;
          }
          ,chkParam:function(param)
          {
        	  var rtnValue  = true;
        	   
        	  if(!util.xss.chkXss(JSON.stringify(param)))
    		  {
    			  rtnValue = false;
    		  } 
        	  return rtnValue;
          }


	};