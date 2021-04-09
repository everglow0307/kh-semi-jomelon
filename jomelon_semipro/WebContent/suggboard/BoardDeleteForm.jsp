<%@page import="com.jomelon.domain.SuggBoardBean"%>
<%@page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	SuggBoardDAO bdao = new SuggBoardDAO();

	int num = Integer.parseInt(request.getParameter("num"));
	//하나의 게시글을 리턴
	SuggBoardBean bean = bdao.getOneUpdateBoard(num);
%>
<center>
<h2>게시글 삭제</h2>
<form action="${pageContext.request.contextPath}/suggboard/BoardDeleteProc.jsp" method="post">
<table width="1500" border="1" bgcolor="darkgray">
	<tr height="40">
		<td width="120" align="center">작성자</td>
		<td width="180" align="center"><%=bean.getWriter() %></td>
		<td width="120" align="center">작성일</td>
		<td width="180" align="center"><%=bean.getReg_date() %></td>
	</tr>
	<tr height="40">
		<td width="120" align="center">제목</td>
		<td align="left" colspan="3"><%=bean.getSubject() %></td>
	</tr>
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td align="left" colspan="3"><input type="password" name="password" size="60"></td>
	</tr>
	<tr height="40">
		<td colspan="4" align="center">
		<input type="hidden" name="num" value="<%=num %>">
		<input type="submit" name="글삭제"> &nbsp;&nbsp;
		<input type="button" onclick="location.href='${pageContext.request.contextPath}/suggBoardList.do'" value="목록보기"></td>
	</tr>
</table>
</form>
</center>
</body>
</html>