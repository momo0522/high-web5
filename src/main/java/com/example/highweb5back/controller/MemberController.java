package com.example.highweb5back.controller;

import com.example.highweb5back.dto.CounselorStatResponseDto;
import com.example.highweb5back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/counselor")
    public ResponseEntity<CounselorStatResponseDto> getCounselorStat(){
        return ResponseEntity.ok(memberService.getCounselorStat());
    }

    @PutMapping("/counselor")
    public ResponseEntity<String> loginCounselor(){
        return ResponseEntity.ok(memberService.loginCounselor());
    }
    @PostMapping("/client")
    public ResponseEntity<Long> makeClient(){
        return ResponseEntity.ok(memberService.makeClient());
    }
}
