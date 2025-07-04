package com.kh.finalproject.report.model.vo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class PenaltyVO {
  
  private Long penaltyNo;
  private String penaltyName;
}
