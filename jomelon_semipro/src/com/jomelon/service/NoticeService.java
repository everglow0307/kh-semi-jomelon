package com.jomelon.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jomelon.domain.NoticeVO;

public interface NoticeService {
	
	// list
	List<NoticeVO> getNoticeList(HttpServletRequest request);
	
	// list- 공개된 페이지만 보기
	List<NoticeVO> getNoticePubList(HttpServletRequest request);
	
	//
	int getNoticeCount(HttpServletRequest request);
	
	NoticeVO getNotice(int id);
	NoticeVO getNextNotice(int id);
	NoticeVO getPrevNotice(int id);
	
	//공지사항 글쓰기
	int insertNotice(HttpServletRequest request);
	
	//일괄삭제, 일괄공개
	int deleteNoticeAll(HttpServletRequest request);
	
	//공지사항 수정
	int editNotice(HttpServletRequest request);
	
	
}
