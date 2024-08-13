package com.kh.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.security.model.vo.Member;

import mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	private BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	
	public void register(Member vo) {
		// System.out.println("암호화 전 : " + vo.getPassword());
		// System.out.println("암호화 후 : " + bcpe.encode(vo.getPassword()));
		
		// 암호화 된 비밀번호로 저장
		vo.setPassword(bcpe.encode(vo.getPassword()));
		vo.setRole("ROLE_ADMIN");
		
		mapper.register(vo);
	}
	
	public Member login(Member vo) {
//		System.out.println("사용자가 입력한 정보 : " + vo);
		Member member = mapper.login(vo.getId());
//		System.out.println("DB에 있는 사용자 정보 : " + member);
		
		if(member != null &&bcpe.matches(vo.getPassword(), member.getPassword())) {
//			System.out.println("비밀번호 일치한지 체크 : " + bcpe.matches(vo.getPassword(), member.getPassword()));
			return member;
		}
		
		return null;
	}
}
