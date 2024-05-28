package com.example.highweb5back.controller;

import com.example.highweb5back.domain.Message;
import com.example.highweb5back.dto.MessageRequestDto;
import com.example.highweb5back.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin
public class PubSubController {
    private final SimpMessagingTemplate template;
    private final MessageService messageService;

    @MessageMapping(value = "/message")
    public void sendMessage(MessageRequestDto dto) {
        Message message = messageService.saveAndReturnMessage(dto);
        template.convertAndSend("/sub/room/" + dto.getRoomId(), message);
    }
}
