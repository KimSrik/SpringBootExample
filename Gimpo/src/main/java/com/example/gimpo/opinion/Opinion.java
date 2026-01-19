package com.example.gimpo.opinion;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Opinion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer rating;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private String writter;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	
	
}
