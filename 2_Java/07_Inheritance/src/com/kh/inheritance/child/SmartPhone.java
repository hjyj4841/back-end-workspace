package com.kh.inheritance.child;

import com.kh.inheritance.parent.Product;

public class SmartPhone extends Product {

	private String agency; // 통신사

	public SmartPhone() {
	}

	public SmartPhone(String brand, String pCode, String name, int price, String agency) {
		super(brand, pCode, name, price);
		this.agency = agency;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	@Override
	public String toString() {
		return super.toString() + "SmartPhone [agency=" + agency + "]";
	}
	
	
}
