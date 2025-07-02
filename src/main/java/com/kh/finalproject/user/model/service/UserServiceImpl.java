package com.kh.finalproject.user.model.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.finalproject.exception.exceptions.DuplicateUserEmailException;
import com.kh.finalproject.exception.exceptions.DuplicateUserIdException;
import com.kh.finalproject.exception.exceptions.DuplicateUserNickameException;
import com.kh.finalproject.user.model.dao.UserMapper;
import com.kh.finalproject.user.model.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void signUp(UserDTO userDTO) {
	
		String userId = userDTO.getUserId();
		String nickName = userDTO.getNickName();
		String email = userDTO.getEmail();
		String password = userDTO.getPassword();
		
		if(userMapper.existsByUserId(userId) > 0 ) {
			throw new DuplicateUserIdException("이미 사용중인 아이디 입니다");
		}
		if(userMapper.existsByUserNickName(nickName) > 0 ) {
			throw new DuplicateUserNickameException("이미 사용중인 닉네임 입니다");
		}
		if(userMapper.existsByUserEmail(email) > 0 ) {
			throw new DuplicateUserEmailException("이미 사용중인 이메일 입니다");
		}
		
		// 이메일인증해야함
		
		String encodedPassowrd = passwordEncoder.encode(password);
		userDTO.setPassword(encodedPassowrd);
		
		userMapper.signUp(userDTO); 
		
		
	}

	
	
	
	
}