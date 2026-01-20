package com.example.gimpo.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gimpo.DataNotFoundException;
import com.example.gimpo.gpUser.GpUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	// 글 목록보기(약식)
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	// 글 작성
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
}