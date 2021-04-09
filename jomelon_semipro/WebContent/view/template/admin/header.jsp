<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminMain/adminMenustyle.css"/>

<div class="navbar">
	<a href="${pageContext.request.contextPath}/main.do">홈</a> <a href="${pageContext.request.contextPath}/brandintro.do">브랜드소개</a> <a href="#buy">제품구매</a>
	<div class="dropdown">
		<button class="dropbtn">
			사용후기 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="${pageContext.request.contextPath}/reviewBoardList.do">텍스트리뷰</a> <a href="#">포토리뷰</a> <a href="#">소셜리뷰</a>
		</div>
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			고객센터 <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-content">
			<a href="#">프로모션</a> <a href="${pageContext.request.contextPath}/noticeList.do">공지사항</a> <a href="${pageContext.request.contextPath}/QnAList.do">QnA</a> <a
				href="#">FAQ</a>
		</div>
	</div>
	<div class="dropdown-right">
	<c:if test="${adminlogin}">
		<a href="${pageContext.request.contextPath}/logout.do">로그아웃</a> 
		<a href="${pageContext.request.contextPath}/adminPage.do">관리자페이지</a>
	</c:if>
	<c:if test="${empty adminlogin}">
		<a href="${pageContext.request.contextPath}/login.do">로그인</a> 
		<a href="${pageContext.request.contextPath}/join.do">회원가입</a>
	</c:if>
	</div>
</div>


