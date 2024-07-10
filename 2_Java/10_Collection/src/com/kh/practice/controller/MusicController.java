package com.kh.practice.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kh.practice.model.Music;

public class MusicController {
	
	private ArrayList<Music> music = new ArrayList<Music>();
	private ArrayList<String> arr = new ArrayList<String>();
	List<Music> musicList = new ArrayList<>();
	
	// 마지막 위치에 곡 추가
	public String addLastMusic(String musicName, String artistName) {
		music.add(new Music(musicName, artistName));
		return "추가 성공";
	}
	
	// 첫 위치에 곡 추가
	public String addFirstMusic(String musicName, String artistName) {
		music.add(0, new Music(musicName, artistName));
		return "추가 성공";
	}
	
	// 전체 곡 목록 출력
	public ArrayList<String> showAllMusicList() {
		arr.clear();
		musicList.clear();
		
		for(Music m : music) {
			arr.add(String.format("%s - %s", 
					m.getArtistName(), 
					m.getMusicName()));
		}
		return arr;
	}
	
	// 특정 곡 검색
	public ArrayList<String> searchMusic(String musicName) {
		arr.clear();
		musicList.clear();
		
		for(Music m : music) {
			if(m.getMusicName().contains(musicName)) {
				arr.add(String.format("%s - %s을 검색 했습니다.", 
					m.getArtistName(), 
					m.getMusicName()));
			}
		}
		return arr;
	}
	
	// 특정 곡 삭제
	public String deleteMusic(String musicName) {
		for(int i = 0; i < music.size(); i++) {
			if(music.get(i).getMusicName().equals(musicName)) {
				String str = String.format("%s - %s을 삭제 했습니다.", 
						music.get(i).getArtistName(),
						music.get(i).getMusicName());
				music.remove(i);
				
				return str;
			}
		}
		return "삭제할 곡이 없습니다.";
	}
	
	// 특정 곡 수정
	public String updateMusic(String findMusicName, String updateMusicName, String updateArtistName) {
		for(int i = 0; i < music.size(); i++) {
			if(music.get(i).getMusicName().equals(findMusicName)) {
				String str = String.format("%s - %s의 값이 변경 되었습니다.", 
						music.get(i).getArtistName(),
						music.get(i).getMusicName());
				music.set(i, new Music(updateMusicName, updateArtistName));
				return str;
			}
		}
		
		return "수정할 곡을 찾지 못했습니다.";
	}
	
	// 가수 명 내림차순 정렬
	public ArrayList<String> artistNameSort() {
		arr.clear();
		musicList.clear();
		
		musicList.addAll(music);
		
		Collections.sort(musicList);
		
		for(Music m : musicList) {
			arr.add(String.format("%s - %s", m.getArtistName(), m.getMusicName()));
		}
		
		return arr;
		
	}
	
	// 곡 명 오름차순 정렬
	public ArrayList<String> musicNameSort() {
		arr.clear();
		musicList.clear();
		
		musicList.addAll(music);
		
		Collections.sort(musicList, musicNameComparator);
		
		for(Music m : musicList) {
			arr.add(String.format("%s - %s", m.getArtistName(), m.getMusicName()));
		}
		
		return arr;
	}
	
	private Comparator<Music> musicNameComparator = new Comparator<Music>() {
		
		@Override
		public int compare(Music o1, Music o2) {
			return o1.getMusicName().compareTo(o2.getMusicName());
			
		}
	};
}
