package com.example.highweb5back.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponseDto {
    private Long id;
    private Long roomId;
    private String sender;
    private String content;
    private LocalDateTime createdTime;
    private String type;
}
