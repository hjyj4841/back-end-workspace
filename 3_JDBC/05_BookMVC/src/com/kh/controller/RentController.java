package com.kh.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DatabaseConnect;

public class RentController {

	private DatabaseConnect dc = DatabaseConnect.getInstance();
	private BookController bc = BookController.getInstance();
	
	// 싱글톤 패턴
	private RentController() {}
	private static RentController instance;
	public static RentController getInstance() {
		if(instance == null) instance = new RentController();
		return instance;
	}
		
	// 1. 책 대여
	public boolean rentBook(int rentMemNo, int rentBookNo ) throws SQLException {
		if(!bc.selectBkNo(rentBookNo)) return false;
		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("rentBook"));
		ps.setInt(1, rentMemNo);
		ps.setInt(2, rentBookNo);
		
		if(ps.executeUpdate() == 0) {
			dc.close(ps, conn);
			return false;
		}
		
		dc.close(ps, conn);
		return true;
	}
	
	// 2. 내가 대여한 책 조회
	public ArrayList<String> printRentBook(int memberNo) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("printRentBook"));
		ps.setInt(1, memberNo);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			result.add(String.format("%d번 - 제목: %s (저자: %s) 대여일: %s / 반납일: %s", 
					rs.getInt("RENT_NO"), rs.getString("BK_TITLE"), rs.getString("BK_AUTHOR"),
					rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE")));
		}
		
		dc.close(rs, ps, conn);
		return result;
	}
	
	// 3. 대여 취소
	public boolean deleteRent(int rentNo, int memberNo) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("deleteRent"));
		ps.setInt(1, rentNo);
		ps.setInt(2, memberNo);
		
		if(ps.executeUpdate() == 0) {
			dc.close(ps, conn);
			return false;
		}
		
		dc.close(ps, conn);
		return true;
	}
}
