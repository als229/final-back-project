package com.kh.finalproject.global.map.model.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class DongVO {
  
  private Long dongNo;
  private Long sigunguNo;
  private String dongName;
}
