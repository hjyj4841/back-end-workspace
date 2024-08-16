package com.semi.youtube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.semi.youtube.model.vo.Member;

import mapper.MemberMapper;

// Spring Security에서 제공하는 UserDetailsService 인터페이스 상속

@Service
public class MemberService implements UserDetailsService{
 
	@Autowired
	private MemberMapper member;
	
	@Autowired
	private PasswordEncoder bcpe;
	
	public boolean check(String id) {
		Member vo = member.check(id);
		if(vo != null) return true;
		return false;
	}
	
	public void signup(Member vo) {
		vo.setPassword(bcpe.encode(vo.getPassword()));
		member.signup(vo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return member.check(username);
	}
	
}
