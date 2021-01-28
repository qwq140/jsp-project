package com.cos.project.domain.book.dto;



import lombok.Data;

@Data
public class SaveReqDto {
	private int userId;
	private int personnel;
	private String depAirportNm;
	private String arrAirportNm;
	private String depPlandTime;
	private String arrPlandTime;
	private String vihicleId;
	private String grade;
	private int charge;
}
