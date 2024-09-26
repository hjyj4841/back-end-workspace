package com.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.domain.Movie;

/*
 * JPA (Java Persistence API)
 * - 자바에서 객체 관계 매핑(ORM) 표준 API
 * - 자바 객체와 관계형 데이터베이스의 테이블 간의 변환을 자동 처리
 * - 직접적으로 SQL을 작성할 필요 없이, 자바 객체(Entity)로 데이터베이스 작업 처리
 * 
 * JpaRepository
 * - String Data JPA에서 제공하는 기본적인 CRUD 작업을 자동화하는 인터페이스
 */
public interface MovieDAO extends JpaRepository<Movie, Integer>{

}
