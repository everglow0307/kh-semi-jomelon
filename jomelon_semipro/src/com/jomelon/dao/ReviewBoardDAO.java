package com.jomelon.dao;

import java.util.ArrayList;
import java.util.List;

import com.jomelon.domain.ReviewBoardVO;

public interface ReviewBoardDAO {

	//리뷰 등록
	int insertReview(ReviewBoardVO reviewVO);

	//리뷰 10개씩 가져오기
	ArrayList<ReviewBoardVO> getAllReview(int startRow, int endRow, String filter, String query);
	
	//모든 리뷰 개수
	int getAllReviewCnt(String filter, String query);
	
	//리뷰글 하나만 가져오기(조회수 증가-리뷰글 상세보기)
	ReviewBoardVO getOneReview(int num);
	
	//답변글 작성하기
	int reInsertReview(ReviewBoardVO reviewVo);
	
	//update할 때, 리뷰글 내용 하나 가져오기(조회수 증가x)
	ReviewBoardVO getOneUpdateReview(int num);
	
	//하나의 리뷰글 수정
	int updateReview(int num, String subject, String content);
	
	//리뷰글 삭제
	int deleteReview(int num);
	
}
