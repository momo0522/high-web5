package com.example.highweb5back.repository;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounselorRoomRepository extends JpaRepository<CounselorRoom, Long> {
    List<CounselorRoom> findByCounselorId(Long counselorId);
}
