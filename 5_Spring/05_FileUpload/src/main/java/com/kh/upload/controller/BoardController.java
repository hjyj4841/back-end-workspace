package com.kh.upload.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;
import com.kh.upload.service.BoardService;

@Controller
public class BoardController {
	
	private String path = "\\\\192.168.10.6\\vibe\\test\\";
	
	@Autowired
	private BoardService service;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		// 중복 방지를 위한 UUID 적용
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString() + "_" + file.getOriginalFilename();
		
//		File copyFile = new File("D:\\upload\\" + fileName);
		File copyFile = new File(path + fileName);
		
		file.transferTo(copyFile); // 업로드한 파일이 지정한 path 위치로 저장
		
		return fileName;
	}
	
	@PostMapping("/upload")
	public String upload(MultipartFile file) throws IllegalStateException, IOException{
		System.err.println("upload : " + file);
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 사이즈 : " + file.getSize());
		System.out.println("파일 파라미터명 : " + file.getName());
		
		if(!file.getOriginalFilename().equals("")) fileUpload(file);
		
		return "redirect:/";
	}
	
	@PostMapping("/multiUpload")
	public String multiUpload(List<MultipartFile> files) throws IllegalStateException, IOException {
		
		for(MultipartFile file : files) {
			if(!file.getOriginalFilename().equals("")) fileUpload(file);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		Paging paging = new Paging(); // 임시
		
		List<Board> list = service.showList(paging);
		
		for(Board b : list) {
			LocalDateTime date = b.getDate();
			Date formatDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
			b.setFormatDate(formatDate);
		}
		
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
	@PostMapping("/write")
	public String write(Board board) throws IllegalStateException, IOException {
		if(!board.getFile().getOriginalFilename().equals("")) {
			String fileName = fileUpload(board.getFile());
			
			board.setUrl("http://192.168.10.6:8080/test/" + fileName);
		}
		service.createBoard(board);
		
		return "redirect:/view?no=" + board.getNo();
	}
	
	@GetMapping("/view")
	public String view(int no, Model model) {
		model.addAttribute("board", service.view(no));
		return "view";
	}
	
	@PostMapping("/update")
	public String update(Board board) throws IllegalStateException, IOException {
		Board b = service.view(board.getNo());
		
		if(!board.getFile().isEmpty()) {
			if(b.getUrl() != null) {
				File deleteFile = new File(path + new File(b.getUrl()).getName());
				deleteFile.delete();
			}
			String fileName = fileUpload(board.getFile());
			board.setUrl("http://192.168.10.6:8080/test/" + fileName);
		}else {
			board.setUrl(b.getUrl());
		}
		service.update(board);
		
		return "redirect:/list";
	}
	
	@GetMapping("/delete")
	public String delete(int no) {
		Board board = service.view(no);
		
		if(board.getUrl() != null) {
			File deleteFile = new File(path + new File(board.getUrl()).getName());
			deleteFile.delete();
		}
		service.delete(no);
		
		return "redirect:/list";
	}
	
}
