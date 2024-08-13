package com.kh.security.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
@AllArgsConstructor @Builder
public class Member {
	private String id;
	private String password;
	private String name;
	private String role;
}
