package com.example.gimpo.notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gimpo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	// 글 목록보기(약식)
	public List<Notice> getList(){
		return this.noticeRepository.findAll();
	}
	// 글 작성(테스트용)
	public void create(String title, String content) {
		// 게시글에 대한 테이블(필드)정의가 완벽하지 않은
		// 상태에서 테스트가 진행중...
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWritter(null);
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