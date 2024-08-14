package com.semi.youtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.semi.youtube.model.vo.Member;

import mapper.MemberMapper;

@Service
public class MemberService {
 
	@Autowired
	private MemberMapper member;
	
	private BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	
	public boolean check(String id) {
		Member vo = member.check(id);
		if(vo != null) return true;
		return false;
	}
	
	public Member login(Member vo) {
		return member.login(vo);
	}
	
	public void signup(Member vo) {
		vo.setPassword(bcpe.encode(vo.getPassword()));
		member.signup(vo);
	}
	
}
