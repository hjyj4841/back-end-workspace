package com.kh.mybatis.service;

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
	
}
