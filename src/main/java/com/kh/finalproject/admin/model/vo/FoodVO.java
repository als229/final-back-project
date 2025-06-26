package com.kh.finalproject.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class FoodVO {
  
  private Long contentId;
  private String foodExp;
  private String mainMenu;
  private String menu;
  private String parking;
}
