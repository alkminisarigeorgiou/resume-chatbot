package com.resume.chatbot.controller;

import com.resume.chatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class ChatController {

    private final ChatService cs;

    public ChatController(ChatService chatService) {
        this.cs = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String userInput) {
        return cs.getResponse(userInput);
    }
}