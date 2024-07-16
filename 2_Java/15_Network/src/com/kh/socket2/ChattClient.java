package com.kh.socket2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*
 * 키보드로 입력받은 데이터를 읽어서 서버로 보낸다
 * 다시 서버가 보낸 메세지를 받아서 클라이언트 자신의 콘솔창에 출력한다
 * 
 * 클라이언트측 로직
 * 1. 소켓 생성
 * 2. 스트림
 */

public class ChattClient {

	public static void main(String[] args) {
		System.out.println("------Client------");
		
		try {
			Socket s = new Socket(InetAddress.getLocalHost().getHostAddress(), 60000);
			System.out.println("Client 소켓 생성");
			
			// 키보드로 입력받은 데이터를 읽어서 서버한테 보낸다.
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			
			// 다시 서버가 보낸 메세지를 받아서 클라이언트 자신의 콘솔창에 출력
			BufferedReader br2 = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			while(true) {
				String line = br.readLine();
				pw.println(line);
				
				String serverMsg = br2.readLine();
				System.out.println("내가 보낸 메세지 : " + serverMsg);
			}
			
		} catch (IOException e) {
			System.err.println("연결끊김");
		}

	}

}
