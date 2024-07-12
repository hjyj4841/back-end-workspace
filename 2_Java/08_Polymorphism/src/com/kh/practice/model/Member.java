package com.kh.practice.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Member {
	private String name;
	private int age;
	private int coupon;
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public Member() {
	}
	public Member(String name, int age, int coupon, ArrayList<Book> bookList) {
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
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}
	
	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", coupon=" + coupon + ", bookList=" + bookList + "]";
	}
	
}
