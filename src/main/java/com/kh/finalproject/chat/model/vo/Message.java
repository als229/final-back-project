package com.kh.finalproject.chat.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Message {

	private Long roomNo;
	private Long userNo;
	private String messageContent;	
	
}
