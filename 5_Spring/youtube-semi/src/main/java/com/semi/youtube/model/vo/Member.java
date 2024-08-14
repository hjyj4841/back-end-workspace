package com.semi.youtube.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
@AllArgsConstructor @Builder
public class Member {
	private String id;
	private String password;
	private String email;
	private String phone;
}
