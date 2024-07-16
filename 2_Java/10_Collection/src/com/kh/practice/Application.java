package com.kh.practice;

import java.util.Scanner;

import com.kh.practice.controller.MusicController;
import com.kh.practice.model.Music;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();
	private Music music = new Music();

	public static void main(String[] args) {
		Application app = new Application();
		
		app.showMenu();
	}

	// 메인메뉴 보기
	public void showMenu() {
		try {
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
			}
		} catch(Exception e) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			showMenu();
		}
	}
	
	// 마지막 위치에 곡 추가
	public void addLastMusic() {
		System.out.println("****** 마지막 위치에 곡 추가 ******");
		System.out.print("곡 명 : ");
		music.setMusicName(sc.nextLine());
		
		System.out.print("가수 명 : ");
		music.setArtistName(sc.nextLine());
		
		System.out.println(mc.addLastMusic(music));
	}
	
	// 첫 위치에 곡 추가
	public void addFirstMusic() {
		System.out.println("****** 첫 위치에 곡 추가 ******");
		System.out.print("곡 명 : ");
		music.setMusicName(sc.nextLine());
		
		System.out.print("가수 명 : ");
		music.setArtistName(sc.nextLine());
		
		System.out.println(mc.addFirstMusic(music));
	}
	
	// 전체 곡 목록 출력
	public void showAllMusicList() {
		System.out.println("****** 전체 곡 목록 출력 ******");
		
		for(Music m : mc.showAllMusicList()) {
			System.out.println(m);
		}
	}
	
	// 특정 곡 검색
	public void searchMusic() {
		System.out.println("****** 특정 곡 검색 ******");
		System.out.print("검색할 곡 명 : ");
		
		for(Music m : mc.searchMusic(sc.nextLine())) {
			System.out.println(m + "을 검색했습니다.");
		}
	}
	
	// 특정 곡 삭제
	public void deleteMusic() {
		System.out.println("****** 특정 곡 삭제 ******");
		System.out.print("삭제할 곡 명 : ");
		Music m = mc.deleteMusic(sc.nextLine());
		
		if(m != null) {
			System.out.println(m + "을 삭제 했습니다.");
		}else {
			System.out.println("삭제할 곡이 없습니다.");
		}
		
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
		
		Music m = mc.updateMusic(findMusicName, music);
		
		if(m != null) {
			System.out.println(m + "의 값이 변경 되었습니다.");
		}else {
			System.out.println("수정할 곡을 찾지 못했습니다.");
		}
		
	}
	
	// 가수 명 내림차순 정렬
	public void artistNameSort() {
		System.out.println("***** 가수 명 내림차순 정렬 ******");
		for(Music m : mc.artistNameSort()) {
			System.out.println(m);
		}
	}
	
	// 곡 명 오름차순 정렬
	public void musicNameSort() {
		System.out.println("****** 곡 명 오름차순 정렬 ******");
		for(Music m : mc.musicNameSort()) {
			System.out.println(m);
		}
	}
}
