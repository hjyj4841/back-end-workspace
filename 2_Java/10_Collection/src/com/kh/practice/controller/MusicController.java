package com.kh.practice.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kh.practice.compare.ArtistDesc;
import com.kh.practice.compare.MusicAsc;
import com.kh.practice.model.Music;

public class MusicController {
	
	private ArrayList<Music> music = new ArrayList<Music>();
	
	// 마지막 위치에 곡 추가
	public String addLastMusic(Music m) {
		if(!m.getArtistName().equals("") && !m.getMusicName().equals("")) {
			music.add(new Music(m.getMusicName(), m.getArtistName()));
			return "추가 성공";
		}
		return "추가 실패";
	}
	
	// 첫 위치에 곡 추가
	public String addFirstMusic(Music m) {
		if(!m.getArtistName().equals("") && !m.getMusicName().equals("")) {
			music.add(0, new Music(m.getMusicName(), m.getArtistName()));
			return "추가 성공";
		}
		return "추가 실패";
	}
	
	// 전체 곡 목록 출력
	public ArrayList<Music> showAllMusicList() {
		return music;
	}
	
	// 특정 곡 검색
	public ArrayList<Music> searchMusic(String musicName) {
		ArrayList<Music> arr = new ArrayList<Music>();
		
		for(Music m : music) {
			if(m.getMusicName().contains(musicName)) {
				arr.add(m);
			}
		}
		return arr;
	}
	
	// 특정 곡 삭제
	public Music deleteMusic(String musicName) {
		for(Music m : music) {
			if(m.getMusicName().equals(musicName)) {
				return music.remove(music.indexOf(m));
			}
		}
		return null;
	}
	
	// 특정 곡 수정
	public Music updateMusic(String findMusicName, Music m) {
		for(Music list : music) {
			if(list.getMusicName().equals(findMusicName)) {
				return music.set(music.indexOf(list), m);
			}
		}
		return null;
	}
	
	// 가수 명 내림차순 정렬
	public ArrayList<Music> artistNameSort() {
		ArrayList<Music> cloneMusic = (ArrayList<Music>) music.clone();
		Collections.sort(cloneMusic, new ArtistDesc());
		
		return cloneMusic;
	}
	
	// 곡 명 오름차순 정렬
	public ArrayList<Music> musicNameSort() {
		ArrayList<Music> cloneMusic = (ArrayList<Music>) music.clone();
		Collections.sort(cloneMusic, new MusicAsc());
		
		return cloneMusic;
	}
}
