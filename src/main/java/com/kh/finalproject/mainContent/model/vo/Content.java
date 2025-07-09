package com.kh.finalproject.mainContent.model.vo;

import java.sql.Date;

import com.kh.finalproject.review.model.vo.Review;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Content {
	
	private int categoryCode;
	private String title;
	private String firstImage;
	private String tel;
	private String homepage;
	private String playTime;
	
}
