package com.semi.youtube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.youtube.model.vo.Video;

import mapper.VideoMapper;

@Service
public class VideoService {

	@Autowired
	private VideoMapper video;
	
	public List<Video> allVideo(){
		return video.allVideo();
	}
	
}
