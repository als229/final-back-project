package com.kh.finalproject.global.map.model.dto;

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
public class DongDTO {
  
  private Long dongNo;
  
  @NotNull(message = "시군구 번호는 필수 입력입니다.")
  private Long sigunguNo;

  @NotBlank(message = "동은 필수 입력입니다.")
  @Size(max = 10, message = "동은 최대 10자까지 입력 가능합니다.")
  private String dongName;
}
