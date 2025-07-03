package com.kh.finalproject.review.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Star {

	private Long userNo;
	private Long contentId;
	private Double point;
}
