package com.kh.finalproject.chat.socketconfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.chat.model.dao.ChatMapper;
import com.kh.finalproject.chat.model.dto.MessageDTO;
import com.kh.finalproject.chat.model.vo.Message;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler  {
	
	private final Map<String, Map<Long, WebSocketSession>> rooms = new ConcurrentHashMap<>();
	private final ChatMapper chatMapper;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("전화 받았습니다.");
		System.out.println(session);
		
		String roomId = getRoomId(session);
	    NwUserDetails loginUser = (NwUserDetails) session.getAttributes().get("principal");
	    long userNo = loginUser.getUserNo();

	    // 방별 내부 맵 가져오기 (없으면 생성)
	    Map<Long, WebSocketSession> userSessions = rooms.computeIfAbsent(roomId, r -> new ConcurrentHashMap<>());

        WebSocketSession oldSession = userSessions.put(userNo, session);
	    
        if (oldSession != null && oldSession.isOpen()) {
            oldSession.close(
              CloseStatus.POLICY_VIOLATION
                         .withReason("새 창에서 재접속되어 이전 연결을 종료합니다.")
            );
        }
        
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String roomId = getRoomId(session);
	    if (roomId == null || roomId.isEmpty()) return;

	    Map<Long, WebSocketSession> userSessions = rooms.get(roomId);
	    if (userSessions == null) return;

	    // 1) 메시지 보낸 사람(현재 세션)의 사용자 정보
	    NwUserDetails loginUser = (NwUserDetails) session.getAttributes().get("principal");
	    long realUserNo = loginUser.getUserNo();
	    String realNickname = loginUser.getUsername();

	    // 2) 클라이언트가 보낸 payload 에서 content 만 꺼내기
	    MessageDTO incoming = objectMapper.readValue(message.getPayload(), MessageDTO.class);
	    String content = incoming.getMessageContent();

	    // 3) DB 저장 (userNo는 반드시 realUserNo 사용)
	    Message requestData = Message.builder()
	        .roomNo(Long.parseLong(roomId))
	        .userNo(realUserNo)
	        .messageContent(content)
	        .build();
	    chatMapper.insertMessage(requestData);

	    // 4) 브로드캐스트: 각 세션마다 mine 계산해서 전송
	    for (WebSocketSession userSess : userSessions.values()) {
	        if (!userSess.isOpen()) continue;

	        NwUserDetails receiver = (NwUserDetails) userSess.getAttributes().get("principal");
	        long receiverUserNo = receiver.getUserNo();
	        boolean isMineForThisClient = (receiverUserNo == realUserNo);  // 변경된 부분

	        // 5) outbound DTO 구성
	        MessageDTO outbound = new MessageDTO();
	        outbound.setRoomNo(requestData.getRoomNo());
	        outbound.setUserNo(realUserNo);
	        outbound.setNickname(realNickname);
	        outbound.setMessageContent(content);
	        outbound.setCreateTime(incoming.getCreateTime());
	        outbound.setMine(isMineForThisClient);

	        // 6) 해당 세션에만 보내기
	        userSess.sendMessage(new TextMessage(objectMapper.writeValueAsString(outbound)));  // 변경된 부분
	    }
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String roomId = getRoomId(session);
	    if (roomId == null || roomId.isEmpty()) return;
	    
	    Map<Long, WebSocketSession> userSessions = rooms.get(roomId);
	    if (userSessions == null) return;

        long userNo = ((NwUserDetails) session.getAttributes().get("principal")).getUserNo();
        WebSocketSession mapped = userSessions.get(userNo);
		
        if (mapped != null && mapped.getId().equals(session.getId())) {
            userSessions.remove(userNo);
            if (userSessions.isEmpty()) {
                rooms.remove(roomId);
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
