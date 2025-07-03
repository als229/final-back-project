package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class Content {
	
	private Long contentId;
	
	private Long categoryCode;
	private String title;
	private String firstImageUrl;
	private String tel;
	private String homepage;
	private String playtime;
	
}
