package com.ingsis.jibberjabbermessages.model;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private String content;

    private long sender;

//    private LocalDateTime time;

    public ChatMessageDto() {
    }

    public ChatMessageDto(String content, long sender) {
        this.content = content;
        this.sender = sender;
//        this.time = time;
    }
    public String getContent() {
        return content;
    }

    public long getSender() {
        return sender;
    }

//    public LocalDateTime getTime() {
//        return time;
//    }
}