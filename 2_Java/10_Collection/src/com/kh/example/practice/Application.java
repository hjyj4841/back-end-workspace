package com.kh.example.practice;

import java.util.HashSet;
import java.util.TreeSet;

public class Application {

	public static void main(String[] args) {

		HashSet<Integer> userNum = new HashSet<>();
		TreeSet<Integer> comNum = new TreeSet<>();
		
		int count = 0;
		int equalsNum = 0;
		int bonus;
		
		while(comNum.size() < 6) comNum.add((int)(Math.random() * 45 + 1));
		
		while(true) {
			bonus = (int)(Math.random() * 45 + 1);
			if(!comNum.contains(bonus)) break;
		}
		
		while(true) {
			userNum.clear();
			equalsNum = 0;
			count++;
			
			while(userNum.size() < 6) userNum.add((int)(Math.random() * 45 + 1));
			
			System.out.println("로또 번호 : " + comNum + ", 보너스 번호 : " + bonus);
			System.out.println("내 번호 : " + userNum);
			
			for(int i : comNum) if(userNum.contains(i)) equalsNum++;
			
			switch(equalsNum) {
				case 6:
					System.out.println("1등 당첨! (횟수 : " + count + ")");
					return;
				case 5:
					if(userNum.contains(bonus)) {
						System.out.println("2등 당첨! (횟수 : " + count + ")");
						return;
					}
					System.out.println("3등 당첨! (횟수 : " + count + ")");
					return;
				case 4:
					System.out.println("4등 당첨! (횟수 : " + count + ")");
					return;
				case 3:
					System.out.println("5등 당첨! (횟수 : " + count + ")");
					return;
				default:	
			}
		}
	}
}
