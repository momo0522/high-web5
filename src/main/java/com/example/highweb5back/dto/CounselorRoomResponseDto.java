package com.example.highweb5back.dto;

import com.example.highweb5back.domain.CounselorRoom;
import lombok.Data;

@Data
public class CounselorRoomResponseDto {
    private Long id;
    private Long counselorId;
    private Long clientId;

    public CounselorRoomResponseDto(CounselorRoom counselorRoom){
        this.id = counselorRoom.getId();
        this.counselorId = counselorRoom.getCounselor().getId();
        this.clientId = counselorRoom.getClient().getId();
    }
}
