package com.kh.finalproject.report.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

  private Long reportNo;

  @NotNull(message = "리뷰 번호는 필수입니다.")
  private Long reviewNo;

  @NotNull(message = "사용자 번호는 필수입니다.")
  private Long userNo;
  private String userName;

  @NotNull(message = "패널티 번호는 필수입니다.")
  private Long penaltyNo;
  private String penaltyName;

  @NotNull(message = "카테고리 번호는 필수입니다.")
  private Long categoryNo;
  private String categoryName;

  @NotBlank(message = "신고 내용은 필수입니다.")
  @Size(max = 200, message = "신고 내용은 최대 200자까지 입력 가능합니다.")
  private String reportContent;

  private Date createdTime;
  private String status;
}
