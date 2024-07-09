package com.kh._abstract.step2;

public class PineApplePizza extends Pizza{

	public PineApplePizza(int price, String brand) {
		super(price, brand);
	}

	@Override
	public void info() {
		System.out.println(brand + "의 파인애플 피자 가격은 " + price +"원");
	}

	@Override
	public void topping() {
		System.out.println("토핑은 파인애플을 포함시킨다.");
	}
	

}
