package com.semi.youtube;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("mapper")
public class YoutubeSemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeSemiApplication.class, args);
	}

}
