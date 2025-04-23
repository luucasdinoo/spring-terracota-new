package com.terracota.infrastructure.api.controller;

import com.terracota.infrastructure.chatbot.ChatbotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("chatbot")
public class ChatbotController {
    private final ChatbotService chatbotService;

    public ChatbotController(final ChatbotService chatbotService) {
        this.chatbotService = Objects.requireNonNull(chatbotService);
    }

    @PostMapping
    public String send(@RequestBody String prompt) throws Exception {
        return chatbotService.processMessage(prompt);
    }
}
