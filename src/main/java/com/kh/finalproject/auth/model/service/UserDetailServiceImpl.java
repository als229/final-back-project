package com.kh.finalproject.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.finalproject.auth.model.dao.AuthMapper;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.user.model.dao.UserMapper;
import com.kh.finalproject.user.model.dto.UserDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private AuthMapper authMapper;
	
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
		System.out.println(userId);
	UserDTO userDTO = authMapper.loadUserByUserId(userId);
		
	System.out.println(userDTO);
		return NwUserDetails.builder()
							.userNo(userDTO.getUserNo())
							.userId(userDTO.getUserId())
							.password(userDTO.getPassword())
							.realName(userDTO.getRealName())
							.nickName(userDTO.getNickName())
							.email(userDTO.getEmail())
							.authorities(Collections.singletonList(new SimpleGrantedAuthority(userDTO.getRole())))
							.build();
							
					  
	}
		
		
		
	
		
	
	
	
	
}
