package com.example.gimpo.opinion;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OpinionForm {
	
	@NotEmpty(message = "만족도 항목을 선택하세요.")
	private String rating;
	
	@NotEmpty(message = "의견 내용을 작성해주세요.")
	private String content;
}
