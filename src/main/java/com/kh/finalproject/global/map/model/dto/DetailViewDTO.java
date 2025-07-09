package com.kh.finalproject.global.map.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class DetailViewDTO {
  
  private Long contentId;

  private Long detailNo;
  
  @NotNull(message = "시도 번호는 필수 입력입니다.")
  private Long dongNo;

  @NotBlank(message = "상세주소는 필수 입력입니다.")
  @Size(max = 100, message = "상세주소는 최대 100자까지 입력 가능합니다.")
  private String detailName;

  @NotBlank(message = "우편번호는 필수 입력입니다.")
  @Size(max = 6, message = "우편번호는 최대 6자 까지 입력 가능합니다.")
  private String postAddress;
}
