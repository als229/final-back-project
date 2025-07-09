package com.kh.finalproject.mainContent.model.dto;

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
public class ContentSearchDTO {

	private int page;
	private int category;
	private int sidoNo;
	private String searchKeyword;
	private String status;
	
}
