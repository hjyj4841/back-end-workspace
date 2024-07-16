package com.kh.step6;

public class MovieProcess {

	public static void main(String[] args) {
		MovieUser user = new MovieUser();
		
		Thread t1 = new Thread(user, "윤대훈");
		Thread t2 = new Thread(user, "이동엽");
		Thread t3 = new Thread(user, "이준용");
		
		t1.start();
		t2.start();
		t3.start();
	}

}
