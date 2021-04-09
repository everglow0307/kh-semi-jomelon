<%@page import="com.jomelon.domain.QnABoardVO"%>
<%@page import="com.jomelon.dao.impl.QnADAOImpl"%>
<%@page import="com.jomelon.dao.QnADAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<link href="${pageContext.request.contextPath}/css/brandintrostyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	a, a:hover {
		color: #000000;
		text-decoration:none;
	}
</style>
<section>

 <%
	int pageNumber = 1;
	if(request.getParameter("pageNumber") != null) {
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}
%> 
		<h1 style="text-align:center; margin-top:3%;">QnA 게시판</h1>
		<FORM action="QnAWrite.do" method="get">
			<table style="text-align: center; width:80%; margin:5%; margin-left:10%; ">
				<thead>
					<tr style="height:80px;">
						<th style="background-color: white; text-align: center; border: 1px solid #dddddd">번호</th>
						<th style="background-color: white; text-align: center; border: 1px solid #dddddd">제목</th>
						<th style="background-color: white; text-align: center; border: 1px solid #dddddd">작성자</th>
						<th style="background-color: white; text-align: center; border: 1px solid #dddddd">작성일</th>
					</tr>
				</thead>
				<tbody>
				<%
					QnADAO qnaDAO = new QnADAOImpl();
					ArrayList<QnABoardVO> list = qnaDAO.getList(pageNumber);
					for(int i=0; i < list.size(); i++) {
				%>
					<tr style="height:40px;">
						<td style="border: 1px solid #dddddd"><%= list.get(i).getQnAID() %></td>
						<td style="border: 1px solid #dddddd"><a href="QnAView.do?QnAID=<%= list.get(i).getQnAID() %>"><%= list.get(i).getQnATitle() %></a></td>
						<td style="border: 1px solid #dddddd"><%= list.get(i).getUserId() %></td>
						<td style="border: 1px solid #dddddd"><%= list.get(i).getQnADate() %></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			
			<input style="margin-left:75%; width:100px; height:50px;" type="submit" value="글쓰기"><br>
			<div style="position:absolute; width:100%">
			<%
				if(pageNumber >= 0) {
			%>
				<div style="float:left;  margin-left:48%;">
					<a href="QnAList.do?pageNumber=<%=pageNumber - 1 %>"><h1 style="color:#dddddd;">< &nbsp;</h1></a>
				</div>
			<%-- <%
				} if(qnaDAO.nextPage(pageNumber + 1)) {	
			%> --%>
				<div style="float:left; width:40%;">
					<a href="QnAList.do?pageNumber=<%=pageNumber + 1 %>"><h1 style="color:#dddddd;">&nbsp; ></h1></a>
				</div>
			<%
				}
			%>
			</div>
		
		</FORM>
		
				
           <!-- QnA 리스트
           <Form action="QnAWrite.do" method="">
           <input type="submit" value="글쓰기">
           </Form> -->
        </section>