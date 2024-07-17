package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnectionTest2 {
	
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/kh";
	public static final String USER = "root";
	public static final String PASSWORD = "qwer1234";

	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(DRIVER_NAME);
			// 2. 데이터베이스 연결
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3. PreparedStatement - INSRT
			String query = "INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO) VALUES(?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, 900);
			ps.setString(2, "이동엽");
			ps.setString(3, "111111-1111111");
			
			// 4. 쿼리문 실행
			System.out.println(ps.executeUpdate() + "명 추가!");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
