package com.example.highweb5back.config;

import com.example.highweb5back.domain.Message;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.time.LocalDateTime;

@Component
public class StompUnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent> {

    private final SimpMessagingTemplate messagingTemplate;

    public StompUnsubscribeEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        if (StompCommand.UNSUBSCRIBE.equals(headerAccessor.getCommand())) {
            String sessionId = headerAccessor.getSessionId();
            String destination = headerAccessor.getDestination();

            // 여기서 destination은 "/sub/room/2"와 같은 형식

            // 구독 취소를 알릴 메시지 생성
            Message message = Message.builder()
                    .content("상담자가 상담방을 나갔습니다.")
                    .createdTime(LocalDateTime.now())
                    .build();

            // 해당 방의 주제에만 메시지 보내기
            messagingTemplate.convertAndSend(destination + "/unsubscribe-notification", message);
        }
    }
}
