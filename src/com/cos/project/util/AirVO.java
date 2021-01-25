package com.cos.project.util;

import lombok.Data;

@Data
public class AirVO {
	private String airlineNm;
	private String arrAirportNm;
	private String arrPlandTime;
	private String depAirportNm;
	private String depPlandTime;
	private String economyCharge;
	private String prestigeCharge;
	private String vihicleId;
}
