package com.kh.step3;


public class BeepPrintTest {

	public static void main(String[] args) {

		Thread tBeep = new Thread(new BeepThread());
//		PrintThread pThread = new PrintThread();
		
		tBeep.start();
//		pThread.start();
		
		for(int i = 0; i < 5; i++) {
			System.out.println("띵~");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
