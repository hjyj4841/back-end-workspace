package com.semi.youtube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.semi.youtube.service.VideoService;

@Controller
public class PageController {

	@Autowired
	private VideoService video;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("list", video.allVideo());
		return "index";
	}
	
}
