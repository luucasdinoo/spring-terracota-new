package com.terracota.infrastructure.chatbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terracota.infrastructure.chatbot.models.ChatbotRequest;
import com.terracota.infrastructure.chatbot.models.ChatbotResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ChatbotService {

    @Value("${open-router.api.key}")
    private String API_KEY;

    private final ObjectMapper objectMapper;

    public ChatbotService(final ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public ChatbotResponse processMessage(final ChatbotRequest request) throws Exception {
        Map<String, Object> data = Map.of(
                "model", "deepseek/deepseek-chat:free",
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", "Você é um assistente de suporte especializado na plataforma de venda de produtos de artesanato 'Terracota'. Responda apenas dúvidas relacionadas ao funcionamento da plataforma, cadastro de produtos, compras, vendas e suporte aos usuários. Se a pergunta não for sobre isso, responda educadamente que não pode ajudar."
                        ),
                        Map.of(
                                "role", "user",
                                "content", request.prompt()
                        )
                ),
                "max_tokens", 4000,
                "temperature", 1.0
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://openrouter.ai/api/v1/chat/completions"))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(data)))
                .build();


        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: " + response.statusCode() + " - " + response.body());

        } else {
            Map<String, Object> responseBody = objectMapper.readValue(response.body(), Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");

            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

            return new ChatbotResponse((String) message.get("content"));
        }
    }

}
