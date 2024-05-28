package com.example.highweb5back.repository;

import com.example.highweb5back.domain.Member;
import com.example.highweb5back.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByType(Type type);
}
