package com.example.highweb5back.repository;

import com.example.highweb5back.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
