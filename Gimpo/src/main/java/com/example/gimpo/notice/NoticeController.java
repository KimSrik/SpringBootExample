package com.example.gimpo.notice;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")		//prefix setting
public class NoticeController {
	private final NoticeService noticeService;
	
	// 공지 전체 보기
	@GetMapping("/list")
	public String list(Model model) {
		List<Notice> lists = this.noticeService.getList();
		model.addAttribute("lists", lists);
		
		return "notice_list";
	}
	
	// 공지 상세 보기
	@GetMapping("/detail/{id}")
	public String noticeDetail(Model model, @PathVariable("id") Integer id) {
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		return "notice_detail";
	}
}
