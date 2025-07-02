package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Lodging {

	private Long contentId;
	private String lodgingExp;
	private String checkIn;
	private String checkOut;
	private String parking;
	private String elevator;

}
