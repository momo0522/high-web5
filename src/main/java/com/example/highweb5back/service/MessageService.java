package com.example.highweb5back.service;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public Message saveAndReturnMessage(MessageRequestDto dto){
        Member member = memberRepository
                .findById(dto.getSenderId())
                .orElseThrow();
        Message message = dto.toDomain(member.getType());
        messageRepository.save(message);
        return message;
    }
}
