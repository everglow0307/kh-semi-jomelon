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

<!--jquery google CDN-->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">

//랜덤으로 보낸 인증번호 저장 변수
var ranAuthNo = 0;

//emailCheck() ajax결과값 저장할 전역변수
var isEmailCheck = false;

//디비에 저장된 이메일인지 확인하기
function emailCheck(){
	 var email = $('#userEmail').val();
	 if(email==""){
		 isEmailCheck = false;
		 alert("이메일을 입력해주세요.");
	 }else if(!isUserEmail()){
		 isEmailCheck = false;
		 alert("이메일 형식을 확인해주세요.");
	 }else{
		 $.ajax({
		 url: "${pageContext.request.contextPath}/emailCheck.do",
		 data: "inputEmail="+email,
		 type: "post",
		 success:function(data){
			 if (data == "0") { 
				 isEmailCheck = false;	
				 alert("입력하신 정보와 일치하는 회원이 없습니다.");
				} else if (data == "1") {
					isEmailCheck = true;
					alert("인증가능한 이메일입니다.");
				}
		 },
			error:function(){
				isEmailCheck = false;
				console.log("ajax통신실패!");
			}
		 });
	}
}


//email 정규식
function isUserEmail() {
	var emailValue = $('#userEmail').val().trim();
	var emailReg = /^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/;
	if (!emailReg.test(emailValue)) {
		$('#emailResult').html(
				'<strong style="color:red;">올바른 이메일형식이 아닙니다.</strong>');
		return false;
	} else {
		$('#emailResult').html("");
		return true;
	}
}

//인증번호 전송
function sendAuthNo(){
var email = $('#userEmail').val();
if(email==""){
	 alert("이메일을 입력해주세요.");
}else if(!isEmailCheck){
	alert("이메일 먼저 확인해주세요.");
}else{
	 $.ajax({
		 url: "${pageContext.request.contextPath}/sendAuthNo.do",
		 data: {inputEmail:email},
		 type:"post",
		 datatypa: "json",
		 success:function(data){
			 if(data.result=='1'){
				alert("인증번호를 전송했습니다.");
			 	ranAuthNo= data.authNo;  //전역변수에 저장하기
			 	console.log(data.authNo);
			 }
		 },
		 error: function(){
			 console.log("ajax통신 실패");
		 }
	 });
	}
}

//인증번호 입력 후 확인
function isAuthNo(){
var inputAuthNo= $('#authNo').val();
var inputEmail = $('#userEmail').val();
if(authNo==""){
	 alert("인증번호를 입력해주세요.");
}else{
	 $.ajax({
		 url: "${pageContext.request.contextPath}/authNoConfirm.do",
		 data: {inputAuthNo:inputAuthNo,ranAuthNo:ranAuthNo,inputEmail:inputEmail},
		 type:"post",
		 success: function(data){
			 if(!data.confirm){
				alert("인증번호가 일치하지 않습니다.");
			}else if(data.confirm){
				location.href="${pageContext.request.contextPath}/findPasswordCompl.do";
			}
		 },
		 error: function(){
			 console.log("ajax통신 실패");
		 }
	 });
	}
}


//비밀번호 찾기 취소
function findPassCancel(){
	location.href="${pageContext.request.contextPath}/login.do";
}

</script>
<div class="container">
	<br>
	<div class="row justify-content-md-center">
		<div class="col-sm-6">
				<!--이메일-->
				<h5>이메일</h5>
				<input type="email" id="userEmail" class="form-control" onkeypress="isUserEmail();" /> <br>
				<input type="button" class="btn btn-dark" value="이메일 확인" onclick="emailCheck();">
				<input type="button" class="btn btn-dark" value="인증번호 전송" onclick="sendAuthNo();" />
				<span id="emailResult"></span>
				<br>
				<br>
				<!--인증번호 입력-->
				<h5>인증번호</h5>
				<input type="text" name="authNo" class="form-control"
					placeholder="인증번호 6자리 숫자 입력" id="authNo"/> <br>
				<button type="submit" class="btn btn-dark" onclick="isAuthNo();">확인</button>
				<button class="btn btn-dark" id="cancel" onclick="findPassCancel();">취소</button>
				
		</div>
	</div>
</div>

