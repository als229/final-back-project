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
    
    @GetMapping("/rooms/content/{contentId}")
    public ResponseEntity<ChatRoomDTO> getOrCreateRoom(@PathVariable Long contentId) {
        ChatRoomDTO room = chatService.getOrCreateRoomByContentId(contentId);
        return ResponseEntity.ok(room);
    }
    
    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long roomId) {
        List<MessageDTO> messages = chatService.getMessagesByRoomNo(roomId);
        return ResponseEntity.ok(messages);
    }

}
