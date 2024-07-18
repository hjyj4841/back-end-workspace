package com.kh.controller;

public class PublisherController {

	// 싱글톤 패턴
	private PublisherController() {}
	private static PublisherController instance;
	public static PublisherController getInstance() {
		if(instance == null) instance = new PublisherController();
		return instance;
	}
}
