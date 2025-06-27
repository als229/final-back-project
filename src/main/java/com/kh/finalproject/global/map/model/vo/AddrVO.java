package com.kh.finalproject.global.map.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class AddrVO {
  
  private Long contentId;
  private Long sidoNo;
  private String sidoName;
  private Long sigunguNo;
  private String sigunguName;
  private Long dongNo;
  private String dongName;
  private Long detailNo;
  private String detailName;
  private String postAddress;
}
