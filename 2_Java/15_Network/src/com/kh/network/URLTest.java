package com.kh.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL(Uniform Resource Locator)
 * - 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소
 * 
 * https://www.youtube.com/results?search_query=여행
 * 프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링
 *  - 프로토콜 : 자원에 접근하기 위해 서버와 통신하는데 사용되는 통신규약
 *  - 호스트명 : 자원을 제공하는 서버 이름
 *  - 포트번호 : 통신에 사용되는 서버의 포트번호
 *  - 경로명 : 접근하려는 자원이 저장된 서버상 위치
 *  - 쿼리(query) : ? 이후의 부분
 */

public class URLTest {

	public void method1() {
		try {
			URL url = new URL("https://www.youtube.com/results?search_query=여행");
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트명 : " + url.getHost());
			System.out.println("포트번호 : " + url.getDefaultPort());
			System.out.println("경로 : " + url.getPath());
			System.out.println("쿼리 : " + url.getQuery());
			System.out.println("경로 + 쿼리 : " + url.getFile());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void method2() {
		try {
			URL url = new URL("https://khedu.co.kr");
			BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line = "";
			while((line = input.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		URLTest u  = new URLTest();
//		u.method1();
		u.method2();
	}

}
