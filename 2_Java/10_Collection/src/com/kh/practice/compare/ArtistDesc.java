package com.kh.practice.compare;

import java.util.Comparator;

import com.kh.practice.model.Music;

public class ArtistDesc implements Comparator<Music>{

	@Override
	public int compare(Music o1, Music o2) {
		return o2.getArtistName().compareTo(o1.getArtistName());
	}

}
