package com.kh.finalproject.content.model.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Content {
	
	private Long contentId;
	
	private Long categoryCode;
	private String title;
	private MultipartFile firstImage;
	private String tel;
	private String homepage;
	private String playtime;
	
}
