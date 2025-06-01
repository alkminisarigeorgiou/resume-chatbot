package com.resume.chatbot.controller;

import com.resume.chatbot.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class ChatController {

    private final ChatService cs;

    public ChatController(ChatService chatService) {
        this.cs = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String,String> body) {

        String userInput =  body.get("message");
        System.out.println("Received input: '" + userInput + "'");
        return cs.getResponse(userInput);
    }
}