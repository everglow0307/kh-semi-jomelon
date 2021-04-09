package com.jomelon.dao;

import java.awt.image.RescaleOp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jomelon.domain.SuggBoardBean;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuggBoardDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// 데이터 베이스의 커넥션풀을 사용하도록 설정하는 메소드
	public void getCon() {
		
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "SEMI";
		    String dbPassword = "semi";
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 하나의 새로운 게시글이 넘어와서 저장되는 메소드
	public void insertBoard(SuggBoardBean bean) {
		
		getCon();
		//빈클래스에 넘어오지 않았던 데이터들을 초기화 해주어야 합니다.
		int ref = 0; // 글 그룹을 의미 = 쿼리를 실행시켜서 가장큰 ref 값을 가져온 후 +1을 더해주면됨
		int re_step = 1; // 새글이기에 = 부모글이기에
		int re_level = 1;
		
		try {
			//가장큰 ref 값을 읽어오는 쿼리 준비
			String refsql = "select max(ref) from board";
			//쿼리실행 객체
			pstmt = conn.prepareStatement(refsql);
			//쿼리실행후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) { //결과 값이 있다면
				ref = rs.getInt(1)+1; // 최대값에 +1을 더해서 글 그룹을 설정
			}
			// 실제로 게시글 전체값을 테이블에 저장
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			//?에 값을 넣음
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			//쿼리를 실행하시오
			pstmt.executeUpdate();
			//자원 반납
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//모든 게시글을 리턴해주는 메소드 작성
	public Vector<SuggBoardBean> getAllBoard(int start, int end){
		
		//리턴할객체 선언
		Vector<SuggBoardBean> v = new Vector<SuggBoardBean>();
		getCon();
		
		try {
			//쿼리준비
			String sql = "select * from (select A.* , Rownum Rnum from (select * from board order by ref desc, re_step asc)A)"
					+ "where Rnum >= ? and Rnum <= ?";
			//쿼리를 실행할객체 선언
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//쿼리실행후 결과 저장
			rs = pstmt.executeQuery();
			//데이터 개수가 몇개인지 모르기에 반복문을 이용하여 데이터를 추출
			while(rs.next()) {
				//데이터를 패키징(가방 = Boardbean 클래스를이요)해줌
				SuggBoardBean bean = new SuggBoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				//패키징한 데이터를 벡터에 저장
				v.add(bean);
			}
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//BoardInfo에 하나의 게시글을 리턴하는 메소드
	public SuggBoardBean getOneBoard(int num) {
		
		//리턴타입 선언
		SuggBoardBean bean = new SuggBoardBean();
		getCon();
		
		try {
			//조회수 증가쿼리
			String readsql = "update board set read_count = read_count + 1 where num=?";
			pstmt = conn.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//쿼리준비
			String sql = "select * from board where num=?";
			//쿼리실행객체
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, num); 
			//쿼리 실행후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	//답변글이 저장되는 메소드
	public void reWriteBoard(SuggBoardBean bean) {
		//부모글그룹과 글레벨 글스텝을 열어드림
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		getCon();
		
		try {
	//////////////////핵심코드///////////////////
			//부모 글보다 큰 re_level의 값을 전부 1씩 증가시켜줌
			String levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";
			//쿼리실행객체 선언
			pstmt = conn.prepareStatement(levelsql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			//쿼리실행
			pstmt.executeUpdate();
			//답변글 데이터를 저장
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			//?에 값을 대입
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);//부모의 ref값을 넣어줌
			pstmt.setInt(6, re_step+1);//답글이기에 부모 글 re_step에 1을 더해줌
			pstmt.setInt(7, re_level+1);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//boardupdate용  Delte시 하나의 게시글을 리턴
		public SuggBoardBean getOneUpdateBoard(int num) {
			
			//리턴타입 선언
			SuggBoardBean bean = new SuggBoardBean();
			getCon();
			
			try {
				
				//쿼리준비
				String sql = "select * from board where num=?";
				//쿼리실행객체
				pstmt = conn.prepareStatement(sql); 
				pstmt.setInt(1, num); 
				//쿼리 실행후 결과를 리턴
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bean.setNum(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setEmail(rs.getString(3));
					bean.setSubject(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setReg_date(rs.getDate(6).toString());
					bean.setRef(rs.getInt(7));
					bean.setRe_step(rs.getInt(8));
					bean.setRe_level(rs.getInt(9));
					bean.setReadcount(rs.getInt(10));
					bean.setContent(rs.getString(11));
				}
				
				conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return bean;
		}
		
		//update와 delete시 필요한 패스워드값을 리턴해주는 메소드
		public String getPass(int num) {
			//리턴할 변수 객체 선언
			String pass = "";
			getCon();
			
			try {
				//쿼리준비
				String sql = "select password from board where num=?";
				//쿼리실행할 객체선언
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				//패스워드값을 저장
				if(rs.next()) {
					pass = rs.getString(1);
				}
				//자원반납
				conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return pass;
		}
	
		//하나의  게시글을 수정하는 메소드
		public void updateBoard(SuggBoardBean bean) {
			getCon();
			
			try {
				//쿼리준비
				String sql = "update board set subject=?, content=? where num=?";
				pstmt = conn.prepareStatement(sql);
				
				//값을 대입
				pstmt.setString(1, bean.getSubject());
				pstmt.setString(2, bean.getContent());
				pstmt.setInt(3, bean.getNum());
				
				pstmt.executeUpdate();
				
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 하나의 게시글을 삭제하는 메소드
		public void deleteBoard(int num) {
			getCon();

			try {
				// 쿼리준비
				String sql = "delete from board where num=?";
				pstmt = conn.prepareStatement(sql);
				// 값을 대입
				pstmt.setInt(1, num);
				//쿼리실행
				pstmt.executeUpdate();
	
				//자원반납
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//전체 글의 갯수를 리턴하는 메소드
		public int getAllCount() {
			getCon();
			//게시글 전체수를 저장하는 변수
			int count = 0;
			
			try {
				//쿼리 준비
				String sql = "select count(*) from board";
				//쿼리를 실행할 객체 선언
				pstmt = conn.prepareStatement(sql);
				//쿼리 실행후 결과를 리턴
				rs = pstmt.executeQuery();
				if(rs.next()){
					count = rs.getInt(1);	//전체게시글수
				}
				conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}
