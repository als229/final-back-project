package com.kh.finalproject.chat.socketconfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kh.finalproject.chat.handshakeInterceptor.AuthHandshakeInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{
	
	private final WebSocketHandler chatHandler;
	private final AuthHandshakeInterceptor   authInterceptor;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatHandler, "ws/chat/{roomId}")
				.addInterceptors(authInterceptor) 
			 	.setAllowedOrigins("http://localhost:5173");
	}

}
