<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mainheaderstyle.css"
	type="text/css" />
<div class="jb-box">
	<video muted autoplay loop>
		<source src="${pageContext.request.contextPath}/images/JoMelone.mp4"
			type="video/mp4">
		<strong>Your browser does not support the video tag.</strong>
	</video>
	<header>
		<p class="jomel">
			<a href="main.do">Jo Melon</a>
		</p>
	</header>
	<nav>
		<ul class="topnav">
			<li class="dropdown"><a href="brandintro.do" class="dropbtn">브랜드소개</a>
				<ul class="dropdown-content">
					<li><a href="brandintro.do">JoMelon 소개</a></li>
					<li><a href="#">Gallay</a></li>
					<li><a href="#">Jo Melon 소식</a></li>
					<li><a href="${pageContext.request.contextPath}/tipBoardList.do">사용자 팁</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropbtn">제품구매</a>
				<ul class="dropdown-content">
					<li><a href="#">향수</a></li>
					<li><a href="#">헤어/바디</a></li>
					<li><a href="#">페이셜</a></li>
					<li><a href="#">이벤트</a></li>
					<li><a href="#">향수추천</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropbtn">고객센터</a>
				<ul class="dropdown-content">
					<li><a href="#">프로모션</a></li>
					<li><a href="${pageContext.request.contextPath}/noticeList.do">공지사항</a></li>
					<li><a href="${pageContext.request.contextPath}/QnAList.do">Q&A</a></li>
					<li><a href="${pageContext.request.contextPath}/suggBoardList.do">건의사항</a></li>
				</ul></li>
			<li class="dropdown"><a href="reviewBoardList.do" class="dropbtn">사용후기</a>
				<ul class="dropdown-content">
					<li><a href="${pageContext.request.contextPath}/reviewBoardList.do">텍스트리뷰</a></li>
					<li><a href="${pageContext.request.contextPath}/reviewBoardList.do">포토리뷰</a></li>
					<li><a href="${pageContext.request.contextPath}/reviewBoardList.do">소셜리뷰</a></li>
				</ul></li>
			<c:if test="${login}">
				<li class="right"><a href="myinfo.do">나의정보</a></li>
				<li class="right"><a href="logout.do">로그아웃</a></li>
			</c:if>
			<c:if test="${empty login}">
				<li class="right"><a href="join.do">회원가입</a></li>
				<li class="right"><a href="login.do">로그인</a></li>
			</c:if>
		</ul>
	</nav>
</div>
