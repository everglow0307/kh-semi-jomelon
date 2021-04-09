<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<center>
	<h2> 게시글 쓰기 </h2>
	
		<form action = "${pageContext.request.contextPath}/tipboard/BoardWriteProc.jsp" method = "post">
			<table	width ="600" border = "1" bordercolor = "gray" class="type06">
				<tr height="40">
						<td align = "center" width ="150">작성자</td>
						<td width="450"><input type= "text" name = "write" size = "60"></td>
				</tr>
				
				<tr height="40">
						<td align = "center" width ="150">제목</td>
						<td width="450"><input type= "text" name = "subject" size = "60"></td>
				</tr>
				
				<tr height="40">
						<td align = "center" width ="150">이메일</td>
						<td width="450"><input type= "email" name = "email" size = "60"></td>
				</tr>
				
				<tr height="40">
						<td align = "center" width ="150">비밀 번호</td>
						<td width="450"><input type= "password" name = "password" size = "60"></td>
				</tr>
				
				<tr height="40">
						<td align = "center" width ="150">글 내용</td>
						<td width="450"><textarea rows= "10" cols = "60" name = "content"></textarea></td>
				</tr>
				
				<tr height="40">
						<td align = "center" colspan = "2">
							<input type= "submit" class="button" value="글쓰기">&nbsp;&nbsp;
							<input type= "reset"  class="button" value="다시 작성">&nbsp;&nbsp;
							<button onclick="location.href='BoardList.jsp'" class="button" >전체글 보기</button>
						</td>
				</tr>
			</table>		
		</form>
	</center>
</body>
</html>