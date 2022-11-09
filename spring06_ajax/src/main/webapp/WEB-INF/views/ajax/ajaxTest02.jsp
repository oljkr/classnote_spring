<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajaxTest02.jsp</title>
	<script src="../js/jquery-3.6.1.min.js"></script>
	<script src="../js/jquery.cookie.js"></script>
</head>
<body>
	<h3>* $.ajax() 테스트 *</h3>
	<button id="btn">* 서버에서 응답받기 *</button>
	<div id="panel"></div>

	<script>
	
	//1)$.ajax() 함수
	//	형식) $.ajax({name:value, name:value, "name":value})
	/*
	$("#btn").click(function(){
		$.ajax({
			url     :"message.do"	//요청명령어
		   ,type    :"get"			//get방식
		   ,dateType:"text" 		//응답메세지 타입
		   ,error   :function(error){
			   alert("에러:" + error);
		   }//error callback
		   ,success :function(result){ //result는 서버가 응답해준 메세지
			   //alert("성공:" + result);
		   	   $("#panel").append(result);
		   	   
		   	   //$("#panel").empty();
		   	   //$("#panel").text(result);	//단순 문자열로 출력
		   	   //댓글같은경우 테이블로 출력을 해야하니까 그럴때 사용할 수 있다.
		   	   //$("#panel").html(result); 	//HTML 구조로 출력
		   }//success callback
		}); //ajax() end
	}); //click() end	
	*/
	
	//2)$.get()방식
	//  AJAX를 GET방식으로 요청하는 방식
	//	형식)$.get("요청명령어", callback함수)
	$("#btn").click(function(){
		$.get("message.do", responseProc);
	}); //click() end
	
	function responseProc(result){
		$("#panel").append(result);
	}//responseProc() end
	
		
	</script>

</body>
</html>