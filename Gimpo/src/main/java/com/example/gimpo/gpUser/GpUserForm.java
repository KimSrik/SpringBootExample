package com.example.gimpo.gpUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GpUserForm {
	
	@Size(min=4, max=20)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String userId;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호 확인은 필수 항목 입니다.")
	private String password2;
	
	@NotEmpty(message = "이름 입력란이 잘못되었습니다.")
	private String username;
	
	@Email
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String email;
}
