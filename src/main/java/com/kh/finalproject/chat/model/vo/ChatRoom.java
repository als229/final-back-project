package com.kh.finalproject.chat.model.vo;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class ChatRoom {

	private String id;
	private String name;
	
}
