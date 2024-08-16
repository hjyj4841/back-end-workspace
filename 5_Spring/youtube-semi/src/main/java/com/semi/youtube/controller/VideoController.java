package com.semi.youtube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.youtube.model.vo.Member;
import com.semi.youtube.model.vo.Subscribe;
import com.semi.youtube.model.vo.VideoLike;
import com.semi.youtube.service.VideoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class VideoController {
	
	@Autowired
	private VideoService video;
	
	// 좋아요
	@ResponseBody
	@PostMapping("/like")
	public void like(int code) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		
		VideoLike data = VideoLike.builder()
				.id(member.getId())
				.videoCode(code)
				.build();
		video.like(data);
	}
	
	// 좋아요 취소
	@ResponseBody
	@PostMapping("/unlike")
	public void unlike(int code){
		video.unlike(code);
	}
	
	// 구독
	public void subs(Subscribe vo) {
//		sub.subs(vo);
	}
	
	// 구독 취소
	public void cancle(int code) {
//		sub.cancle(code);
	}
	
	// 댓글 작성
	
	// 댓글 수정
	
	// 댓글 삭제
	
}
