package com.kh.finalproject.global.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
@AllArgsConstructor
public class MemberVO {

  Long userNo;
  String nickName;
  String status;
}
