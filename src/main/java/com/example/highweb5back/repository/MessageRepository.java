package com.example.highweb5back.repository;

import com.example.highweb5back.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
