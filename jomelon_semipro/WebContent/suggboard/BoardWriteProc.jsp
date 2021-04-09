<%@page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("euc-kr"); // 한글처리
%>
<!-- 게시글 작성한 데이터를 한번에 읽어드림 -->
<jsp:useBean id="boardbean" class="com.jomelon.domain.SuggBoardBean">
	<jsp:setProperty name="boardbean" property="*"></jsp:setProperty>
</jsp:useBean>

<%
	// 데이터 베이스 쪽으로 빈클래스를 넘겨옴
	SuggBoardDAO bdao = new SuggBoardDAO();
	
	// 데이터 저장 메소드를 호출
	bdao.insertBoard(boardbean);
	
	// 게시글 저장후 전체 게시글 보기
	response.sendRedirect("../suggBoardList.do");
%>
</body>
</html>