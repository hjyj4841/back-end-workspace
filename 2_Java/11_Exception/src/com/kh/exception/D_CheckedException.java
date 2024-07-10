package com.kh.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class D_CheckedException {

	/*
	 * CheckedException
	 * - Exception을 상속하고 있는 예외들을 CheckedException이라 한다.
	 * - 컴파일 시 예외 처리 코드가 있는지 검사하는 예외
	 * - 예외 처리(try~catch, throws)가 되어 있지 않으면 컴파일 에러를 발생
	 * - 조건문이나 소스코드 수정으로는 해결이 되지 않는다
	 * - 주로 외부에 매개체와 입출력이 일어날 때 발생한다.
	 */
	
	// 여기서 다룬 코드는 뒤에 (IO) 강의 때 나옴 -> 파일 입출력
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("a.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
