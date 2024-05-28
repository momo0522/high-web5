package com.example.highweb5back.dto;

import com.example.highweb5back.domain.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDto {
    private Long roomId;
    private Long senderId;
    private String content;

    public Message toDomain(){
        return Message.builder()
                .content(content)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
