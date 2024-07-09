package com.kh.example.practice;

import java.util.HashSet;
import java.util.TreeSet;

public class Application {

	public static void main(String[] args) {

		HashSet<Integer> userNum = new HashSet<>();
		TreeSet<Integer> comNum = new TreeSet<>();
		
		int count = 0;
		int equalsNum = 0;
		
		while(comNum.size() < 6) {
			int random = (int)(Math.random() * 45 + 1);
			if(!comNum.contains(random)) comNum.add(random);
		}
		while(true) {
			count++;
			while(userNum.size() < 6) {
				int random = (int)(Math.random() * 45 + 1);
				if(!userNum.contains(random)) userNum.add(random);
			}
			System.out.println("로또 번호 : " + comNum);
			System.out.println("내 번호 : " + userNum);
			
			for(int i : comNum) {
				if(userNum.contains(i)) {
					equalsNum++;
				}
			}
			if(equalsNum == 6) break;
			equalsNum = 0;
			userNum.clear();
		}
		System.out.println("횟수 : " + count);
	}

}
