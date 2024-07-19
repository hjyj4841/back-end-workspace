package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.model.vo.Member;

import config.DatabaseConnect;

public class MemberDAO {
	
	DatabaseConnect dc = new DatabaseConnect();
	
	// 4. 회원가입
	public void registerMember(String memberId, String memberPwd, String memberName) throws SQLException {		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("registerMember"));
		ps.setString(1, memberId);
		ps.setString(2, memberPwd);
		ps.setString(3, memberName);
		ps.executeUpdate();
		
		dc.close(ps, conn);
	}
	
	// 5. 로그인
	public Member login(String memberId, String memberPwd) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("login"));
		ps.setString(1, memberId);
		ps.setString(2, memberPwd);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Member member = new Member(
					rs.getInt("MEMBER_NO"), 
					memberId, 
					memberPwd, 
					rs.getString("MEMBER_NAME"),
					rs.getString("STATUS").charAt(0),
					rs.getDate("ENROLL_DATE"));
			
			dc.close(rs, ps, conn);
			return member;
		}
		
		dc.close(rs, ps, conn);
		return null;
	}
	
	// 4. 회원탈퇴
	public boolean deleteMember(int memberNo) throws SQLException {		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("deleteMember"));
		ps.setInt(1, memberNo);
		
		if(ps.executeUpdate() == 0) {
			dc.close(ps, conn);
			return false;
		}
		
		dc.close(ps, conn);
		return true;
	}
	
}
