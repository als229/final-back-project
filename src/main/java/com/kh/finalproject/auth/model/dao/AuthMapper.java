package com.kh.finalproject.auth.model.dao;


import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.user.model.dto.UserDTO;

@Mapper
public interface AuthMapper {

	UserDTO loadUserByUserId(String userId);
	
	FindResponseDTO selectByfindId(FindDTO findDTO);
	
	FindResponseDTO selectByfindPw(FindDTO findDTO);
	
	
}
