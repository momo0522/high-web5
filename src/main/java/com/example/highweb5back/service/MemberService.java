package com.example.highweb5back.service;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public String loginCounselor(){
        Member counselor;
        Optional<Member> counselorOp = memberRepository.findByType(Type.COUNSELOR);
        counselor = counselorOp.orElseGet(this::createCounselor);
        counselor.setLastLogin(LocalDateTime.now());
        memberRepository.save(counselor);
        return "상담사 로그인 완료";
    }

    public String makeClient(){
        memberRepository.save(createClient());
        return "상담사 생성 완료";
    }

    Member createCounselor(){
        Member counselor = new Member();
        counselor.setType(Type.COUNSELOR);
        counselor.setName("counselor");
        return counselor;
    }

    Member createClient(){
        Member client = new Member();
        client.setType(Type.CLIENT);
        client.setName("client" + (int)(Math.random()*10000));
        return client;
    }
}
