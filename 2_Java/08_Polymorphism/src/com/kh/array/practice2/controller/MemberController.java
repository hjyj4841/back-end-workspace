package com.kh.array.practice2.controller;

import com.kh.array.practice2.model.Member;

public class MemberController {

	private Member[] member = new Member[3];
	public int count = 0;
	/* 완전 자유 */
	
	// 새 회원 등록
	public void addMember(Member m) {
		member[count++] = new Member(m.getId(), m.getName(), m.getPwd(), m.getEmail(), m.getGender(), m.getAge());
	}

	// 회원 아이디 검색 -> 멤버 index를 아이디로 조회
	public int checkId(String id) {
		for(int i = 0; i < member.length; i++) {
			if(member[i] != null && member[i].getId().equals(id)) {
				return i; // 기존 멤버 배열에 아이디가 있는 경우
			}
		}
		return -1; // 아이디가 없는 경우
	}
	
	// 회원 정보 수정
	public void modifyMember(String id, String name, String email, String pwd) {
		// 멤버의 index 찾기
		int index = checkId(id);
		
		member[index].setId(id);
		member[index].setName(name);
		member[index].setEmail(email);
		member[index].setPwd(pwd);
	}
	
	// 회원 정보 출력
	public Member[] infoMember() {
		return member;
	}
}
