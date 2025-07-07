package com.kh.finalproject.home.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeVO {

	private Long contentId;
	private String title;
	private String firstImage;
	
	private String sidoName;
	private String sigunguName;
	private String dongName;
	private Long dongNo;
	private String detailName;
	private String postAddres;
	private Long likeCount;
	private String category;
	
}
