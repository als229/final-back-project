package com.kh.finalproject.festival.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class FestivalVO {

  private Long contentId;
  private String program; 
  private String eventExp; 
  private String sponsor;
  private String usetimeFestival;
  private Date eventStartDate;
  private Date eventEndDate;
}
