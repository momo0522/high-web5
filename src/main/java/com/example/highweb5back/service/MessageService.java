package com.example.highweb5back.service;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.CounselorRoomRepository;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;
    private final CounselorRoomRepository counselorRoomRepository;
    private final SimpMessagingTemplate template;

    private final ConcurrentHashMap<Long, Long> chatSessions = new ConcurrentHashMap<>();


    public void saveAndSendMessage(MessageRequestDto dto){
        Member member = memberRepository
                .findById(dto.getSenderId())
                .orElseThrow();
        CounselorRoom counselorRoom = counselorRoomRepository.findById(dto.getRoomId()).orElseThrow();
        Message message = dto.toDomain(counselorRoom, member);
        messageRepository.save(message);

        template.convertAndSend("/sub/room/" + dto.getRoomId(), message.toDto());
    }

    public void createNewChatSession(Long clientId) {
        Member counselor = memberRepository.findByType(Type.COUNSELOR).get(0);
        Long sessionId = counselor.getId();
        // 기존 세션 종료
        if (chatSessions.containsKey(sessionId)) {
            endChatSession(sessionId);
        }
        // 새로운 세션 생성
        chatSessions.put(sessionId, clientId);
    }

    public void endChatSession(Long sessionId) {
        // 세션 데이터 정리
        Long clientId = chatSessions.remove(sessionId);
        if (clientId != null) {
            // 클라이언트에게 세션 종료 알림
            template.convertAndSend("/sub/session-end", "Session ended");

        }
    }
}
