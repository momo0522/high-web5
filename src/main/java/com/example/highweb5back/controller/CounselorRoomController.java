package com.example.highweb5back.controller;

import com.example.highweb5back.dto.CounselorRoomRequestDto;
import com.example.highweb5back.dto.CounselorRoomResponseDto;
import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.service.CounselorRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/counselor-room")
@RequiredArgsConstructor
public class CounselorRoomController {
    private final CounselorRoomService counselorRoomService;

    @PutMapping("")
    public ResponseEntity<Long> requestEnterRoom(){
        return ResponseEntity.ok(counselorRoomService.enterRoom());
    }

    @PostMapping("")
    public ResponseEntity<Long> makeRoom(
            @RequestBody CounselorRoomRequestDto dto
    ){
        return ResponseEntity.ok(counselorRoomService.makeRoom(dto));
    }

    @GetMapping("")
    public ResponseEntity<List<CounselorRoomResponseDto>> getRoomList(){
        return ResponseEntity.ok(counselorRoomService.getRoomList());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<MessageResponseDto>> getRoomMessage(
            @PathVariable("roomId") Long roomId
    ){
        return ResponseEntity.ok(counselorRoomService.getRoomMessage(roomId));
    }


}
