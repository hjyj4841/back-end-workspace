package com.kh.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.security.config.TokenProvider;
import com.kh.security.model.vo.Member;
import com.kh.security.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@PostMapping("/register")
	public String register(Member vo) {
		
		service.register(vo);
		
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public void login(Member vo) {
		Member member = service.login(vo);
		if(member != null) {
			// 로그인 성공 -> 세션에 값을 담기 (서버에 고객 정보 임시 저장)
			//			-> 토큰 생성해서 부여 (서버는 토큰 생성만, 가지고 있는건 클라이언트)
			String token = tokenProvider.create(member);
			System.out.println(token);
		}
	}
}
