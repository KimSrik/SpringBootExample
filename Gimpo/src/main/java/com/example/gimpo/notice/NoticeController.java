package com.example.gimpo.notice;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gimpo.gpUser.GpUser;
import com.example.gimpo.gpUser.GpUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")		//prefix setting
public class NoticeController {
	private final NoticeService noticeService;
	
	private final GpUserService gpUserService;
	
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
	
	// 공지 작성하기 (진입)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String noticeCreate(NoticeForm noticeForm) {
		return "notice_form";
	}
	
	// 공지 작성하기 (전송)
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "notice_form";
		}
		
		GpUser gpUser = this.gpUserService.getUser(principal.getName());
		
		this.noticeService.create(noticeForm.getTitle(), noticeForm.getContent(), gpUser);
		return "redirect:/notice/list";
	}
	
	
}
