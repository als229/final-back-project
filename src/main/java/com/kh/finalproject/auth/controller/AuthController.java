package com.kh.finalproject.auth.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.auth.model.dto.EmailDTO;
import com.kh.finalproject.auth.model.dto.FindDTO;
import com.kh.finalproject.auth.model.dto.FindResponseDTO;
import com.kh.finalproject.auth.model.dto.LoginDTO;
import com.kh.finalproject.auth.model.dto.LoginResponseDTO;
import com.kh.finalproject.auth.model.dto.LogoutDTO;
import com.kh.finalproject.auth.model.service.AuthService;
import com.kh.finalproject.token.model.service.TokenService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api")
public class AuthController {

	private final AuthService authService;
	private final TokenService tokenService;
	
	@PostMapping("/auth/tokens")
	public ResponseEntity<ResponseData> login(@RequestBody @Valid LoginDTO loginDTO) {
		
		LoginResponseDTO loginResponse = authService.login(loginDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.items(loginResponse)
												.message("로그인 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
		
	}
	
	@DeleteMapping("/auth/logout")
	public ResponseEntity<ResponseData> logout(@RequestBody LogoutDTO logoutDTO){
		
		
		tokenService.deleteToken(logoutDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("로그아웃 성공")
												.build();
		
		
		return ResponseEntity.ok(responseData);
		
	}
	
	@PostMapping("/auth/find-id")
	public ResponseEntity<ResponseData> findId(@RequestBody FindDTO findDTO){
		
		authService.findId(findDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("아이디 이메일 전송 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
	}
	
	@PostMapping("/auth/find-pw")
	public ResponseEntity<ResponseData> findPw(@RequestBody FindDTO findDTO){
		
		 authService.findPw(findDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("임시비밀번호 전송 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
	}
	
	@PostMapping("auth/email-send")
	public ResponseEntity<ResponseData> sendEmailCode(@RequestBody Map<String, String> email){
		
		String eabsbsd = email.get("email");
		
		authService.sendEmailCode(eabsbsd);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("이메일전송 성공")
												.build();
		return ResponseEntity.ok(responseData);
		
	}
	
	@PostMapping("auth/email-verify")
	public ResponseEntity<ResponseData> verifyCode(@RequestBody EmailDTO emailDTO){
		
		authService.verifyCode(emailDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("인증성공")
												.build();
		
		return ResponseEntity.ok(responseData);
	
	}
	
}
