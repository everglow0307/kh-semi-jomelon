<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>비밀번호 확인</title>

  <!-- Bootstrap4 -->
<link rel="stylesheet"
href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

//비밀번호 검사(정규식)
function isUserPw() {
	var passValue = $('#inputPassword').val().trim();
	//영문자로 시작해야 하고, 특수문자,숫자를 모두 포함(8~20자)
	var passReg = /^[a-zA-Z]{1}(?=.*[\W])(?=.*[0-9]).{7,19}$/;
	if (!passReg.test(passValue)) {
		$('#passwordResult').html('<strong style="color:red;">영문자로 시작, 특수문자, 숫자 모두 포함하여 8~20자 이내</strong>');
		return false;
	} else {
		$('#passwordResult').html('');
		return true;
	}
}

//비밀번호확인 검사(정규식)
function isUserPwCheck() {
	var passValue = $('#inputPasswordCheck').val().trim();
	//영문자로 시작해야 하고, 특수문자,숫자를 모두 포함(8~20자)
	var passReg = /^[a-zA-Z]{1}(?=.*[\W])(?=.*[0-9]).{7,19}$/;
	if (!passReg.test(passValue)) {
		$('#passwordChResult').html('<strong style="color:red;">영문자로 시작, 특수문자, 숫자 모두 포함하여 8~20자 이내</strong>');
		return false;
	} else {
		$('#passwordChResult').html('');
		return true;
	}
}

//비밀번호 일치여부 체크(비밀번호확인 입력버튼의 focus가 사라질때)
function isUserPwMatch() {
	if ($('#inputPasswordCheck').val() != $("#inputPassword").val()) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	} else if ($('#inputPasswordCheck').val() == $("#inputPassword")
			.val()
			&& isUserPwCheck() && isUserPw()) {
		return true;
	}
}

function validate(){
	var password = $("#inputPassword");
	var passwordCh = $("#inputPasswordCheck");
	
	if(password.val()==""){
		password.focus();
		return false;
	}else if(passwordCh.val()==""){
		passwordCh.focus();
		return false;
	}else if(!isUserPw()){
		password.focus();
		return false;
	}else if(!isUserPwCheck()){
		passwordCh.focus();
		return false;
	}else if(!isUserPwMatch()){
		password.focus();
		return false;
	}else{
		return true;
	}
}

</script>
</head>
<body>
  <div class="jumbotron jumbotron-fluid">
    <div class="container">
      <h4><strong>비밀번호 찾기</strong></h4>
    </div>
  </div>
  <div class="container">
    <div class="row justify-content-center">
    <form action="${pageContext.request.contextPath}/newUserPassword.do" method="post">
      새 비밀번호&nbsp;&nbsp; <input type="password" name="userPw" id="inputPassword" onkeyup="isUserPw()"/>
      <div id="passwordResult"></div><br>
      비밀번호 확인&nbsp;&nbsp; <input type="password" name="userPwCh"  id="inputPasswordCheck"onkeyup="isUserPwCheck()" onblur="isUserPwMatch()" />
    <div id="passwordChResult"></div>
    </div><br>
    <div class="row justify-content-center">
      <input type="submit" class="btn btn-dark" value="비밀번호 변경" onsubmit="return validate()" />&nbsp;&nbsp;
      <a href="main.do" class="btn btn-dark">메인으로 돌아가기</a>
    </form>
    </div>
  </div>
</body>
</html>