package com.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Movie {
	
	@Id
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String genre;
	
	@Column
	private String actor;
}
