package com.kh.finalproject.mainContent.model.vo;

import com.kh.finalproject.review.model.vo.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Tour {
	private Long contentId;
	private String tourExp;
	private String usetimeTour;
	private String parking;
	
}
