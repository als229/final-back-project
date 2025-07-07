package com.kh.finalproject.diner.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class DinerVO {
  
  private Long contentId;
  private String foodExp;
  private String mainMenu;
  private String menu;
  private String parking;
}
