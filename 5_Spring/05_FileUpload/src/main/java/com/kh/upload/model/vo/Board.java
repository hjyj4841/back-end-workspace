package com.kh.upload.model.vo;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor 
@NoArgsConstructor @Builder
public class Board {
	private int no;
	private String title;
	private String content;
	private LocalDateTime date;
	private String url;
	
	private MultipartFile file; // 사실 DTO 따로 만들어서 사용하는 게 원칙
}
