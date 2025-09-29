package com.spring.verification.springbackendverification.repository;

import com.spring.verification.springbackendverification.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}