package com.bs.spring.common.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ChattingServer extends TextWebSocketHandler {

    private Map<String,WebSocketSession> client=new HashMap<>();

    @Autowired
    private  ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //onOpen
        log.info("사용자가 접속했다. ");
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("전송된 데이터 : {}",message.getPayload());
        Message msg=objectMapper.readValue(message.getPayload(),Message.class);
        switch(msg.getType()){
            case "open": addClient(session,msg);break;
            case "send": sendChatMessage(msg);break;
        }
    }

    private void sendChatMessage(Message msg) {
        if(msg.getReceiver()==null || msg.getReceiver().equals("")){

            try {
                final String strMsg = objectMapper.writeValueAsString(msg);
                client.values().forEach(client -> {
                    try {
                        client.sendMessage(new TextMessage(strMsg));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                final String strMsg = objectMapper.writeValueAsString(msg);

                if(client.get(msg.getReceiver())!=null) client.get(msg.getReceiver()).sendMessage(new TextMessage(strMsg));
                if(client.get(msg.getSender())!=null)   client.get(msg.getSender()).sendMessage(new TextMessage(strMsg));
            }catch (Exception e){

            }
        }
    }

    private void addClient(WebSocketSession session,Message msg){
        client.put(msg.getSender(),session);
        sendMessage(Message.builder().type("open").msg(msg.getSender()+"님이 참여").build());
    }

    private void sendMessage(Message msg){
        try {
          final   String strMsg = objectMapper.writeValueAsString(msg);
            client.values().forEach(client->{
                try {
                    client.sendMessage(new TextMessage(strMsg));
                }catch(Exception e){
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //onClose
        super.afterConnectionClosed(session, status);
    }
}
