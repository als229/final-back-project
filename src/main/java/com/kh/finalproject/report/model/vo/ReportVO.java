package com.kh.finalproject.report.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class ReportVO {

  private Long reportNo;
  private Long reviewNo;
  private Long userNo;
  private Long penaltyNo;
  private Long categoryNo;
  private String reportContent;
  private String status;
}
