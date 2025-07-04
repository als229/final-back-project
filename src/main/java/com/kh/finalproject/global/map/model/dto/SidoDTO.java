package com.kh.finalproject.global.map.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SidoDTO {
  
  private Long sidoNo;

  @NotBlank(message = "시도는 필수 입력입니다.")
  @Size(max = 10, message = "시도는 최대 10자까지 입력 가능합니다.")
  private String sidoName;
}
