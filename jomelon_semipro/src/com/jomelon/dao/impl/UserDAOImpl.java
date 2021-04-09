package com.jomelon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.UserDAO;
import com.jomelon.domain.UserVO;
import com.jomelon.util.SecurityUtil;

public class UserDAOImpl implements UserDAO {

	// 회원가입
	@Override
	public int userInsert(UserVO userVo) {

		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String query = "INSERT INTO SEMI_USER VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			System.out.println("날짜 : " + userVo.getUserBirth());
			pstmt.setString(1, userVo.getUserId());
			pstmt.setString(2, SecurityUtil.encryptSHA256(userVo.getUserPw()));// 비밀번호 집어넣는 부분 암호화1
			pstmt.setString(3, userVo.getUserEmail());
			pstmt.setString(4, userVo.getUserName());
			pstmt.setString(5, userVo.getUserGender());
			pstmt.setString(6, userVo.getUserPostCode());
			pstmt.setString(7, userVo.getUserRoadAddress());
			pstmt.setString(8, userVo.getUserDetailAddress());
			pstmt.setString(9, userVo.getUserPhone());
			pstmt.setString(10, userVo.getUserBirth());
			pstmt.setString(11, userVo.getUserPolicy());

			result = pstmt.executeUpdate();

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 로그인성공? 실패?
	@Override
	public int selectUserCntByIdPw(UserVO user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int result = 0;

		try {
			String query = "SELECT COUNT(*) FROM SEMI_USER WHERE USER_ID=? AND USER_PW=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, SecurityUtil.encryptSHA256(user.getUserPw())); // 비밀번호 집어넣는 부분 암호화2
			rset = pstmt.executeQuery();

			// string == 가 아니라 equals인거 맨날 까먹음 ;;
			if (user.getUserId().equals("admin") && user.getUserPw().equals("9876")) {
				result = 2;
			} else {
				while (rset.next()) {
					result = rset.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	//아이디값으로 유저정보 가져오기
	@Override
	public UserVO selectUserByUserId(UserVO user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String query = "SELECT * FROM SEMI_USER WHERE USER_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				user.setUserId(rset.getString(1));
				user.setUserPw(SecurityUtil.encryptSHA256(rset.getString(2)));// 비밀번호 집어넣는 부분 암호화3
				user.setUserEmail(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setUserGender(rset.getString(5));
				user.setUserPostCode(rset.getString(6));
				user.setUserRoadAddress(rset.getString(7));
				user.setUserDetailAddress(rset.getString(8));
				user.setUserPhone(rset.getString(9));
				user.setUserBirth(rset.getString(10));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return user;
	}

	// 아이디중복체크
	public int userIdCheck(String user_id) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int count = 0;

		try {
			String query = "SELECT COUNT(*) AS CNT FROM SEMI_USER WHERE USER_ID=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			// System.out.println(user.getUserId());
			rset = pstmt.executeQuery();

			if (rset.next()) {
				count = rset.getInt("CNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return count;

	}

	// 이메일중복체크
	@Override
	public UserVO userEmailCheck(String inputEmail) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserVO user = null;

		try {
			String query = "SELECT * FROM SEMI_USER WHERE USER_EMAIL=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputEmail);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				user = new UserVO();
				user.setUserId(rset.getString(1));
				user.setUserPw(rset.getString(2));
				user.setUserEmail(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setUserGender(rset.getString(5));
				user.setUserPostCode(rset.getString(6));
				user.setUserRoadAddress(rset.getString(7));
				user.setUserDetailAddress(rset.getString(8));
				user.setUserPhone(rset.getString(9));
				user.setUserBirth(rset.getString(10));
				user.setUserPolicy(rset.getString(11));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return user;
	}

	// 회원 정보수정

	public int userUpdate(UserVO user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String query = "UPDATE SEMI_USER SET " + " USER_PW=?, USER_EMAIL=?, USER_GENDER=?, "
					+ " USER_POSTCODE=?, USER_ROADADDRESS=?, USER_DETAILADDRESS=?, "
					+ " USER_PHONE=?, USER_BIRTH=? WHERE USER_ID=? ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, SecurityUtil.encryptSHA256(user.getUserPw())); // 비밀번호 집어넣는 부분 암호화4
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserGender());
			pstmt.setString(4, user.getUserPostCode());
			pstmt.setString(5, user.getUserRoadAddress());
			pstmt.setString(6, user.getUserDetailAddress());
			pstmt.setString(7, user.getUserPhone());
			pstmt.setString(8, user.getUserBirth());
			pstmt.setString(9, user.getUserId());

			result = pstmt.executeUpdate();

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 회원탈퇴
	public int userDelete(UserVO user) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String query = "DELETE FROM SEMI_USER WHERE USER_ID=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, user.getUserId());

			result = pstmt.executeUpdate();

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}

		System.out.println("DAO : " + result);
		return result;

	}

	// 고객 비밀번호만 수정
	public int userPasswordUpdate(String userPw, String userEmail) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String query = "UPDATE SEMI_USER SET USER_PW = ? WHERE USER_EMAIL=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, SecurityUtil.encryptSHA256(userPw));
			pstmt.setString(2, userEmail);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
