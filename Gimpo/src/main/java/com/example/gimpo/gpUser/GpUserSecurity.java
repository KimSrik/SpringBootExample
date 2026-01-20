package com.example.gimpo.gpUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GpUserSecurity implements UserDetailsService {

	private final GpUserRepository gpUserRepository;

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<GpUser> _gpUser = this.gpUserRepository.findByUsername(username);
		
		if(_gpUser.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");			
		}
		GpUser gpUser = _gpUser.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(GpUserRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(GpUserRole.USER.getValue()));
		}
		
		return new User(gpUser.getUsername(), gpUser.getPassword(), authorities);
	}


}
