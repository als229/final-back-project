package com.kh.finalproject.festival.model.dto;

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
public class FestivalDTO {
  
  private Long contentId;

  @NotNull(message = "축제 카테고리 코드는 필수 입력입니다.")
  private Long categoryCode;

  @NotBlank(message = "축제 이름은 필수 입력입니다.")
  @Size(max = 100, message = "축제 이름은 최대 100자까지 입력 가능합니다.")
  private String title;

  private String firstImage;

  @NotBlank(message = "축제 전화번호는 필수 입력입니다.")
  @Size(max = 15, message = "축제 전화번호는 최대 15자까지 입력 가능합니다.")
  private String tel;

  @NotBlank(message = "축제 공식홈페이지 주소는 필수 입력입니다.")
  @Size(max = 200, message = "축제 공식홈페이지 주소는 최대 200자까지 입력 가능합니다.")
  private String homepage;

  @NotBlank(message = "축제 운영시간은 필수 입력입니다.")
  @Size(max = 100, message = "축제 운영시간은 최대 100자까지 입력 가능합니다.")
  private String playTime;

  @NotBlank(message = "축제 프로그램 설명은 필수 입력입니다.")
  @Size(max = 800, message = "축제 프로그램 설명은 최대 800자까지 입력 가능합니다.")
  private String program; 

  @NotBlank(message = "축제 설명은 필수 입력입니다.")
  @Size(max = 800, message = "축제 설명은 최대 800자까지 입력 가능합니다.")
  private String eventExp; 

  @NotBlank(message = "축제 스폰서는 필수 입력입니다.")
  @Size(max = 100, message = "축제 스폰서는 최대 100자까지 입력 가능합니다.")
  private String sponsor;

  @NotBlank(message = "축제 입장료는 필수 입력입니다.")
  @Size(max = 100, message = "축제 입장료는 최대 100자까지 입력 가능합니다.")
  private String usetimeFestival;

  @NotNull(message = "축제 시작일은 필수 입력입니다.")
  private Date eventStartDate;

  @NotNull(message = "축제 종료일은 필수 입력입니다.")
  private Date eventEndDate;

  private Date createdTime;
  private Date modifiedTime;
  
  private String fileUrl;
}
