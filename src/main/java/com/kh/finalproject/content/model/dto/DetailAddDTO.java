package com.kh.finalproject.content.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailAddDTO {
	
	private Long contentId;
	
	private Long detailNo;
	private Long dongNo;
	private String detailName;
	private String postAddress;

}
