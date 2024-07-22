package com.kh.controller;

import java.sql.SQLException;

import com.kh.model.dao.MemberDAO;
import com.kh.model.dao.RentDAO;
import com.kh.model.vo.Member;

public class MemberController {

	private MemberDAO member = new MemberDAO();
	private RentDAO rent = new RentDAO();
	
	// 4. 회원가입
	public boolean registerMember(String memberId, String memberPwd, String memberName){
		try {
			member.registerMember(memberId, memberPwd, memberName);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// 5. 로그인
	public Member login(String memberId, String memberPwd) {
		try {
			Member m = member.login(memberId, memberPwd);
			if(m.getStatus() == 'N') return m;
		} catch (SQLException | NullPointerException e) {
			return null;
		}
		return null;
	}
	
	// 5. 회원탈퇴
	public boolean deleteMember(int memberNo) {
		try {
			if(rent.printRentBook(memberNo).size() > 0) return false;
			// 만약 회원 탈퇴시 대여중인 책들 모두 기록 삭제 방식으로 할 시 -> DELETE 조건 CASCADE
			if(member.deleteMember(memberNo) == 1) return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
		
	}
}
