package com.kh.finalproject.log.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class UserLogVO {

  private Long userNo;
  private String oldInfo;
  private String newInfo;
}
