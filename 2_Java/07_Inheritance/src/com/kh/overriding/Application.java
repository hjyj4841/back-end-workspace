package com.kh.overriding;

import com.kh.overriding.model.Customer;
import com.kh.overriding.model.VIPCustomer;

public class Application {

	public static void main(String[] args) {
		
		Customer customer1 = new Customer("김경주");
		Customer customer2 = new Customer("김경주");
		
		System.out.println(customer1);
		System.out.println(customer2);
		
		System.out.println(customer1 == customer2); // false
		System.out.println(customer1.equals(customer2)); // false -> true
		
		// ~~님의 등급은 ~~이며, 지급해야 하는 금액은 ~원이며, 적립된 포인트는 ~~점 입니다.
		System.out.printf("%s님의 등급은 %s이며, 지급해야 하는 금액은 %d원이며, 적립된 포인트는 %d점 입니다.\n",
				customer1.getName(), customer1.getGrade(), customer1.calcPrice(10000), customer1.getBonusPoint());
		
		VIPCustomer customer3 = new VIPCustomer("김진주");
		VIPCustomer customer4 = new VIPCustomer("김진주");
		
		System.out.println(customer3.equals(customer4)); // true
		
		System.out.printf("%s님의 등급은 %s이며, 지급해야 하는 금액은 %d원이며, 적립된 포인트는 %d점 입니다.\n",
				customer3.getName(), customer3.getGrade(), customer3.calcPrice(10000), customer3.getBonusPoint());
	}
	
}
