<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jomelon.domain.QnABoardVO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<link href="${pageContext.request.contextPath}/css/brandintrostyle.css" rel="stylesheet" type="text/css" />
<section>

<%
QnABoardVO qnaVO = (QnABoardVO)request.getAttribute("qnaVO");
%>

		<h1 style="text-align:center;"> Q&A 게시판 글 수정</h1>
		
		<form action="QnAUpdate.do" method="post">
			<table style="text-align: center; width:80%; margin:3%; margin-left:10%; border: 1px solid #dddddd;">
				<thead>
					<tr>
						<th colspan="3" style="background-color: white; text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<tr style="text-align:left;">
					<td style="margin-left:10%;">글번호 : <input type="text" value="<%= qnaVO.getQnAID() %>" readonly  name="QnAID" ></td>
					</tr>
					<tr style="text-align:left;">
						<td>제목 : <input type="text" name="QnATitle" maxlength="50" style="width:50%; height:50px; margin:1%" value="<%= qnaVO.getQnATitle() %>"></td>
					</tr>
					<tr>
						<td><textarea name="QnAContent" maxlength="2048" style="height:500px; width:95%;  margin:3%"><%= qnaVO.getQnAContent() %></textarea></td>
					</tr>
				</tbody>
			</table>
				<input type="submit" style="margin-left:80%; width:100px; height:50px;" value="글수정">
			</form>
				
				
           <!-- QnA 수정페이지
           <Form action="QnAInput.do">
           <input type="submit" value="글수정"> -->
           
        </section>