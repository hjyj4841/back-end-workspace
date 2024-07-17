package com.kh.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @NoArgsConstructor @AllArgsConstructor
public class Member {

	private String id;
	private String password;
	private String name;

}
