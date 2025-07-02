package com.kh.finalproject.content.model.dto;

import java.sql.Date;

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
public class FestivalDTO {
	
	private Long contentId;
	private String program;
	private String eventExp;
	private String sponsor;
	private String usetimeFestival;
	private Date eventStartDate;
	private Date eventEndDate;

}
