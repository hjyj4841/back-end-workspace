package com.kh.object.model;

import java.io.Serializable;

// 객체를 직렬화로 통과하려면 반드시 implements Serializable
public class Person implements Serializable {

	private String name;
	private String id;
	
	public Person() {
	}
	public Person(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}
}
