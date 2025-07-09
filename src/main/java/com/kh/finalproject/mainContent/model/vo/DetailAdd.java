package com.kh.finalproject.mainContent.model.vo;

import com.kh.finalproject.review.model.vo.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class DetailAdd {

	private Long contentId;
	private Long dongNo;
	private String detailName;
	private String postAddress;
	
}
