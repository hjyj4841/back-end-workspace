package com.kh.practice.model;

import java.util.Arrays;

public class Member {
	private String name;
	private int age;
	private int coupon;
	private Book[] bookList = new Book[2];
	
	public Member() {
	}
	public Member(String name, int age, int coupon, Book[] bookList) {
		this.name = name;
		this.age = age;
		this.coupon = coupon;
		this.bookList = bookList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public Book[] getBookList() {
		return bookList;
	}
	public void setBookList(Book[] bookList) {
		this.bookList = bookList;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", coupon=" + coupon + ", bookList="
				+ Arrays.toString(bookList) + "]";
	}
	
	
}
