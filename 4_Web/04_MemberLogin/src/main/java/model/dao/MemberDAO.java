package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Member;

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
	
	// DAO 개발할 때 중요한 건
	// 매개변수(파라미터) 뭘 가지고 와야 되는지, 리턴타입 결과 출력이 어떤게 필요한지
	
	// 회원가입
	public void registerMember(Member member) throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO MEMBER VALUES(?, ?, ?)");
		ps.setString(1, member.getId());
		ps.setString(2, member.getPassword());
		ps.setString(3, member.getName());
		ps.executeUpdate();
		
		close(conn, ps);
	}
	
	public Member loginMember(String id, String password) throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ? AND PASSWORD = ?");
		ps.setString(1, id);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
	
		Member m = null;
		
		if(rs.next()) {
			m = new Member(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("NAME"));
		}
		close(conn, ps, rs);
		return m;
	}
	
	public Member searchMember(String id) throws SQLException {
		Connection conn = databaseConnect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		Member m = null;
		
		if(rs.next()) {
			m = new Member(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("NAME"));
		}
		
		close(conn, ps, rs);
		return m;
	}
	
	public ArrayList<Member> allSearchMember() throws SQLException {
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
	
}
