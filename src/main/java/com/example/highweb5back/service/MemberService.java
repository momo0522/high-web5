package com.example.highweb5back.service;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.dto.CounselorStatResponseDto;
import com.example.highweb5back.enums.Type;
import com.example.highweb5back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public CounselorStatResponseDto getCounselorStat(){
        CounselorStatResponseDto dto = new CounselorStatResponseDto();
        List<Member> memberList = memberRepository.findByType(Type.CLIENT);
        Member counselor = memberRepository.findByType(Type.COUNSELOR).get(0);

        // 총 누적
        dto.setTotalClientNum((long) memberList.size());
        // 하루 누적
        dto.setTodayClientNum(getTodayClientNum());
        // 최종 접속 이후 누적
        dto.setAfterLoginClientNum(getAfterLoginClientNum(counselor.getLastLogin()));
        return dto;
    }

    private Long getTodayClientNum() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.from(today), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.from(today), LocalTime.MAX);

        List<Member> todayLastLoginMembers = memberRepository.findByTypeAndLastLoginBetween(Type.CLIENT, startOfDay, endOfDay);

        return (long) todayLastLoginMembers.size();
    }

    private Long getAfterLoginClientNum(LocalDateTime lastLogin) {
        List<Member> membersAfterSpecificTime = memberRepository.findByTypeAndLastLoginAfter(Type.CLIENT, lastLogin);
        return (long) membersAfterSpecificTime.size();
    }
    public String loginCounselor(){
        Member counselor;
        List<Member> counselorOp = memberRepository.findByType(Type.COUNSELOR);
        if(counselorOp.isEmpty()){
            counselor = createCounselor();
        }else{
            counselor = counselorOp.get(0);
        }
        counselor.setLastLogin(LocalDateTime.now());
        memberRepository.save(counselor);
        return "상담사 로그인 완료";
    }

    public Long makeClient(){
        Member client = createClient();
        memberRepository.save(client);
        return client.getId();
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
        client.setLastLogin(LocalDateTime.now());
        return client;
    }
}
