package com.kh.finalproject.review.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Review {
	
	private Long reviewNo;
	private Long userNo;
	private Long contentId;
	private String content;
	private Double point;
	
}
