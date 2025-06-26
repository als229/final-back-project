package com.kh.finalproject.admin.model.dto;

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
public class LodgingDTO {

  private Long contentId;

  @NotNull(message = "숙소 카테고리 코드는 필수 입력입니다.")
  private Long categoryCode;

  @NotBlank(message = "숙소 이름은 필수 입력입니다.")
  @Size(max = 100, message = "숙소 이름은 최대 100자까지 입력 가능합니다.")
  private String title;

  private String firstImage;

  @NotBlank(message = "숙소 전화번호는 필수 입력입니다.")
  @Size(max = 15, message = "숙소 전화번호는 최대 15자까지 입력 가능합니다.")
  private String tel;

  @NotBlank(message = "숙소 공식홈페이지 주소는 필수 입력입니다.")
  @Size(max = 200, message = "숙소 공식홈페이지 주소는 최대 200자까지 입력 가능합니다.")
  private String homepage;

  @NotBlank(message = "숙소 운영시간은 필수 입력입니다.")
  @Size(max = 100, message = "숙소 운영시간은 최대 100자까지 입력 가능합니다.")
  private String playTime;

  @NotBlank(message = "숙소 설명은 필수 입력입니다.")
  @Size(max = 800, message = "숙소 설명은 최대 800자까지 입력 가능합니다.")
  private String lodgingExp;

  @NotBlank(message = "숙소 체크인 시간은 필수 입력입니다.")
  @Size(max = 10, message = "숙소 체크인 시간은 최대 10자까지 입력 가능합니다.")
  private String checkIn;

  @NotBlank(message = "숙소 체크아웃 시간은 필수 입력입니다.")
  @Size(max = 10, message = "숙소 체크아웃 시간은 최대 10자까지 입력 가능합니다.")
  private String checkOut;

  @NotBlank(message = "주차가능 여부 Y/N. 기본값은 Y 입니다.")
  private String parking;

  @NotBlank(message = "엘리베이터 여부 Y/N. 기본값은 Y 입니다.")
  private String elevator;

  private Date createdTime;
  private Date modifiedTime;
  
  private String fileUrl;
}
