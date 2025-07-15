package com.kh.finalproject.mainContent.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class MapXY {
	private Long contentId;
	private String mapX;
	private String mapY;
}
