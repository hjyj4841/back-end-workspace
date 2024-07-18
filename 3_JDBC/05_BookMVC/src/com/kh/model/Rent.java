package com.kh.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class Rent {
	private int rentNo;
	private int rentMemNo;
	private int rentBookNo;
	private Date rentalDate;
}
