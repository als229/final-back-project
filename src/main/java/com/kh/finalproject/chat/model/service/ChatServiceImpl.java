package com.kh.finalproject.chat.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.chat.model.dao.ChatMapper;
import com.kh.finalproject.chat.model.dto.ChatRoomDTO;
import com.kh.finalproject.chat.model.dto.MessageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
    private final ChatMapper chatMapper;
	
	@Override
    public ChatRoomDTO getOrCreateRoomByContentId(Long contentId) {
		ChatRoomDTO room = chatMapper.findRoomByContentId(contentId);
        if (room == null) {
            chatMapper.insertRoom(contentId);
            room = chatMapper.findRoomByContentId(contentId);
        }
        return room;
    }

    @Override
    public List<MessageDTO> getMessagesByRoomNo(Long roomNo) {
        return chatMapper.selectMessagesByRoomNo(roomNo);
    }

}
