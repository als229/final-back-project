package com.kh.finalproject.mainContent.model.dto;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class MainContentReqDTO {
	
	// content common 
	private Long contentId;
    @NotNull(message = "카테고리 코드는 필수입니다.")
	private int categoryCode;
    @NotBlank(message = "제목은 필수입니다.")
	private String title;
	private String firstImage;
    @NotBlank(message = "전화번호는 필수입니다.")
	private String tel;
    @NotBlank(message = "홈페이지는 필수입니다.")
	private String homepage;
    @NotBlank(message = "운영시간은 필수입니다.")
	private String playTime;
	private String status;
	private String mapY;
	private String mapX;
	private String thumbnail;
	private String thumbnailUrl;
	
	private List<String> deletedOriginalImages;
	private List<String> imges;
	
	// juso
	private Long dongNo;
    @NotBlank(message = "시/도 정보는 필수입니다.")
	private String sidoName;
    @NotBlank(message = "시/군/구 정보는 필수입니다.")
	private String sigunguName;
    @NotBlank(message = "세부주소는 필수 입니다.")
	private String detailName;
    @NotBlank(message = "동 이름은 필수 입니다.")
    private String dongName;
    @NotBlank(message = "우편번호는 필수 입니다.")
	private String postAddress;
	
    // festival
    private String program;

    private String eventExp;

    private String sponsor;

    private String useTimeFestival;

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

    private String useTimeTour;

    private String parking;
    
    private String originalImages;

	

}
