package com.example.gimpo.gpUser;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GpUserController {
	private final GpUserService gpUserService;
}
