package com.kh.finalproject.token.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.auth.model.dto.LogoutDTO;
import com.kh.finalproject.token.model.vo.RefreshTokenVO;

@Mapper
public interface TokenMapper {

	
	RefreshTokenVO selectTokenByToken(RefreshTokenVO token);
	
	int deleteTokenByUserNo(Long userNo);
	
	int insertRefreshToken(RefreshTokenVO token);
	
	int deleteToken(LogoutDTO logoutDTO);
	
}