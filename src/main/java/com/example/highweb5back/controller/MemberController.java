package com.example.highweb5back.controller;

import com.example.highweb5back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PutMapping("/counselor")
    public ResponseEntity<String> loginCounselor(){
        return ResponseEntity.ok(memberService.loginCounselor());
    }
    @PostMapping("/client")
    public ResponseEntity<Long> makeClient(){
        return ResponseEntity.ok(memberService.makeClient());
    }
}
