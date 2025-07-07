package com.kh.finalproject.global.map.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class DetailAddVO {
  
  private Long contentId;
  private Long detailNo;
  private Long dongNo;
  private String detailName;
  private String postAddress;
}
