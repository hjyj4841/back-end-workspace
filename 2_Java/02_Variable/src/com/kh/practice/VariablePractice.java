package com.kh.practice;

import java.util.Scanner;

public class VariablePractice {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		VariablePractice variablePractice = new VariablePractice();
		
		variablePractice.method1();
		variablePractice.method2();
		variablePractice.method3();
		variablePractice.method4();
		variablePractice.method5();
		variablePractice.method6();
		variablePractice.method7();
	}
	
	/*
	 * 영화관의 요금표는 다음과 같다.
	 *  - 성인 : 10000원
	 *  - 청소년 : 7000원
	 * 
	 * 성인 2명과 청소년 3명이 영화를 보려고 할 때 지불해야 할 금액을 계산 후 출력하세요.
	 * */
	public void method1() {
		System.out.println("-------Q1----------");
		
		int adult = 10000;
		int child = 7000;
		
		System.out.printf("금액은 %d원 입니다.\n", adult*2+child*3);
	}
	
	/*
	 * x=5, y=7, z=9의 값을 직접 변경하지 않고 변수를 사용하여
	 * 
	 * x=7
	 * y=9
	 * z=5
	 * 
	 * 로 출력하세요.
	 * */
	public void method2() {
		System.out.println("-------Q2----------");
		
		int x = 5;
		int y = 7;
		int z = 9;
		
		int result = x;
		x = y;
		y = z;
		z = result;
		
		
		System.out.printf("x = %d\ny = %d\nz = %d\n", x, y, z);
	}

	/*
	 * 정수 두 개를 입력 받아 두 수의 합(+), 차(-), 곱(*), 나누기(/)한 몫을 출력하세요.
	 * 
	 * 첫 번째 정수 : 23
	 * 두 번째 정수 : 7
	 * 더하기 : 30
	 * 빼기 : 16
	 * 곱하기 : 161
	 * 나누기 몫 : 3
	 * */
	public void method3() {
		System.out.println("-------Q3----------");
		
		System.out.print("첫번째 정수 : ");
		int num1 = Integer.parseInt(sc.nextLine());
		
		System.out.print("두번째 정수 : ");
		int num2 = Integer.parseInt(sc.nextLine());
		
		System.out.printf("더하기 : %d\n빼기 : %d\n곱하기 : %d\n나누기 몫 : %d\n",
				num1 + num2,
				num1 - num2,
				num1 * num2,
				num1 / num2);

	}

	/*
	 * 키보드로 가로, 세로 값을 입력 받아 사각형의 면적과 둘레를 계산하여 출력하세요.
	 * 공식) 면적 : 가로 * 세로
	 *       둘레 : (가로 + 세로) * 2
	 * 
	 * 가로 : 13.5
	 * 세로 : 41.7
	 * 면적 : 562.95
	 * 둘레 : 110.4
	 * */
	public void method4() {
		System.out.println("-------Q4----------");
		
		System.out.print("가로 : ");
		double width = Double.parseDouble(sc.nextLine());
		
		System.out.print("세로 : ");
		double height = Double.parseDouble(sc.nextLine());

		System.out.printf("면적 : %.2f\n둘레 : %.1f\n",
				width * height,
				(width + height) * 2);
	}

	/*
	 * 영어 문자열 값을 입력 받아 문자에서 첫번째, 두번째, 마지막 글자(.length())를 출력하세요.
	 * 
	 * 문자열을 입력하세요 : apple
	 * 첫번째 문자 : a
	 * 두번째 문자 : p
	 * 마지막 문자 : e
	 * */
	public void method5() {
		System.out.println("-------Q5----------");
		
		System.out.print("문자열을 입력하세요 : ");
		String str = sc.nextLine();
		
		System.out.printf("첫번째 문자 : %s\n두번째 문자 : %s\n마지막 문자 : %s\n",
				str.charAt(0), str.charAt(1), str.charAt(str.length()-1));
	}

	/*
	 * 문자 하나를 입력 받아 그 문자와 다음 문자의 유니코드를 출력하세요.
	 * 
	 * 문자 : A
	 * A unicode : 65
	 * B unicode : 66
	 * */
	public void method6() {
		System.out.println("-------Q6----------");

		System.out.print("문자 : ");
		char code = sc.nextLine().charAt(0);
		int numCode = code;
		
		System.out.printf("%c unicode : %d\n%c unicode : %d\n", 
				code, numCode,
				(int)numCode+1, numCode+1);
	}
	
	/*
	 * 국어, 영어, 수학 세 과목의 점수를 입력 받아 총점과 평균을 출력하세요.
	 * 
	 * 국어 : 75
	 * 영어 : 63
	 * 수학 : 80
	 * 총점 : 218
	 * 평균 : 72.67
	 * */
	public void method7() {
		System.out.println("-------Q7----------");
		
		System.out.print("국어 : ");
		int num1 = Integer.parseInt(sc.nextLine());
		
		System.out.print("영어 : ");
		int num2 = Integer.parseInt(sc.nextLine());
		
		System.out.print("수학 : ");
		int num3 = Integer.parseInt(sc.nextLine());
		
		System.out.printf("총점 : %d\n평균 : %.2f\n",
				num1+num2+num3,
				(double)(num1+num2+num3)/3);

	}

}
