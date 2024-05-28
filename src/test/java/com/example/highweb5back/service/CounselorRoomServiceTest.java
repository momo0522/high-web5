package com.example.highweb5back.service;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.CounselorRoomRepository;
import com.example.highweb5back.repository.MemberRepository;
import com.example.highweb5back.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CounselorRoomServiceTest {
    @InjectMocks
    CounselorRoomService counselorRoomService;

    @Mock
    CounselorRoomRepository counselorRoomRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MessageRepository messageRepository;

    private Member client;
    private Member counselor;
    private CounselorRoom counselorRoom;

    private MessageRequestDto messageDto;

    private ArrayList<Message> list;
    private Message message;
    @BeforeEach
    void setUp(){
        client = createClient();
        counselor = createCounselor();
        counselorRoom = new CounselorRoom(counselor, client);
        messageDto = createMessageDto(counselorRoom.getId(), client.getId());
        message = messageDto.toDomain(client.getType());
        list = new ArrayList<>();
        list.add(message);

        when(messageRepository.findByCounselorRoom(counselorRoom)).thenReturn(list);
        when(counselorRoomRepository.findById(counselorRoom.getId())).thenReturn(Optional.ofNullable(counselorRoom));

        when(memberRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));
        when(memberRepository.findById(counselor.getId())).thenReturn(Optional.ofNullable(counselor));
    }

    @Test
    @DisplayName("메세지 리스트 받기 테스트")
    void getRoomMessage(){


        List<Message> result = counselorRoomService.getRoomMessage(counselorRoom.getId());
        assertThat(result.get(0).getContent()).isEqualTo(message.getContent());
    }

    Member createClient(){
        Member client = new Member();
        client.setId(1L);
        client.setType(Type.CLIENT);
        client.setName("client");
        return client;
    }

    Member createCounselor(){
        Member counselor = new Member();
        counselor.setId(2L);
        counselor.setType(Type.COUNSELOR);
        counselor.setName("counselor");
        return counselor;
    }

    MessageRequestDto createMessageDto(Long roomId, Long senderId){
        MessageRequestDto dto = new MessageRequestDto();
        dto.setRoomId(roomId);
        dto.setContent("test content");
        dto.setSenderId(senderId);
        return dto;
    }
}
