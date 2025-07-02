package com.kh.finalproject.security.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.token.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization =request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorization == null || !authorization.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
	
		String token = authorization.substring(7);
	
	
		try {
			 Claims claims = jwtUtil.parseJwt(token);
			
			 String userId = claims.getSubject();
			 
			 NwUserDetails user = (NwUserDetails)userDetailsService.loadUserByUsername(userId);
			 
			 UsernamePasswordAuthenticationToken authentication =
				        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			 
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			 
		 } catch (JwtException e) {
			 log.warn("유효하지 않은 토큰입니다: {} ", e.getMessage());
		 }
	
	
	}

	
	
	
	
}
