package com.kh.finalproject.auth.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Getter
@ToString
@Builder
public class NwUserDetails  implements UserDetails {

	 
	private Long userNo;
	private String userId;
	private String password;
	private String email;
	private String nickName;
	private String realName;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	@Override
	public String getUsername() {
		return userId;
	}
}
