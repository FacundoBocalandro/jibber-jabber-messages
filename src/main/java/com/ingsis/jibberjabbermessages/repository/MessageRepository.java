package com.ingsis.jibberjabbermessages.repository;

import com.ingsis.jibberjabbermessages.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
}