package com.kh.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * VO(Value Object)
 * - 한 개 또는 그 이상의 속성들을 묶어서 특정 값을 나타내는 객체
 */

@AllArgsConstructor @NoArgsConstructor @Data
public class Book {
	private int bkNo;
	private String bkTitle;
	private String bkAuthor;
	private int bkPrice;
	private Publisher publisher;
	
}
