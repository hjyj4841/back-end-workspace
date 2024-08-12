package com.semi.youtube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.youtube.model.vo.Subscribe;
import com.semi.youtube.model.vo.Video;
import com.semi.youtube.model.vo.VideoLike;

import mapper.SubscribeMapper;
import mapper.VideoLikeMapper;
import mapper.VideoMapper;

@Service
public class VideoService {

	@Autowired
	private VideoMapper video;
	
	@Autowired
	private VideoLikeMapper like;
	
	@Autowired
	private SubscribeMapper sub;
	
	public List<Video> allVideo(){
		return video.allVideo();
	}
	
	public Video detail(int videoCode) {
		return video.detail(videoCode);
	}
	
	public void like(VideoLike vo) {
		like.like(vo);
	}
	
	public VideoLike checkLike(VideoLike vo) {
		return like.check(vo);
	}
	
	public void unlike(int code) {
		like.unlike(code);
	}
	
	public Subscribe check(Subscribe vo) {
		return sub.check(vo);
	}
	
	public int count(int code) {
		return sub.count(code);
	}
	
	public void subs(Subscribe vo) {
		sub.subs(vo);
	}
	
	public void cancle(int code) {
		sub.cancle(code);
	}
	
}
