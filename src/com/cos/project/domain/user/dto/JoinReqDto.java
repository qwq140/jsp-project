package com.cos.project.domain.user.dto;

import lombok.Data;

@Data
public class JoinReqDto {
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
}
