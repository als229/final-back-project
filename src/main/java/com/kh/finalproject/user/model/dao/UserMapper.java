package com.kh.finalproject.user.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.user.model.dto.MypageDTO;
import com.kh.finalproject.user.model.dto.UpdatePasswordDTO;
import com.kh.finalproject.user.model.dto.UserDTO;

@Mapper
public interface UserMapper {

	
	void signUp(UserDTO userDTO);
	
	int existsByUserId(String userId);
	
	int existsByUserNickName(String nickName);
	
	int existsByUserEmail(String Email);
	
	
	
	int deleteUser(Map<String, Object> deleteInfo);
	
	String findPasswordByUserNo(Long userNo);
	
	void updateNewPw(UpdatePasswordDTO updatePasswordDTO);
	
	UserDTO selectProfile(Long userNo);
	
	int updateNickname(UserDTO userDTO);
	
	int selectNickName(String nickName);
	
	
	List<MypageDTO> selectComments(Long userNo);
	
	List<MypageDTO> selectFavorite(Long userNo);
	
}
