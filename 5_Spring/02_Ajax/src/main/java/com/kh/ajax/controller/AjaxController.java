package com.kh.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.ajax.model.vo.Member;
import com.kh.ajax.service.MemberService;

@Controller
public class AjaxController {
	
	private int count = 0;
	
	@Autowired
	private MemberService member;
	
	@ResponseBody
	@GetMapping("/count")
	public int count() {
		System.out.println("ajax로 요청이 들어옴!");
		return ++count;
	}
	
	@ResponseBody
	@GetMapping("/encoding")
	public String nickName(String nick) {
		return nick;
	}
	
	@ResponseBody
	@PostMapping("/check")
	public boolean idCheck(String id) {
		return member.idCheck(id);
	}
	
	@ResponseBody
	@PostMapping("/serial")
	public Member serial(Member m) {
		return member.serial(m);
	}
}
