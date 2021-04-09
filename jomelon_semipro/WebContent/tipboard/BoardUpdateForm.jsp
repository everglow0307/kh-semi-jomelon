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
<title></title>
</head>
<body>
<center>
	<h2> 게시글 수정</h2>
	<% 
		//해당 게시글 번호를 통하여 게시글을 수정할지
		int num = Integer.parseInt(request.getParameter("num").trim());
	
		//하나의 게시글의 대한 정보를 리턴 
		TipBoardDAO bdao = new TipBoardDAO();
		TipBoardBean bean = bdao.getOneUpdateBoard(num);
	%>
	<form action="${pageContext.request.contextPath}/tipboard/BoardUpdateProc.jsp" method ="post">
		<table width = "600" border="1" bordercolor = "gray" class="type06">
			<tr height= "40">
				<td width="120" align="center">작성자</td>
				<td width="180" align="center"><%=bean.getWrite()%></td>
				<td width="120" align="center">작성자</td>
				<td width="180" align="center"><%=bean.getReg_date()%></td>
			</tr>
			
			<tr height= "40">
				<td width="120" align="center">제목</td>
				<td width="480" colspan="3">&nbsp;<input type="text" name="subject" value="<%=bean.getSubject()%>" size="60"></td>
			</tr>
			
			<tr height= "40">
				<td width="120" align="center">패스워드</td>
				<td width="480" colspan="3">&nbsp;<input type="password" name="password" size="60"></td>
			</tr>
			
			<tr height= "40">
				<td width="120" align="center">글내용</td>
				<td width="480" colspan="3"><textarea rows="10" cols="60" name="content" align="left"><%=bean.getContent()%></textarea></td>
			</tr>
			
			<tr height= "40">
				<td colspan="4" align="center">
					<input type="hidden" name="num" value="<%=bean.getNum() %>">
					<input type="submit" class="button" value="글수정">&nbsp;&nbsp;
					<input type="button" class="button" onclick = "location.href='${pageContext.request.contextPath}/'" value="전체글 보기">
				</td>
			</tr>
		</table>
	</form>
</center>
</body>
</html>