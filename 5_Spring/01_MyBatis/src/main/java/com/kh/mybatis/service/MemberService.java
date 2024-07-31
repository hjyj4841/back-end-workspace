package com.kh.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mybatis.model.vo.Member;

import mapper.MemberMapper;

@Service
public class MemberService {

	@Autowired
	private MemberMapper mapper;
		
	public void register(Member member) {
		mapper.register(member);
	}
	
	public List<Member> allMember() {
		return mapper.allMember();
	}
	
	public Member login(Member member) {
		return mapper.login(member);
	}
	
	public void update(Member member) {	
		mapper.update(member);
	}
	
}
