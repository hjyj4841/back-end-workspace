package com.semi.youtube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.youtube.model.vo.Member;
import com.semi.youtube.model.vo.Paging;
import com.semi.youtube.model.vo.Subscribe;
import com.semi.youtube.model.vo.Video;
import com.semi.youtube.model.vo.VideoLike;
import com.semi.youtube.service.VideoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	@Autowired
	private VideoService video;
	
	// 비디오 전체 목록 보기
	@GetMapping("/")
	public String index(Model model, Paging paging) {
		model.addAttribute("list", video.allVideo(paging));
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<Video> list(Paging paging){
		return video.allVideo(paging);
	}
	
	// 비디오 1개 보여주기
	// 좋아요 관련 정보 가져오기
	// 구독자수, 구독 관련 정보 가져오기
	@GetMapping("/{videoCode}")
	public String detail(@PathVariable("videoCode") int videoCode, Model model, Paging paging) {
		
		Video data = video.detail(videoCode);
		
		model.addAttribute("video", data);
		model.addAttribute("list", video.allVideo(paging));
		model.addAttribute("count", video.count(data.getChannel().getChannelCode()));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Member member = (Member) authentication.getPrincipal();
		
		VideoLike like = null;
		Subscribe sub = null;
		
		if(member != null) {
			like = video.checkLike(VideoLike.builder()
					.id(member.getId())
					.videoCode(videoCode)
					.build());
			
			sub = video.check(Subscribe.builder()
					.id(member.getId())
					.channelCode(data.getChannel().getChannelCode())
					.build());
		}
		
		model.addAttribute("like", like);
		model.addAttribute("sub", sub);
		
		return "detail";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
}
