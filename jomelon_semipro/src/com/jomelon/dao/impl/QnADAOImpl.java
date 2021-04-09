package com.jomelon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.QnADAO;
import com.jomelon.domain.QnABoardVO;

public class QnADAOImpl implements QnADAO{
	
	// 페이지번호 담아오기?
	public int getNext() {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String SQL="SELECT QNAID FROM QNA ORDER BY QNAID DESC";
		try {
			pstmt = conn.prepareStatement(SQL);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				return rset.getInt(1);
			}
			return 1;	// 첫번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 호출
	}
	
	// 글 쓰기
	public int write(String QnATitle, String userId, String QnAContent) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO QNA (QNATITLE, USER_ID, QNADATE, QNACONTENT, QNAAVAILABLE) VALUES(?,?,SYSDATE,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, QnATitle);
			pstmt.setString(2, userId);
			pstmt.setString(3, QnAContent);
			pstmt.setInt(4, 1);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 호출
		
	}
	
	public ArrayList<QnABoardVO> getList(int pageNumber) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String SQL = "SELECT * FROM (SELECT * FROM QNA WHERE QNAID <= ? AND QNAAVAILABLE = 1 ORDER BY QNAID DESC) WHERE ROWNUM <= 10";
		ArrayList<QnABoardVO> list = new ArrayList<QnABoardVO>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnABoardVO qnaVO = new QnABoardVO();
				qnaVO.setQnAID(rset.getInt(1));
				qnaVO.setQnATitle(rset.getString(2));
				qnaVO.setUserId(rset.getString(3));
				qnaVO.setQnADate(rset.getString(4));
				qnaVO.setQnAContent(rset.getString(5));
				qnaVO.setQnAAvailable(rset.getInt(6));
				list.add(qnaVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String SQL = "SELECT * FROM QNA WHERE QNAID < ? AND QNAAVAILABLE = 1 ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public QnABoardVO getQnA(int QnAID) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String SQL = "SELECT * FROM QNA WHERE QNAID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QnAID);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				QnABoardVO qnaboardVO = new QnABoardVO();
				qnaboardVO.setQnAID(rset.getInt(1));
				qnaboardVO.setQnATitle(rset.getString(2));
				qnaboardVO.setUserId(rset.getString(3));
				qnaboardVO.setQnADate(rset.getString(4));
				qnaboardVO.setQnAContent(rset.getString(5));
				qnaboardVO.setQnAAvailable(rset.getInt(6));
				return qnaboardVO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 글 수정
	public int update(int QnAID, String QnATitle, String QnAContent) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String SQL="UPDATE QNA SET QNATITLE = ?, QNACONTENT = ? WHERE QNAID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, QnATitle);
			pstmt.setString(2, QnAContent);
			pstmt.setInt(3, QnAID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 호출
	}
	
	// 글 삭제
	public int delete(int QnAID) {
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		String SQL="UPDATE QNA SET QNAAVAILABLE = 0 WHERE QNAID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, QnAID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 호출
		
	}
	
	
	
}
