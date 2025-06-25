package com.kh.finalproject.util.model.dto;

import com.kh.finalproject.auth.model.dto.LoginResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData {

	
	private Object items;
	private String message;
	private String code;
	
}
