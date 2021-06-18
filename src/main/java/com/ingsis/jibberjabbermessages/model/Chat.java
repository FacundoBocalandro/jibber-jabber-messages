package com.ingsis.jibberjabbermessages.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long user1;

    private Long user2;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    private Set<ChatMessage> messages;

    public Chat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUser1() {
        return user1;
    }

    public void setUser1(Long user1) {
        this.user1 = user1;
    }

    public Long getUser2() {
        return user2;
    }

    public void setUser2(Long user2) {
        this.user2 = user2;
    }

    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<ChatMessage> messages) {
        this.messages = messages;
    }


    public void addMessage(ChatMessage chatMessage) {
        messages.add(chatMessage);
    }
}