package com.kh.finalproject.mainContent.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MainContentResDTO {
	
	private Long contentId;
	private int categoryCode;
	private String title;
	private String firstImage;
	private String tel;
	private String homepage;
	private String playTime;
	private Date createdTime;
	private Date modifiedTime;
	private String status;
	
	private List<String> fileUrl;
	
	// juso
	private Long dongNo;
	private String detailName;
	private String postAddress;
	
	// festival
	private String program;
	private String eventExp;
	private String sponsor;
	private String usetimeFestival;
	private Date eventStartDate;
	private Date eventEndDate;
	
	// food
	private String foodExp;
	private String mainMenu;
	
	// lodging
	private String lodgingExp;
	private String checkIn;
	private String checkOut;
	private String elevator;
	
	// tour
	private String tourExp;
	private String usetimeTour;
	private String parking;
	
	private String sidoName;
	private String categoryName;
	private String sigunguName;
	private Long sidoNo;
	
	private DetailDTO detailDto;
	
	
}
