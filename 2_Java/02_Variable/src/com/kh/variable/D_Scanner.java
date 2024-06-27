package com.kh.variable;

import java.util.Scanner;

public class D_Scanner {

	/*
	 * Scanner
	 * - 화면(Console)으로부터 데이터를 "입력"받는 클래스
	 * 
	 * 1. import java.util.Scanner; 추가
	 * 
	 * 2. Scanner 객체 생성
	 * 		Scanner sc = new Scanner(System.in);
	 * 
	 * 3. Scanner 메서드 사용
	 * 		입력값이
	 * 		- 문자열일 때 : sc.next() - 공백이 있는 경우 공백 이전까지
	 * 					sc.nextLine() - 공백 포함 엔터 전까지
	 * 		- 정수일 때 : sc.nextInt()
	 * 		- 실수일 때 : sc.nextFloat(), sc.nextDouble();
	 * 
	 * 		=> 권장하기는 sc.nextLine() 만 사용하는 것을 권장 얘만 enter 처리
	 */
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("당신의 이름은 무엇입니까? > ");
		String name = sc.next(); // enter 처리 X
		sc.nextLine(); // enter 처리
		
		System.out.print("당신의 나이는 몇살입니까? > ");
//		int age = sc.nextInt(); // enter 처리 X
//		sc.nextLine();
		
		int age = Integer.parseInt(sc.nextLine()); // String -> int
		
		System.out.print("당신의 집은 어디십니까? > ");
		String addr = sc.nextLine();
		
		System.out.print("당신의 키는 몇입니까? (소수점 첫째 자리까지 입력하세요.) > ");
//		double height = sc.nextDouble(); // enter 처리 X
//		sc.nextLine();
		
		double height = Double.parseDouble(sc.nextLine()); // String -> double 
		
		System.out.print("당신의 성별은 무엇입니까? (남/여) > ");
//		char gender = sc.nextLine().charAt(0);
		String input = sc.nextLine();
		char gender = input.charAt(0);
		
		
//		System.out.println("당신의 이름은 " + name 
//				+ "이고 사는 곳은 " + addr 
//				+ "이고 나이는 " + age
//				+ "살, 키는 " + height 
//				+ "cm, 성별은 " + gender + "입니다.");
		
		// printf 변경
		System.out.printf("당신의 이름은 %s이고 사는 곳은 %s이고 나이는 %d살, 키는 %.1fcm, 성별은 %c입니다." 
				, name, addr, age, height, gender);
		
	}

}
