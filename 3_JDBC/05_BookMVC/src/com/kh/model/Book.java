package com.kh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Book {
	private int bkNo;
	private String bkTitle;
	private String bkAuthor;
	private int bkPrice;
	private int bkPubNo;
	
	@Override
	public String toString() {
		return String.format("%d번 : %s (저자-%s) 가격 %d", bkNo, bkTitle, bkAuthor, bkPrice);
	}
}
