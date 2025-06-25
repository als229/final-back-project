package com.kh.finalproject.token.util;


import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kh.finalproject.user.model.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretkey;
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		byte[] keyArr = Base64.getDecoder().decode(secretkey);
		this.key = Keys.hmacShaKeyFor(keyArr);
				
	}
	
	public String getAccessToken(String userId) {
		
		
		return Jwts.builder()
				   .subject(userId)
				   .issuedAt(new Date())
				   .expiration(new Date(System.currentTimeMillis() + 3600000L  * 24))
				   .signWith(key)
				   .compact();
	}
	
	
	public String getRefreshToken(Long userNo, String userId) {
		return Jwts.builder()
				   .subject(userId)
				   .issuedAt(new Date())
				   .expiration(new Date(System.currentTimeMillis() + 3600000L * 24 * 30))
				   .signWith(key)
				   .compact();
	}
	
	public Claims parseJwt(String token) {
		
		return Jwts.parser()
				   .verifyWith(key)
				   .build()
				   .parseSignedClaims(token)
				   .getPayload();
	}
	
	
	
}
