package com.kh.step1.model;
/*
 * MVC패턴
 * 	Model - DB상에서는 Table(Model 하나에 Table 하나)
 * 	View - FontEnd 영역, 화면 영역
 * 	Controller - 기능 개발
 */
public class Tv {
	
	// Tv 기능이 더 있지만 당장 프로그래밍 하는데 필요한 속성과 기능만
	// 선택하여 작성하는 것 = *추상화*
	
	// 속성(property) : 멤버변수(member variable), 특성(attribute), 필드(field)
	public boolean power; // 전원상태(on/off)
	public int channel; // 채널
	
	// 기능(function) : 메서드(method), 함수(function)
	public void power() {
		power = !power; // Tv를 끄거나 키는 기능
	}
	
	public void channelUp() {
		++channel; // 채널 번호를 높이는 기능
	}
	
	public void channelDown() {
		--channel; // 채널 번호를 내리는 기능
	}
	
}
