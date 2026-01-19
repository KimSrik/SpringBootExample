package com.example.gimpo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String root() {
		return "/notice_list";
		//return "redirect:/notice_list";
	}
}
