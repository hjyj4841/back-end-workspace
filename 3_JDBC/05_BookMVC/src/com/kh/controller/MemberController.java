package com.kh.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.model.Member;

import config.DatabaseConnect;

public class MemberController {

	private DatabaseConnect dc = DatabaseConnect.getInstance();
	
	// 싱글톤 패턴
	private MemberController() {}
	private static MemberController instance;
	public static MemberController getInstance() {
		if(instance == null) instance = new MemberController();
		return instance;
	}
	
	// memberId로 중복 조회 true = 중복
	public boolean selectMemberId(String memberId) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("selectMemberId"));
		ps.setString(1, memberId);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			dc.close(rs, ps, conn);
			return true;
		}
		
		dc.close(rs, ps, conn);
		return false;
	}
	
	// 4. 회원가입
	public boolean registerMember(String memberId, String memberPwd, String memberName) throws SQLException {
		if(selectMemberId(memberId)) return false;
		
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("registerMember"));
		ps.setString(1, memberId);
		ps.setString(2, memberPwd);
		ps.setString(3, memberName);
		ps.executeUpdate();
		
		dc.close(ps, conn);
		return true;
	}
	
	// 5. 로그인
	public Member login(String memberId, String memberPwd) throws SQLException {
		Connection conn = dc.connect();
		PreparedStatement ps = conn.prepareStatement(dc.getProperties().getProperty("login"));
		ps.setString(1, memberId);
		ps.setString(2, memberPwd);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			Member member = new Member(rs.getInt("MEMBER_NO"), rs.getString("MEMBER_ID"), 
					rs.getString("MEMBER_PWD"), rs.getString("MEMBER_NAME"));
			
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
