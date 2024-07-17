package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.ServerInfo;

public class DBConnectionTest3 {
	
	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
			
			// 2. DB 연결
			Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
			
			// 3. PrepearedStatement - 쿼리 : UPDATE (EMP_ID 선택해서 EMP_NAME 변경)
			String query = "UPDATE EMPLOYEE SET EMP_NAME = ? WHERE EMP_ID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, "홍길동");
			ps.setInt(2, 900);
			
			// 4. 쿼리문 실행
			System.out.println(ps.executeUpdate() + "명 업데이트 성공");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
