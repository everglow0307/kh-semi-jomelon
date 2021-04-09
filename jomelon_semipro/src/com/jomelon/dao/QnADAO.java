package com.jomelon.dao;

import java.util.ArrayList;

import com.jomelon.domain.QnABoardVO;
import com.jomelon.domain.UserVO;

public interface QnADAO {

	// 페이지번호 담아오기?
	int getNext();
	
	// 글 쓰기
	int write(String QnATitle, String userId, String QnAContent);
	
	ArrayList<QnABoardVO> getList(int pageNumber);
	
	boolean nextPage(int pageNumber);
	
	QnABoardVO getQnA(int QnAID);
	
	// 글 수정
	int update(int QnAID, String QnATitle, String QnAContent);
	
	// 글 삭제
	int delete(int QnAID);
	
	
}
