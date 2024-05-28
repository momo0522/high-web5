package com.example.highweb5back.repository;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByType(Type type);
    List<Member> findByTypeAndLastLoginBetween(Type type, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Member> findByTypeAndLastLoginAfter(Type type, LocalDateTime startDateTime);
}
