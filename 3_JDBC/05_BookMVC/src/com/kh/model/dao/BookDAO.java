package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.model.vo.Publisher;

import config.DatabaseConnect;

/*
 * DAO(Data Access Object)
 * - DB에 접근하는 역할을 하는 객체 (CRUD)
 */

public class BookDAO {
	
	DatabaseConnect dc = new DatabaseConnect();
	
	// 1. 전체 책 조회
	public ArrayList<Book> printBookAll() throws SQLException{
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("printBookAll"));
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Book> list = new ArrayList<Book>();
		
		while(rs.next()) {
			Book book = new Book();
			book.setBkNo(rs.getInt("BK_NO"));
			book.setBkTitle(rs.getString("BK_TITLE"));
			book.setBkAuthor(rs.getString("BK_AUTHOR"));
			
			Publisher publisher = new Publisher();
			publisher.setPubName(rs.getString("PUB_NAME")); 
			book.setPublisher(publisher);
			
			list.add(book);
		}
		
		dc.close(rs, ps, conn);
		return list;
	}
	
	// 제목, 저자 중복 확인 true = 중복
	public boolean selectBook(String bkTitle, String bkAuthor) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("selectBook"));
		ps.setString(1, bkTitle);
		ps.setString(2, bkAuthor);
		ResultSet rs = ps.executeQuery();
		
		boolean check = rs.next();
		dc.close(rs, ps, conn);
		return check;
	}
	
	// 2. 책 등록 true - 등록성공
	public void registerBook(String bkTitle, String bkAuthor) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("registerBook"));
		ps.setString(1, bkTitle);
		ps.setString(2, bkAuthor);
		ps.executeUpdate();
		
		dc.close(ps, conn);
	}
	
	// bk_no로 조회 대여정보 조회 = true면 있음
	public boolean selectBkNo(int bkNo) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("selectBkNo"));
		ps.setInt(1, bkNo);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			dc.close(rs, ps, conn);
			return true;
		}
		
		dc.close(rs, ps, conn);
		return false;
	}
	
	// 3. 책 삭제 
	public void sellBook(int bkNo) throws SQLException{		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("sellBook"));
		ps.setInt(1, bkNo);
		ps.executeUpdate();
		
		dc.close(ps, conn);
	}

}
