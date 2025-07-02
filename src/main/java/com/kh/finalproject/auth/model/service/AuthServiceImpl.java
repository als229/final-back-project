package com.kh.finalproject.auth.model.service;

import java.util.HashMap;
import java.util.Map;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kh.finalproject.auth.model.dao.AuthMapper;
import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.model.dto.LoginDTO;
import com.kh.finalproject.auth.model.dto.LoginResponseDTO;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.exception.exceptions.InvaildFindIdException;
import com.kh.finalproject.exception.exceptions.InvaildFindPwException;
import com.kh.finalproject.exception.exceptions.LoginFailedException;
import com.kh.finalproject.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthMapper authMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	

	@Override
	public LoginResponseDTO login(LoginDTO loginDTO) {
	    String userId = loginDTO.getUserId();
	    String password = loginDTO.getPassword();

	    Authentication authentication = null;
	    try {
	        authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(userId, password)  
	        );
	    } catch (AuthenticationException e) {
	        throw new LoginFailedException("아이디 또는 비밀번호가 틀립니다.");
	    }
		
		NwUserDetails nwUserDetails = (NwUserDetails) authentication.getPrincipal();
		
		String acceessToken = tokenService.generateAccessToken(userId);
		String refreshToken = tokenService.generateRefreshToken(nwUserDetails.getUserNo(),userId);
		
	
		return LoginResponseDTO.builder()
							   .userId(nwUserDetails.getUserId())
							   .realName(nwUserDetails.getRealName())
							   .nickName(nwUserDetails.getNickName())
							   .email(nwUserDetails.getEmail())
							   .accessToken(acceessToken)
							   .refreshToekn(refreshToken)
							   .build();
	}


	@Override
	public FindResponseDTO findId(FindDTO findIdDTO) {
		
		FindResponseDTO selectByfindId =  authMapper.selectByfindId(findIdDTO);
		if(selectByfindId == null) {
			throw new InvaildFindIdException("존재하지 않는 아이디입니다");
		}
		
		
		return selectByfindId;
	}

	@Override
	public FindResponseDTO findPw(FindDTO findDTO) {
		
		FindResponseDTO selsectByPw = authMapper.selectByfindPw(findDTO);
		if(selsectByPw == null) {
			throw new InvaildFindPwException("존재하지 않는 아이디 입니다");
		}
		
		
		return selsectByPw;
	}


	@Override
	public String sendEmailCode(String email) {
		
		
		return null;
	}

	
	
	
	
}
