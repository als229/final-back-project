package com.kh.finalproject.mainContent.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Festival {
	private Long contentId;
	private String program;
	private String eventExp;
	private String sponsor;
	private String useTimeFestival;
	private Date eventStartDate;
	private Date eventEndDate;
}
