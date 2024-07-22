package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.model.vo.Rent;

import config.DatabaseConnect;

public class RentDAO {
	
	DatabaseConnect dc = new DatabaseConnect();
	
	// 1. 책 대여
	public void rentBook(int rentMemNo, int rentBookNo ) throws SQLException {		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("rentBook"));
		ps.setInt(1, rentMemNo);
		ps.setInt(2, rentBookNo);
		
		ps.executeUpdate();
		dc.close(ps, conn);
	}
	
	// rent_book_no로 중복 조회 true=중복
	public boolean selectRentforBkNo(int rentBookNo) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("selectRentforBkNo"));
		ps.setInt(1, rentBookNo);
		
		ResultSet rs = ps.executeQuery();
		boolean check = rs.next();
		
		dc.close(rs, ps, conn);
		return check;
	}
	
	// 2. 내가 대여한 책 조회
	public ArrayList<Rent> printRentBook(int memberNo) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("printRentBook"));
		ps.setInt(1, memberNo);
		ResultSet rs = ps.executeQuery();
				
		ArrayList<Rent> list = new ArrayList<Rent>();
		
		while(rs.next()) {
			Rent rent = new Rent();
			rent.setRentNo(rs.getInt("RENT_NO"));
			rent.setRentalDate(rs.getDate("RENT_DATE"));
			
			Book book = new Book();
			book.setBkTitle(rs.getString("BK_TITLE"));
			book.setBkAuthor(rs.getString("BK_AUTHOR"));
			rent.setBook(book);
			
			list.add(rent);
		}
		
		dc.close(rs, ps, conn);
		return list;
	}
	
	// 3. 대여 취소
	public int deleteRent(int rentNo, int memberNo) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("deleteRent"));
		ps.setInt(1, rentNo);
		ps.setInt(2, memberNo);
		
		int result = ps.executeUpdate();
		dc.close(ps, conn);
		return result;
	}
	
}
