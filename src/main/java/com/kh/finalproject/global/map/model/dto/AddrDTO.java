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
public class AddrDTO {
  
  private Long contentId;

  private Long sidoNo;

  @NotBlank(message = "시도는 필수 입력입니다.")
  @Size(max = 10, message = "시도는 최대 10자까지 입력 가능합니다.")
  private String sidoName;

  private Long sigunguNo;

  @NotBlank(message = "시군구는 필수 입력입니다.")
  @Size(max = 10, message = "시군구는 최대 10자까지 입력 가능합니다.")
  private String sigunguName;

  private Long dongNo;

  @NotBlank(message = "동은 필수 입력입니다.")
  @Size(max = 10, message = "동은 최대 10자까지 입력 가능합니다.")
  private String dongName;

  private Long detailNo;

  @NotBlank(message = "상세주소는 필수 입력입니다.")
  @Size(max = 100, message = "상세주소는 최대 100자까지 입력 가능합니다.")
  private String detailName;

  @NotBlank(message = "우편번호는 필수 입력입니다.")
  @Size(max = 6, message = "우편번호는 최대 6자 까지 입력 가능합니다.")
  private String postAddress;
}
