package com.kh.finalproject.user.model.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.user.model.dto.MypageDTO;
import com.kh.finalproject.user.model.dto.UpdatePasswordDTO;
import com.kh.finalproject.user.model.dto.UserDTO;

public interface UserService {

	void signUp(UserDTO userDTO);
	
	int checkUserId(String userId);
	
	void delete(Long userNo, String refreshToken, String passowrd);

	void updatePw(UpdatePasswordDTO updatePasswordDTO);
	
	UserDTO selectProfile(Long userNo);
	
	UserDTO updateProfile(Long userNo, MultipartFile file);
	
	void updateNickname(UserDTO userDTO);
	
	
	void deleteProfile(Long userNo);
	
	
	List<MypageDTO> selectComments(Long userNo);
	
	List<MypageDTO> selectFavorite(Long userNo);
	
}
