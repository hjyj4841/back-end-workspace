package com.kh.socket2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.kh.network.InetAddressTest;

/*
 * 클라이언트가 보낸 메세지를 받아서
 * 다시 클라이언트에게 받은 메세지를 보낸다
 * 
 * 서버측 로직
 * 1. ServerSocket 생성 port : 60000
 * 2. 클라이언트가 서버에 접속하면 accept로 받아서 Socket 하나 리턴
 * 3. 소켓으로부터 스트림 리턴
 */

public class ChattServer {
	
	public static void main(String[] args) {
		System.out.println("------Server------");
		
		try {
			ServerSocket server = new ServerSocket(60000);
			System.out.println("Server Ready..");
			
			Socket s = server.accept();
			System.out.println(s.getInetAddress() + " Client 소켓 접속");
			
			// 클라이언트가 보낸 메세지를 받는다
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// 다시 클라이언트에게 받은 메세지를 보낸다
			PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
			
			String line = "";
			while((line = br.readLine()) != null) {
				System.out.println("[" + s.getInetAddress() + "] : " + line); // 사용자가 입력한 값 출력
				pw.println(line);
			}
			
		} catch (IOException e) {
			System.err.println("연결끊김");
		}
		
		
	}

}
