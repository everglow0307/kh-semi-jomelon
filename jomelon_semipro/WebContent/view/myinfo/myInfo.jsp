<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
<!--주소 api(daum)-->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

    function execDaumPostcode() {
      new daum.Postcode({
        oncomplete: function (data) {
          var roadAddr = data.roadAddress; // 도로명 주소 변수

          document.getElementById('postcode').value = data.zonecode;
          document.getElementById("roadAddress").value = roadAddr;
          document.getElementById("jibunAddress").value = data.jibunAddress;

          var guideTextBox = document.getElementById("guide");
          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if (data.autoRoadAddress) {
            var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
            guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
            guideTextBox.style.display = 'block';

          } else if (data.autoJibunAddress) {
            var expJibunAddr = data.autoJibunAddress;
            guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
            guideTextBox.style.display = 'block';
          } else {
            guideTextBox.innerHTML = '';
            guideTextBox.style.display = 'none';
          }
        }
      }).open();
    }
  </script>
<style>
a {
	color: black;
}

.required {
	color: red;
}
</style>
<!--jquery google CDN-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
<br><h3 class="form-row justify-content-center">회원정보 확인 및 수정</h3><br>
	
	
	<form method="post" action="myinfoUpt.do">
	<!--아이디 readonly-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputId"><strong><span
							class="required">*</span>아이디</strong></label>&nbsp;&nbsp;<span class="result id"></span>
					<input type="text" class="form-control" id="inputId"
						data-rule-required="true"
						value="${u.getUserId()}" maxlength="12"
						name="userId" readonly>
				</div>
			</div>
			

			<br>
			<!--비밀번호 변경-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputPassword"><strong><span
							class="required">*</span>패스워드변경</strong></label>&nbsp;&nbsp;<span></span> <input
						type="password" class="form-control" id="inputPassword"
						name="userPw" data-rule-required="true"
						placeholder="8~20자이내로 영문자, 특수문자, 숫자를 모두 포함해야 합니다."
						placeholder="패스워드" maxlength="20" onkeyup="isUserPw()">
				</div>
			</div>
			<br>
			<!--비밀번호 확인-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputPasswordCheck"><strong><span
							class="required">*</span>패스워드확인</strong></label>&nbsp;&nbsp;<span></span> <input
						type="password" class="form-control" id="inputPasswordCheck"
						data-rule-required="true" placeholder="패스워드 확인" maxlength="20"
						name="userPwCh">
				</div>
			</div>
			<br>
			<!--이름 readonly-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputName"><strong><span
							class="required">*</span>이름</strong></label> <input type="text"
						class="form-control" id="inputName" data-rule-required="true"
						value="${u.getUserName()}" maxlength="5" name="userName" readonly>
				</div>
			</div>
			<br>
