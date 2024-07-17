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
	private Connection conn;
	
	public void DatabaseConnect() {
		try {
			p.load(new FileInputStream("src/com/kh/config/member.properties"));
			
			Class.forName(ServerInfo.DRIVER_NAME);
			conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		ps.close();
		conn.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		rs.close();
		closeAll(ps, conn);
	}
	
		
	public boolean signUp(Member member) throws SQLException {
		DatabaseConnect();
		// 회원가입 기능 구현! 
		// -> 아이디가 기존에 있는지 체크 여부!
		PreparedStatement ps = conn.prepareStatement(p.getProperty("select"));
		ps.setString(1, member.getId());
		
		ResultSet rs = ps.executeQuery();
	
		if(rs.next()) {
			closeAll(rs, ps, conn);
			return false;
		}
		// -> member 테이블에 데이터 추가! 
		ps = conn.prepareStatement(p.getProperty("insert"));
		
		ps.setString(1, member.getId());
		ps.setString(2, member.getPassword());
		ps.setString(3, member.getName());
		
		ps.executeUpdate();
		
		closeAll(rs, ps, conn);
		return true;
	}
	
	public String login(Member member) throws SQLException {
		DatabaseConnect();

		PreparedStatement ps = conn.prepareStatement(p.getProperty("select"));
		ps.setString(1, member.getId());
		
		ResultSet rs = ps.executeQuery();
		// -> member 테이블에서 id와 password로 멤버 정보 하나 가져오기!
		if(rs.next()) {
			if(rs.getString("PASSWORD").equals(member.getPassword())) {
				String name = rs.getString("NAME");
				closeAll(rs, ps, conn);
				return name;
			}
		}
		closeAll(rs, ps, conn);
		return null;
	}
	
	public boolean changePassword(Member member, String newPassword) throws SQLException {
		DatabaseConnect();
		
		// 비밀번호 바꾸기 기능 구현!
		PreparedStatement ps = conn.prepareStatement(p.getProperty("select"));
		ps.setString(1, member.getId());
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		String name = rs.getString("NAME");
		
		// -> login 메서드 활용 후 사용자 이름이 null이 아니면 member 테이블에서 id로 새로운 패스워드로 변경
		if(rs.getString("PASSWORD").equals(member.getPassword())) {
			ps = conn.prepareStatement(p.getProperty("updatePwd"));
			ps.setString(1, newPassword);
			ps.setString(2, member.getId());
			
			ps.executeUpdate();
			
			closeAll(rs, ps, conn);
			return true;
		}
		
		closeAll(rs, ps, conn);
		return false;
	}
	
	public String changeName(Member member) throws SQLException {
		DatabaseConnect();
		// 이름 바꾸기 기능 구현!
				// -> member 테이블에서 id로 새로운 이름으로 변경 
		PreparedStatement ps = conn.prepareStatement(p.getProperty("updateName"));
		ps.setString(1, member.getName());
		ps.setString(2, member.getId());
		ps.executeUpdate();
		
		ps = conn.prepareStatement(p.getProperty("select"));
		ps.setString(1, member.getId());
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		String name = rs.getString("NAME");
		if(name != null) {
			closeAll(rs, ps, conn);
			return name;
		}
		
		return null;
	}
	
}









