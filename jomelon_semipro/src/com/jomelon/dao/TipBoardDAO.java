package com.jomelon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jomelon.domain.TipBoardBean;

public class TipBoardDAO{
	    ResultSet rs; 
	    Connection conn;
	    PreparedStatement pstmt;

	 public void getcon() {
		  try {
		   String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		   String dbID = "SEMI";
		   String dbPassword = "semi";
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	 }
	 
	 
	
	public void insertBoard(TipBoardBean bean){
		getcon();
		
		int ref = 0;
		int re_stop = 1;
		int re_level = 1;
		try {
			String refSQL = "SELECT max(ref) FROM TipBoard";
			pstmt = conn.prepareStatement(refSQL);
			 rs = pstmt.executeQuery();	
			if(rs.next()){
				ref = rs.getInt(1)+1;	
			}
			
			String SQL = "INSERT INTO TipBoard VALUES(TipBoard_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bean.getWrite());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_stop);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());		
			pstmt.executeUpdate();	
			conn.close();
			}catch(Exception e) {
				e.printStackTrace();	
			}	
	}
	
	
	public Vector<TipBoardBean> getAllBoard(int start , int end){
		Vector<TipBoardBean> v = new Vector<TipBoardBean>();
		getcon();
		try {
			String SQL = "select * from (select A.* ,Rownum Rnum from (select * from TipBoard order by ref desc ,re_stop asc)A)"
					+ "where Rnum >= ? and Rnum <= ?";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,start);
			pstmt.setInt(2,end);
			
			rs = pstmt.executeQuery();	
			
			while(rs.next()){
				 TipBoardBean bean = new TipBoardBean();
				 bean.setNum(rs.getInt(1));
				 bean.setWrite(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_stop(rs.getInt(8));
				 bean.setRe_level(rs.getInt(9));
				 bean.setReadcount(rs.getInt(10));
				 bean.setContent(rs.getString(11));
				 
				 v.add(bean);
			 }
			
			conn.close();
			
			}catch(Exception e) {
				e.printStackTrace();	
			}	
		
			return v;
	}
	
	public TipBoardBean getOneBoard(int num){	
		TipBoardBean bean = new TipBoardBean();
		getcon();
		try {
			String readsql = "update TipBoard set readcount = readcount+1 where num=?";
			pstmt = conn.prepareStatement(readsql);
			pstmt.setInt(1,num);
			pstmt.executeUpdate();	
			
			String SQL = "select * from TipBoard where num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,num);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				 bean.setNum(rs.getInt(1));
				 bean.setWrite(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_stop(rs.getInt(8));
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
	
	public void reWriteBoard(TipBoardBean bean){
		int ref =bean.getRef();
		int re_stop = bean.getRe_stop();
		int re_level = bean.getRe_level();
		
		getcon();
		
		try {
				String  levelsql = "update TipBoard set re_level=re_level+1 where ref=? and re_level > ?";
				pstmt = conn.prepareStatement(levelsql);
				pstmt.setInt(1 , ref);
				pstmt.setInt(2 , re_level);
				pstmt.executeUpdate();
				String sql ="insert into TipBoard values(TipBoard_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getWrite());
				pstmt.setString(2, bean.getEmail());
				pstmt.setString(3, bean.getSubject());
				pstmt.setString(4, bean.getPassword());
				pstmt.setInt(5, ref);
				pstmt.setInt(6, re_stop+1);
				pstmt.setInt(7, re_level + 1);
				pstmt.setString(8, bean.getContent());
				
				pstmt.executeUpdate();	
				conn.close();
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public TipBoardBean getOneUpdateBoard(int num){	
		TipBoardBean bean = new TipBoardBean();
		getcon();
		try {
			String SQL = "select * from TipBoard where num=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,num);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				 bean.setNum(rs.getInt(1));
				 bean.setWrite(rs.getString(2));
				 bean.setEmail(rs.getString(3));
				 bean.setSubject(rs.getString(4));
				 bean.setPassword(rs.getString(5));
				 bean.setReg_date(rs.getDate(6).toString());
				 bean.setRef(rs.getInt(7));
				 bean.setRe_stop(rs.getInt(8));
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
	
	public String getPass(int num) {
		String pass = "";
		getcon();
		
		try {		
			String sql = "select password from TipBoard where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				pass = rs.getString(1);	
			}
			
			conn.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return pass;
	}
	
	public void updateBoard(TipBoardBean bean){
		getcon();
		
		try {		
			String sql = "update TipBoard set subject=? , content= ? where num = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,bean.getSubject());
			pstmt.setString(2,bean.getContent());
			pstmt.setInt(3,bean.getNum());
				
			pstmt.executeUpdate();
			
			conn.close();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBoard(int num) {
		getcon();
		
		try {		
			String sql = "delete from TipBoard where num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			
			pstmt.executeUpdate();
			
			conn.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
	public int getAllCount(){
		getcon();
		
		int count = 0;
		
		try {		
			String sql = "select count(*) from TipBoard";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}