package com.example.highweb5back.repository;

import com.example.highweb5back.domain.CounselorRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CounselorRoomRepository extends JpaRepository<CounselorRoom, Long> {
    List<CounselorRoom> findByCounselorId(Long counselorId);
    Optional<CounselorRoom> findByClientId(Long clientId);
    Optional<CounselorRoom> findTopByOrderByIdDesc();
}
