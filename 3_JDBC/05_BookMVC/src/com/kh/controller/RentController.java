package com.kh.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.dao.BookDAO;
import com.kh.model.dao.RentDAO;


public class RentController {
	
	RentDAO dao = new RentDAO();
	BookDAO bookDAO = new BookDAO();
	
	// 1. 책 대여
	public boolean rentBook(int rentMemNo, int rentBookNo) {
		try {
			if(!bookDAO.selectBkNo(rentBookNo)) return false;
			if(dao.selectRentforBkNo(rentBookNo)) return false;
			
			return dao.rentBook(rentMemNo, rentBookNo);
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	// 2. 내가 대여한 책 조회
	public ArrayList<String> printRentBook(int memberNo) {
		try {
			return dao.printRentBook(memberNo);
		} catch (SQLException e) {
			return null;
		}
	}
	
	// 3. 대여 취소
	public boolean deleteRent(int rentNo, int memberNo) {
		try {
			return dao.deleteRent(rentNo, memberNo);
		} catch (SQLException e) {
			return false;
		}
	}
	
}
