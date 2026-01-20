package com.example.gimpo.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.gimpo.gpUser.GpUser;
import com.example.gimpo.opinion.Opinion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@ManyToOne
	private GpUser writter;
	
	private String content;
	
	private Integer views;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
	@OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE)
	private List<Opinion> opinionList;
	
	@ManyToMany
	Set<GpUser> voter;
	
}
