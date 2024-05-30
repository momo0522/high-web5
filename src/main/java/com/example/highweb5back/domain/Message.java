package com.example.highweb5back.domain;

import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="counselor_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CounselorRoom counselorRoom;

    private String content;
    private String sender;
    private Type type;
    private LocalDateTime createdTime;

    public MessageResponseDto toDto(){
        return MessageResponseDto.builder()
                .roomId(counselorRoom.getId())
                .sender(sender)
                .id(id)
                .content(content)
                .type(type.name())
                .createdTime(createdTime)
                .build();
    }
}
