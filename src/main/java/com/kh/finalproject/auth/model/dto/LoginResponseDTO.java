package com.kh.finalproject.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

	
	private String userId;
	private String realName;
	private String nickName;
	private String email;
	private String accessToken;
	private String refreshToekn;
}
