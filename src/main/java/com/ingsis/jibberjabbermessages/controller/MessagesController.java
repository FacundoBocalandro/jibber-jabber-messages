package com.ingsis.jibberjabbermessages.controller;
import com.ingsis.jibberjabbermessages.model.*;
import com.ingsis.jibberjabbermessages.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RestController
public class MessagesController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{to}/{id}")
    public void sendMessage(@DestinationVariable long to, @Payload final ChatMessageDto chatMessage, @DestinationVariable long id) {
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, chatMessage);
        messageService.newMessage(chatMessage, to, id);
    }

    @PostMapping("/new-chat")
    public void createChat(@RequestBody NewChatDto newChatDto){
        messageService.createChat(newChatDto);
    }

    @GetMapping("/messages/{id}")
    public Set<ChatMessage> getMessages(@PathVariable long id){
        return messageService.getMessages(id);
    }

    @GetMapping("/messages/chats")
    public Set<Chat> getChats(){
        return messageService.getChats();
    }
}
