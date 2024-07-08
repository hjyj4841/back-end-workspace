package com.kh.practice.controller;

import com.kh.practice.model.Book;
import com.kh.practice.model.Member;

public class MemberController {

	private Member member = new Member();
	private Book[] book = new Book[4];
	public int count = 0;
	
	// 멤버 입력
	public void insertUser(String name, int age) {
		member.setName(name);
		member.setAge(age);
	}
	
	// book 임시 값 넣기
	public void insertBook(String title, boolean coupon, int accessAge) {
		book[count++] = new Book(title, coupon, accessAge);
	}
	
	// book 정보 조회 메서드
	public Book viewBook(int num) {
		return book[num];
	}
	
	// book 대여
	public String rentalBook(int num) {
		if(rentalLimit()) { // 대여횟수 초과
			return "더 이상 대여할 수 없습니다.";
		}else if(rentalAlready(book[num])) { // 이미 대여 했음
			return "이미 대여한 책입니다.";
		}else if(rentalAge(book[num])) { // 나이가 불가능
			return "나이 제한으로 대여 불가능입니다.";
		}
		for(int i = 0; i < member.getBookList().length; i++) { // 대여 가능
			if(member.getBookList()[i] == null) {
				member.getBookList()[i] = new Book(book[num].getTitle(),
								book[num].isCoupon(), book[num].getAccessAge());
				if(book[num].isCoupon()) member.setCoupon(member.getCoupon()+1); // 쿠폰이 true면 +1
				break;
			}
		}
		return "성공적으로 대여되었습니다.";
	}
	
	// 대여 횟수 초과 조회
	public boolean rentalLimit() {
		if(member.getBookList()[member.getBookList().length-1] != null) {
			return true; // 대여 횟수 초과
		}
		return false; // 대여 가능
	}
	
	// 이미 대여한 책인지 조회
	public boolean rentalAlready(Book b) {
		for(int i = 0; i < member.getBookList().length; i++) {
			if(member.getBookList()[i] != null && member.getBookList()[i].equals(b)) {
				return true; // 이미 대여함
			}
		}
		return false; // 대여 가능
	}
	
	// 나이 제한 조회
	public boolean rentalAge(Book b) {
		if(b.getAccessAge() > member.getAge()) {
			return true; // 대여 불가
		}
		return false;// 대여 가능
	}
	
	// member 조회
	public Member showMember() {
		return member;
	}

}
