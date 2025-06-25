package com.kh.finalproject.user.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.user.model.dto.UserDTO;
import com.kh.finalproject.user.model.service.UserService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseData> signUp(@RequestBody @Valid UserDTO userDTO ) {
		
		userService.signUp(userDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("회원가입 완료")
												.build();
												
		return ResponseEntity.ok(responseData);
	}
	
	
	
	
	
}
