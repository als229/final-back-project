package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Food {
	
	private Long contentId;
	private String foodExp;
	private String mainMenu;
	private String menu;
	private String parking;

}
