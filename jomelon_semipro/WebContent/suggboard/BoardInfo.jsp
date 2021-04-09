<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page import="com.jomelon.domain.SuggBoardBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int num = (Integer)request.getAttribute("num");//공백 제거후 정수형으로 바뀜
		
		//데이터 베이스 접근
		SuggBoardDAO bdao = new SuggBoardDAO();
		
		//boardbean타입으로 하나의 게시글을 리턴
		SuggBoardBean bean  = bdao.getOneBoard(num);
	%>
	
	<center>
		<h2>게시글 보기</h2>
			<table width = "1500" border = "1" bgcolor="darkgray">
				<tr height = "40">
					<td width = "100" align ="center">글번호 </td>
					<td width = "180" align ="left"><%=bean.getNum()%></td>
					<td width = "120" align ="center">조회수 </td>
					<td width = "180" align ="center"><%=bean.getReadcount()%> </td>
				</tr>
				
				<tr height = "40">
					<td width = "100" align ="center">작성자 </td>
					<td width = "180" align ="left"><%=bean.getWriter()%></td>
					<td width = "120" align ="center">작성일 </td>
					<td width = "180" align ="center"><%=bean.getReg_date()%> </td>
				</tr>
				
				<tr height = "40">
					<td width = "120" align ="center">이메일 </td>
					<td colspan = "3" align ="center"><%=bean.getEmail()%></td>
				</tr>
								
				<tr height = "40">
					<td width = "120" align ="center">제목 </td>
					<td colspan = "3" align ="center"><%=bean.getSubject()%></td>
				</tr>
				
				<tr height = "80">
					<td width = "120" align ="center">글 내용 </td>
					<td colspan = "3" align ="center"><%=bean.getContent()%></td>
				</tr>
				
				<tr height = "40">
					<td align ="center" colspan = "4">
						<input type="button" value="답글쓰기" 
						onclick="location.href='${pageContext.request.contextPath}/suggboard/BoardReWriteForm.jsp?num=<%=bean.getNum()%>&ref=<%=bean.getRef()%>&re_step=<%=bean.getRe_step()%>&re_level=<%=bean.getRe_level()%>'">   
						<input type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/suggboard/BoardUpdateForm.jsp?num=<%=bean.getNum()%>'"> 
						<input type="button" value="삭제하기" onclick="location.href='${pageContext.request.contextPath}/suggboard/BoardDeleteForm.jsp?num=<%=bean.getNum()%>'"> 
						<input type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath}//suggBoardList.do'">     
					</td>
				</tr>
		</table>
	</center>
</body>
</html>