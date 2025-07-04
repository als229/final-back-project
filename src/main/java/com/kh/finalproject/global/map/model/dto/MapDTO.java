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
public class MapDTO {

  private Long contentId;

  @NotBlank(message = "위도는 필수 입력입니다.")
  @Size(max = 20, message = "위도는 최대 20자까지 입력 가능합니다.")
  private String mapX;

  @NotBlank(message = "경도는 필수 입력입니다.")
  @Size(max = 20, message = "경도는 최대 20자까지 입력 가능합니다.")
  private String mapY;
}
