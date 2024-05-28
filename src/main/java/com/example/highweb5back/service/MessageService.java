package com.example.highweb5back.service;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public MessageResponseDto saveAndReturnMessage(MessageRequestDto dto){
        Message message = dto.toDomain();
        messageRepository.save(message);

        Member member = memberRepository
                .findById(dto.getSenderId())
                .orElseThrow();
        return new MessageResponseDto(message, member.getType());
    }
}
