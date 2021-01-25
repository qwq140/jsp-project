package com.cos.project.domain.book.dto;

import lombok.Data;

@Data
public class SearchReqDto {
	private String depAirportId;
	private String arrAirportId;
	private String depPlandTime;
	private String arrPlandTime;
	private String adults;
	private String child;
}
