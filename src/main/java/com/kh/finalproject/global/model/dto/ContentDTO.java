package com.kh.finalproject.global.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
  
  private Long contentId;
  private Long categoryCode;
  private String title;
  private String firstImage;
  private String tel;
  private String homepage;
  private String playTime;
  private String fileUrl;
}
