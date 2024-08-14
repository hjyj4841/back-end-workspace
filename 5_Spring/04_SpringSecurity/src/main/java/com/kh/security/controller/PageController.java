package com.kh.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.security.model.vo.Member;

@Controller
public class PageController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/member")
	public String member() {
		return "member";
	}
	
	@GetMapping("/admin")
	public String admin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null && authentication.isAuthenticated()) {
			Member member = (Member) authentication.getPrincipal();
			if(member.getRole().equals("ROLE_ADMIN")) {
				return "admin";
			}
		}
		
		return "redirect:/";
	}
}
