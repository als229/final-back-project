package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContentImg {

	private Long contentImgNo;
	private Long contentId;
	private String fileUrl;
	
}
