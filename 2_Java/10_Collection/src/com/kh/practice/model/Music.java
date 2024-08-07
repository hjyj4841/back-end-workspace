package com.kh.practice.model;

public class Music {
	private String musicName;
	private String artistName;
	
	public Music() {
	}
	public Music(String musicName, String artistName) {
		this.musicName = musicName;
		this.artistName = artistName;
	}
	
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	@Override
	public String toString() {
		return artistName + " - " + musicName;
	}
	
}
