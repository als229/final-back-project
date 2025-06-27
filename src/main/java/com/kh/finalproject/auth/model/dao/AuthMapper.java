package com.kh.finalproject.auth.model.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.auth.model.dto.EmailDTO;
import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.vo.EmailCodeVO;
import com.kh.finalproject.user.model.dto.UserDTO;

@Mapper
public interface AuthMapper {

	UserDTO loadUserByUserId(String userId);
	
	FindResponseDTO selectByfindId(FindDTO findDTO);
	
	void tempPassword (Map<String, String>  tempInfo);
	
	FindResponseDTO selectEmail (FindDTO findDTO);
	
	FindResponseDTO selectByfindPw(FindDTO findDTO);
	
	int sendEmailCode(EmailCodeVO emailCodeInfo);
	
	EmailCodeVO verifyCode(EmailDTO emailDTO);
	
	int deleteEmailCode(EmailDTO emailDTO);
	
	void delete(Map<String, Object> deleteInfo);
	
	
	
	
}
