package com.restaurantreservation.aruaru.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	
	Map<String, WebSocketSession> sessionMap = new HashMap<>(); 
	
	@Override 
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지 발송
		
		String msg = message.getPayload();
		File file = new File("E:\\workspace\\Test_SpringBoot\\file.text");
		byte[] binary = msg.getBytes();
		FileWriter stream;
		try {
			stream = new FileWriter(file,true);
			stream.append(new String(binary));
			stream.append("\n");
			stream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try{
				wss.sendMessage(new TextMessage(msg));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		
		
		
	}
	
	@Override 
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
}
