package com.jomelon.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.ReviewBoardDAO;
import com.jomelon.domain.ReviewBoardVO;

public class ReviewBoardDAOImpl implements ReviewBoardDAO {

	//리뷰 등록
	@Override
	public int insertReview(ReviewBoardVO reviewVO) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		int ref=0; //글그룹=쿼리를 실행시켜 가장 큰 ref값을 가져온후 1더하기
		//답변형게시판이라 그룹끼리의 num이 필요하기 때문에 
		int reStep=1; //새글이니까-부모글이니까
		int reLevel=1;
		
		try {
			//1.ref값 가져오기
			//가장 큰 ref값을 읽어오는 쿼리 준비
			String refquery = "SELECT MAX(REF) FROM SEMI_REVIEW";
			//쿼리실행 객체
			pstmt = conn.prepareStatement(refquery);
			//쿼리실행 후 결과를 리턴
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ref=rset.getInt(1)+1;
			}
			
			//2.등록하기
			String query = "INSERT INTO SEMI_REVIEW VALUES(review_seq.nextVal,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, reviewVO.getWriter());
			pstmt.setString(2, reviewVO.getSubject());
			pstmt.setString(3, reviewVO.getPassword());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, reStep);
			pstmt.setInt(6, reLevel);
			pstmt.setString(7, reviewVO.getContent());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	//리뷰 10개씩 가져오기
	@Override
	public ArrayList<ReviewBoardVO> getAllReview(int startRow, int endRow, String filter, String query) {
		
		ArrayList<ReviewBoardVO> reviewList = new ArrayList<ReviewBoardVO>();
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println(filter);
		System.out.println(query);
		
		try {
			String sql = "SELECT * FROM (SELECT A.*,ROWNUM RNUM FROM (SELECT * FROM SEMI_REVIEW WHERE "+filter+" LIKE ? ORDER BY REF DESC, RE_STEP ASC)A)"
					+ "WHERE RNUM>=? AND RNUM<=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewBoardVO review = new ReviewBoardVO();
				review.setNum(rset.getInt(1));
				review.setWriter(rset.getString(2));
				review.setSubject(rset.getString(3));
				review.setPassword(rset.getString(4));
				review.setRegDate(rset.getDate(5).toString());
				review.setRef(rset.getInt(6));
				review.setReStep(rset.getInt(7));
				review.setReLevel(rset.getInt(8));
				review.setReadCount(rset.getInt(9));
				review.setContent(rset.getString(10));
				
				reviewList.add(review);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return reviewList;
	}

	//모든 리뷰 개수
	@Override
	public int getAllReviewCnt(String filter, String query) {
		Connection conn = JDBCTemplate.getConnection();
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println(filter);
		System.out.println(query);
		try {
			String sql = "SELECT COUNT(*) FROM SEMI_REVIEW WHERE "+filter+" LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return count;
	}

	//리뷰글 하나만 가져오기
	@Override
	public ReviewBoardVO getOneReview(int num) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ReviewBoardVO review = null;
		ResultSet rset = null;
		
		try {
			//하나의 리뷰글을 읽었을 떄 조회수 증가
			String query = "UPDATE SEMI_REVIEW SET READ_COUNT = READ_COUNT+1 WHERE NUM=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//한 리뷰글에 대한 정보를 리턴
			String query2="SELECT * FROM SEMI_REVIEW WHERE NUM=?";
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				review = new ReviewBoardVO();
				
				review.setNum(rset.getInt(1));
				review.setWriter(rset.getString(2));
				review.setSubject(rset.getString(3));
				review.setPassword(rset.getString(4));
				review.setRegDate(rset.getDate(5).toString());
				review.setRef(rset.getInt(6));
				review.setReStep(rset.getInt(7));
				review.setReLevel(rset.getInt(8));
				review.setReadCount(rset.getInt(9));
				review.setContent(rset.getString(10));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return review;
	}

	//답변글 작성하기
	@Override
	public int reInsertReview(ReviewBoardVO review) {
		Connection conn = JDBCTemplate.getConnection();
		int ref=review.getRef();
		int reStep=review.getReStep();
		int reLevel= review.getReLevel();
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {

		//부모글 보다 큰 re_level의 값을 전부 1씩 증가시켜줌 
		String  levelsql = "update semi_review set re_level=re_level+1 where ref=? and re_level > ?";
		//쿼리 삽입 객체 선언 
		pstmt = conn.prepareStatement(levelsql);
		pstmt.setInt(1 , ref);
		pstmt.setInt(2 , reLevel);
		//쿼리 실행 
		pstmt.executeUpdate();
		//답변글 데이터를 저장
		String sql ="insert into semi_review values(review_seq.NEXTVAL,?,?,?,sysdate,?,?,?,0,?)";
		pstmt = conn.prepareStatement(sql);
		//?에 값을 대입
		pstmt.setString(1, review.getWriter());
		pstmt.setString(2, review.getSubject());
		pstmt.setString(3, review.getPassword());
		pstmt.setInt(4, ref);//부모의 ref 값을 넣어줌 
		pstmt.setInt(5, reStep+1);//답글이기에 부모글 re_step에 1을 넣어줌 
		pstmt.setInt(6, reLevel + 1);
		pstmt.setString(7, review.getContent());
		
		//쿼리를 실행하시오
		result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//update할 때, 리뷰글 내용 하나 가져오기(조회수 증가x)
	public ReviewBoardVO getOneUpdateReview(int num) {
		ReviewBoardVO reviewVo= new ReviewBoardVO();
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM SEMI_REVIEW WHERE NUM=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reviewVo.setNum(rset.getInt(1));
				reviewVo.setWriter(rset.getString(2));
				reviewVo.setSubject(rset.getString(3));
				reviewVo.setPassword(rset.getString(4));
				reviewVo.setRegDate(rset.getDate(5).toString());
				reviewVo.setRef(rset.getInt(6));
				reviewVo.setReStep(rset.getInt(7));
				reviewVo.setReLevel(rset.getInt(8));
				reviewVo.setReadCount(rset.getInt(9));
				reviewVo.setContent(rset.getString(10));
			}
			
			
		}catch(Exception e) {
			
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return reviewVo;
	}
	
	//하나의 리뷰글을 수정
	public int updateReview(int num, String subject, String content) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "UPDATE SEMI_REVIEW SET SUBJECT=?,CONTENT=? WHERE NUM = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//하나의 리뷰글을 삭제
	public int deleteReview(int num) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			String query = "DELETE FROM SEMI_REVIEW WHERE NUM=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}
	
	

