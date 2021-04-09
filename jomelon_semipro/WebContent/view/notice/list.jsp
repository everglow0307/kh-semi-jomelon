<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>

<head>
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

.search_select {
	height: 30px;
}

.search_box {
	margin-left: auto;
	margin-right: auto;
}

.pagination {
	justify-content: center;
}

.bold {
	font-weight: bold;
	background: lightgray;
}

.filterSelect {
	margin-top: 50px;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	color: #ccc;
}


</style>

</head>

<body>

	<div class="container">

		<br> <br>
		<h2 class="notice">공지사항</h2>
		<br> <br>

		<main class="main">
			<!-- 목록 -->
			<table class="table">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="n" items="${list}">
						<tr>
							<td>${n.id}</td>
							<td><a href="noticeDetail.do?id=${n.id }">${n.title}</a></td>
							<td>${n.writerId}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${n.regdate}" /></td>
							<td>${n.hit}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<br>

			<!-- 검색 -->
			<form>
				<table class="search_box">
					<tr>
						<td><select class="search_select" name="f">
								<option ${(param.f == "title")?"selected":""} value="title">제목</option>
								<option ${(param.f == "writer_id")?"selected":""}
									value="writer_id">작성자</option>
						</select></td>
						<td><input type="text" name="q" value="${param.q }">
							<button type="submit">검색</button></td>
					</tr>
				</table>
				<br>
			</form>

			<!-- 페이지 -->

			<c:set var="page" value="${(empty param.p)? 1: param.p}" />
			<c:set var="startNum" value="${page-(page-1)%5}" />
			<c:set var="lastNum"
				value="${fn:substringBefore(Math.ceil(count/10),'.')}" />


			<ul class="pagination">

				<li class="page-item"><c:if test="${startNum>1}">
						<a class="page-link" href="?p=${startNum-1}&t=&q="
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if> <c:if test="${startNum<=1}">
						<a class="page-link" onclick="alert('이전 페이지가 없습니다.');"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
						</a>
					</c:if></li>

				<c:forEach var="i" begin="0" end="4">
					<c:if test="${(startNum+i)<=lastNum}">
						<li class="page-item"><a
							class="page-link ${(page==(startNum+i))?'bold':''}"
							href="?p=${startNum+i}&f${param.f}=&q=${param.q}">
								${startNum+i}</a></li>
					</c:if>
				</c:forEach>

				<li class="page-item"><c:if test="${startNum+5<=lastNum}">
						<a class="page-link" href="?p=${startNum+5}&t=&q="
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
						</a>
					</c:if> <c:if test="${startNum+5>lastNum}">
						<a class="page-link" onclick="alert('다음 페이지가 없습니다.');"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
							class="sr-only">Next</span>
						</a>
					</c:if></li>
			</ul>

		</main>

	</div>


</body>

</html>