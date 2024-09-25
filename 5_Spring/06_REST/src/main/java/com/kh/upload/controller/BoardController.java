package com.kh.upload.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.upload.model.dto.BoardDTO;
import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;
import com.kh.upload.service.BoardService;

/*
 * REST (Representational State Transfer)
 * : API를 설계하는 아키텍처
 * 
 * RESTful : REST 원칙을 준수하는 방식
 * 
 * API (Application Programming Interface) 
 * : 서로 다른 애플리케이션들이 서로 데이터를 주고받을 수 있게 하는 도구
 */

@RestController
@RequestMapping("/api/*") // http://localhost:8080/api/
public class BoardController {
	
	private String path = "\\\\192.168.10.51\\upload\\";
	
	@Autowired
	private BoardService service;
	
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		// 중복 방지를 위한 UUID 적용
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString() + "_" + file.getOriginalFilename();
		
//		File copyFile = new File("D:\\upload\\" + fileName);
		File copyFile = new File(path + fileName);
		
		file.transferTo(copyFile); // 업로드한 파일이 지정한 path 위치로 저장
		
		return fileName;
	}

	// CRUD : Create - Post, Read - Get, Update - Put, Delete - Delete
	
	// Create - Post
	@PostMapping("/board")
	public ResponseEntity write(Board board) throws IllegalStateException, IOException {
		if(!board.getFile().getOriginalFilename().equals("")) {
			String fileName = fileUpload(board.getFile());
			
			board.setUrl("http://192.168.10.51/upload/" + fileName);
		}
		service.createBoard(board);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Read - Get : 전체 목록 보기
	@GetMapping("/board")
	public ResponseEntity list(Paging paging) {
		
		List<Board> list = service.showList(paging);
		
		for(Board b : list) {
			LocalDateTime date = b.getDate();
			Date formatDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
			b.setFormatDate(formatDate);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new BoardDTO(list, new Paging(paging.getPage(), service.total())));
	}
	
	// Read - Get : 1개 가져오기
	@GetMapping("/board/{no}")
	public ResponseEntity view(@PathVariable int no) {
		Board board = service.view(no);

		if(board != null) {
			return ResponseEntity.status(HttpStatus.OK).body(board);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	// Update - Put
	@PutMapping("/board")
	public ResponseEntity update(Board board) throws IllegalStateException, IOException {
		Board b = service.view(board.getNo());
		
		if(!board.getFile().isEmpty()) {
			if(b.getUrl() != null) {
				File deleteFile = new File(path + new File(b.getUrl()).getName());
				deleteFile.delete();
			}
			String fileName = fileUpload(board.getFile());
			board.setUrl("http://192.168.10.51/upload/" + fileName);
		}else {
			board.setUrl(b.getUrl());
		}
		service.update(board);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Delete - Delete
	@DeleteMapping("/board/{no}")
	public ResponseEntity delete(@PathVariable int no) {
		Board board = service.view(no);
		
		if(board.getUrl() != null) {
			File deleteFile = new File(path + new File(board.getUrl()).getName());
			deleteFile.delete();
		}
		service.delete(no);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
