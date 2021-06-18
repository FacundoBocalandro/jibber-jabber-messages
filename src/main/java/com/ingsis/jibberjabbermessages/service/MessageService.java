package com.ingsis.jibberjabbermessages.service;

import com.ingsis.jibberjabbermessages.dto.UserDto;
import com.ingsis.jibberjabbermessages.model.*;
import com.ingsis.jibberjabbermessages.repository.ChatRepository;
import com.ingsis.jibberjabbermessages.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @Value("${auth-server-url}")
    String authServerUrl;

    private Long getUserId(){
        return ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public void newMessage(ChatMessageDto chatMessageDto, long to, long id) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String authUrl = authServerUrl + "user-info-by-id?id=";
//        HttpEntity<String> httpEntity = new HttpEntity<>(null);
//        ResponseEntity<UserDto> userInfoResponse = restTemplate.exchange(authUrl + to, HttpMethod.GET, httpEntity, UserDto.class);
//        if (userInfoResponse.getStatusCodeValue() != 200) throw new RequestRejectedException("Unable to fetch user information");
//        UserDto toUser = userInfoResponse.getBody();
//        UserDto sender = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Chat chat = chatRepository.findById(id);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDto.getContent());
//        chatMessage.setTime(chatMessageDto.getTime());
        chatMessage.setSenderId(id);
        chatMessage.setToId(to);
        chatMessage.setChat(chat);
        chat.addMessage(chatMessage);
        messageRepository.save(chatMessage);
        chatRepository.save(chat);
    }

    public Set<ChatMessage> getMessages(long id) {
        return chatRepository.findById(id).getMessages();
    }


    public Set<Chat> getChats() {
        Long userId = getUserId();
        return chatRepository.findAllByUser1OrUser2(userId, userId);
    }

    public void createChat(NewChatDto newChatDto) {
        Chat chat = new Chat();
        chat.setUser1(newChatDto.getUser1());
        chat.setUser2(newChatDto.getUser2());
        chatRepository.save(chat);
    }
}