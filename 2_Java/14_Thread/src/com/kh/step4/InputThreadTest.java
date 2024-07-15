package com.kh.step4;

import javax.swing.JOptionPane;

// 프로세스 - 두 스레드 간의 Communication은 프로세스의 자원으로 해야 한다.
public class InputThreadTest {
	
	boolean check = false;

	public static void main(String[] args) {
		
		InputThreadTest process = new InputThreadTest();
		
		CountThread count = new CountThread(process);
		InputThread input = new InputThread(process);
		
		count.start();
		input.start();
		
		
	}

}
