package com.kh.finalproject.user.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.exception.exceptions.DuplicateNicknameException;
import com.kh.finalproject.exception.exceptions.DuplicateUserEmailException;
import com.kh.finalproject.exception.exceptions.DuplicateUserIdException;
import com.kh.finalproject.exception.exceptions.DuplicateUserNickameException;
import com.kh.finalproject.exception.exceptions.InvaildPasswordException;
import com.kh.finalproject.token.model.dao.TokenMapper;
import com.kh.finalproject.user.model.dao.UserMapper;
import com.kh.finalproject.user.model.dto.MypageDTO;
import com.kh.finalproject.user.model.dto.UpdatePasswordDTO;
import com.kh.finalproject.user.model.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final TokenMapper tokenMapper;
	
	
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
		
		
		String encodedPassword = passwordEncoder.encode(password);
		userDTO.setPassword(encodedPassword);
		
		userMapper.signUp(userDTO); 
		
		
		
	}
	@Override
	public int checkUserId(String userId) {
		
		return userMapper.existsByUserId(userId);
		
	
	}
	
	

	@Override
	public void delete(Long userNo, String refreshToken, String password) {
		
			String realPassword = userMapper.findPasswordByUserNo(userNo);
		
		
		if(!passwordEncoder.matches(password,realPassword)){
			throw new InvaildPasswordException ("비밀번호가 일치하지 않습니다");
			}
		 userMapper.deleteUser(userNo);
		 tokenMapper.deleteUserToken(userNo);	
			
}


	@Override
	public void updatePw(UpdatePasswordDTO updatePasswordDTO) {
	
		String passwordCheck = userMapper.findPasswordByUserNo(updatePasswordDTO.getUserNo());
		if(!passwordEncoder.matches(updatePasswordDTO.getPassword(), passwordCheck))
			throw new InvaildPasswordException("비밀번호가 일치하지 않습니다");
		
		String newEncoderPw = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
		
		updatePasswordDTO.setNewPassword(newEncoderPw);
		userMapper.updateNewPw(updatePasswordDTO);
		
	}

	@Override
	public UserDTO selectProfile(Long userNo) {
		
		UserDTO profileInfo = userMapper.selectProfile(userNo);
		
		return profileInfo;
	}

	@Override
	public UserDTO updateProfile(Long userNo, MultipartFile file) {
		
		
		return null;
	}

	
	@Override
	public void updateNickname(UserDTO userDTO) {
		
		String nickName = userDTO.getNickName();
		
		 if (nickName == null || nickName.trim().isEmpty()) {
		        throw new IllegalArgumentException("닉네임이 비어있음");
		    }

		
		int count = userMapper.selectNickName(nickName);
		
		if(count > 0) {
			throw new DuplicateNicknameException("이미 존재하는 닉네임입니다");
		}
		
		userMapper.updateNickname(userDTO);
				   
		
	}
	
	
	@Override
	public List<MypageDTO> selectComments(Long userNo) {
	
		List<MypageDTO> comment = userMapper.selectComments(userNo);
		
		return comment;
	}

	@Override
	public List<MypageDTO> selectFavorite(Long userNo) {
		
		List<MypageDTO> favorite = userMapper.selectFavorite(userNo);
		
		return favorite;
	}




	

	

	
	
	
	
	
	
	
	
	
	
}
