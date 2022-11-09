<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>idCheck_cookie.jsp</title>
	<script src="../js/jquery-3.6.1.min.js"></script>
	<script src="../js/jquery.cookie.js"></script>
</head>
<body>
	<!-- 쿠키를 활용하여 아이디 중복확인을 해야만 회원가입이 가능하다 -->
	<h3>회원등록폼(cookie)</h3>
	
	<form name="memfrm" method="post" action="./insert.do" onsubmit="return send()">
		<table border="1" width="400px">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="userid" id="userid">
					<input type="button" value="아이디중복확인" id="btn_userid">    
					<br>
					<span id="panel"></span><!-- 아이디 중복 관련 메세지 -->
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="passwd"></td>
			</tr>
	<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원가입">
				</td>
			</tr>	
			</table>	
	</form>
	
	<script>
		
		//6)해당 페이지가 로딩되었을 때 아이디중복확인과 관련된 쿠키변수가 있다면 지우고 시작해야함
		$(function(){
			$.removeCookie("checkID");
		}); //end
	
	
		//1) id="btn_userid" 아이디 중복 확인 버튼을 클릭했을 때
		$("#btn_userid").click(function(){
			//2)입력한 id="btn_userid"값을 변수에 대입하기
			//전송해야 하는 값이 많은 경우 변수를 만들어서 옮겨 담아서 전송한다. params(여러개),param 이런거 많이 사용함.
			let params="userid="+$("#userid").val().trim();
		
			//3)post방식으로 서버에 요청해서 응답받기
			//형식) $.post("요청명령어", 전달값, 콜백함수 , 응답받는형식)
			
			//..1 text응답
			//$.post("idcheckcoockieproc.do", params, checkID, "text");
			
			
			//..2 JSON응답
			$.post("idcheckcoockieproc.do", params, checkID, "json");
			
		}); //click() end
		
		
		//4)콜백함수
		function checkID(result){
			
			//..1 text응답
			//alert(result);
			
			//..2 JSON응답
			//alert(result);//[object Object] 또는 
			//alert(result.count);
			
			//5)서버에서 응답받는 메세지(result)를 본문의 id=panel에 출력하고, 쿠키변수에 저장
			//  형식) $.cookie("쿠키변수명", 값)
			let count=eval(result.count); //형변환
			if(count==0){
				$("#panel").css("color","blue");
				$("#panel").text("사용 가능한 아이디 입니다");
				$.cookie("checkID", "PASS"); //아이디중복확인을 했다는 증거
			}else{
				$("#panel").css("color","red");
				$("#panel").text("중복된 아이디 입니다");
				$("#userid").focus(); //커서생성
			}//if end			
		}//checkID end
		
		//7)아이디중복확인을 해야만 회원가입폼이 서버로 전송
		function send() {
			//아이디 입력했는지?
			//비밀번호는 입력했는지?
			//이름은 입력했는지?
			//이메일을 입력했는지? ...글자수 등..
			
			//아이디중복확인을 했는지?
			let checkID=$.cookie("checkID"); //쿠키변수값 가져오기
			if(checkID=="PASS"){
				return true; //서버로 전송
			}else{
				$("#panel").css("color","green");
				$("#panel").text("아이디 중복 확인 해주세요");
				$("#userid").focus();
				return false;
			}//if end
		}//send() end
		
	</script>
</body>
</html>