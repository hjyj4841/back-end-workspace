package com.kh.practice.controller;

import java.util.ArrayList;

import com.kh.practice.model.Book;
import com.kh.practice.model.Member;

public class MemberController {

	private Member member = new Member();
	public ArrayList<Book> book = new ArrayList<Book>();
	
	// 멤버 입력
	public void insertUser(String name, int age) {
		member.setName(name);
		member.setAge(age);
	}
	
	// book 임시 값 넣기
	public void insertBook(String title, boolean coupon, int accessAge) {
		book.add(new Book(title, coupon, accessAge));
	}
	
	// book 정보 조회 메서드
	public Book viewBook(int num) {
		return book.get(num);
	}
	
	// book 대여
	public String rentalBook(int num) {
		ArrayList<Book> bookList = member.getBookList();
		
		try {
			// 이미 대여 했음
			if(rentalAlready(book.get(num))) return "이미 대여한 책입니다.";
			// 나이가 불가능
			if(book.get(num).getAccessAge() > member.getAge()) return "나이 제한으로 대여 불가능입니다.";
			
			// 대여 가능시 값 넣기
			bookList.add(new Book(book.get(num).getTitle(), book.get(num).isCoupon(), book.get(num).getAccessAge()));
			member.setBookList(bookList);
			// 쿠폰이 true면 +1
			if(book.get(num).isCoupon()) member.setCoupon(member.getCoupon()+1);
			
			return "성공적으로 대여되었습니다.";
		}catch(ArrayIndexOutOfBoundsException e) {
			return "번호를 잘못 입력하였습니다.";
		}
	}
	
	// 이미 대여한 책인지 조회
	public boolean rentalAlready(Book b) {
		for(Book book : member.getBookList()) {
			if(book != null && book.equals(b)) return true; // 이미 대여함
		}
		return false; // 대여 가능
	}
	
	// member 조회
	public Member showMember() {
		return member;
	}

}
