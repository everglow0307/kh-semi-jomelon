<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginstyle.css"/>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<script type="text/javascript">
		function checkValue()
		{
			inputForm = eval("document.loginInfo");
			if(!inputForm.userId.value)
			{
				alert("아이디를 입력하세요");	
				inputForm.userId.focus();
				return false;
			}
			if(!inputForm.userPw.value)
			{
				alert("비밀번호를 입력하세요");	
				inputForm.userPw.focus();
				return false;
			}
		}
	</script>
<body>
	
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h1>Login</h1>
                    <div class="d-flex justify-content-end social_icon">
                        <span><i class="fab fa-facebook-square"></i></span>
                        <span><i class="fab fa-google-plus-square"></i></span>
                        <span><i class="fab fa-twitter-square"></i></span>
                    </div>
                </div>
                <div class="card-body">
                    <form name="loginInfo" action="login.do" method="post" onsubmit="return checkValue()">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="username" name="userId">

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                            </div>
                            <input type="password" class="form-control" placeholder="password" name="userPw">
                        </div>
                        <div class="row align-items-center remember">
                            <input type="checkbox">로그인 상태 유지 
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Login" class="btn float-right login_btn" >
                        </div>
                    </form>
                </div>
                <div class="card-footer">
                    <div class="d-flex justify-content-center links">
                        	<a href="join.do" >회원가입</a>
                    </div>
                    <div class="d-flex justify-content-center">
                        <a href="findId.do" >아이디찾기</a>
                        <a href="findPassword.do">비밀번호찾기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</body>

</html>