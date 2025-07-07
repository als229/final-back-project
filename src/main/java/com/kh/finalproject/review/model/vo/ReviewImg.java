package com.kh.finalproject.review.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class ReviewImg {

	private Long reviewImgNo;
	private Long reviewNo;
	private String fileUrl;
	
}
