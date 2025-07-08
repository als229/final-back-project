package com.kh.finalproject.mainContent.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class FileUrl {

	private Long contentImageNo;
	private Long contentId;
	private String fileUrl;
}
