package com.jomelon.dao.impl;

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

import com.jomelon.common.dbutil.JDBCTemplate;
import com.jomelon.dao.NoticeDAO;
import com.jomelon.domain.NoticeVO;
import com.jomelon.util.SecurityUtil;

public class NoticeDAOImpl implements NoticeDAO {
	
	// List
	public List<NoticeVO> getNoticeList(String field/*TITLE, WRITER_ID*/, String query/*A*/, int page) {

		List<NoticeVO> list = new ArrayList<NoticeVO>();
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String sql= "SELECT * FROM ( "
					+ " SELECT ROWNUM NUM, N.* "
					+ " FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N " 
					+ " ) "
					+ " WHERE NUM BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%" );
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3,  page*10);
		
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int id = rset.getInt("ID");
				String title = rset.getString("TITLE");
				String writerId = rset.getString("WRITER_ID");
				Date regdate = rset.getDate("REGDATE");
				int hit = rset.getInt("HIT");
				String files = rset.getString("FILES");
				String content =rset.getString("CONTENT");
				boolean pub = rset.getBoolean("PUB");

				NoticeVO notice = new NoticeVO(id, title, writerId, regdate, hit, files, content, pub);

				list.add(notice);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}
	
	public List<NoticeVO> getNoticePubList(String field/*TITLE, WRITER_ID*/, String query/*A*/, int page) {

		List<NoticeVO> list = new ArrayList<NoticeVO>();
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String sql= "SELECT * FROM ( "
					+ " SELECT ROWNUM NUM, N.* "
					+ " FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N " 
					+ " ) "
					+ " WHERE PUB=1 AND NUM BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%" );
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3,  page*10);
		
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int id = rset.getInt("ID");
				String title = rset.getString("TITLE");
				String writerId = rset.getString("WRITER_ID");
				Date regdate = rset.getDate("REGDATE");
				int hit = rset.getInt("HIT");
				String files = rset.getString("FILES");
				String content =rset.getString("CONTENT");
				boolean pub = rset.getBoolean("PUB");

				NoticeVO notice = new NoticeVO(id, title, writerId, regdate, hit, files, content, pub);

				list.add(notice);
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}
	
	public int getNoticeCount(String field, String query) {
		
		int count =0;
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 집계하는 값을 원함 count
		try {
			String sql= "SELECT COUNT(ID) COUNT FROM ( "
					+ " SELECT ROWNUM NUM, N.* "
					+ " FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N " 
					+ " ) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+query+"%" );
						
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = rset.getInt("count");				
			}
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return count;
	}
	
	
	//Detail 클릭한 글번호의 글정보 하나 가져오기.
	// ----------------------------------------------------------------------getNoticeCount
	public NoticeVO getNotice(int id) {
		
		NoticeVO notice = null;
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;


		try {
			//하나의 리뷰글을 읽었을 떄 조회수 증가
			String sql1 = "UPDATE NOTICE SET HIT = HIT+1 WHERE ID=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			//하나의 공지글에 대한 정보를 리턴
			String sql= "SELECT * FROM NOTICE WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
				
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int nid = rset.getInt("ID");
				String title = rset.getString("TITLE");
				String writerId = rset.getString("WRITER_ID");
				Date regdate = rset.getDate("REGDATE");
				int hit = rset.getInt("HIT");
				String files = rset.getString("FILES");
				String content = rset.getString("CONTENT");
				boolean pub = rset.getBoolean("PUB");

				notice = new NoticeVO(nid, title, writerId, regdate, hit, files, content, pub);
			}
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return notice;

		}
	
	
	
	public NoticeVO getNextNotice(int id) {
	
		NoticeVO notice = null;
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String sql= "SELECT * FROM NOTICE WHERE ID = (SELECT ID FROM NOTICE WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND ROWNUM = 1)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
						
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int nid = rset.getInt("ID");
				String title = rset.getString("TITLE");
				String writerId = rset.getString("WRITER_ID");
				Date regdate = rset.getDate("REGDATE");
				int hit = rset.getInt("HIT");
				String files = rset.getString("FILES");
				String content = rset.getString("CONTENT");
				boolean pub = rset.getBoolean("PUB");

				notice = new NoticeVO(nid, title, writerId, regdate, hit, files, content, pub);
			}
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return notice;
	}
	
	public NoticeVO  getPrevNotice(int id) {
		
		NoticeVO notice = null;
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			String sql= "SELECT * FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = ?) AND ROWNUM = 1";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
						
			rset = pstmt.executeQuery();
			if (rset.next()) {
				int nid = rset.getInt("ID");
				String title = rset.getString("TITLE");
				String writerId = rset.getString("WRITER_ID");
				Date regdate = rset.getDate("REGDATE");
				int hit = rset.getInt("HIT");
				String files = rset.getString("FILES");
				String content = rset.getString("CONTENT");
				boolean pub = rset.getBoolean("PUB");

				notice = new NoticeVO(nid, title, writerId, regdate, hit, files, content, pub);
			}
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return notice;
	}
	
	//공지사항 글쓰기
	
	public int insertNotice(NoticeVO notice) {

		int result=0;

		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;

		try {
			String sql= "INSERT INTO NOTICE(TITLE, CONTENT, WRITER_ID, PUB) VALUES(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getWriterId());
			pstmt.setBoolean(4,  notice.getPub());
						
			result = pstmt.executeUpdate();
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}	
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//일괄삭제
	
	public int deleteNoticeAll(int[] ids) {
		
		int result=0;
		
		String params="";
		
		for(int i=0; i<ids.length; i++) {
			params += ids[i];
			
			if(i < ids.length-1) {
				params += ",";
			}
		}
		
		// 괄호안에 예- 1,2,3,... 쉼표로 구분해서 들어와야함 
		//?로는 넣을수 없고 쉼표를 넣은 변수(params)를 만들어 줘야함

		Connection conn= JDBCTemplate.getConnection();
		Statement st = null;

		try {
			String sql= "DELETE NOTICE WHERE ID IN ("+params+")";
			
			st = conn.createStatement();
			
			result = st.executeUpdate(sql);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}	
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(st);
		}
		return result;
		
	}
	
	//일괄 공개 - 다양한 자료형받을 수 있도록 구현(사용자의편의를 위해서) 
	public int pubNoticeAll(int[] oids,int[] cids) {
		// 정수형 배열-> 문자열배열로 변경 하면서 동시에 리스트 컬렉션에 저장
		List<String> oidsList = new ArrayList<String>();
		for(int i=0; i<oids.length; i++) {
			oidsList.add(String.valueOf(oids[i]));
		}
		List<String> cidsList = new ArrayList<String>();
		for(int i=0; i<cids.length; i++) {
			cidsList.add(String.valueOf(oids[i]));
		}
		
		return pubNoticeAll(oidsList, cidsList);
	}
	
	public int pubNoticeAll(List<String> oids, List<String> cids) {
		
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	
	// CSV - 콤마로 구분된 값 (예를들어 "20,30,43,56")
	public int pubNoticeAll(String oidsCSV, String cidsCSV) {
		int result = 0;
		
		Connection conn= JDBCTemplate.getConnection();
		Statement stOpen = null;
		Statement stClose = null;

		try {
			//String sqlOpen= "UPDATE NOTICE SET PUB=1 WHERE ID IN ("+oidsCSV+")";
			String sqlOpen= String.format("UPDATE NOTICE SET PUB=1 WHERE ID IN (%s)", oidsCSV);
			String sqlClose = String.format("UPDATE NOTICE SET PUB=0 WHERE ID IN (%s)", cidsCSV);
			
			stOpen = conn.createStatement();
			result += stOpen.executeUpdate(sqlOpen);
			
			stClose = conn.createStatement();
			result += stClose.executeUpdate(sqlClose);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}	
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(stOpen);
			JDBCTemplate.close(stClose);
		}

		return 0;
	}
	
	
	// 공지사항 수정
	public int updateNotice(NoticeVO notice) {

		int result=0;

		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;

		try {
			String sql= "UPDATE NOTICE SET TITLE=?, CONTENT=?, WRITER_ID=?, PUB=? WHERE ID=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getWriterId());
			pstmt.setBoolean(4,  notice.getPub());
			pstmt.setInt(5, notice.getId());
						
			result = pstmt.executeUpdate();
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}	
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


		
		
	

}
