package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tour {
	
	private Long contentId;
	private String tourExp;
	private String usetimeTour;
	private String parking;

}
