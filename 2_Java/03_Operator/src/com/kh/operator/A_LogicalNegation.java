package com.kh.operator;

public class A_LogicalNegation {

	public static void main(String[] args) {
		/*
		 * 논리 부정 연산자
		 * 
		 * !논리값(true, false)
		 * 
		 * - 논리값을 반대로 바꾸는 연산자 (true -> false, false -> true)
		 */
		boolean b1 = true;
		boolean b2 = false;
		
		System.out.println(!b1); // false
		System.out.println(!b2); // true
		
		b1 = !b2;
		
		System.out.println(!!b1); // true
	}

}
