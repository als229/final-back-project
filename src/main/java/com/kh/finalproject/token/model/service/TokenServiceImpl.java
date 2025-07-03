package com.kh.finalproject.token.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.auth.model.dto.LogoutDTO;
import com.kh.finalproject.exception.exceptions.InvalidTokenException;
import com.kh.finalproject.token.model.dao.TokenMapper;
import com.kh.finalproject.token.model.vo.RefreshTokenVO;
import com.kh.finalproject.token.util.JwtUtil;
import com.kh.finalproject.user.model.dto.UserDTO;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final JwtUtil jwtUtil;
	private final TokenMapper tokenMapper;
	
	@Override
	public String generateAccessToken(String userId) {
		
		String accessToken = jwtUtil.getAccessToken(userId);
		
		return accessToken;
	}

	@Override
	public String generateRefreshToken(Long userNo, String userId) {
		
		
		String refreshToken = jwtUtil.getRefreshToken(userNo,userId);
								
		
		return refreshToken;
	}

	
	@Override
	public String reissueToken(String RefreshToken) {
		
		
		 RefreshTokenVO token = RefreshTokenVO.builder().refreshToken(RefreshToken).build();
		 RefreshTokenVO existingToken = tokenMapper.selectTokenByToken(token);
		if(existingToken == null) {
			throw new InvalidTokenException("토큰이 존재하지 않습니다.");
		}
		
		Claims claims = jwtUtil.parseJwt(RefreshToken);
		String userId = claims.getSubject();
		Long userNo = existingToken.getUserNo();
		
		
		tokenMapper.deleteTokenByUserNo(existingToken.getUserNo());
		String newToken = jwtUtil.getRefreshToken(userNo,userId);
		
		RefreshTokenVO newRefreshTokenVO= RefreshTokenVO.builder()
														.userNo(userNo)
														.refreshToken(newToken)
														.expirationTime(System.currentTimeMillis() + 36000000L * 24 * 7)
														.build();
		 tokenMapper.insertRefreshToken(newRefreshTokenVO);
		 
		return newToken;
	}

	@Override
	public void deleteToken(LogoutDTO logoutDTO) {

		tokenMapper.deleteToken(logoutDTO);
		
		
	}

	
	
	
	
}
