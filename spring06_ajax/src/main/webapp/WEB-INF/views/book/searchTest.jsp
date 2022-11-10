<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>searchTest.jsp</title>
	<script src="../js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h3>교재 검색</h3>
	
	<form name="search" id="search">
		<input type="text" name="keyword" id="keyword"/>
		<input type="button" value="검색" id="btn"/>
	</form>
	<!-- 검색 결과 출력 -->
	<div id="panel" style="display:none"></div>
	
	<script>
		$("#keyword").keyup(function(){
			//alert("test");
			
			if($("#keyword").val()==""){ //검색어가 존재하지 않으면
				$("#panel").hide();		 //출력결과 숨기기
			}//if end
			
			//1)id="keyword" 값 가져오기
			//let params=$("#keyword").val();
			//alert(params);
			
			//2)<form id="search"></form>
			//  폼안의 모든 컨트롤요소들 가져오기
			let params=$("#search").serialize();
			//alert(params); //keyword=java
			
			$.post("searchproc.do", params, responseProc);
			
		});//keyup() end
		
		
		function responseProc(data){
			//alert(data);
			//예) 3|"자바" "자바 프로그래밍" "자바 안드로이드"
			
			if(data.length>0){ //응답받은 내용이 있는지
				let result=data.split("|"); // | 기호를 기준으로 문자열 분리
				//alert(result[0]); //3
				//alert(result[1]); //"자바" "자바 프로그래밍" "자바 안드로이드"
				let title=result[1].split(",");
				let str=""; //최종 결과값
				$.each(title, function(index, key){
					//alert(index);
					//alert(key);
					str += "<a href='#'>" + key + "</a>";
					str += "<br>";
				});//each() end
				
				$("#panel").html(str);
				$("#panel").show();
				
			}else{
				$("#panel").hide();
			}//if end
			
		}//responseProc() end
	</script>
</body>
</html>