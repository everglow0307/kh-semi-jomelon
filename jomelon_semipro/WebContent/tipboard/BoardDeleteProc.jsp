<%@page import="com.jomelon.dao.TipBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String pass =  request.getParameter("password");
		int num = Integer.parseInt(request.getParameter("num"));
		
		//데이터 베이스 연결 
		TipBoardDAO bdao = new TipBoardDAO();
		String password = bdao.getPass(num);
		
		//기존 패스워드 값과 delete form에서 작성한 패스워드를 비교 
		if(pass.equals(password)){
			
			//패스워드가 둘다 같다면 
			bdao.deleteBoard(num);
			
			response.sendRedirect("../tipBoardList.do");
	
		}else{
	%>
	<script>
		alert("패스워드가 틀려서 삭제할수 없습니다. 패스워드를 확인해 주세요");
		history.go(-1);
	</script>	
	<% 
	}
	%>

</body>
</html>