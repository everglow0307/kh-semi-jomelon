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
 
</style>
</head>
<body>
  <div class="container">
    <div class="page-header text-center">
      <h3><strong>텍스트 리뷰</strong></h3>
    </div>
    <form action="${pageContext.request.contextPath}/reviewBoardDelete.do" method="post">
      <br><br><br>
    <div class="row">
      <div class="col-sm-7">
        <h5><strong>비밀번호</strong></h5>
        <input type="password" class="form-control" name="reviewPw"/>
      </div>
    </div>
    <!-- 리뷰목록버튼 -->
    <div class="row">
      <div class="col-sm-12">
        <br>
        <input type="hidden" name="num" value="${review.num }"/>
        <input type="hidden" name="password" value="${review.password }"/>
        <input type="submit" class="btn btn-default" role="button" value="리뷰 삭제"/>
        <a href="${pageContext.request.contextPath}/reviewBoardList.do" class="btn btn-default" role="button">취소</a>
      </div>
    </div>
  </form>
   
  </div><!--container-->
</body>
</html>