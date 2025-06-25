package com.kh.finalproject.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.user.model.dto.UserDTO;

@Mapper
public interface UserMapper {

	
	// 회원가입
	void signUp(UserDTO userDTO);
	
	int existsByUserId(String userId);
	
	int existsByUserNickName(String nickName);
	
	int existsByUserEmail(String Email);
	
	
	
	
}
