package com.jomelon.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.NoticeDAO;
import com.jomelon.dao.impl.NoticeDAOImpl;
import com.jomelon.domain.NoticeVO;
import com.jomelon.service.NoticeService;




public class NoticeServiceImpl implements NoticeService{

	NoticeDAO ndao = new NoticeDAOImpl();
	
	// ---------------------------------------------------------------------getNoticeList

	// 구현!!
	public List<NoticeVO> getNoticeList(HttpServletRequest request) {
		//list.jsp에서 list?f=title&q=a 로 전달
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title"; // 기본값 (null값 빈문자열- 전달 안됐을경우)
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";// 기본값
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		
		int page = 1;// 기본값
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		
		return ndao.getNoticeList(field, query, page);
	}
	// 공개된 페이지만 보기
	public List<NoticeVO> getNoticePubList(HttpServletRequest request) {
		//list.jsp에서 list?f=title&q=a 로 전달
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "title"; // 기본값 (null값 빈문자열- 전달 안됐을경우)
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";// 기본값
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		
		int page = 1;// 기본값
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		
		return ndao.getNoticePubList(field, query, page);
	}

	// ---------------------------------------------------------------------getNoticeCount

	// 구현!!
	public int getNoticeCount(HttpServletRequest request) {
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		
		String field = "title"; // 기본값 (null값 빈문자열- 전달 안됐을경우)
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		
		String query = "";// 기본값
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		
				
		return ndao.getNoticeCount(field, query);
	}

	// ----------------------------------------------------------------------getNoticeCount
	public NoticeVO getNotice(int id) {
		
		return ndao.getNotice(id);
	}

	public NoticeVO getNextNotice(int id) {
		
		return ndao.getNextNotice(id);
	}

	public NoticeVO getPrevNotice(int id) {

		return ndao.getPrevNotice(id);
	}
	
	
	// ------------------------------------------------admin(관리자페이지)


	//공지사항 글쓰기
	public int insertNotice(HttpServletRequest request) {
		
		String title = request.getParameter("title") ;
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		// open-> check가 되면 true check가 안되면 null값 
		boolean pub = false;
		if(isOpen != null){
			pub=true;
		}

		NoticeVO notice = new NoticeVO();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		
		// 인증권한 나중에 처리해줘야함 일단..
		notice.setWriterId("jomelon");

		return ndao.insertNotice(notice);
	}

//	
//	public int deleteNotice(int id) {
//
//		return 0;
//	}
//
//	public int updateNotice(NoticeVO notice) {
//
//		return 0;
//	}
//
//	List<NoticeVO> getNoticeNewestList() {
//
//		return null;
//	}

	// 일괄삭제 , 일괄공개
	public int deleteNoticeAll(HttpServletRequest request) {
		int result = 0;
		
		//checkbox에 체크한 값의 id(글번호)전달받음
				String[] openIds = request.getParameterValues("open-id"); //오픈체크한 글번호
				String[] delIds = request.getParameterValues("del-id");
				
				String cmd = request.getParameter("cmd");
				String ids_ = request.getParameter("ids");//임시
				String[] ids = ids_.trim().split(" "); //전체 목록의 글번호
				
				switch(cmd) {
				case "일괄공개":
					//for(String openId : openIds) 
						//System.out.printf("open id : %s\n", openId );
						
					List<String> oids = Arrays.asList(openIds);// Arrays.alist(배열) - 배열을 리스트형태로 바꾸어줌.
					// 1,2,3,4,5,6,7,8,9,10 - //3,5,8
					// 1,2,4,6,7,9,10
					List<String> cids = new ArrayList(Arrays.asList(ids));
					cids.removeAll(oids);
					System.out.println(Arrays.asList(ids));
					System.out.println(oids);
					System.out.println(cids);
					
					// Transaction처리
					result = ndao.pubNoticeAll(oids, cids);
					
					break;
				case "일괄삭제":
						
					// string-> int
					int[] ids1 = new int [delIds.length];
					for(int i=0; i<delIds.length; i++) {
						ids1[i] = Integer.parseInt(delIds[i]);
					}		
				
					result =  ndao.deleteNoticeAll(ids1);
					break;
				}
		
		
		return result;
		
	}
	
	// 공지사항 수정
	public int editNotice(HttpServletRequest request) {
		
		NoticeVO notice = new NoticeVO();
		
		int id= Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title") ;
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		// open-> check가 되면 true check가 안되면 null값 
		
		boolean pub = false;
		if(isOpen != null){
			pub=true;
		}
		
		notice.setId(id);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		
		
		// 인증권한 나중에 처리해줘야함 일단..
		notice.setWriterId("jomelon");

		return ndao.updateNotice(notice);
		
	}
}