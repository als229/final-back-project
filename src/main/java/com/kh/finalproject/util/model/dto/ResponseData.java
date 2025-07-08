package com.kh.finalproject.util.model.dto;


import com.kh.finalproject.common.PageInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData {

	
	private Object items;
	private String message;
	private String code;
	private PageInfo pageInfo;
	
}
