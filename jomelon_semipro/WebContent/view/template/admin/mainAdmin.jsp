<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
/* 푸터 */
footer {
	background-color: black;
	font-family: "맑은고딕";
	color: white;
	vertical-align: middle;
	font-size: 16px;
	height: 70px;
	position: relative;
}

.btn-group .button {
	background-color: #28231d; /* Green */
	border: none;
	color: white;
	padding: 8px 30px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	font-family: "맑은고딕";
	cursor: pointer;
	float: left;
}

.btn-group .button:hover {
	background-color: gray;
}
</style>
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<c:if test="${contentPage eq null}">
		<c:import url="center.jsp" />
	</c:if>
	<c:if test="${!empty contentPage}">
		<c:import url="${contentPage}"></c:import>
	</c:if>
	<footer>
		<br> &ensp; ⓒ copyright by KH
	</footer>

</body>
</html>