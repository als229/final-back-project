package com.kh.finalproject.lodging.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class LodgingVO {
  
  private Long contentId; 
  private String lodgingExp;
  private String checkIn;
  private String checkOut;
  private String parking;
  private String elevator;
}
