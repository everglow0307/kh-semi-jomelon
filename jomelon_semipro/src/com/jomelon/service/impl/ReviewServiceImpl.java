package com.jomelon.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.ReviewBoardDAO;
import com.jomelon.dao.impl.ReviewBoardDAOImpl;
import com.jomelon.domain.ReviewBoardVO;
import com.jomelon.service.ReviewService;

public class ReviewServiceImpl implements ReviewService{

	ReviewBoardDAO reviewDao = new ReviewBoardDAOImpl();
	
	//리뷰등록
	@Override
	public int insertReview(HttpServletRequest request) {
		ReviewBoardVO review = new ReviewBoardVO();
		
		review.setSubject(request.getParameter("reviewSubject"));
		review.setWriter(request.getParameter("reviewWriter"));
		review.setContent(request.getParameter("reviewContent"));
		review.setPassword(request.getParameter("reviewPw"));
		
		return reviewDao.insertReview(review);
	}

	// 리뷰글 10개 가져오기
	@Override
	public ArrayList<ReviewBoardVO> getAllReview(int startRow, int endRow, String filter, String query) {
		
		return reviewDao.getAllReview(startRow,endRow,filter,query);
	}

	//모든 리뷰글 개수
	@Override
	public int getAllReviewCnt(String filter, String query) {
		return reviewDao.getAllReviewCnt(filter, query);
	}

	//리뷰글 하나만 가져오기
	@Override
	public ReviewBoardVO getOneReview(HttpServletRequest request) {
		//num
		int num = Integer.parseInt(request.getParameter("num"));
		
		return reviewDao.getOneReview(num);
	}

	//리뷰 답변글 작성
	@Override
	public int reInsertReview(HttpServletRequest request) {
		ReviewBoardVO review = new ReviewBoardVO();
		
		review.setWriter(request.getParameter("reviewWriter"));
		review.setSubject(request.getParameter("reviewSubject"));
		review.setPassword(request.getParameter("reviewPw"));
		review.setContent(request.getParameter("reviewContent"));
		review.setRef(Integer.parseInt(request.getParameter("ref")));
		review.setReStep(Integer.parseInt(request.getParameter("reStep")));
		review.setReLevel(Integer.parseInt(request.getParameter("reLevel")));
		return reviewDao.reInsertReview(review);
	}

	//리뷰글 수정할 때, 내용 불러오기
	@Override
	public ReviewBoardVO getOneUpdateReview(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		return reviewDao.getOneUpdateReview(num);
	}

	//리뷰글 수정
	@Override
	public int updateReview(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("reviewSubject");
		String content = request.getParameter("reviewContent");
		
		return reviewDao.updateReview(num, subject, content);
	}

	//리뷰글 삭제
	@Override
	public int deleteReview(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		return reviewDao.deleteReview(num);
	}

	
	
	

}
