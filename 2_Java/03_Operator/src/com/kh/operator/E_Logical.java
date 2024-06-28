package com.kh.operator;

import java.util.Scanner;

public class E_Logical {
	Scanner sc = new Scanner(System.in);

	/*
	 * 논리 연산자
	 *  - 두 개의 논리값을 연산해주는 연산자
	 *  - 논리연산한 결과마저 논리값
	 *  
	 *  논리값 && 논리값 (and) : 왼쪽, 오른쪽 둘 다 true일 경우만 결과값이 true
	 *  논리값 || 논리값 (or) : 왼쪽, 오른쪽 둘 중에 하나라도 true일 경우 결과값이 true
	 */
	
	public static void main(String[] args) {
		E_Logical e = new E_Logical();
		
//		e.method1();
//		e.method2();
		e.pracitce();
	}
	
	public void method1() {
		// 사용자가 입력한 정수값이 1부터 100 사이의 값인지 확인
		System.out.print("정수값 입력해 주세요 > ");
		int number = sc.nextInt();
		// 1 <= number <= 100
		// 1 <= number && number <= 100
		boolean result = 1 <= number && number <= 100;
		System.out.println("1부터 100사이의 값인가요? : " + result);
	}

	public void method2() {
		int number = 10;
		boolean result = false;
		
		// Short Cut Evaluation
		
		// &&
		// true && true = true
		// true && false = false
		// false && true = false
		// false && false = false
		// && 연산자를 기준으로 앞에서 이미 false이기 때문에 굳이 뒤쪽의 연산을 수행하지 않는다
		result = (number < 5) && (++number > 0);
		
		// result? number?
		System.out.println(result); // false
		System.out.println(number); // 10
		
		// ||
		// true || true = true
		// true || false = true
		// false || true = true
		// false || false = false
		result = (number < 20) || (++number > 0);
		
		System.out.println(result); // true
		System.out.println(number); // 10
		
	}
	
	/*
	 * 실습문제
	 * 
	 * 사용자가 입력한 문자 값이 알파벳인지 확인하기
	 * A ~ Z : 65 ~ 90, a ~ z : 97 ~ 122
	 */
	public void pracitce() {
		System.out.print("알파벳 입력 : ");
		char a = sc.nextLine().charAt(0);
		
		boolean result = (a >= 65 && a <= 90) || (a >= 97 && a <= 122);
		result = (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z');
		System.out.println(result);
		
	}
	
}
