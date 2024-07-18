package com.kh.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private String address;
	private String phone;
	private char status;
	private Date enrollDate;
	
	public Member(int memberNo, String memberId, String memberPwd, String memberName) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
	}
}