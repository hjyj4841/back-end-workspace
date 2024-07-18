package com.kh.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.Book;

import config.DatabaseConnect;

public class BookController {

	private DatabaseConnect dc = DatabaseConnect.getInstance();
	
	// 싱글톤 패턴
	private BookController() {}
	private static BookController instance;
	public static BookController getInstance() {
		if(instance == null) instance = new BookController();
		return instance;
	}
	
	// 1. 전체 책 조회
	public ArrayList<Book> printBookAll() throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("printBookAll"));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			books.add(new Book(rs.getInt("BK_NO"), rs.getString("BK_TITLE"), 
					rs.getString("BK_AUTHOR"), rs.getInt("BK_PRICE"), rs.getInt("BK_PUB_NO")));
		}
		
		dc.close(rs, ps, conn);
		return books;
	}
	
	// 제목, 저자 중복 확인 true = 중복
	public boolean selectBook(String bkTitle, String bkAuthor) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("selectBook"));
		ps.setString(1, bkTitle);
		ps.setString(2, bkAuthor);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			dc.close(rs, ps, conn);
			return true;
		}
		
		dc.close(rs, ps, conn);
		return false;
	}
	
	// 2. 책 등록 true - 등록성공
	public boolean registerBook(String bkTitle, String bkAuthor) throws SQLException {
		if(selectBook(bkTitle, bkAuthor)) return false;
			
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("registerBook"));
		ps.setString(1, bkTitle);
		ps.setString(2, bkAuthor);
		ps.executeUpdate();
		
		dc.close(ps, conn);
		return true;
	}
	
	// 3. 책 삭제
	public boolean sellBook(int bkNo) throws SQLException{
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("sellBook"));
		ps.setInt(1, bkNo);
		
		if(ps.executeUpdate() == 0) {
			dc.close(ps, conn);
			return false;
		}
		
		dc.close(ps, conn);
		return true;
	}
	
	// bk_no 조회
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
	
}
