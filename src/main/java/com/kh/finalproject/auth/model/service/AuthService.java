package com.kh.finalproject.auth.model.service;



import com.kh.finalproject.auth.model.dto.EmailDTO;
import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.model.dto.LoginDTO;
import com.kh.finalproject.auth.model.dto.LoginResponseDTO;
import com.kh.finalproject.auth.vo.NwUserDetails;

public interface AuthService {

	
	LoginResponseDTO login(LoginDTO loginDTO);
	
	FindResponseDTO findId(FindDTO findDTO);
	
	FindResponseDTO findPw(FindDTO findDTO);
	
	int sendEmailCode(String email);
	
	EmailDTO verifyCode(EmailDTO emailDTO);

	NwUserDetails getUserDetails();
	
	
}
