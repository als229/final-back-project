package com.kh.finalproject.global.member.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
  
  private Long userNo;
  private String userId;
  private String realName;

	@NotBlank(message = "닉네임은 비어있거나 공백이 포함될 수 없습니다.")
  private String nickName;
  private String email;
  private Date createdTime;
  private String status;
}
