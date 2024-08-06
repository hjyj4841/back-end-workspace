package com.semi.youtube.model.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Comment {
	private int commentCode;
	private String commentText;
	private LocalDateTime commentDate;
	private String id; // member
	private int videoCode; // video
	private int parentCode;
}
