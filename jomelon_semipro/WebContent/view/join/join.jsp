<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원가입</title>
<!-- Bootstrap4 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- ▶ajax 라이브러리 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!--주소 api(daum)-->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						var roadAddr = data.roadAddress; // 도로명 주소 변수

						document.getElementById('postcode').value = data.zonecode;
						document.getElementById("roadAddress").value = roadAddr;
						document.getElementById("jibunAddress").value = data.jibunAddress;

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
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
	<br>
	<div class="container">
		<div class="row">
			<div class="col">
				<span class="required">*항목은 필수입력</span>
			</div>
		</div>
		<hr>
		<!-- 본문 들어가는 부분 -->
		<form method="post" action="join.do">
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label><strong><span class="required">*</span>개인정보취급방침</strong></label>
					<textarea class="form-control" rows="8" style="resize: none">개인정보의 항목 및 수집방법

본인은 귀사에 이력서를 제출함에 따라 [개인정보 보호법] 제15조 및 제17조에 따라 아래의 내용으로 
개인정보를 수집, 이용 및 제공하는데 동의합니다. 
						
□ 개인정보의 수집 및 이용에 관한 사항
- 수집하는 개인정보 항목 (이력서 양식 내용 일체) : 성명, 주민등록번호, 
전화번호, 주소, 이메일, 가족관계, 학력사항, 경력사항, 자격사항 등과 그 外 이력서 기재 내용 일체
					
