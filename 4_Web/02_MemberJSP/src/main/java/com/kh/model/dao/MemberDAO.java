package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Member;

public class MemberDAO {
	
	public MemberDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection databaseConnect() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "qwer1234");
	}
	
	public void close(Connection conn, PreparedStatement ps) throws SQLException {
		conn.close();
		ps.close();
	}
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
		rs.close();
		close(conn, ps);
	}
	
	// 회원가입 - member 스키마의 member 테이블
	public void registerMember(String id, String password, String name) throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO MEMBER VALUES(?, ?, ?)");
		ps.setString(1, id);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.executeUpdate();
		
		close(conn, ps);
	}
	
	public ArrayList<Member> viewMember() throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		while(rs.next()) {
			list.add(new Member(rs.getString("id"), rs.getString("password"), rs.getString("name")));
		}
		close(conn, ps, rs);
		return list;
	}
	
	public Member searchMember(String id) throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();

		Member member = new Member(rs.getString("id"), rs.getString("password"), rs.getString("name"));
		close(conn, ps, rs);
				
		return member;
	}
	
}
