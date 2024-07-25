package com.kh.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Rent {
	private int rentNo;
	private int rentMemNo;
	private Book book;
	private Date rentalDate;
}
