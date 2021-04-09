<%@page import="com.jomelon.dao.TipBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<!--데이터를 한번에 받아오는 빈클래스를 사용하도록  -->
	<jsp:useBean id = "boardbean" class= "com.jomelon.domain.TipBoardBean">
		<jsp:setProperty name="boardbean" property = "*" />
	</jsp:useBean>

	<% 
		//데이터 베이스 객체 생성
		TipBoardDAO bdao = new TipBoardDAO();
		bdao.reWriteBoard(boardbean);
		
		response.sendRedirect("../tipBoardList.do");
		
	%>

</body>
</html>