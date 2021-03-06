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

		// ???????????? ?????? ?????? count
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
	
	
	//Detail ????????? ???????????? ????????? ?????? ????????????.
	// ----------------------------------------------------------------------getNoticeCount
	public NoticeVO getNotice(int id) {
		
		NoticeVO notice = null;
		
		Connection conn= JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;


		try {
			//????????? ???????????? ????????? ??? ????????? ??????
			String sql1 = "UPDATE NOTICE SET HIT = HIT+1 WHERE ID=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			//????????? ???????????? ?????? ????????? ??????
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
	
	//???????????? ?????????
	
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
	
	//????????????
	
	public int deleteNoticeAll(int[] ids) {
		
		int result=0;
		
		String params="";
		
		for(int i=0; i<ids.length; i++) {
			params += ids[i];
			
			if(i < ids.length-1) {
				params += ",";
			}
		}
		
		// ???????????? ???- 1,2,3,... ????????? ???????????? ??????????????? 
		//??????? ????????? ?????? ????????? ?????? ??????(params)??? ????????? ?????????

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
	
	//?????? ?????? - ????????? ??????????????? ??? ????????? ??????(????????????????????? ?????????) 
	public int pubNoticeAll(int[] oids,int[] cids) {
		// ????????? ??????-> ?????????????????? ?????? ????????? ????????? ????????? ???????????? ??????
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
	
	// CSV - ????????? ????????? ??? (???????????? "20,30,43,56")
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
	
	
	// ???????????? ??????
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
