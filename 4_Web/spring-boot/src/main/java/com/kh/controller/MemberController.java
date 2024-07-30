package com.kh.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.model.vo.Member;
import com.kh.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register") 
	public String register(Member vo) {
		try {
			service.register(vo);
		} catch (SQLException e) {}
		return "redirect:/";
	}
	
	@GetMapping("login")
	public void login() {}
	
	@PostMapping("login")
	public String login(Member vo, HttpServletRequest request) {
		Member member = null;
		try {
			member = service.login(vo);
		} catch (SQLException e) {}
		
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		
		return "redirect:/";
	}
	
	@GetMapping("search")
	public void search() {}
	
	@GetMapping("find")
	public String find(@RequestParam("id") String id, Model model) {
		try {
			Member member = service.search(id);
			if (member != null) {
				model.addAttribute("member", member);
				return "search_ok";
			}
		} catch (Exception e) {}
		return "redirect:/fail"; 
	}
	
	@GetMapping("fail")
	public String fail() {
		return "search_fail";
	}
	
	@GetMapping("allMember")
	public String allMember(Model model) {
		try {
			List<Member> list = service.allMember();
			model.addAttribute("list", list);
		} catch (Exception e) {}
		
		return "allMember";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		if(member != null) session.invalidate();
		
		return "redirect:/";
	}
}
