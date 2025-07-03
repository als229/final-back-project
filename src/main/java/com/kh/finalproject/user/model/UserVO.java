package com.kh.finalproject.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVO {

	
	private Long userNo;
	private String userId;
	private String password;
	private String realName;
	private String nickName;
	private String email;
	
	
	
}