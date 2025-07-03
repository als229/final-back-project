package com.kh.finalproject.chat.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.chat.model.dto.ChatRoomDTO;
import com.kh.finalproject.chat.model.dto.MessageDTO;
import com.kh.finalproject.chat.model.vo.Message;

@Mapper
public interface ChatMapper {
	
    ChatRoomDTO findRoomByContentId(Long contentId);
    
    void insertRoom(Long contentId);

    void insertMessage(Message message);  
    
    List<MessageDTO> selectMessagesByRoomNo(Long roomNo);

}
