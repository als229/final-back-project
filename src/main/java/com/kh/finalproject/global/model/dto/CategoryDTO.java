package com.kh.finalproject.global.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

  private Long categoryCode;

  @NotBlank(message = "카테고리 이름은 필수 입력입니다.")
  @Size(max = 10, message = "카테고리 이름은 최대 10자까지 입력 가능합니다.")
  private String categoryName;
}
