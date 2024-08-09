package com.kh.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ajax.model.vo.Member;

import mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper member;
	
	public boolean idCheck(String id) {
		if(member.idCheck(id) == null) return false; // 아이디 없음
		else return true; // 아이디 있음
	}
	
	public Member serial(Member m) {
		member.serial(m);
		return member.idCheck(m.getId());
	}
}
