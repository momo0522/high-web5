package com.example.highweb5back.repository;

import com.example.highweb5back.domain.CounselorRoom;
import com.example.highweb5back.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByCounselorRoom(CounselorRoom counselorRoom);
}
