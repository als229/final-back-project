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
public class LodgingDTO {

	private Long contentId;
	private String lodgingExp;
	private String checkIn;
	private String checkOut;
	private String parking;
	private String elevator;

}
