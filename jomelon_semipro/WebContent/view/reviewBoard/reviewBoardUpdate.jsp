<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>텍스트리뷰</title>
  
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
  /* 리뷰제목, 작성자, 내용 문구 폰트 조정 */
  .form-group{
    font-size: 15px;
    font-weight: bold;
  }
  /* 리뷰목록 돌아가기, 취소버튼 아래 마진조정 */
  .reset{
    margin-bottom:20px;
  }
</style>
</head>
<body>
  <div class="container">
    <div class="page-header text-center">
      <h3><strong>텍스트 리뷰</strong></h3>
    </div>
    <form action="${pageContext.request.contextPath}/reviewBoardUpdate.do" method="post">
     <!--제목 및 작성자-->
      <div class="row">
        <div class="col-sm-10">
          <div class="form-group">
            제목<input type="text" class="form-control col-xs-2" name="reviewSubject" value="${review.subject }"/>
          </div>
          <br><br>
          <div class="form-group">
            작성자 <input type="text" class="form-control" name="reviewWriter" value="${review.writer }"/>
          </div>
        </div>
      </div>
      <!--글내용-->
      <div class="row">
        <div class="col-sm-12">
          <div class="form-group">
            <label for="contents">내용</label>
            <textarea class="form-control" rows="30" name="reviewContent">${review.content }</textarea>
          </div>
        </div>
      </div>
      <!-- 파일 -->
      <div class="row">
        <br>
        <div class="col-sm-12">
          <input type="file" />
        <br>
        </div>
      </div>
      <!-- 비밀번호 입력 및 완료버튼 -->
      <div class="row">
        <div class="col-sm-3 text-left">
         비밀번호 <input type="password" class="form-control" name="reviewPw" />
        </div>
        <div class="col-sm-9 text-right">
        	<input type="hidden" name="num" value="${review.num }">
        	<input type="hidden" name="password" value="${review.password}">
            <input type="submit" class="btn btn-default" value="수정" id="update" />
        </div>
      </div>
    </form>
      <div class="row reset">
        <br>
        <div class="col-sm-12 text-left">
          <a href="${pageContext.request.contextPath}/reviewBoardList.do" class="btn btn-default" role="button">취소</a>
          <a href="${pageContext.request.contextPath}/reviewBoardList.do" class="btn btn-default" role="button">리뷰목록</a>
        </div>
      </div>
    </div><!--container-->
</body>
</html>