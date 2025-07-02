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
public class FoodDTO {

	private Long contentId;
	private String foodExp;
	private String mainMenu;
	private String menu;
	private String parking;
}
