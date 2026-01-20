package com.example.gimpo.gpUser;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gimpo.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GpUserService {

    private final PasswordEncoder passwordEncoder;
	private final GpUserRepository gpUserRepository;
	
	// 회원 가입 서비스
	public GpUser create(String username, String password, String name, String email) {
		GpUser user = new GpUser();
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		this.gpUserRepository.save(user);
		
		return user;
	}
	
	public GpUser getUser(String username) {
		Optional<GpUser> gpUser = this.gpUserRepository.findByUsername(username);
		
		if(gpUser.isPresent()) {
			return gpUser.get();
		}else {
			throw new DataNotFoundException("gpUser not found");
		}
	}
	

}
