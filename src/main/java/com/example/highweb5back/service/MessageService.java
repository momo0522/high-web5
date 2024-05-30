package com.example.highweb5back.service;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.repository.CounselorRoomRepository;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;
    private final CounselorRoomRepository counselorRoomRepository;
    public MessageResponseDto saveAndReturnMessage(MessageRequestDto dto){
        Member member = memberRepository
                .findById(dto.getSenderId())
                .orElseThrow();
        CounselorRoom counselorRoom = counselorRoomRepository.findById(dto.getRoomId()).orElseThrow();
        Message message = dto.toDomain(counselorRoom, member);
        messageRepository.save(message);

        return message.toDto();
    }
}
