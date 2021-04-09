<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Icon -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<style>
.notice {
	text-align: center;
}

.btn-list {
	text-align: center;
}

.btn-text {
	width: 80px;
	background-color: lightgray;
	border: none;
	color: black;;
	padding: 10px 0;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	margin: 4px;
	cursor: pointer;
}

.text-align-center {
	text-align: center;
}
</style>

</head>

<body>
	<br>
	<br>
	<h2 class="notice">공지사항</h2>
	<br>
	<br>
	<main>
		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th colspan="3">${n.title }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>작성일</th>
					<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
							value="${n.regdate}" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${n.writerId}</td>
					<th>조회수</th>
					<td>${n.hit }</td>
				</tr>
				<tr class="content">
					<td colspan="4">${n.content }</td>
				</tr>
			</tbody>
		</table>
		<div class="margin-top text-align-center">
			<a class="btn-text btn-cancel" href="${pageContext.request.contextPath}/admin/noticeList.do">목록</a> 
			<a class="btn-text btn-default" href="noticeEdit.do?id=${n.id }">수정</a>
			<a	class="btn-text btn-default" href="noticeDel.do?id=${n.id }">삭제</a>
		</div>
		<br>
		<div class="margin-top">
			<table class="table border-top-default">
				<tbody>

					 <tr>
                        <th>다음글</th>
                    
                        <td colspan="3"  class="text-align-left text-indent"><a class="text-align-left" href="noticeDetail.do?id=${nn.id }">${(empty nn.title)? "다음 글이 없습니다.":nn.title }</a></td>
                    </tr>                    
                    <tr>
                        <th>이전글</th>
                        <td colspan="3"  class="text-align-left text-indent"><a href="noticeDetail.do?id=${pn.id }">${(empty pn.title)? "이전 글이 없습니다.":pn.title }</a></td>
                    </tr>

				</tbody>
			</table>
		</div>
	</main>


</body>

</html>