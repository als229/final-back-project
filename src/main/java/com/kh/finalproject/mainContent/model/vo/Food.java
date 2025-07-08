package com.kh.finalproject.mainContent.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Food {
	private Long contentId;
	private String foodExp;
	private String mainMenu;
	private String parking;
}
