package com.kh._abstract.step2;

public abstract class Pizza {
	
	protected int price;
	protected String brand;
	
	public Pizza(int price, String brand) {
		this.price = price;
		this.brand = brand;
	}
	
	// 피자 만드는 과정
	public void makePizza() {
		info();
		System.out.println("피자 반죽과 함께 도우를 빚다.");
		topping();
		System.out.println("피자를 180도에서 10분간 구운다.");
		System.out.println("피자를 8등분으로 자른다.");
		System.out.println("피자를 포장한다.");
		System.out.println();
	}
	
	public abstract void info();
	public abstract void topping();
}
