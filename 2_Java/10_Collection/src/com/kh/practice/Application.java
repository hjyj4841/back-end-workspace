package com.kh.practice;

import java.util.Scanner;

import com.kh.practice.controller.MusicController;
import com.kh.practice.model.Music;

public class Application {
	
	Scanner sc = new Scanner(System.in);
	MusicController mc = new MusicController();
	Music music = new Music();

	public static void main(String[] args) {
		Application a = new Application();
		
		a.showMenu();
	}

	// 메인메뉴 보기
	public void showMenu() {
		while(true) {
			System.out.println("===== 메인 메뉴 =====");
			System.out.println("1. 마지막 위치에 곡 추가");
			System.out.println("2. 첫 위치에 곡 추가");
			System.out.println("3. 전체 곡 목록 출력");
			System.out.println("4. 특정 곡 검색");
			System.out.println("5. 특정 곡 삭제");
			System.out.println("6. 특정 곡 수정");
			System.out.println("7. 가수 명 내림차순 정렬");
			System.out.println("8. 곡 명 오름차순 정렬");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 입력 : ");
			
			try {
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					addLastMusic();
					break;
				case 2:
					addFirstMusic();
					break;
				case 3:
					showAllMusicList();
					break;
				case 4:
					searchMusic();
					break;
				case 5:
					deleteMusic();
					break;
				case 6:
					updateMusic();
					break;
				case 7:
					artistNameSort();
					break;
				case 8:
					musicNameSort();
					break;
				case 9:
					System.out.println("종료");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	// 마지막 위치에 곡 추가
	public void addLastMusic() {
		System.out.println("****** 마지막 위치에 곡 추가 ******");
		System.out.print("곡 명 : ");
		music.setMusicName(sc.nextLine());
		
		System.out.print("가수 명 : ");
		music.setArtistName(sc.nextLine());
		
		System.out.println(mc.addLastMusic(music.getMusicName(), music.getArtistName()));
	}
	
	// 첫 위치에 곡 추가
	public void addFirstMusic() {
		System.out.println("****** 첫 위치에 곡 추가 ******");
		System.out.print("곡 명 : ");
		music.setMusicName(sc.nextLine());
		
		System.out.print("가수 명 : ");
		music.setArtistName(sc.nextLine());
		
		System.out.println(mc.addFirstMusic(music.getMusicName(), music.getArtistName()));
	}
	
	// 전체 곡 목록 출력
	public void showAllMusicList() {
		System.out.println("****** 전체 곡 목록 출력 ******");
		
		for(String str : mc.showAllMusicList()) {
			System.out.println(str);
		}
	}
	
	// 특정 곡 검색
	public void searchMusic() {
		System.out.println("****** 특정 곡 검색 ******");
		System.out.print("검색할 곡 명 : ");
		
		for(String str : mc.searchMusic(sc.nextLine())) {
			System.out.println(str);
		}
	}
	
	// 특정 곡 삭제
	public void deleteMusic() {
		System.out.println("****** 특정 곡 삭제 ******");
		System.out.print("삭제할 곡 명 : ");
		System.out.println(mc.deleteMusic(sc.nextLine()));
	}
	
	// 특정 곡 수정
	public void updateMusic() {
		System.out.println("****** 특정 곡 수정 ******");
		System.out.print("검색할 곡 명 : ");
		String findMusicName = sc.nextLine();
		
		System.out.print("수정할 곡 명 : ");
		music.setMusicName(sc.nextLine());
		
		System.out.print("수정할 가수 명 : ");
		music.setArtistName(sc.nextLine());
		
		System.out.println(mc.updateMusic(findMusicName, 
				music.getMusicName(), music.getArtistName()));
		
	}
	
	// 가수 명 내림차순 정렬
	public void artistNameSort() {
		System.out.println("***** 가수 명 내림차순 정렬 ******");
		for(String str : mc.artistNameSort()) {
			System.out.println(str);
		}
	}
	
	// 곡 명 오름차순 정렬
	public void musicNameSort() {
		System.out.println("****** 곡 명 오름차순 정렬 ******");
		for(String str : mc.musicNameSort()) {
			System.out.println(str);
		}
	}
	
}
