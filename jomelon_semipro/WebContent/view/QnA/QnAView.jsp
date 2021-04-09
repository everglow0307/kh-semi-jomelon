<%@page import="com.jomelon.domain.QnABoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<link href="${pageContext.request.contextPath}/css/brandintrostyle.css" rel="stylesheet" type="text/css" />
<section>

<%
 QnABoardVO qnaVO = (QnABoardVO)request.getAttribute("qnaVO");
%>
			<h1 style="text-align:center; margin:3%;">Q&A 게시판 </h1>
			<table style="text-align: center; width:80%; margin:7%; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="border: 1px solid #dddddd; background-color: white; text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="border: 1px solid #dddddd; width:20%; height:100px;">글제목</td>
						<td colspan="2" style="border: 1px solid #dddddd;"><%= qnaVO.getQnATitle() %></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd; width:20%; height:50px;">작성자</td>
						<td colspan="2" style="border: 1px solid #dddddd;"><%= qnaVO.getUserId() %></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd; width:20%; height:50px;">작성일자</td>
						<td colspan="2" style="border: 1px solid #dddddd;"><%= qnaVO.getQnADate()%></td>
					</tr>
					<tr>
						<td style="border: 1px solid #dddddd; width:20%; height:500px;">내용</td>
						<td colspan="2" style=" border: 1px solid #dddddd; min-height:200px; text-align:center;"><%= qnaVO.getQnAContent().replaceAll(" ","&nbsp;").replaceAll("<","&lt;").replace("\n", "<br>") %></td>
					</tr>
				</tbody>
			</table>
			
				<a style="text-decoration:none; color:black; margin-left:60%" href="QnAUpdate.do?QnAID=<%= qnaVO.getQnAID() %>">수정</a>
				<a style="text-decoration:none; color:black; margin-left:5%" onclick="return contirm('정말로 삭제하시겠습니까?')" href="QnADelete.do?QnAID=<%= qnaVO.getQnAID() %>">삭제</a>
			<a href="QnAList.do" style="text-decoration:none; color:black; margin-left:5%">목록으로 돌아가기</a>


<!--            QnA 상세페이지(보기만)
           <Form action="QnAInput.do">
           <input type="submit" value="글쓰기"> -->

        </section>