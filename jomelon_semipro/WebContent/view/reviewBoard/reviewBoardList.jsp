<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
  
  /* 링크스타일 지정 */
  a{
  	text-decoration:none;
    color:black;
  }
  a:hover{
    color:#ccc;
  }

  /* 리뷰 작성자, 작성일, 조회수 */
  .rvFilter{
    margin-bottom:20px; 
    font-weight:bolder;
  }

  /* 검색필터 margin-top 조정 */
  .filterSelect{
    margin-top:50px;
  }

</style>
</head>
<body>
<c:if test="${!empty msg}">
	<script type="text/javascript">
	alert("수정/삭제 비밀번호가 틀렸습니다.");
	</script>
</c:if>

  <div class="container">
    <div class="page-header text-center">
      <h3><strong>텍스트 리뷰</strong></h3>
    </div>
    <!--텍스트리뷰 공지사항-->
    <table class="table table-striped">
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
      <c:set var="number" value="${number}"/>
      <c:forEach var="review" items="${reviewList}">
        
        <tr>
          <td>${number}</td>
          <td>
          <!--  답변형을 표시하기 위함.-->
          	<c:if test="${review.reStep>1 }">
          		<c:forEach var="j" begin="1" end="${(review.reStep-1)*5}">
          			&nbsp;
          		</c:forEach>
          	</c:if>
          	<a href="${pageContext.request.contextPath}/reviewBoardInfo.do?num=${review.num}">
          	${review.subject}</a>
          </td>
          <td>${review.writer}</td>
          <td>${review.regDate}</td>
          <td>${review.readCount}</td>
        </tr>
        <c:set var="number" value="${number-1}"/>
       </c:forEach>
      </tbody>
    </table>
    <!-- 검색필터, 검색창 -->
    <div class="row filterSelect">
      <div class="col-sm-8">
        <form action="${pageContext.request.contextPath}/reviewBoardList.do" method="get">
        <div class="col-sm-3 form-group">
          <select class="form-control" id="sel1" name="searchFilter">
            <option value="subject" ${searchFilter=="subject"?"selected":""} >제목</option>
            <option value="content" ${searchFilter=="content"?"selected":""} >내용</option>
            <option value="writer" ${searchFilter=="writer"?"selected":""} >작성자</option>
          </select>
        </div>
        <div class="col-sm-7 input-group">
          <input type="text" class="form-control" name="search" value="${search}" >
          <div class="input-group-btn">
            <button class="btn btn-default" type="submit">
              <i class="glyphicon glyphicon-search"></i>
            </button>
          </div>
        </div>
        </form>
      </div>
      <!-- 리뷰작성버튼 -->
      <div class="col-sm-4 text-right">
        <a href="${pageContext.request.contextPath}/reviewBoardWrite.do" class="btn btn-default" role="button" >리뷰 작성</a>
      </div>
    </div>
    <!-- 페이지매김 -->
    <div class="row">
      <div class="col-sm-12 text-center">
      <ul class="pagination" >
      
      <c:if test="${count>0 }">
      	<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0?0:1) }"/>
      	<c:set var="startPage" value="${1}"/>
      	<c:if test="${currentPage%3!=0}">
      		<fmt:parseNumber var="result" value="${currentPage/3 }" integerOnly="true"/>
      		<c:set var="startPage" value="${result*3+1}" />
      	</c:if>
      	<c:if test="${currentPage%3==0}">
      		<fmt:parseNumber var="result" value="${currentPage/3 }" integerOnly="true"/>
      		<c:set var="startPage" value="${(result-1)*3+1}" />
      	</c:if>
      	<!-- 화면에 보여질 숫자 표현 -->
      	<c:set var="pageBlock" value="${3 }"/>
      	<c:set var="endPage" value="${startPage+pageBlock-1}"/>
      	
      	<c:if test="${endPage>pageCount }">
      		<c:set var="endPage" value="${pageCount}"/>
      	</c:if>
      	
      	<!-- 이전 링크를 걸지 파악 -->
      	<c:if test="${startPage>3 }">
      		<li><a href="reviewBoardList.do?pageNum=${startPage-3}" >[이전]</a></li>
      	</c:if>
  
      	<!--  페이징 처리 -->
      	<c:forEach var="i" begin="${startPage}" end="${endPage}">
      		<li><a href="reviewBoardList.do?pageNum=${i}&searchFilter=${searchFilter}&search=${search}">${i }</a></li>
      	</c:forEach>
      	
      	<!-- 다음 -->
      	<c:if test="${endPage<pageCount}">
      		<li><a href="reviewBoardList.do?pageNum=${startPage+3}">[다음]</a></li>
      	</c:if>
      	
      </c:if>
      </ul>
    </div>
    </div>
  </div><!--container-->
</body>
</html>