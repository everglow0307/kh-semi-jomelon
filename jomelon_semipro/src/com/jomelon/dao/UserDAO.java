package com.jomelon.dao;

import com.jomelon.domain.UserVO;

public interface UserDAO {
	
	//회원가입
	int userInsert(UserVO user);

	//아이디와 비밀번호 정보로 회원정보 찾기(count(*))
	int selectUserCntByIdPw(UserVO user);
	
	//아이디값으로 회원정보불러오기
	UserVO selectUserByUserId(UserVO user);
	
	// 아이디 중복체크 
	int userIdCheck(String user_id);
	
	//이메일 중복체크
	UserVO userEmailCheck(String inputEmail);
	
	// 회원정보수정
	int userUpdate(UserVO user);
	
	// 회원탈퇴
	int userDelete(UserVO user);
	
	//고객 비밀번호만 수정(비밀번호 찾기)-이메일 이용 
	int userPasswordUpdate(String userPw, String userEmail);
	
}
