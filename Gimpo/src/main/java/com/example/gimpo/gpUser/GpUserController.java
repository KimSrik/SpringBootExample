package com.example.gimpo.gpUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gpuser")
public class GpUserController {
	private final GpUserService gpUserService;
	
	@GetMapping("/signup")
	public String signup(GpUserForm gpUserForm) {
		return "signup_form";
	}
}
