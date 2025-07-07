package com.kh.finalproject.tour.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class TourVO {
  
  private Long contentId;
  private String tourExp; 
  private String usetimeTour;
  private String parking;
}
