package com.kh.finalproject.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class mapVO {
  
  private Long contentId;
  private String mapX;
  private String mapY;
}
