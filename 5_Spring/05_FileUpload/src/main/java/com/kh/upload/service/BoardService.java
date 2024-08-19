package com.kh.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.upload.model.vo.Board;
import com.kh.upload.model.vo.Paging;

import mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public void createBoard(Board board) {
		mapper.createBoard(board);
	}
	
	public List<Board> showList(Paging paging){
		
		/*
		 * limit가 10인 경우
		 * page = 1 -> offset = 0
		 * page = 2 -> offset = 10
		 * page = 3 -> offset = 20 ...
		 * 
		 * offset = (page - 1) * limit
		 */
		paging.setOffset((paging.getPage() - 1) * paging.getLimit());
		
		return mapper.showList(paging);
	}
	
	public Board view(int no) {
		return mapper.view(no);
	}
	
	public void update(Board board) {
		mapper.update(board);
	}
	
	public void delete(int no) {
		mapper.delete(no);
	}
}
