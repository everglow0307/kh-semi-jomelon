<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>텍스트 리뷰</title>
<!--Bootstrap3 CDN-->
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
  /* 리뷰 헤더부분 패딩 조정 */
  .page-header{
    padding:20px 0;
  }
  
  /* 리뷰내용 마진 */
  .prvContent{
    margin: 20px 0;
  }
 
</style>
</head>
<body>
  <div class="container">
    <div class="page-header text-center">
      <h3><strong>텍스트 리뷰</strong></h3>
    </div>
    <!-- 리뷰제목, 작성자명 -->
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>제목</th>
          <td colspan="3">${review.subject}</td>
        </tr>
        <tr>
          <th>글번호</th>
          <td>${review.num }</td>
          <th>작성자</th>
          <td>${review.writer }</td>
        </tr>
        <tr>
          <th>작성일</th>
          <td>${review.regDate }</td>
          <th>조회수</th>
          <td>${review.readCount }</td>
        </tr>
      </tbody>
    </table>
    
    <!--리뷰내용-->
    <div class="row prvContent">
      <div class="col-sm-12">
		${review.content }
      </div>
    </div>
    <!-- 리뷰목록버튼 -->
    <div class="row">
      <div class="col-sm-12">
        <a href="${pageContext.request.contextPath}/reviewBoardList.do" class="btn btn-default" role="button">리뷰목록</a>
        <a href="${pageContext.request.contextPath}/reviewBoardReWrite.do?
        num=${review.num}&ref=${review.ref}&reStep=${review.reStep}&reLevel=${review.reLevel}" class="btn btn-default" role="button">답글작성</a>
        <a href="${pageContext.request.contextPath}/reviewBoardUpdate.do?num=${review.num}" class="btn btn-default" role="button">수정</a>
        <a href="${pageContext.request.contextPath}/reviewBoardDelete.do?num=${review.num}" class="btn btn-default" role="button">삭제</a>
      </div>
    </div>
    <div class="row">
      <hr>
    </div>
  </div><!--container-->
</body>
</html>