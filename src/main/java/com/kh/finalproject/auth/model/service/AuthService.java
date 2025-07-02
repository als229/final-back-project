package com.kh.finalproject.auth.model.service;



import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.model.dto.LoginDTO;
import com.kh.finalproject.auth.model.dto.LoginResponseDTO;

public interface AuthService {

	
	LoginResponseDTO login(LoginDTO loginDTO);
	
	FindResponseDTO findId(FindDTO findDTO);
	
	FindResponseDTO findPw(FindDTO findDTO);
	
	String sendEmailCode(String email);
	
}
