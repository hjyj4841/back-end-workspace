package com.kh.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import com.kh.model.dao.BookDAO;
import com.kh.model.dao.RentDAO;
import com.kh.model.vo.Rent;


public class RentController {
	
	RentDAO rent = new RentDAO();
	BookDAO book = new BookDAO();
	
	// 1. 책 대여
	public boolean rentBook(int rentMemNo, int rentBookNo) {
		try {
			if(!book.selectBkNo(rentBookNo)) return false;
			if(!rent.selectRentforBkNo(rentBookNo)) {
				rent.rentBook(rentMemNo, rentBookNo);
				return true;
			}
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	// 2. 내가 대여한 책 조회
	public ArrayList<String> printRentBook(int memberNo) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			
			for(Rent r : rent.printRentBook(memberNo)) {
				LocalDate localDate = new Date(r.getRentalDate().getTime()).toLocalDate();
				
				list.add(String.format("%d번 - 제목: %s (저자: %s) 대여일: %s / 반납일: %s", 
						r.getRentNo(), r.getBook().getBkTitle(), r.getBook().getBkAuthor(),
						r.getRentalDate(), localDate.plusDays(14)));
			}
			
			return list;
		} catch (SQLException e) {
			return null;
		}
	}
	
	// 3. 대여 취소
	public boolean deleteRent(int rentNo, int memberNo) {
		try {
			if(rent.deleteRent(rentNo, memberNo) == 1) return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
}
