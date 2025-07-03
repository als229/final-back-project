package com.kh.finalproject.content.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Festival {
	
	private Long contentId;
	private String program;
	private String eventExp;
	private String sponsor;
	private String usetimeFestival;
	private Date eventStartDate;
	private Date eventEndDate;

}
