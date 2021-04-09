<%@page import="com.jomelon.dao.SuggBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
%>
<!-- 사용자 데이터를 읽어드리는 빈클래스를설정 -->
<jsp:useBean id="boardbean" class="com.jomelon.domain.SuggBoardBean">
	<jsp:setProperty name="boardbean" property="*" />
</jsp:useBean>
<%
	//데이터 베이스에 연결
	SuggBoardDAO bdao = new SuggBoardDAO();
	//해당 게시글의 패스워드값을 얻어옴
	String pass = bdao.getPass(boardbean.getNum());
	
	//기본 패스워드값과 update시 작성했던 password값이 같은지 비교
	if(pass.equals(boardbean.getPassword())){
		//데이터 수정하는 메소드 호출
		bdao.updateBoard(boardbean);
		response.sendRedirect("../suggBoardList.do");
	}else{	//패스워드가 틀리다면
%>
		<script type="text/javascript">
		alert("패스워드가 일치 하지 않습니다. 다시 확인후 수정해주세요");
		history.go(-1);
		</script>
<%
	}
%>
</body>
</html>