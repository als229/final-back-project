package com.kh.finalproject.report.model.dto;

import jakarta.validation.constraints.NotBlank;
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
public class ReportCategoryDTO {
  
  private Long categoryNo;

  @NotBlank(message = "신고 카테고리 이름은 필수입니다.")
  @Size(max = 100, message = "신고 카테고리 이름은 최대 100자까지 입력 가능합니다.")
  private String categoryName;
}
