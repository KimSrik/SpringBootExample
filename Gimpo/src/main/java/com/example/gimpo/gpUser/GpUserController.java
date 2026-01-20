package com.example.gimpo.gpUser;

import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class GpUserController {
	private final GpUserService gpUserService;
	
	@GetMapping("/signup")
	public String signup(GpUserForm gpUserForm) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid GpUserForm gpUserForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(!gpUserForm.getPassword1().equals(gpUserForm.getPassword2())){
			bindingResult.rejectValue("password2", "passwordIncorrect", "입력하신 두 비밀번호가 일치하지 않습니다.");
			return "signup_form";
		}
		
		try {
			gpUserService.create(gpUserForm.getUsername(), gpUserForm.getPassword1(), gpUserForm.getName(), gpUserForm.getEmail());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();			
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/";
		
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
