package com.example.highweb5back.service;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.CounselorRoomRequestDto;
import com.example.highweb5back.dto.CounselorRoomResponseDto;
import com.example.highweb5back.dto.MessageResponseDto;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.CounselorRoomRepository;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounselorRoomService {
    private final MessageRepository messageRepository;
    private final CounselorRoomRepository counselorRoomRepository;
    private final MemberRepository memberRepository;

    public Long enterRoom(){
        Optional<CounselorRoom> roomOp = counselorRoomRepository.findTopByOrderByIdDesc();
        return roomOp.isEmpty() ? -1 : roomOp.get().getId();
    }

    public Long makeRoom(CounselorRoomRequestDto dto){
        Member client = memberRepository.findById(dto.getClientId()).orElseThrow();
        Member counselor = memberRepository.findByType(Type.COUNSELOR).get(0);
        Optional<CounselorRoom> room = counselorRoomRepository.findByClientId(dto.getClientId());

        if(room.isEmpty()){
            CounselorRoom newRoom = new CounselorRoom(counselor, client);
            counselorRoomRepository.save(newRoom);
            return newRoom.getId();
        }
        return room.get().getId();
    }

    public List<CounselorRoomResponseDto> getRoomList(){
        Member counselor = memberRepository.findByType(Type.COUNSELOR).get(0);
        List<CounselorRoom> counselorRoomList = counselorRoomRepository.findByCounselorId(counselor.getId());

        ArrayList<CounselorRoomResponseDto> resultList = new ArrayList<>();
        for(CounselorRoom counselorRoom: counselorRoomList){
            resultList.add(new CounselorRoomResponseDto(counselorRoom));
        }
        return resultList;
    }

    public ArrayList<MessageResponseDto> getRoomMessage(Long roomId){
        CounselorRoom room = counselorRoomRepository.findById(roomId).orElseThrow();
        ArrayList<MessageResponseDto> list = new ArrayList<>();
        for(Message message: messageRepository.findByCounselorRoom(room)){
            list.add(message.toDto());
        }

        return list;
    }

}
