package com.kh.finalproject.user.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.finalproject.user.model.dto.MypageDTO;
import com.kh.finalproject.user.model.dto.UpdatePasswordDTO;
import com.kh.finalproject.user.model.dto.UserDTO;

@Mapper
public interface UserMapper {

	
	void signUp(UserDTO userDTO);
	
	int existsByUserId(String userId);
	
	int existsByUserNickName(String nickName);
	
	int existsByUserEmail(String Email);
	
	
	
	int deleteUser(Long userNo);
	
	String findPasswordByUserNo(Long userNo);
	
	void updateNewPw(UpdatePasswordDTO updatePasswordDTO);
	
	UserDTO selectProfile(Long userNo);
	
	int updateNickname(UserDTO userDTO);
	
	int selectNickName(String nickName);
	
	
	void updateProfile(@Param("userNo") Long userNo, @Param("fileUrl") String fileUrl);
	
	int deleteProfile(Long userNo);
	
	List<MypageDTO> selectComments(Long userNo);
	
	List<MypageDTO> selectFavorite(Long userNo);
	
}
