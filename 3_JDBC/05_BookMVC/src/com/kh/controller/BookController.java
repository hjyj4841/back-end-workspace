package com.kh.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.dao.BookDAO;
import com.kh.model.dao.RentDAO;
import com.kh.model.vo.Book;

public class BookController {

	private BookDAO book = new BookDAO();
	private RentDAO rent = new RentDAO();
	
	// 1. 전체 책 조회
	public ArrayList<String> printBookAll() {
		ArrayList<String> result = new ArrayList<String>();
		
		try {
			ArrayList<Book> books = book.printBookAll();
			
			for(Book book : books) {
				String bookpub = book.getPublisher().getPubName() == null ? "출판사 없음" : book.getPublisher().getPubName();
				result.add(String.format("%d번 도서: %s (저자: %s) - 출판사 : %s", 
						book.getBkNo(), book.getBkTitle(), book.getBkAuthor(), bookpub));
			}
			
			return result;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	// 2. 책 등록 true - 등록성공
	public boolean registerBook(String bkTitle, String bkAuthor) {
		try {
			if (!book.selectBook(bkTitle, bkAuthor)) {
				book.registerBook(bkTitle, bkAuthor);
				return true;
			}
		} catch (SQLException e) {}
		return false;
	}
	
	// 3. 책 삭제 
	public boolean sellBook(int bkNo) {
		try {
			if(!rent.selectRentforBkNo(bkNo)) {
				return false;
			}
			for(Book b : book.printBookAll()) {
				if(b.getBkNo() == bkNo) {
					book.sellBook(bkNo);
					return true;
				}
			}
		} catch (SQLException e) {}
		return false;
	}
	
}
