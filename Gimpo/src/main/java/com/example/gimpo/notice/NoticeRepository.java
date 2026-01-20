package com.example.gimpo.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	// 페이징
	Page<Notice> findAll(Pageable pageable);
}
