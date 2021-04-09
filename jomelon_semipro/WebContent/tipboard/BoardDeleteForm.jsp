<%@page import="com.jomelon.domain.TipBoardBean"%>
<%@page import="com.jomelon.dao.TipBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<style>

.button {

    width:100px;

    background-color: #f8585b;

    border: none;

    color:#fff;

    padding: 10px 0;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;

}
	table.type06 {
	  border-collapse: collapse;
	  text-align: left;
	  line-height: 1.5;
	  
	  margin: 20px 10px;
	  font-size : 15px;
	}
	table.type06 tr {
	  width: 150px;
	  padding: 10px;
	  vertical-align: top;
	}
	table.type06 td {
	  width: 350px;
	  padding: 10px;
	  vertical-align: top;
	}
	table.type06 .even {
	  background: #efefef;
	  font-weight : bold;
	}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		TipBoardDAO bdao = new TipBoardDAO();
	
		int num = Integer.parseInt(request.getParameter("num"));
		//하나의 게시글로 리턴 
		TipBoardBean bean =  bdao.getOneUpdateBoard(num);
	%>
<center>
	<form action = "BoardDeleteProc.jsp" method="post">
		<table width="600" border="1" bordercolor = "gray" class="type06">
			<tr height="40">
				<td width="120" align ="center">작성자</td>
				<td width="180" align ="center"><%=bean.getWrite() %></td>
				<td width="120" align ="center">작성일</td>
				<td width="180" align ="center"><%=bean.getReg_date() %></td>
			</tr>
			
			<tr height="40">
				<td width="120" align ="center">제목</td>
				<td align ="left" colspan="3"><%=bean.getSubject() %></td>
			</tr>
			
			<tr height="40">
				<td width="120" align ="center">패스워드</td>
				<td align ="left" colspan="3"><input type ="password" name="password"></td>
			</tr>
			
			<tr height="40">
				<td align ="center" colspan="4">
					<input type ="hidden" name ="num" value="<%=num %>">
					<input class="button" type ="submit" value="글삭제">&nbsp;&nbsp;
					<input class="button" type ="button" onclick="location.href='${pageContext.request.contextPath}/tipBoardList.do'"value="목록보기 ">
				</td>
			</tr>
		</table>
	</form>
</center>

</body>
</html>