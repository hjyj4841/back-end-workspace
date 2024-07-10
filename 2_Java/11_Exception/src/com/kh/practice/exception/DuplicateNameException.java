package com.kh.practice.exception;

public class DuplicateNameException extends Exception{

	public DuplicateNameException() {
		this("중복된 사람이 있어서 에러 발생!");
	}

	public DuplicateNameException(String message) {
		super(message);
	}

}
