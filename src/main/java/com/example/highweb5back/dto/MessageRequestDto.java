package com.example.highweb5back.dto;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDto {
    private Long roomId;
    private Long senderId;
    private String content;

    public Message toDomain(CounselorRoom counselorRoom, Member member){
        return Message.builder()
                .counselorRoom(counselorRoom)
                .content(content)
                .type(member.getType())
                .sender(member.getName())
                .createdTime(LocalDateTime.now())
                .build();
    }
}
