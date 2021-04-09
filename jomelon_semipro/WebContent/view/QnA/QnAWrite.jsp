<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width", initial-scale="1">
<link href="${pageContext.request.contextPath}/css/brandintrostyle.css" rel="stylesheet" type="text/css" />
<section>
		<h1 style="text-align:center;">QnA 게시판 글 작성</h1>
		<form action="QnAWrite.do" method="post">
			<table style=" width:80%; margin:3%; margin-left:10%; border: 2px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: white; text-align: center;"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>&nbsp; 제목 : <input type="text" name="QnATitle" maxlength="50" style="width:50%; height:50px; margin:3%;"></td>
					</tr>
					<tr>
						<td><textarea name="QnAContent" maxlength="2048" style="height:500px; width:95%;  margin:3%"></textarea></td>
					</tr>
					<tr>
						<td> 작성자 : <input type="text" name="userId" style="width:20%; height:30px;"></td>
					</tr>
				</tbody>
			</table>
				<input type="submit" style="margin-left:80%; width:100px; height:50px;" value="글작성">
			</form>
		</div>
	</div>

<!--            QnA 입력페이지
           <Form action="QnA.do" method="get">
           <input type="submit" value="등록">
           
           </Form>
 -->        </section>