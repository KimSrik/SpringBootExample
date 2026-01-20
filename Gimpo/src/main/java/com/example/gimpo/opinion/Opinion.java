package com.example.gimpo.opinion;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.gimpo.gpUser.GpUser;
import com.example.gimpo.notice.Notice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	private GpUser writter;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@ManyToOne
	private Notice notice;
	
	@ManyToMany
	Set<GpUser> voter;
	
	
	
}
