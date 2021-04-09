<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- 부트스트랩 4적용 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function sendAuthNo(){
		
	}
</script>

<div class="container">
	<div class="row justify-content-md-center">
		<div class="col-sm-6">
			<form class="form" action="findId.do" method="post">
				<!--이름-->
				<br>
				<h5>이름</h5>
				<input type="text" id="userName" class="form-control" /><br>
				<!--이메일-->
				<h5>이메일</h5>
				<input type="email" id="userEmail" class="form-control" /> <br>
				<input type="button" class="btn btn-primary" value="인증번호 받기" onclick="sendAuthNo();"/>
				<br>
				<br>
				<!--인증번호 입력-->
				<h5>인증번호</h5>
				<input type="text" name="authNo" class="form-control"
					placeholder="인증번호 6자리 숫자 입력" /> <br>
				<button type="submit" class="btn btn-primary">완료</button>
			</form>
		</div>
	</div>
</div>