<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<center>
<h2>게시글 쓰기</h2>

<form action="${pageContext.request.contextPath}/suggboard/BoardWriteProc.jsp" method="post">
<table width="1500" border="1" bordercolor="gray" bgcolor="darkgray">
	<tr height="40">
		<td align="center" width="150">작성자</td>
		<td width="450"><input type="text" name="writer"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150">제목</td>
		<td width="450"><input type="text" name="subject"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150">이메일</td>
		<td width="450"><input type="email" name="email"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150">비밀번호</td>
		<td width="450"><input type="password" name="password"></td>
	</tr>
	<tr height="40">
		<td align="center" width="150">글내용</td>
		<td width="450"><textarea rows="10" cols="50" name="content"></textarea></td>
	</tr>
	<tr height="40">
		<td align="center" colspan="2"></td>
		<input type="submit" value="글쓰기"> &nbsp;&nbsp;
		<input type="reset" value="다시작성"> &nbsp;&nbsp;
		<button onclick="location.href='BoardList.jsp'">전체 게시글보기</button>
	</tr>
	
</table>
</form>

</center>
</body>
</html>