<!--이메일-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputEmail"><strong><span
							class="required">*</span>이메일</strong></label>&nbsp;&nbsp;<span></span> <input
						type="email" class="form-control" id="inputEmail"
						data-rule-required="true" value="${u.getUserEmail()}" maxlength="40"
						name="userEmail" onkeypress="isUserEmail()">
				</div>
			</div>
			<!--이메일 중복확인-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="button" class="btn btn-dark" value="이메일 중복확인"
						id="emailDupliCheck" />
					<!--▶ span 를 통해 경고문이 들어갈 공간 만들어 놓기. -->
					<span class="col-lg-10" id="emailCheck"></span>
				</div>
			</div>
			<script type="text/javascript">
				$(function() {
					$("#emailDupliCheck").on('click',function() {
						var inputEmail = $("#inputEmail").val();
						if (inputEmail == "") {
							$("#emailCheck").text("이메일 입력해주세요.");
							$("#emailCheck").css("color", "red");
						} else if (!isUserEmail()) {
							$("#emailCheck").text("이메일을 확인해주세요.");
							$("#emailCheck").css("color", "red");
						} else if (isUserEmail()) {
						// ajax호출
							$.ajax({
								url : "${pageContext.request.contextPath}/emailCheck.do",
								// data : 요청 시 전달할 파라미터 설정
								data : "inputEmail="+ inputEmail, // key=input / value= var input에 저장된 값.
								// key: value
								// type : 전송방식 (GET/ POST)
								type : "post",
								// success : Ajax 통신 성공 시 처리할 함수를 지정하는 속성
								//  매개변수명 임의 지정 가능
								success : function(data) {
									console.log("1=중복o /0=중복 x : "+ data);
									console.log("1=중복o /0=중복 x : "+ data) // data매개변수 : 서버에서 응담이 왔을 떄 그 값이 저장되는 변수
									if (data == "0") { // 문자열로 받는것 주의!!
										$("#emailCheck").text("사용가능한 이메일입니다.");
										$("#emailCheck").css("color","blue");
									} else if (data == "1") {
										$("#emailCheck").text("이미 사용중인 이메일입니다.");
										$("#emailCheck").css("color","red");
									}
								},
								// error : Ajax 통신 실패시 처리할 함수를 지정한속성
								error : function() {
									console.log("Ajax 통신 실패!");
								}
							});//ajax호출닫기
						}//else if문 닫기
					});//onclick이벤트 닫기
				});//onload닫기
			</script>
			<br>
			<!--우편번호-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="postcode"><strong><span
							class="required">*</span>우편번호</strong></label> <input type="text"
						class="form-control" id="postcode" data-rule-required="true"
						maxlength="40" name="userPostCode" value="${u.getUserPostCode()}">
				</div>
			</div>
			<!--우편번호 찾기-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="button" class="btn btn-dark"
						onclick="execDaumPostcode()" value="우편번호 찾기">
				</div>
			</div>
			<br>
			<!--도로명 주소-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="roadAddress"><strong><span
							class="required">*</span>도로명주소</strong></label> <input type="text"
						class="form-control" id="roadAddress" data-rule-required="true"
						maxlength="40" name="userRoadAddress" value="${u.getUserRoadAddress()}">
				</div>
			</div>
			<br>
			<!-- 상세주소 -->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="detailAddress"><strong>상세주소</strong></label> <input
						type="text" class="form-control" id="detailAddress"
						data-rule-required="true" maxlength="40" name="userDetailAddress"
						value="${u.getUserDetailAddress()}">
				</div>
			</div>
			<br>
			<!--생년월일-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputBirth"><strong>생년월일</strong></label> <input
						type="date" class="form-control" id="inputBirth"
						data-rule-required="true" maxlength="40"
						name="userBirth" value="${u.getUserBirth() }">
				</div>
			</div>
			
			
			<!--휴대폰 번호-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputPhoneNumber"><strong><span
							class="required">*</span>휴대폰 번호</strong></label> <input type="tel"
						class="form-control onlyNumber" id="inputPhoneNumber"
						data-rule-required="true" value="${u.getUserPhone()}"
						maxlength="11" name="userPhone"  onblur="isUserPhone()">
				</div>
			</div>
			<br>
			<!--성별-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputGender"><strong>성별</strong></label> <select
						class="form-control" id="inputGender" name="userGender">
						<!--가져온 성별정보 변수 g에 저장  -->
						<c:set var="g" value="${u.getUserGender()}" />					
						<option ${(g == "M")? "selected":""} value="M"> 남</option>
						<option ${(g == "F")? "selected":""} value="F">여</option>
					</select>
				</div>
			</div>
			<br>
			<!--수정 완료버튼-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="submit" class="btn btn-dark" value="수정 완료">
				</div>
			</div>
		</form>
			<!-- 회원 탈퇴버튼  -->
			<!-- 폼태그 안에있어도 폼액션에 설정한 페이지말고 다른페이지로 가는 방법은?  -->
			<br>
			<form method="post" action ="myinfoDel.do">
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="submit" class="btn btn-dark" value="회원 탈퇴" >
				</div>
			</div>
			</form>
			<br>
		<hr>
	</div>
	<!--//div.container-->
	<!--// 본문 들어가는 부분 -->

	<!--유효성검사 및 정규식 표현-->
	<script type="text/javascript">
		//개인정보취급방침(필수동의)
		function isUserPolicy() {
			if ($('input[name="userPolicy"]:checked').val() == "N") {
				alert("개인정보취급방침에 동의하여 주시기 바랍니다.");
				return false;
			} else {
				return true;
			}
		}

		//아이디 검사(정규식)
		function isUserId() {
			var idValue = $("#inputId").val().trim();
			//영문자로 시작하고, 영문자 및 _,숫자 허용(4~12자)
			var idReg = /^[a-zA-Z]{1}[a-zA-Z_\d]{3,11}$/;
			if (!idReg.test(idValue)) {
				$(".result.id")
						.html(
								'<strong style="color:red;">영문자로 시작, 언더스코어(_), 숫자만 가능(4~12자)</strong>');
				return false;
			} else {
				$('.result.id').html('');

				return true;
			}
		}

		function idDupliCheck() {
			var inputId = $("#inputId").val();

			if (isUserId()) {

			}
		}

		//비밀번호 검사(정규식)
		function isUserPw() {
			var passValue = $('#inputPassword').val().trim();
			//영문자로 시작해야 하고, 특수문자,숫자를 모두 포함(8~20자)
			var passReg = /^[a-zA-Z]{1}(?=.*[\W])(?=.*[0-9]).{7,19}$/;
			if (!passReg.test(passValue)) {
				$('#inputPassword')
						.prev()
						.html(
								'<strong style="color:red;">영문자로 시작, 특수문자, 숫자 모두 포함하여 8~20자 이내</strong>');
				return false;
			} else {
				$('#inputPassword').prev().html('');
				return true;
			}
		}

		//비밀번호확인 검사(정규식)
		function isUserPwCheck() {
			var passValue = $('#inputPasswordCheck').val().trim();
			//영문자로 시작해야 하고, 특수문자,숫자를 모두 포함(8~20자)
			var passReg = /^[a-zA-Z]{1}(?=.*[\W])(?=.*[0-9]).{7,19}$/;
			if (!passReg.test(passValue)) {
				$('#inputPasswordCheck')
						.prev()
						.html(
								'<strong style="color:red;">영문자로 시작, 특수문자, 숫자 모두 포함하여 8~20자 이내</strong>');
				return false;
			} else {
				$('#inputPasswordCheck').prev().html('');
				return true;
			}
		}

		//비밀번호 일치여부 체크(비밀번호확인 입력버튼의 focus가 사라질때)
		function isUserPwMatch() {
			if ($('#inputPasswordCheck').val() != $("#inputPassword").val()) {
				$('#inputPasswordCheck').prev().html(
						'<strong style="color:red;">비밀번호가 일치하지 않습니다.</strong>');
				return false;
			} else if ($('#inputPasswordCheck').val() == $("#inputPassword")
					.val()
					&& isUserPwCheck() && isUserPw()) {
				$('#inputPasswordCheck').prev().html(
						'<strong style="color:green;">비밀번호가 일치합니다.</strong>');
				return true;
			}
		}

		//이름 정규식
		function isUserName() {
			var nameValue = $('#inputName').val().trim();
			//한글만 입력가능 
			var nameReg = /^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,5}$/;
			if (!nameReg.test(nameValue)) {
				$('#inputName').val('');
				return false;
			} else {
				return true;
			}
		}

		//email 정규식
		function isUserEmail() {
			var emailValue = $('#inputEmail').val().trim();
			var emailReg = /^[a-zA-Z0-9_+.-]+@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/;
			if (!emailReg.test(emailValue)) {
				$('#inputEmail').prev().html(
						'<strong style="color:red;">올바른 이메일형식이 아닙니다.</strong>');
				return false;
			} else {
				$('#inputEmail').prev().html("");
				return true;
			}
		}

		//폰번호 정규식
		function isUserPhone() {
			var phoneValue = $('#inputPhoneNumber').val().trim();
			//010-4자리-4자리
			var phoneReg = /(^010)(\d{4})(\d{4})/;
			if (!phoneReg.test(phoneValue)) {
				$('#inputPhoneNumber').val('');
				return false;
			} else {
				return true;
			}
		}

		$(function() {
			var id = $('#inputId');
			var pass = $('#inputPassword');
			var chPass = $('#inputPasswordCheck');
			var name = $('#inputName');
			var email = $('#inputEmail');
			var postCode = $('#postcode');
			var phone = $('#inputPhoneNumber');

			//validate   
			$("form").submit(
					function() {
						if (id.val() == "") {
							id.focus();
							return false;
						} else if (pass.val() == "") {
							pass.focus();
							return false;
						} else if (chPass.val() == "") {
							chPass.focus();
							return false;
						} else if (name.val() == "") {
							name.focus();
							return false;
						} else if (email.val() == "") {
							email.focus();
							return false;
						} else if (postCode.val() == "") {
							postCode.focus();
							return false;
						} else if (phone.val() == "") {
							phone.focus();
							return false;
						} else if (!isUserPolicy()) {
							$('input:radio').focus();
							return false;
						} else if (!isUserId()) {
							alert("아이디를 다시 확인해주세요.");
							id.focus();
							return false;
						} else if (!isUserPw() || !isUserPwCheck()
								|| !isUserPwMatch()) {
							alert("비밀번호를 다시 확인해주세요.");
							pass.focus();
							return false;
						} else if (!isUserEmail) {
							alert("이메일을 다시 확인해주세요.");
							email.focus();
							return false;
						} else {
							return true;
						}
					});

		});
	</script>
</div>

</body>
</html>