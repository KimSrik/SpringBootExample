package com.example.gimpo.gpUser;

import lombok.Getter;

@Getter
public enum GpUserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	GpUserRole(String value) {
		this.value = value;
	}
	
	private String value;
}
