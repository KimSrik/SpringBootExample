package com.example.gimpo.opinion;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.gimpo.gpUser.GpUser;
import com.example.gimpo.gpUser.GpUserService;
import com.example.gimpo.notice.Notice;
import com.example.gimpo.notice.NoticeController;
import com.example.gimpo.notice.NoticeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/opinion")			//prefix setting
public class OpinionController {

    private final NoticeController noticeController;

	private final OpinionService opinionService;
	
	private final NoticeService noticeService;
	
	private final GpUserService gpUserService;
	
	// 의견 등록
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String opinionCreate(Model model, @PathVariable("id") Integer id,
			@Valid OpinionForm opinionForm, BindingResult bindingResult, Principal principal){
		Notice notice = this.noticeService.getNotice(id);
		GpUser gpUser = this.gpUserService.getUser(principal.getName());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("notice", notice);
			return "notice_detail";
		}
		
		this.opinionService.create(notice, opinionForm.getContent(), opinionForm.getRating(), gpUser);
		return String.format("redirect:/notice/detail/%s", id);
	}
	
	// 의견 수정 -> 버튼 클릭
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String opinionModify(OpinionForm opinionForm, 
		@PathVariable("id") Integer id, Principal principal) { 
		
		Opinion opinion = this.opinionService.getOpinion(id);
		
		if(!opinion.getWritter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		
		opinionForm.setContent(opinion.getContent());
		opinionForm.setRating(Integer.toString(opinion.getRating()));
		
		return "opinion_form";
	}
	
	
	// 의견 수정 -> 수정 페이지에서 DB로
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String opinionModify(@Valid OpinionForm opinionForm,
			BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
		
		if(bindingResult.hasErrors()) {
			return "opinion_form";
		}
		
		Opinion opinion = this.opinionService.getOpinion(id);
		
		if(!opinion.getWritter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		Notice notice = opinion.getNotice();
		
		Integer noticeId = notice.getId();
		
		this.opinionService.modify(opinion, opinionForm.getContent(), opinionForm.getRating());
		return String.format("redirect:/notice/detail/%s", noticeId);	
		
	}
	
	
	
	
}
