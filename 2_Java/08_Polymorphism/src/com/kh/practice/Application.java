package com.kh.practice;

import java.util.Scanner;

import com.kh.practice.controller.MemberController;
import com.kh.practice.model.Book;
import com.kh.practice.model.Member;

public class Application {
	Scanner sc = new Scanner(System.in);
	MemberController mc = new MemberController();
	Member member = new Member();
	
	public static void main(String[] args) {
		
		Application app = new Application();
		
		app.insertUser();
		app.showMenu();
	}
	
	// 유저 입력
	public void insertUser() {
		// 유저 입력 전에 미리 넣을 book 객체 임시값
		mc.insertBook("밥을 지어요", true, 0);
		mc.insertBook("오늘은 아무래도 덮밥", false, 0);
		mc.insertBook("원피스", false, 15);
		mc.insertBook("귀멸의 칼날", false, 19);
		
		System.out.print("이름 : ");
		member.setName(sc.nextLine());
		System.out.print("나이 : ");
		member.setAge(Integer.parseInt(sc.nextLine()));
		
		mc.insertUser(member.getName(), member.getAge());
	}
	
	// 메뉴화면
	public void showMenu() {
		while(true) {
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 대여하기");
			System.out.println("3. 프로그램 종료하기");
			System.out.print("메뉴 번호 : ");
			switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println(mc.showMember());
					break;
				case 2:
					bookRental();
					break;
				case 3:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("올바른 번호가 아닙니다. 다시 입력하세요.");
			}
		}
	}
	
	// 도서 대여
	public void bookRental() {
		for(int i = 0; i < mc.count; i++) {
			System.out.println((i+1) +"번도서 : " + mc.viewBook(i));
		}
		System.out.print("대여할 도서 번호 선택 : ");
		int num = Integer.parseInt(sc.nextLine())-1;
		
		if(num > 5) {
			System.out.println("올바른 번호가 아닙니다. 다시 입력하세요.");
		}
		
		System.out.println(mc.rentalBook(num));
	}
	
}
