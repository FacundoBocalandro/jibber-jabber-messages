package com.ingsis.jibberjabbermessages.repository;

import com.ingsis.jibberjabbermessages.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findById(long id);
    Set<Chat> findAllByUser1OrUser2(Long user1, Long user2);
}
