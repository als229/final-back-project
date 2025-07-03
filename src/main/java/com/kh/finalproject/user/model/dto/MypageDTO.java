package com.kh.finalproject.user.model.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MypageDTO {

	
	private String title;
	private String firstImage;
	private String category;
	
	
	private String content;
	private Date createTime;
	private String fileUrl;

}
