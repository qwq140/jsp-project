package com.cos.project.domain.user.dto;

import lombok.Data;

@Data
public class UpdateReqDto {
	private int id;
	private String password;
	private String email;
	private String address;
}
