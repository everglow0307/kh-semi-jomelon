package com.jomelon.service;

import javax.servlet.http.HttpServletRequest;

import com.jomelon.domain.UserVO;

public interface UserService {
	
	//회원가입 
	int joinUser (HttpServletRequest req);
	
	//로그인 정보(아이디, 비밀번호 ) 저장
	UserVO getLogin (HttpServletRequest req); 
	
	//로그인 시도
	int login(UserVO user);
	
	//로그인 성공한 유저 정보 가져오기
	UserVO Info(UserVO user);
	
	//아이디 체크
	int idCheck(String inputId);
	
	//이메일 체크
	UserVO emailCheck(String inputEmail);
	
	
	//마이페이지 - 세션아이디, 입력비밀번호 저장
	UserVO getMyInfoPw(HttpServletRequest req);
	
	//회원정보
	UserVO getUserInfo(HttpServletRequest req);
	
	//회원정보수정
	int userUpt(HttpServletRequest req);
	
	//회원 탈퇴
	int userDel(UserVO user);
	
	//메일전송(배열[0]-인증번호값,배열[1]-메일전송여부)
	int[] sendUserMailAuthNo(String email);
	
	//비밀번호찾기용 - 비밀번호만 수정
	int userPasswordUpdate(HttpServletRequest req);
	
}
