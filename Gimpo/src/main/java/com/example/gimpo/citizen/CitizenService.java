package com.example.gimpo.citizen;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitizenService {
	private final CitizenRepository citizenRepository;
}
