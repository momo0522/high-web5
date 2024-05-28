package com.example.highweb5back.dto;

import com.example.highweb5back.domain.Message;
import com.example.highweb5back.enums.Type;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDto {
    private Long roomId;
    private Long senderId;
    private String content;

    public Message toDomain(Type type){
        return Message.builder()
                .content(content)
                .type(type)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
