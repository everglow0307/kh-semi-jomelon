<%@page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	String pass = request.getParameter("password");
	int num = Integer.parseInt(request.getParameter("num"));
	
	//데이터 베이스 연결
	SuggBoardDAO bdao = new SuggBoardDAO();
	String password = bdao.getPass(num);
	
	//기존 패스워드값과 delete form에서 작성한 패스워드를 비교
	if(pass.equals(password)){
		
		//패스워드가 같다면
		bdao.deleteBoard(num);
		
		response.sendRedirect("BoardList.jsp");
	}else{
%>

	<script>
	alert("패스워드가 틀려서 삭제 할수 없습니다. 패스워드를 확인해주세요");
	history.go(-1);
	</script>
<%		
	}
%>
</body>
</html>