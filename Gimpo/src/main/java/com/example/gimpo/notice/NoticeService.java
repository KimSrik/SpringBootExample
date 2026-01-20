package com.example.gimpo.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.gimpo.DataNotFoundException;
import com.example.gimpo.gpUser.GpUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	/*
	// 공지 글 목록보기(페이징 X)
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	*/
	
	// 공지 글 목록보기(페이징 포함)
	public Page<Notice> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.noticeRepository.findAll(pageable);
		
	}
	
	
	// 공지 글 작성
	public void create(String title, String content, GpUser gpUser) {
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWritter(gpUser);
		notice.setContent(content);
		notice.setCreateDate(LocalDateTime.now());
		notice.setModifyDate(LocalDateTime.now());
		notice.setViews(0);
		this.noticeRepository.save(notice);
	}
	
	public Notice getNotice(Integer id) {
		Optional<Notice> notice = this.noticeRepository.findById(id);
		
		if(notice.isPresent()) {
			return notice.get();
		}else {
			throw new DataNotFoundException("객체를 찾는 부분에 문제가 발생하였습니다.");
		}
	}
	
	// 공지 글 수정하기
	public void modify(Notice notice, String title, String content) {
		notice.setTitle(title);
		notice.setContent(content);
		notice.setModifyDate(LocalDateTime.now());
		this.noticeRepository.save(notice);
	}
	
	// 공지 글 삭제하기
	public void delete(Notice notice) {
		this.noticeRepository.delete(notice);
	}
}