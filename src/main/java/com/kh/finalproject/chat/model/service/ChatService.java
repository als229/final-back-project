package com.kh.finalproject.chat.model.service;

import java.util.List;

import com.kh.finalproject.chat.model.dto.ChatRoomDTO;
import com.kh.finalproject.chat.model.dto.MessageDTO;

public interface ChatService {

	public ChatRoomDTO getOrCreateRoomByContentId(Long contentId);
	
	public List<MessageDTO> getMessagesByRoomNo(Long roomNo);
}
