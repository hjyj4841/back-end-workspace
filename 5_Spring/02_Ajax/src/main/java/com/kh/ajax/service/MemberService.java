package com.kh.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper member;
	
	public boolean idCheck(String id) {
		if(member.idCheck(id) == null) return false; // 아이디 없음
		else return true; // 아이디 있음
	}
}