- 개인정보의 이용 목적 : 수집된 개인정보를 사업장 신규 채용 서류 심사 및 
인사서류로 활용하며, 목적 외의 용도로는 사용하지 않습니다.
						
						
□ 개인정보의 보관 및 이용 기간
- 귀하의 개인정보를 다음과 같이 보관하며, 수집, 이용 및 제공목적이 달성된 경우
[개인정보 보호법] 제21조에 따라 처리합니다. 
          </textarea>
				</div>
			</div>
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label> <input type="radio" id="userPolicyY"
						name="userPolicy" value="Y" checked onchange="isUserPolicy()" />
						동의합니다.
					</label> <label> <input type="radio" id="userPolicyN"
						name="userPolicy" value="N" onchange="isUserPolicy()" /> 동의하지
						않습니다.
					</label>
				</div>
			</div>
			<br>
			<!--아이디-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputId"><strong><span
							class="required">*</span>아이디</strong></label>&nbsp;&nbsp;<span class="result id"></span>
					<input type="text" class="form-control" id="inputId"
						data-rule-required="true"
						placeholder="4~12자이내의 영문자, 언더스코어(_), 숫자만 입력 가능합니다." maxlength="12"
						name="userId" onkeyup="isUserId()" />
				</div>
			</div>

			<!--아이디 중복확인-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="button" class="btn btn-dark" value="아이디 중복확인"
						id="idDupliCheck" onclick="fnIdDupliCheck()" />

					<!--▶ span 를 통해 경고문이 들어갈 공간 만들어 놓기. -->
					<span class="col-lg-10" id="idCheck"></span>
				</div>
			</div>

			<script type="text/javascript">
			var idDup = false;
			
				function fnIdDupliCheck(){
					var inputId = $("#inputId").val();
					if (inputId == "") {
						$("#idCheck").text("아이디를 입력해주세요.");
						$("#idCheck").css("color", "red");
						idDup=false;
					} else if (!isUserId()) {
						$("#idCheck").text("아이디를 확인해주세요.");
						$("#idCheck").css("color", "red");
						idDup=false;
					} else {
					// ajax호출
						$.ajax({
							url : "${pageContext.request.contextPath}/idCheck.do",
							// data : 요청 시 전달할 파라미터 설정
							data : "inputId="+ inputId, // key=input / value= var input에 저장된 값.
							// key: value
							// type : 전송방식 (GET/ POST)
							type : "post",
							// success : Ajax 통신 성공 시 처리할 함수를 지정하는 속성
							//  매개변수명 임의 지정 가능
							success : function(data) {
								console.log("1=중복o /0=중복 x : "+ data);
								console.log("1=중복o /0=중복 x : "+ data) // data매개변수 : 서버에서 응담이 왔을 떄 그 값이 저장되는 변수
								if (data == "0") { // 문자열로 받는것 주의!!
									$("#idCheck").text("사용가능한 아이디 입니다.");
									$("#idCheck").css("color","blue");
									idDup=true;
								} else if (data == "1") {
									$("#idCheck").text("이미 사용중인 아이디 입니다.");
									$("#idCheck").css("color","red");
									$("#idCheck").val("");
									idDup=false;
								}
							},
							// error : Ajax 통신 실패시 처리할 함수를 지정한속성
							error : function() {
								console.log("Ajax 통신 실패!");
								idDup=false;
							}
						});//ajax호출닫기
					}//else if문 닫기
				}//function 닫기
			</script>
			<br>
			<!--비밀번호-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputPassword"><strong><span
							class="required">*</span>패스워드</strong></label>&nbsp;&nbsp;<span></span> <input
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
						name="userPwCh" onkeyup="isUserPwCheck()" onblur="isUserPwMatch()">
				</div>
			</div>
			<br>
			<!--이름-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputName"><strong><span
							class="required">*</span>이름</strong></label> <input type="text"
						class="form-control" id="inputName" data-rule-required="true"
						placeholder="한글만 입력 가능합니다." maxlength="5" name="userName"
						onkeypress="isUserName()">
				</div>
			</div>
			<br>
			<!--이메일-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputEmail"><strong><span
							class="required">*</span>이메일</strong></label>&nbsp;&nbsp;<span></span> <input
						type="email" class="form-control" id="inputEmail"
						data-rule-required="true" placeholder="이메일" maxlength="40"
						name="userEmail" onkeypress="isUserEmail()">
				</div>
			</div>
			<!--이메일 중복확인-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="button" class="btn btn-dark" value="이메일 중복확인"
						id="emailDupliCheck" onclick="fnEmailDupliCheck()" />
					<!--▶ span 를 통해 경고문이 들어갈 공간 만들어 놓기. -->
					<span class="col-lg-10" id="emailCheck"></span>
				</div>
			</div>
			<script type="text/javascript">
			var emailDup = false;
			
				function fnEmailDupliCheck(){
					var inputEmail = $("#inputEmail").val();
					if (inputEmail == "") {
						$("#emailCheck").text("이메일 입력해주세요.");
						$("#emailCheck").css("color", "red");
						emailDup=false;
					} else if (!isUserEmail()) {
						$("#emailCheck").text("이메일을 확인해주세요.");
						$("#emailCheck").css("color", "red");
						emailDup=false;
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
									emailDup=true;
								} else if (data == "1") {
									$("#emailCheck").text("이미 사용중인 이메일입니다.");
									$("#emailCheck").css("color","red");
									emailDup=false;
								}
							},
							// error : Ajax 통신 실패시 처리할 함수를 지정한속성
							error : function() {
								console.log("Ajax 통신 실패!");
								emailDup=false;
							}
						});//ajax호출닫기
					}//else if문 닫기
				}
					
			</script>
			<br>
			<!--우편번호-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="postcode"><strong><span
							class="required">*</span>우편번호</strong></label> <input type="text"
						class="form-control" id="postcode" data-rule-required="true"
						maxlength="40" name="userPostCode" placeholder="우편번호">
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
						maxlength="40" name="userRoadAddress" placeholder="도로명주소">
				</div>
			</div>
			<br>
			<!-- 상세주소 -->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="detailAddress"><strong>상세주소</strong></label> <input
						type="text" class="form-control" id="detailAddress"
						data-rule-required="true" maxlength="40" name="userDetailAddress"
						placeholder="상세주소">
				</div>
			</div>
			<br>
			<!--생년월일-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputBirth"><strong>생년월일</strong></label> <input
						type="date" class="form-control" id="inputBirth"
						data-rule-required="true" placeholder="생년월일" maxlength="40"
						name="userBirth">
				</div>
			</div>
			<br>
			<!--휴대폰 번호-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputPhoneNumber"><strong><span
							class="required">*</span>휴대폰 번호</strong></label> <input type="tel"
						class="form-control onlyNumber" id="inputPhoneNumber"
						data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요."
						maxlength="11" name="userPhone" onblur="isUserPhone()">
				</div>
			</div>
			<br>
			<!--성별-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<label for="inputGender"><strong>성별</strong></label> <select
						class="form-control" id="inputGender" name="userGender">
						<option value="M">남</option>
						<option value="F">여</option>
					</select>
				</div>
			</div>
			<br>
			<!--회원가입 완료버튼-->
			<div class="form-row justify-content-center">
				<div class="col-lg-10">
					<input type="submit" class="btn btn-dark" value="회원가입 완료">
				</div>
			</div>
			<br>
		</form>
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
						} else if (!isUserEmail()) {
							alert("이메일을 다시 확인해주세요.");
							email.focus();
							return false;
						} else if(!idDup){
							alert("아이디 중복을 확인해주세요.");
							id.focus();
							return false;
						}else if(!emailDup){
							alert("이메일 중복을 확인해주세요.");
							email.focus();
							return false;
						}else{
							return true;
						}
					});

		});
	</script>
</body>
</html>