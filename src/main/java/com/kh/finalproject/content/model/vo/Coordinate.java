package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Coordinate {
	
	private Long contentId;
	private String mapX;
	private String mapY;

}
