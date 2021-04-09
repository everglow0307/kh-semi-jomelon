<%@page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!-- 데이터를 한번에 받아오는 빈클래스를 사용하도록 -->
<jsp:useBean id="boardbean" class="com.jomelon.domain.SuggBoardBean">
	<jsp:setProperty name="boardbean" property="*" /> 
</jsp:useBean>

<%
	//데이터베이스 객체 생성
	SuggBoardDAO bdao = new SuggBoardDAO();
	bdao.reWriteBoard(boardbean);
	
	//답변 데이터를 모두 저장후 전체 게시글 보기를 설정
	response.sendRedirect("../suggBoardList.do");
%>
</body>
</html>