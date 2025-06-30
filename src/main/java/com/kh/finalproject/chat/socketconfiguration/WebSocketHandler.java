package com.kh.finalproject.chat.socketconfiguration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.finalproject.chat.model.dao.ChatMapper;
import com.kh.finalproject.chat.model.vo.Message;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler  {
	
	private final Set<WebSocketSession> users = new HashSet();
	private final Map<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap();
	private final ChatMapper chatMapper;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("전화 받았습니다.");
		System.out.println(session);

		String roomId = getRoomId(session);

		if (!"".equals(roomId)) {
			rooms.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(session);
		}
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("메시지 송신자 : " + session);
		System.out.println("수신된 메시지 : " + message);
		String roomId = getRoomId(session);
		if (roomId == null) return;
		System.out.println(message.getPayload());

		Message chatMessage = objectMapper.readValue(message.getPayload(), Message.class);
		
		Message requestData = null;
		
		// 동권이 코드 받고 수정
//		CustomUserDetails user = authService.getUserDetails();
//		Long userNo = user.getMemberNo();
		
		requestData = Message.builder()
				.roomNo(Long.parseLong(roomId))
				.userNo(userNo)
				.messageContent(chatMessage.getMessageContent())
				.build();

		// 메시지 저장
		chatMapper.insertMessage(requestData);

		// 나와 같은방인 친구들에게만 브로드캐스트

		// Set<WebSocketSession> currentRoom = rooms.get(roomId);
		TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chatMessage));

		for (WebSocketSession user : rooms.getOrDefault(roomId, Collections.emptySet())) {
			if (user.isOpen()) {
				user.sendMessage(textMessage);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("전화 끊었습니다.");
		String roomId = getRoomId(session);
		if (!"".equals(roomId)) {
			Set<WebSocketSession> users = rooms.get(roomId);
			if (users != null) {
				users.remove(session);
				if (users.isEmpty()) {
					rooms.remove(roomId);
				}
			}
		}

	}

	private String getRoomId(WebSocketSession session) {
		String path = session.getUri().getPath();
		String[] paths = path.split("/");
		String roomId = "";
		if (paths.length >= 4) {
			return roomId = paths[3];
		}
		return "";
	}


}
