package com.kh.finalproject.global.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class CategoryVO {

  private Long categoryCode;
  private String categoryName;
}
