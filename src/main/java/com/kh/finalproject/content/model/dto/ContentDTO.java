package com.kh.finalproject.content.model.dto;

import org.springframework.web.multipart.MultipartFile;

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
public class ContentDTO {
	
	private Long categoryCode;
	private String title;
	private MultipartFile firstImage;
	private String tel;
	private String homepage;
	private String playtime;

	// 상세 정보 DTO 추가
	private CoordinateDTO coordinateDTO;
	private LodgingDTO lodgingDTO;
	private TourDTO tourDTO;
	private FoodDTO foodDTO;
	private FestivalDTO festivalDTO;
}
