package com.example.highweb5back.controller;

import com.example.highweb5back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/counselor")
    public ResponseEntity<String> makeCounselor(){
        return ResponseEntity.ok(memberService.makeCounselor());
    }
    @PostMapping("/client")
    public ResponseEntity<String> makeClient(){
        return ResponseEntity.ok(memberService.makeClient());
    }
}
