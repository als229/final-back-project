package com.kh.finalproject.user.controller;



import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.user.model.dto.MypageDTO;
import com.kh.finalproject.user.model.dto.UpdatePasswordDTO;
import com.kh.finalproject.user.model.dto.UserDTO;
import com.kh.finalproject.user.model.service.UserService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
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
	
	@DeleteMapping("/users/delete")
	public ResponseEntity<ResponseData> delete(@AuthenticationPrincipal NwUserDetails user, @RequestBody Map<String, String> refreshToken){
		
		String delRefrshToken = refreshToken.get("refreshToken");
		
		userService.delete(user.getUserNo(),delRefrshToken);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("회원탈퇴 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
		
	}	
	
	@PutMapping("/users/update-pw")
	public ResponseEntity<ResponseData> updatePw(@AuthenticationPrincipal NwUserDetails user, @RequestBody UpdatePasswordDTO updatePasswordDTO){
		
		updatePasswordDTO.setUserNo(user.getUserNo());
		
		userService.updatePw(updatePasswordDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("비밀번호 변경 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
		
	}
	
	/// S3 버킷만들어야함
	@GetMapping("/users/select-profile")
	public ResponseEntity<ResponseData> selectProfile(@AuthenticationPrincipal NwUserDetails user){
		
		
		
		UserDTO response = userService.selectProfile(user.getUserNo());
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.items(response)
												.message("프로필 조회 성공")
												.build();
		return ResponseEntity.ok(responseData);
	}	
	
	
	
	@PutMapping("/users/update-profile")
	public ResponseEntity<ResponseData> updateProfile(@AuthenticationPrincipal NwUserDetails user,
													  @RequestParam("file") MultipartFile file){
		
		
		userService.updateProfile(user.getUserNo(), file);
		
		
		return null;
	}
	
	@PutMapping("/users/update-nickname")
	public ResponseEntity<ResponseData> updateNickname(@AuthenticationPrincipal NwUserDetails user,@RequestBody UserDTO userDTO){
		
		userDTO.setUserNo(user.getUserNo());
		
		userService.updateNickname(userDTO);
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.message("닉네임 변경 성공")
												.build();
		return ResponseEntity.ok(responseData);
	}
	
	
	
	
	@GetMapping("/users/comments")
	public ResponseEntity<ResponseData> selectComments(@AuthenticationPrincipal NwUserDetails user){
		
		List<MypageDTO> response = userService.selectComments(user.getUserNo());
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.items(response)
												.message("댓글 조회 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
	}
	
	
	@GetMapping("/users/favorite")
	public ResponseEntity<ResponseData> selectFavorite(@AuthenticationPrincipal NwUserDetails user){
		
		List<MypageDTO> response = userService.selectFavorite(user.getUserNo());
		
		ResponseData responseData = ResponseData.builder()
												.code("A100")
												.items(response)
												.message("즐겨찾기 조회 성공")
												.build();
		
		return ResponseEntity.ok(responseData);
	}
	
	
	

}
