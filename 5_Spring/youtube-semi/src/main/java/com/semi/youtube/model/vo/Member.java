package com.semi.youtube.model.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Spring Security에서 제공하는 UserDetails 인터페이스 상속

@Data @NoArgsConstructor 
@AllArgsConstructor @Builder
public class Member implements UserDetails{
	private String id;
	private String password;
	private String email;
	private String phone;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}
	
	@Override
	public String getUsername() {
		return id;
	}
}
