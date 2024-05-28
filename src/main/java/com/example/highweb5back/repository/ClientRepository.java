package com.example.highweb5back.repository;

import com.example.highweb5back.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
