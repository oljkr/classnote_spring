<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>list.jsp</title>
	<style>
		*{ font-family: gulim; font-size: 24px; }
	</style>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="title">미디어 그룹 목록</div>
	<div class="content">
		<input type="button" value="그룹등록" onclick="location.href='create.do'">
	</div>
	
	<c:if test="${requestScope.count==0}">
		<table><tr><td>게시판에 글 없음!!</td></tr></table>
	</c:if>
	
	<c:if test="${requestScope.count>0}">
		<table>
		<tr>
			<th>그룹번호</th>
			<th>그룹제목</th>
			<th>수정/삭제</th>
		</tr>
	
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.mediagroupno}</td>
				<td><a href="../media/list.do?mediagroupno=${dto.mediagroupno}">${dto.title}</a></td>
				<td>
					<input type="button" value="수정" onclick="location.href='update.do?mediagroupno=${dto.mediagroupno}'">
					<input type="button" value="삭제" onclick="location.href='delete.do?mediagroupno=${dto.mediagroupno}'">
				</td>
			</tr>
		</c:forEach>
		
		</table>
	</c:if>
	
	<!-- 페이지 리스트 -->
	<c:if test="${requestScope.count>0 }">
		<c:set var="pageCount" value="${requestScope.totalPage}"/> <!-- 전체 페이지수 -->
		<c:set var="startPage" value="${requestScope.startPage}"/>
		<c:set var="endPage" value="${requestScope.endPage}"/>
		
		<div class="content">
			<!-- Cont에서 계산되는 endPage가 현재 데이터 갯수로 계산되는 전체 페이지 수보다 클 경우 
		     	 endPage를 현재데이터 갯수로 계산되는 전체 페이지 수+1로 설정함 -->
			<c:if test="${endPage>pageCount}"> 
				<c:set var="endPage" value="${pageCount+1}"/>
			</c:if>
			
			<!-- 현재 페이지가 11이상일 때 [이전]버튼이 나타남 -->
			<c:if test="${startPage>0}"> 
				<a href="./list.do?pageNum=${startPage}">[이전]</a>
			</c:if>
			

			<c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
				<c:choose>
					<c:when test="${pageNum==i}"><span style="font-weight: bold">${i}</span></c:when>
					<c:when test="${pageNum!=i}"><a href="./list.do?pageNum=${i}">[${i}]</a></c:when>
				</c:choose>
			</c:forEach>
			
			<!-- Cont에서 계산되는 endPage가 현재 데이터 갯수로 계산되는 전체 페이지 수보다 작을경우 
		    	 [다음]버튼을 나타내게 함 -->
			<c:if test="${endPage<pageCount}">
				<a href="./list.do?pageNum=${startPage+11}">[다음]</a>
			</c:if>
		</div>
		
	</c:if>
	
</body>
</html>