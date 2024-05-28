package com.example.highweb5back.dto;

import com.example.highweb5back.domain.Message;
import com.example.highweb5back.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private String content;
    private String type;

    public MessageResponseDto(Message message, Type type){
        this.id = message.getId();
        this.type = type.name();
        this.content = message.getContent();
    }
}
