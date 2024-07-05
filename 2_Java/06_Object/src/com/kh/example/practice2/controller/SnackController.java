package com.kh.example.practice2.controller;

import com.kh.example.practice2.model.Snack;

public class SnackController {
	private Snack s = new Snack();
	
	public String saveData(String kind, String name, String flavor, int numOf, int price) {
		s.setKind(kind);
		s.setName(name);
		s.setFlavor(flavor);
		s.setNumOf(numOf);
		s.setPrice(price);
		
		return "저장 완료되었습니다.";
	}
	
	public String confirmData() {
		return String.format("%s(%s - %s) %d개 %d원", 
				s.getKind(), s.getName(), s.getFlavor(), s.getNumOf(), s.getPrice());
	}
}
