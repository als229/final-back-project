package com.kh.finalproject.global.map.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class SidoVO {
  
  private Long sidoNo;
  private String sidoName;
}
