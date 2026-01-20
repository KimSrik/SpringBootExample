package com.example.gimpo.notice;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
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

	/*
	// 공지 전체 보기 (페이징 없이)
	@GetMapping("/list")
	public String list(Model model) {
		List<Notice> lists = this.noticeService.getList();
		model.addAttribute("lists", lists);
		
		return "notice_list";
	}
	*/
	
	// 공지 전체 보기 (페이징 포함)
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Notice> paging = this.noticeService.getList(page);
		model.addAttribute("paging", paging);
		
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
	
	// 공지 작성하기 (DB로 전송)
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "notice_form";
		}
		
		GpUser gpUser = this.gpUserService.getUser(principal.getName());
		System.out.println("-------------" + gpUser);
		
		this.noticeService.create(noticeForm.getTitle(), noticeForm.getContent(), gpUser);
		return "redirect:/notice/list";
	}
	
	// 공지 수정하기 (진입)
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String noticeModify(NoticeForm noticeForm, @PathVariable("id") Integer id, Principal principal) {
		Notice notice = this.noticeService.getNotice(id);
		
		if(!notice.getWritter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		noticeForm.setTitle(notice.getTitle());
		noticeForm.setContent(notice.getContent());
		
		return "notice_form";
	}
	
	// 공지 수정하기 (DB로 전송)
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String noticeModify(@Valid NoticeForm noticeForm, 
			BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "notice_form";
		}
		
		Notice notice = this.noticeService.getNotice(id);
		
		if(!notice.getWritter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		this.noticeService.modify(notice, noticeForm.getTitle(), noticeForm.getContent());
		return String.format("redirect:/notice/detail/%s", id);
	}
	
	// 공지 삭제하기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String noticeDelete(Principal principal, @PathVariable("id") Integer id) {
		Notice notice = this.noticeService.getNotice(id);
		
		if(!notice.getWritter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}
		
		this.noticeService.delete(notice);
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
}
