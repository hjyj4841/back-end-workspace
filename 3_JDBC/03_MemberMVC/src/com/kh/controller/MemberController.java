package com.kh.controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.config.ServerInfo;
import com.kh.model.Member;

public class MemberController {
	private Properties p = new Properties();	
	// 싱글톤 패턴(Singleton Pattern)
	// - 디자인 패턴 중 하나로 클래스가 최대 한번만 객체 생성되도록 하는 패턴
	
	// 1. 생성자는 private
	private MemberController() {
		
		try { // 생성자에서 예외처리는 try - catch 사용
			
			p.load(new FileInputStream("src/com/kh/config/member.properties"));
			
			// (1) 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 2. 유일한 객체를 담을 static 변수
	private static MemberController instance;
	
	// 3. 객체를 반환하는 static 메서드
	public static MemberController getInstance(){
		if(instance == null) instance = new MemberController();
		return instance;
	}
	
	// 데이터 베이스 연결할 메서드
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}
	
	// 자원 반납할 메서드 2개 (오버로딩)
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		close(ps, conn);
	}
	
	// member 아이디 중복 체크하는 메서드
	public boolean idCheck(String id) throws SQLException {
		Connection conn = connect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("idCheck"));
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		String checkId = null;
		if(rs.next()) checkId = rs.getString("ID");
		close(rs, ps, conn);
		
		if(checkId != null) return true;
		return false;
	}
	
	public boolean signUp(Member m) throws SQLException {
//		try - catch 방식
//		try {
//			Connection conn = connect();
//			PreparedStatement ps = conn.prepareStatement(p.getProperty("signUp"));
//			ps.setString(1, m.getId());
//			ps.setString(2, m.getPassword());
//			ps.setString(3, m.getName());
//			
//			ps.executeUpdate();
//			close(ps, conn);
//			return true;
//			
//		} catch (SQLException e) {
//			return false;
//		}
		
//		조건 거는 방식
		if(!idCheck(m.getId())) {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(p.getProperty("signUp"));
			ps.setString(1, m.getId());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getName());
			
			ps.executeUpdate();
			close(ps, conn);
			return true;
		}
		return false;
	}
	
	public String login(String id, String password) throws SQLException {
		
		Connection conn = connect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("login"));
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		String name = null;
		
		if(rs.next()) name = rs.getString("NAME");
		close(rs, ps, conn);
		
		return name;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) throws SQLException {
		if(login(id, oldPw) != null) {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(p.getProperty("changePassword"));
			ps.setString(1, newPw);
			ps.setString(2, id);
			ps.executeUpdate();
			
			close(ps, conn);
			return true;
		}
		return false;
	}
	
	public void changeName(String id, String newName) throws SQLException {
		Connection conn = connect();
		PreparedStatement ps = conn.prepareStatement(p.getProperty("changeName"));
		ps.setString(1, newName);
		ps.setString(2, id);
		ps.executeUpdate();
		close(ps, conn);
	}
	
}









