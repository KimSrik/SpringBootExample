package com.example.gimpo.opinion;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gimpo.DataNotFoundException;
import com.example.gimpo.gpUser.GpUser;
import com.example.gimpo.notice.Notice;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpinionService {
	
	private final OpinionRepository opinionRepository;
	
	// 의견 등록
	public void create(Notice notice, String content, String rating, GpUser writter) {
		Opinion opinion = new Opinion();
		Integer rate = Integer.parseInt(rating);
		
		opinion.setContent(content);
		opinion.setCreateDate(LocalDateTime.now());
		opinion.setRating(rate);
		opinion.setNotice(notice);
		opinion.setWritter(writter);
		this.opinionRepository.save(opinion);
	}
	
	// 의견 불러오기
	public Opinion getOpinion(Integer id) {
		
		Optional<Opinion> opinion = this.opinionRepository.findById(id);
		
		if(opinion.isPresent()) {
			return opinion.get();
		}else {
			throw new DataNotFoundException("객체를 찾을수가 없습니다.");
		}
	}
	
	// 의견 수정
	public void modify(Opinion opinion, String content, String rating) {
		Integer rate = Integer.parseInt(rating);
		opinion.setContent(content);
		opinion.setModifyDate(LocalDateTime.now());
		opinion.setRating(rate);
		this.opinionRepository.save(opinion);
		
	}
	
	// 의견 삭제하기
	public void delete(Opinion opinion) {
		this.opinionRepository.delete(opinion);
	}
}
