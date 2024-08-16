package com.kh.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.upload.model.vo.Board;

import mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public void createBoard(Board board) {
		mapper.createBoard(board);
	}
	
	public List<Board> showList(){
		return mapper.showList();
	}
}
