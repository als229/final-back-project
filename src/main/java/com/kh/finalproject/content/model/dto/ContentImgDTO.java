package com.kh.finalproject.content.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentImgDTO {
	
	private Long contentImgNo;
	private Long contentId;
	private String fileUrl;
	
}
