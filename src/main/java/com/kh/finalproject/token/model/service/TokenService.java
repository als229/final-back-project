package com.kh.finalproject.token.model.service;

import com.kh.finalproject.auth.model.dto.LogoutDTO;

public interface TokenService {

	String generateAccessToken(String userId);

	String generateRefreshToken(Long userNo, String userId);
	
	String  reissueToken(String RefreshToken);
	
	void deleteToken(LogoutDTO logoutDTO);


}
