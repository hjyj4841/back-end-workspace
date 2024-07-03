package com.kh.step5;

import com.kh.step5.model.Book;

public class Application {

	public static void main(String[] args) {
		// 생성자 방식으로 값을 담아냄
		Book book1 = new Book("THE MONEY BOOK", 22000, 0.1, "토스");
		System.out.println(book1);
		
		// setter 방식으로 값을 담아냄
		Book book2 = new Book();
		book2.setTitle("허송세월");
		book2.setAuthor("김훈");
		book2.setPrice(18000);
		book2.setDiscountRate(0.2);
		
		System.out.println(book2);
		
		System.out.println(book2.getTitle());
		System.out.println(book1.getTitle());
		
		// 허송세월의 원래 가격은 18000원, 할인된 가격은 14400원
		System.out.printf("%s의 원래 가격은 %d원, 할인된 가격은 %d원\n", 
				book2.getTitle(), book2.getPrice(), 
				(int)(book2.getPrice() - book2.getPrice() * book2.getDiscountRate()));
		// THE MONEY BOOK의 원래 가격은 22000원, 할인된 가격은 19800원
		System.out.printf("%s의 원래 가격은 %d원, 할인된 가격은 %d원\n",
				book1.getTitle(), book1.getPrice(), 
				(int)(book1.getPrice() - book1.getPrice() * book1.getDiscountRate()));
		
	}

}
