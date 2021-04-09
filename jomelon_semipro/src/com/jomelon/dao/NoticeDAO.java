package com.jomelon.dao;

import java.util.List;

import com.jomelon.domain.NoticeVO;

public interface NoticeDAO {
	
	//list
	List<NoticeVO> getNoticeList(String field, String query, int page);
	
	//공개체크 list
	List<NoticeVO> getNoticePubList(String field, String query, int page);
	
	//
	int getNoticeCount(String field, String query);
	
	// Detail 클릭한 글번혼의 글정보 하나 가져오기
	NoticeVO getNotice(int id);
	
	// 다음글
	NoticeVO getNextNotice(int id);
	
	// 이전글
	NoticeVO  getPrevNotice(int id);
	
	//공지사항글쓰기
	int insertNotice(NoticeVO notice);
	
	//일괄삭제
	int deleteNoticeAll(int[] ids);
	
	//일괄공개 - 다양한 자료형 받을 수 있도록
	int pubNoticeAll(int[] oids,int[] cids);
	int pubNoticeAll(List<String> oids, List<String> cids);
	int pubNoticeAll(String oidsCSV, String cidsCSV);
	
	//공지사항수정
	int updateNotice(NoticeVO notice);


}
