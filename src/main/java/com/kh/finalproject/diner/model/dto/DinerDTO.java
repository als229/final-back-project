package com.kh.finalproject.diner.model.dto;

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
public class DinerDTO {

  private Long contentId;

  @NotNull(message = "음식점 카테고리 코드는 필수 입력입니다.")
  private Long categoryCode;

  @NotBlank(message = "음식점 이름은 필수 입력입니다.")
  @Size(max = 100, message = "음식점 이름은 최대 100자까지 입력 가능합니다.")
  private String title;

  private String firstImage;

  @NotBlank(message = "음식점 전화번호는 필수 입력입니다.")
  @Size(max = 15, message = "음식점 전화번호는 최대 15자까지 입력 가능합니다.")
  private String tel;

  @NotBlank(message = "음식점 공식홈페이지 주소는 필수 입력입니다.")
  @Size(max = 200, message = "음식점 공식홈페이지 주소는 최대 200자까지 입력 가능합니다.")
  private String homepage;

  @NotBlank(message = "음식점 운영시간은 필수 입력입니다.")
  @Size(max = 100, message = "음식점 운영시간은 최대 100자까지 입력 가능합니다.")
  private String playTime;

  @NotBlank(message = "음식점 설명은 필수 입력입니다.")
  @Size(max = 800, message = "음식점 설명은 최대 800자까지 입력 가능합니다.")
  private String foodExp;

  @NotBlank(message = "음식점 대표 메뉴는 필수 입력입니다.")
  @Size(max = 100, message = "음식점 대표 메뉴는 최대 100자까지 입력 가능합니다.")
  private String mainMenu;

  @NotBlank(message = "음식점 메뉴는 필수 입력입니다.")
  @Size(max = 100, message = "음식점 메뉴는 최대 100자까지 입력 가능합니다.")
  private String menu;

  @NotBlank(message = "주차가능 여부 Y/N. 기본값은 Y 입니다.")
  private String parking;

  private Date createdTime;
  private Date modifiedTime;

  private String fileUrl;
}
