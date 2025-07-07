package com.kh.finalproject.token.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RefreshTokenVO {

	private Long userNo;
	private String refreshToken;
	private Long expirationTime;
	
}
