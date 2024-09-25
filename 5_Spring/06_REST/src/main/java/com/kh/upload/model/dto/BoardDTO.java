package com.kh.upload.model.dto;

import java.util.List;

import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BoardDTO {
	private List<Board> list;
	private Paging paging;
}
