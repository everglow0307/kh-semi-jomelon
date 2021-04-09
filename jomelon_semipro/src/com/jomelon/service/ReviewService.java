package com.jomelon.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jomelon.domain.ReviewBoardVO;

public interface ReviewService {
	
	//insertReview - 글등록하기
	int insertReview(HttpServletRequest request);
	
	//리뷰글 10개씩 가져오기
	ArrayList<ReviewBoardVO> getAllReview(int startRow, int endRow, String filter, String query);
	
	//모든 리뷰글 개수
	int getAllReviewCnt(String filter, String query);
	
	//리뷰글 하나만 가져오기
	ReviewBoardVO getOneReview(HttpServletRequest request);
	
	//답변글 작성하기
	int reInsertReview(HttpServletRequest request);
	
	//리뷰글 수정시 내용 불러오기
	ReviewBoardVO getOneUpdateReview(HttpServletRequest request);
	
	//리뷰글 수정
	int updateReview(HttpServletRequest request);
	
	//리뷰글 삭제
	int deleteReview(HttpServletRequest request);
	
}
