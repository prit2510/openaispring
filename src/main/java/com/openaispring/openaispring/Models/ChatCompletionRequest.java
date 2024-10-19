package com.openaispring.openaispring.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionRequest {
    private String model;
    private List<ChatMessage> messages;
    private Integer max_tokens;
    private Double temperature;

    public ChatCompletionRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new ChatMessage("user", prompt));
        this.max_tokens = 2000; // Optional: adjust this based on your needs
        this.temperature = 1.0; // Optional: adjust this based on your needs
    }
}
