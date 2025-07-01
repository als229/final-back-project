package com.kh.finalproject.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.chat.model.dto.ChatRoomDTO;
import com.kh.finalproject.chat.model.dto.MessageDTO;
import com.kh.finalproject.chat.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {
	
    private final ChatService chatService;
    
    @GetMapping("/content/{contentNo}")
    public ResponseEntity<ChatRoomDTO> getOrCreateRoom(@PathVariable("contentNo") Long contentNo) {
        ChatRoomDTO room = chatService.getOrCreateRoomByContentId(contentNo);
        return ResponseEntity.ok(room);
    }
    
    @GetMapping("/{roomNo}/messages")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable("roomNo") Long roomNo) {
    	List<MessageDTO> messages = chatService.getMessagesByRoomNo(roomNo);
        return ResponseEntity.ok(messages);
    }
    

}
