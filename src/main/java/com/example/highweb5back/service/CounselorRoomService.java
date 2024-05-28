package com.example.highweb5back.service;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.CounselorRoomRequestDto;
import com.example.highweb5back.dto.CounselorRoomResponseDto;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.CounselorRoomRepository;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CounselorRoomService {
    private final MessageRepository messageRepository;
    private final CounselorRoomRepository counselorRoomRepository;
    private final MemberRepository memberRepository;

    public Long makeRoom(CounselorRoomRequestDto dto){
        Member client = memberRepository.findById(dto.getClientId()).orElseThrow();
        Member counselor = memberRepository.findByType(Type.COUNSELOR).orElseThrow();
        CounselorRoom room = new CounselorRoom(counselor, client);
        counselorRoomRepository.save(room);
        return room.getId();
    }

    public List<CounselorRoomResponseDto> getRoomList(){
        ArrayList<CounselorRoomResponseDto> resultList = new ArrayList<>();
        List<CounselorRoom> counselorRoomList = counselorRoomRepository.findByCounselorId(1L);
        for(CounselorRoom counselorRoom: counselorRoomList){
            resultList.add(new CounselorRoomResponseDto(counselorRoom));
        }
        return resultList;
    }

    public List<Message> getRoomMessage(Long roomId){
        CounselorRoom room = counselorRoomRepository.findById(roomId).orElseThrow();
        return messageRepository.findByCounselorRoom(room);
    }

}
