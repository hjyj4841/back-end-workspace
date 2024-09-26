package com.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.domain.Movie;
import com.jpa.service.MovieService;

@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge=6000) // CORS error
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	// 추가
	@PostMapping("/movie")
	public ResponseEntity add(@RequestBody Movie vo) {
		service.change(vo);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// 전체 조회
	@GetMapping("/movie")
	public ResponseEntity viewAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.viewAll());
	}
	
	// 1개 조회
	@GetMapping("/movie/{id}")
	public ResponseEntity view(@PathVariable int id) {
		Movie movie = service.view(id);
		if(movie != null) {
			return ResponseEntity.status(HttpStatus.OK).body(movie);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	// 수정
	@PutMapping("/movie")
	public ResponseEntity update(@RequestBody Movie vo) {
		service.change(vo);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// 삭제
	@DeleteMapping("/movie/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
