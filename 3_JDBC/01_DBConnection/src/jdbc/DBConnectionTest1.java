package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DBConnectionTest1 {

	public static void main(String[] args) {
		
		// JDBC(Java Database Connectivity) 작업 4단계
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("1. 드라이버 로딩...!");
			
			// 2. 데이터베이스와 연결
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kh", "root", "qwer1234");
			System.out.println("2. 데이터베이스와 연결...!");
			
			// 3. Statement 객체 - 쿼리 : SELECT
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee");
			
			// 4. 쿼리문 실행
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String empId = rs.getString("emp_id");
				String empName = rs.getString("emp_name");
				int salary = rs.getInt("salary");
				float bonus = rs.getFloat("bonus");
				Date hireDate = rs.getDate("hire_date");
				char entYn = rs.getString("ent_yn").charAt(0);
				
				System.out.println(empId + " / " + empName + " / " + salary + " / " + bonus + " / " + hireDate + " / " + entYn);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
