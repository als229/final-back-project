package com.kh.finalproject.mainContent.model.vo;

import com.kh.finalproject.review.model.vo.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Lodging {
	private Long contentId;
	private String lodgingExp;
	private String checkIn;
	private String checkOut;
	private String parking;
	private String elevator;
	
}
