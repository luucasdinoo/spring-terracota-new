package com.terracota.infrastructure.api.controller;

import com.terracota.infrastructure.chatbot.ChatbotService;
import com.terracota.infrastructure.chatbot.models.ChatbotRequest;
import com.terracota.infrastructure.chatbot.models.ChatbotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Chatbot interaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chatbot interaction successful"),
    })
    @PostMapping
    public ChatbotResponse send(@RequestBody ChatbotRequest prompt) throws Exception {
        return chatbotService.processMessage(prompt);
    }
}
