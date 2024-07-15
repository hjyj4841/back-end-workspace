package com.kh.step2;

public class Application {

	public static void main(String[] args) {
		
		// 1. Thread 생성
		GoThread go = new GoThread();
		ComeThread come = new ComeThread();
		
		Thread tGo = new Thread(go, "GoThread");
		Thread tCome = new Thread(come, "ComeThread");
		
		// 2. start 호출
		tGo.start();
		tCome.start();
		
	}

}
