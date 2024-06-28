package com.kh.practice;

import java.util.Scanner;

public class OperatorPractice {
    
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		OperatorPractice op = new OperatorPractice();
		
		op.method1();
		op.method2();
		op.method3();
		op.method4();
		op.method5();
		op.method6();
		op.method7();
	}

	//모든 사람이 연필을 골고루 나눠가지려고 한다. 인원 수와 연필 개수를 입력 받고 
	//1인당 동일하게 나눠가진 연필 개수와 나눠주고 남은 연필의 개수를 출력하세요.
	public void method1() {
		System.out.println("---------Q1---------");
		
		System.out.printf("인원 수 : ");
		int person = Integer.parseInt(sc.nextLine());
		
		System.out.printf("연필 개수 : ");
		int pen = Integer.parseInt(sc.nextLine());
		
		int num1 = pen / person;
		int num2 = pen % person;
		
		System.out.println("동일하게 나눠가진 연필 개수 : " + num1);
		System.out.println("남은 연필 개수 : " + num2);
	}
	
	//입력 받은 숫자를 산술 연산자만 사용해서 십의 자리 이하는 버리는 코드를 작성하세요.
	//만약 432이라면 400, 111이라면 100이 출력됩니다.
	public void method2() {
		System.out.println("---------Q2---------");
		
		System.out.print("정수 입력 : ");
		int num = Integer.parseInt(sc.nextLine());
		
		int result = num - (num % 100);
		
		System.out.println(result);
	}

	//3개의 수를 입력 받아 입력 받은 수가 모두 같으면 true, 아니면 false를 출력하세요.
	public void method3() {
		System.out.println("---------Q3---------");
		
		System.out.print("첫번째 정수 : ");
		int num1 = Integer.parseInt(sc.nextLine());
		
		System.out.print("두번째 정수 : ");
		int num2 = Integer.parseInt(sc.nextLine());
		
		System.out.print("세번째 정수 : ");
		int num3 = Integer.parseInt(sc.nextLine());
		
		System.out.println((num1 == num2) && (num2 ==num3));

	}

	//입력 받은 하나의 정수가 짝수이면 "짝수다", 짝수가 아니면 "짝수가 아니다"를 출력하세요.
	public void method4() {
		System.out.println("---------Q4---------");
		System.out.print("정수 입력 : ");
		int num = Integer.parseInt(sc.nextLine());
		
		String result = num % 2 == 0 ? "짝수다" : "짝수가 아니다";
		
		System.out.println(result);
		
	}

	//주민번호(-포함)를 입력받아 남자인지 여자인지 구분하여 출력하세요
	public void method5() {
		System.out.println("---------Q5---------");
		
		System.out.print("주민번호 입력 (-포함) : ");
		char no = sc.nextLine().charAt(7);
		
		String gender = no == '1' ? "남자" : "여자";
		
		System.out.println(gender);
		
	}

	//나이를 입력 받아 어린이(13세 이하)인지, 청소년(13세 초과 ~ 19세 이하)인지, 성인(19세 초과)인지 출력하세요.
	public void method6() {
		System.out.println("---------Q6---------");
		
		System.out.print("나이 : ");
		int age = Integer.parseInt(sc.nextLine());
		
		String result = age <= 13 ? "어린이(13세 이하)" : age > 19 ? "성인(19세 초과)" : "청소년(13세 초과 ~ 19세 이하)";
		
		System.out.println(result);
		
	}

	//사과의 개수와 바구니의 크기(바구니 1개의 들어가는 사과의 개수)를 입력받아
	//필요한 바구니의 수를 출력하세요
	public void method7() {
		System.out.println("---------Q7---------");
		
		System.out.print("사과의 개수 : ");
		int apple = Integer.parseInt(sc.nextLine());
		
		System.out.print("바구니의 크기 : ");
		int bag = Integer.parseInt(sc.nextLine());
		
		int result = (apple / bag) + (apple % bag >= 1 ? 1 : 0);
		
		System.out.println("필요한 바구니의 수 : " + result);
	}
	
}