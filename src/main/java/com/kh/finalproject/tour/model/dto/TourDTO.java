package com.kh.finalproject.tour.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {

  private Long contentId;

  @NotNull(message = "관광지 카테고리 코드는 필수 입력입니다.")
  private Long categoryCode;

  @NotBlank(message = "관광지 이름은 필수 입력입니다.")
  @Size(max = 100, message = "관광지 이름은 최대 100자까지 입력 가능합니다.")
  private String title;

  private String firstImage;

  @NotBlank(message = "관광지 전화번호는 필수 입력입니다.")
  @Size(max = 15, message = "관광지 전화번호는 최대 15자까지 입력 가능합니다.")
  private String tel;

  @NotBlank(message = "관광지 공식홈페이지 주소는 필수 입력입니다.")
  @Size(max = 200, message = "관광지 공식홈페이지 주소는 최대 200자까지 입력 가능합니다.")
  private String homepage;

  @NotBlank(message = "관광지 운영시간은 필수 입력입니다.")
  @Size(max = 100, message = "관광지 운영시간은 최대 100자까지 입력 가능합니다.")
  private String playTime;

  @NotBlank(message = "관광지 설명은 필수 입력입니다.")
  @Size(max = 800, message = "관광지 설명은 최대 800자까지 입력 가능합니다.")
  private String tourExp; 

  @NotBlank(message = "관광지 입장료는 필수 입력입니다.")
  @Size(max = 100, message = "관광지 입장료는 최대 100자까지 입력 가능합니다.")
  private String usetimeTour;

  @NotBlank(message = "주차가능 여부 Y/N. 기본값은 Y 입니다.")
  private String parking;

  private Date createdTime;
  private Date modifiedTime;
  
  private String fileUrl;
}
