package com.kh;

import java.sql.SQLException;
import java.util.Scanner;

import com.kh.controller.BookController;
import com.kh.controller.MemberController;
import com.kh.controller.RentController;
import com.kh.model.Book;
import com.kh.model.Member;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = MemberController.getInstance();
	private BookController bc = BookController.getInstance();
	private RentController rc = RentController.getInstance();
	private Member member = new Member();

	public static void main(String[] args) {
		Application app = new Application();
		app.mainMenu();
	}

	public void mainMenu() {
		System.out.println("===== 도서 관리 프로그램 =====");

		boolean check = true;
		while (check) {
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 등록");
			System.out.println("3. 책 삭제");
			System.out.println("4. 회원가입");
			System.out.println("5. 로그인");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 입력 : ");
			try {
				switch (Integer.parseInt(sc.nextLine())) {
					case 1:
						printBookAll();
						break;
					case 2:
						registerBook();
						break;
					case 3:
						sellBook();
						break;
					case 4:
						registerMember();
						break;
					case 5:
						login();
						break;
					case 9:
						check = false;
						System.out.println("프로그램 종료");
						break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 1. 전체 책 조회
	public void printBookAll() throws SQLException {
		for(Book book : bc.printBookAll()) System.out.println(book);
	}

	// 2. 책 등록
	public void registerBook() throws SQLException {
		System.out.print("책 제목 : ");
		String bkTitle = sc.nextLine();
		
		System.out.print("책 저자 : ");
		String bkAuthor = sc.nextLine();
		
		if(bc.registerBook(bkTitle, bkAuthor)) System.out.println("성공적으로 책을 등록했습니다.");
		else System.out.println("책을 등록하는데 실패했습니다.");
	}

	// 3. 책 삭제
	public void sellBook() throws SQLException {
		printBookAll();
		
		System.out.print("삭제할 책 번호 : ");
		
		if(bc.sellBook(Integer.parseInt(sc.nextLine()))) System.out.println("성공적으로 책을 삭제했습니다.");
		else System.out.println("책을 삭제하는데 실패했습니다.");
	}

	// 4. 회원가입
	public void registerMember() throws SQLException {
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		
		if(mc.registerMember(memberId, memberPwd, memberName)) System.out.println("성공적으로 회원가입을 완료하였습니다.");
		else System.out.println("회원가입에 실패했습니다.");
	}

	// 5. 로그인
	public void login() throws SQLException {
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		
		member = mc.login(memberId, memberPwd);
		if(member != null) {
			System.out.println(member.getMemberName() + "님, 환영합니다!");
			memberMenu();
		}else System.out.println("로그인 정보가 틀렸습니다.");
	}

	public void memberMenu() throws SQLException {
		boolean check = true;
		while (check) {
			System.out.println("1. 책 대여");
			System.out.println("2. 내가 대여한 책 조회");
			System.out.println("3. 대여 취소");
			System.out.println("4. 로그아웃");
			System.out.println("5. 회원탈퇴");
			System.out.print("메뉴 번호 입력 : ");
			switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					rentBook();
					break;
				case 2:
					printRentBook();
					break;
				case 3:
					deleteRent();
					break;
				case 4:
					check = false;
					break;
				case 5:
					deleteMember();
					check = false;
					break;
			}
		}
	}

	// 1. 책 대여
	public void rentBook() throws SQLException {
		printBookAll();
		
		System.out.print("대여할 책 번호 : ");
		
		if(rc.rentBook(member.getMemberNo(), Integer.parseInt(sc.nextLine()))) System.out.println("성공적으로 책을 대여했습니다.");
		else System.out.println("책을 대여하는데 실패했습니다.");
	}

	// 2. 내가 대여한 책 조회
	public void printRentBook() throws SQLException {
		for(String list : rc.printRentBook(member.getMemberNo())) System.out.println(list);
	}

	// 3. 대여 취소
	public void deleteRent() throws SQLException {
		printRentBook();
		
		System.out.print("취소할 대여 번호 : ");
		
		if(rc.deleteRent(Integer.parseInt(sc.nextLine()), member.getMemberNo())) System.out.println("성공적으로 대여를 취소했습니다.");
		else System.out.println("대여를 취소하는데 실패했습니다.");
	}

	// 4. 회원탈퇴
	public void deleteMember() throws SQLException {
		if(mc.deleteMember(member.getMemberNo())) System.out.println("회원탈퇴 하였습니다 ㅠㅠ");
		else System.out.println("회원탈퇴하는데 실패했습니다.");
	}

}
