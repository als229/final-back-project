package com.kh.finalproject.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class ContentVO {

  private Long contentId;
  private Long categoryCode;
  private String title;
  private String firstImage;
  private String tel;
  private String homepage;
  private String playTime;
  private String fileUrl;
}
