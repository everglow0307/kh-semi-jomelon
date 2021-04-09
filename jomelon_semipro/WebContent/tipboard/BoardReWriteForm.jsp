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
	<h2>답변글 입력하기</h2>
	<% 
		//게시글 읽기에서 답변글쓰기를 클릭하면 넘겨주는 데이터들을 받아줌
		int num = Integer.parseInt(request.getParameter("num"));
		int ref = Integer.parseInt(request.getParameter("ref"));
		int re_stop = Integer.parseInt(request.getParameter("re_stop"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
	%>

	<form action="BoardReWriteProc.jsp" method="post">
		<table width="600" border="1" bordercolor="gray" class="type06">
			<tr height = "40">
				<td width="150" align="center"> 작성자</td>
				<td width="450"><input type ="text" name="write" size ="60"></td>
			</tr>
			
			<tr height = "40">
				<td width="150" align="center">제목</td>
				<td width="450"><input type ="text" name="subject"  value ="[답변]" size ="60"></td>
			</tr>
			
			<tr height = "40">
				<td width="150" align="center">이메일</td>
				<td width="450"><input type ="email" name="email" size ="60"></td>
			</tr>
			
			<tr height = "40">
				<td width="150" align="center">비밀 번호</td>
				<td width="450"><input type ="password" name="password" size ="60"></td>
			</tr>
		
			<tr height = "40">
				<td width="150" align="center">글 내용</td>
				<td width="450"><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			
			<!--form에서 사용자로부터 입력바지 않고 데이터를 넘김 -->
			<tr height ="40">
				<td align ="center" colspan="2">
					<input type="hidden" name ="ref" value="<%=ref%>">
					<input type="hidden" name ="re_stop" value="<%=re_stop%>">
					<input type="hidden" name ="re_level" value="<%=re_level%>">
					<input type="submit" class="button" value="답글쓰기 완료">&nbsp;&nbsp;
					<input type="reset" class="button" value="취소">
					<input type="button" class="button" onclick="location.href='${pageContext.request.contextPath}/tipBoardList.do'" value="전체글 보기">
				</td>
			</tr>
		</table>
	</form>
	</center>
</body>
</html>